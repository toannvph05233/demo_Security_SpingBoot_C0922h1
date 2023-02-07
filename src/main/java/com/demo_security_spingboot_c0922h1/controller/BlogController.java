package com.demo_security_spingboot_c0922h1.controller;

import com.demo_security_spingboot_c0922h1.model.Blog;
import com.demo_security_spingboot_c0922h1.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BlogController {
    @Autowired
    BlogService blogService;
    @GetMapping("/blogs")
    public ModelAndView show(@RequestParam(defaultValue = "0") int page){
        ModelAndView modelAndView = new ModelAndView("showBlog");
        modelAndView.addObject("blogs",blogService.getAll(PageRequest.of(page,3)));
        return modelAndView;
    }
    @GetMapping("/admin")
    public ModelAndView admin(@RequestParam(defaultValue = "0") int page){
        ModelAndView modelAndView = new ModelAndView("showBlog");
        modelAndView.addObject("blogs",blogService.getAll(PageRequest.of(page,3)));
        return modelAndView;
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
