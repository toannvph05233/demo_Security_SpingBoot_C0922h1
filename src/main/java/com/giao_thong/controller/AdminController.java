package com.giao_thong.controller;

import com.giao_thong.model.Account;
import com.giao_thong.model.Blog;
import com.giao_thong.model.Car;
import com.giao_thong.model.Violate;
import com.giao_thong.service.AccountService;
import com.giao_thong.service.BlogService;
import com.giao_thong.service.CarService;
import com.giao_thong.service.ViolateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    final int ROLE_GT = 1;
    @Autowired
    private AccountService accountService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private ViolateService violateService;

    @Autowired
    private CarService carService;

    @Autowired
    private HttpSession session;

    @GetMapping
    public ModelAndView admin() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = accountService.findByUsername(userDetails.getUsername());
        session.setAttribute("account", account);
        ModelAndView modelAndView = new ModelAndView("/admin");
        List<Account> accounts = accountService.findAllByRolesId(ROLE_GT);
        List<Blog> blogs = blogService.getAll();
        List<Car> cars = carService.getAll();
        List<Violate> violates = violateService.getAll();
        modelAndView.addObject("accounts", accounts);
        modelAndView.addObject("blogs", blogs);
        modelAndView.addObject("cars", cars);
        modelAndView.addObject("violates", violates);
        return modelAndView;

    }
}
