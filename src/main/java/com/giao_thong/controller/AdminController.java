package com.giao_thong.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.giao_thong.model.*;
import com.giao_thong.repository.ICategoryRepo;
import com.giao_thong.repository.ICommentRepo;
import com.giao_thong.repository.IViolateRepo;
import com.giao_thong.service.AccountService;
import com.giao_thong.service.BlogService;
import com.giao_thong.service.CarService;
import com.giao_thong.service.ViolateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {
    final int ROLE_GT = 1;

    @Autowired
    private AccountService accountService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private ICategoryRepo categoryRepo;

    @Autowired
    private CarService carService;

    @Autowired
    private IViolateRepo violateRepo;

    @Autowired
    private HttpSession session;

    @Autowired
    ICommentRepo iCommentRepo;

    @GetMapping
    public ModelAndView admin() throws JsonProcessingException {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = accountService.findByUsername(userDetails.getUsername());
        session.setAttribute("account", account);
        ModelAndView modelAndView = new ModelAndView("admin");
        List<Account> accounts = accountService.findAllByRolesId(ROLE_GT);
        List<Blog> blogs = blogService.getAll();
        List<Car> cars = carService.getAll();
        List<Violate> violates = (List<Violate>) violateRepo.findAll();

        Map<String, Long> violationsPerMonth = violates.stream()
                .collect(Collectors.groupingBy(v -> v.getTime().getMonth().toString(), Collectors.counting()));

        long countV = violates.size();
        long countVDone = violates.stream().filter(v -> v.getStatus().equals("đã xử lý")).count();
        long countVNotDone = violates.stream().filter(v -> v.getStatus().equals("chưa xử lý")).count();

        Map<String, Long> violationsByVehicleType = violates.stream()
                .collect(Collectors.groupingBy(v -> v.getCar().getCategory(), Collectors.counting()));

        modelAndView.addObject("countV", countV);
        modelAndView.addObject("countVDone", countVDone);
        modelAndView.addObject("countVNotDone", countVNotDone);
        modelAndView.addObject("accounts", accounts);
        modelAndView.addObject("categories", categoryRepo.findAll());
        modelAndView.addObject("blogs", blogs);
        modelAndView.addObject("cars", cars);
        modelAndView.addObject("violationsPerMonth", new ObjectMapper().writeValueAsString(violationsPerMonth));
        modelAndView.addObject("violationsByVehicleType", new ObjectMapper().writeValueAsString(violationsByVehicleType));

        return modelAndView;
    }



    @GetMapping("/chat")
    public String chat() {
        return "ChatAdmin";
    }

    @PostMapping("/account/add")
    public String addAccount(@ModelAttribute Account account) {
        Role role = new Role();
        role.setId(ROLE_GT);
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        account.setRoles(roles);
        accountService.save(account);
        return "redirect:/admin";
    }

    @GetMapping("/account/delete/{id}")
    public String deleteAccount(@PathVariable int id) {
        List<Violate> violates = violateRepo.findAllByAccount_Id(id);
        for (Violate v:violates) {
            violateRepo.deleteById(v.getId());
        }
        accountService.deleteById(id);
        return "redirect:/admin";
    }

    @PostMapping("/blog/add")
    public String addBlog(@ModelAttribute Blog blog) {
        blogService.save(blog);
        return "redirect:/admin";
    }


    @GetMapping("/blog/delete/{id}")
    public String deleteBlog(@PathVariable int id) {
        List<Comment> comments = iCommentRepo.findAllByBlog_Id(id);
        for (Comment c:comments) {
            iCommentRepo.deleteById(c.getId());
        }
        blogService.deleteById(id);
        return "redirect:/admin";
    }

    @PostMapping("/car/add")
    public String addCar(@ModelAttribute Car car) {
        carService.save(car);
        return "redirect:/admin";
    }

    @GetMapping("/car/delete/{id}")
    public String deleteCar(@PathVariable int id) {
        List<Violate> violates = violateRepo.findAllByCar_Id(id);
        for (Violate v:violates) {
            violateRepo.deleteById(v.getId());
        }
        carService.deleteById(id);
        return "redirect:/admin";
    }
}
