package com.giao_thong.controller;

import com.giao_thong.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BlogController {
    @Autowired
    BlogService blogService;
    @GetMapping("/blogs")
    public ModelAndView show(@RequestParam(defaultValue = "0") int page){
        ModelAndView modelAndView = new ModelAndView("blogs");
        modelAndView.addObject("blogs",blogService.getAll(PageRequest.of(page,6)));
        return modelAndView;
    }
//    @GetMapping("/admin")
//    public ModelAndView admin(@RequestParam(defaultValue = "0") int page){
//        ModelAndView modelAndView = new ModelAndView("showBlog");
//        modelAndView.addObject("blogs",blogService.getAll(PageRequest.of(page,3)));
//        return modelAndView;
//    }

    @GetMapping("/blog/{id}")
    public ModelAndView blog(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("blog");
        modelAndView.addObject("blog",blogService.findById(id));
        return modelAndView;
    }
}
