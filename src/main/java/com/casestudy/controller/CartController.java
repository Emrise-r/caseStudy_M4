package com.casestudy.controller;

import com.casestudy.model.Cart;
import com.casestudy.service.cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cart")
public class CartController {


    @Autowired
    private CartService cartService;



//    @ModelAttribute("products")
//    public Iterable<Product> products(Pageable pageable) {
//        return productService.findAll(pageable);
//    }

//    @GetMapping("/list")
//    public ResponseEntity<Iterable<Cart>> findAll() {
//        return new ResponseEntity<>(cartService.findAll(), HttpStatus.OK);
//    }

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
