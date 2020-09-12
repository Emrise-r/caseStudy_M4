package com.casestudy.controller;


import com.casestudy.model.Category;
import com.casestudy.model.Product;
import com.casestudy.service.category.CategoryService;
import com.casestudy.service.product.ProductService;
import com.casestudy.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/product")
public class ProductController {

//    private static String username = null;

    @Autowired
    Environment evn;

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    UserService userService;
//    RoleService roleService;

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
        if (name.contains("anonymousUser")  ) {
            return null;
        } else {
            return userService.findByName(name).getRole().getName();
        }
    }

//    @ModelAttribute("authentication")
//    public String getAuthentication() {
//        String authentication = null;
//        Object principal = SecurityContextHolder.getContext().getAuthentication();
//
//        if (principal instanceof UserDetails) {
//            authentication = ((Authentication)principal).getName();
//        } else {
//            authentication = principal.toString();
//        }
//        return authentication;
//    }


    @ModelAttribute("categories")
    public Iterable<Category> categories() {
        return categoryService.findAll();
    }

    @GetMapping()
    public ModelAndView index(Pageable pageable){

        ModelAndView modelAndView = new ModelAndView("/eshopper/index");
        modelAndView.addObject("products", productService.findAll(pageable));
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createStudent(@ModelAttribute Product productForm){

        MultipartFile file = productForm.getImgFile();
        String image = file.getOriginalFilename();
        String fileUpload = evn.getProperty("file_upload").toString();
        try {
            FileCopyUtils.copy(file.getBytes(), new File(fileUpload + image));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Product product = new Product();
        product.setName(productForm.getName());
        product.setImg(image);
        product.setDescription(productForm.getDescription());
        product.setPrice(productForm.getPrice());
        product.setQuantity(productForm.getQuantity());
        product.setCategory(productForm.getCategory());
        productService.save(product);
        return new ModelAndView("/product/create", "product", new Product());

    }

}
