//package com.casestudy.controller;
//
//
//import com.casestudy.service.uploadfile.StorageService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.io.IOException;
//import java.util.List;
//
//@Controller
//@RequestMapping("/files")
//public class FileController {
//
//    @Autowired
//    private StorageService storageService;
//
//    @GetMapping("/")
//    public ModelAndView ListUploadFiles() throws IOException {
//        ModelAndView modelAndView = new ModelAndView("uploadForm");
//        return modelAndView;
//    }
//
//
//}
