package com.giao_thong.controller;

import com.giao_thong.model.Account;
import com.giao_thong.model.Blog;
import com.giao_thong.model.Car;
import com.giao_thong.model.Violate;
import com.giao_thong.service.AccountService;
import com.giao_thong.service.CarService;
import com.giao_thong.service.ViolateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/gt")
public class GiaoThongController {
    @Autowired
    private ViolateService violateService;

    @Autowired
    private CarService carService;

    @Autowired
    private HttpSession session;

    @Autowired
    private AccountService accountService;

    @GetMapping
    public ModelAndView carts(@RequestParam(defaultValue = "") String category) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = accountService.findByUsername(userDetails.getUsername());
        session.setAttribute("account", account);
        ModelAndView modelAndView = new ModelAndView("/giaothong");
        List<Car> cars;
        if (category.equals("")){
            cars = carService.getAll();
        }else {
            cars = carService.getAllByCategory(category);
        }
        modelAndView.addObject("cars", cars);
        return modelAndView;
    }

    @GetMapping("{id}")
    public ModelAndView cartDetail(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("profile_cart");
        Car car = carService.findById(id);
        List<Violate> violates = violateService.findAllByCar_Id(car.getId());
        modelAndView.addObject("car",car);
        modelAndView.addObject("violates",violates);
        return modelAndView;
    }

    @PostMapping("/car/edit")
    public String editCart(@ModelAttribute Car car){
        carService.save(car);
        return "redirect:/gt";
    }

//    @GetMapping("/car/delete")
//    public String editCart(@ModelAttribute Car car){
//        carService.save(car);
//        return "redirect:/gt";
//    }

}
