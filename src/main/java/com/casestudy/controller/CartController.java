package com.casestudy.controller;

import com.casestudy.model.Cart;
import com.casestudy.model.Category;
import com.casestudy.model.User;
import com.casestudy.service.cart.CartService;
import com.casestudy.service.category.CategoryService;
import com.casestudy.service.product.ProductService;
import com.casestudy.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductController productController;

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @ModelAttribute("username")
    public String getPrincipal() {
        String username = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }

    @ModelAttribute("role")
    public String getRole() {
        String name = this.getPrincipal();
        if (name.contains("anonymousUser") ) {
            return null;
        } else {
            return userService.findByName(name).getRole().getName();
        }
    }

    @ModelAttribute("categories")
    public Iterable<Category> categories() {
        return categoryService.findAll();
    }

    private User getUser() {
        String name = this.getPrincipal();
        if (name.contains("anonymousUser")) {
            return null;
        } else {
            return userService.findByName(name);
        }
    }

    @GetMapping()
    public ModelAndView showCart() {
        ModelAndView modelAndView = new ModelAndView("/eshopper/cart");
        Iterable<Cart> carts = cartService.findAllByOrderNumberAndUser(this.getUser().getOrderNumber(), this.getUser());
        modelAndView.addObject("carts", carts);
        return modelAndView;
    }

    @GetMapping("/addCart/{productId}")
    public ModelAndView addCart(@PathVariable("productId") Long productId, @RequestParam("s") Optional<String> keyword, @RequestParam("page") Optional<Integer> page) {
        ModelAndView modelAndView = productController.index(keyword, page);
        if (this.getUser() == null) {
            modelAndView.addObject("message","Vui long dang nhap de mua hang");
            return modelAndView;
        } else {
            Cart cart = cartService.findByProductAndUser(productService.findByProductId(productId), this.getUser());
            if(cart != null) {
                cart.setQuantity(cart.getQuantity() + 1);
                cartService.save(cart);
                Long countOrder = cartService.countByOrderNumberAndUser(this.getUser().getOrderNumber(), this.getUser());
                modelAndView.addObject("countOrder", countOrder);
                return modelAndView;
            }
            cart = new Cart();
            if(this.getUser().getOrderNumber() == null) {
                this.getUser().setOrderNumber(Long.valueOf(1));
            }
            cart.setOrderNumber(this.getUser().getOrderNumber());
            cart.setQuantity(1);
            cart.setProduct(productService.findByProductId(productId));
            cart.setUser(this.getUser());
            cartService.save(cart);
            Long countOrder = cartService.countByOrderNumberAndUser(this.getUser().getOrderNumber(), this.getUser());
            modelAndView.addObject("countOrder", countOrder);
            return modelAndView;
        }
    }


    @GetMapping("/list")
    public ModelAndView list(){
        ModelAndView modelAndView = new ModelAndView("cart/index");
        modelAndView.addObject("carts", cartService.findAll());
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("create");
        return modelAndView;
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("cart") Cart cart){
        cartService.save(cart);
        return "redirect:/cart/list";
    }

    @DeleteMapping("/delete/{cart_id}")
    public ResponseEntity<Cart> delete(@PathVariable Long cart_id) {
        cartService.remove(cart_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
