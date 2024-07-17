package com.giao_thong.controller;

import com.giao_thong.model.Comment;
import com.giao_thong.repository.ICommentRepo;
import com.giao_thong.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;

@Controller
public class BlogController {
    @Autowired
    BlogService blogService;
    @Autowired
    ICommentRepo iCommentRepo;
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
        modelAndView.addObject("comments",iCommentRepo.findAllByBlog_Id(id));
        return modelAndView;
    }

    @PostMapping("/blog/addComment")
    public String blog(@ModelAttribute Comment comment, @RequestParam int blogId){
       comment.setBlog(blogService.findById(blogId));
       comment.setDate(new Date(System.currentTimeMillis()));
       iCommentRepo.save(comment);
        return "redirect:/blog/"+blogId;
    }
}
