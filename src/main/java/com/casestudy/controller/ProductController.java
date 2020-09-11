package com.casestudy.controller;


import com.casestudy.model.Category;
import com.casestudy.model.Product;
import com.casestudy.service.category.CategoryService;
import com.casestudy.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Pageable;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    Environment evn;

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @ModelAttribute("categories")
    public Iterable<Category> categories() {
        return categoryService.findAll();
    }

    @GetMapping()
    public ModelAndView index(Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("product/list");
        modelAndView.addObject("list", productService.findAll(pageable));
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
