package com.giao_thong.controller;

import com.giao_thong.model.Account;
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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/violates")
public class ViolateController {
    @Autowired
    ViolateService violateService;

    @Autowired
    CarService carService;
    @Autowired
    AccountService accountService;

    @GetMapping("/delete")
    public String delete(@RequestParam int idViolate, @RequestParam int idCart) {
        violateService.delete(idViolate);
        return "redirect:/gt/" + idCart;
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Violate violate, @RequestParam int violateCarId, @RequestParam String timeV) {
        // Define a DateTimeFormatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

        // Parse the String to LocalDateTime
        LocalDateTime dateTime = LocalDateTime.parse(timeV, formatter);
        violate.setTime(dateTime);
        Car car = carService.findById(violateCarId);
        violate.setCar(car);
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = accountService.findByUsername(userDetails.getUsername());
        violate.setAccount(account);
        violateService.save(violate);
        return "redirect:/gt/" + violateCarId;
    }
}
