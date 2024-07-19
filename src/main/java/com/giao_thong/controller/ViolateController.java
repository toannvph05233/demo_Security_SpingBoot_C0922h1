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
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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
    public String create(@ModelAttribute Violate violate, @RequestParam int violateCarId, @RequestParam String timeV, @RequestParam MultipartFile imgFile) throws IOException {
        // Define a DateTimeFormatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        if (!imgFile.isEmpty()) {
            String name = imgFile.getOriginalFilename();
            FileCopyUtils.copy(imgFile.getBytes(), new File("/Users/johntoan98gmail.com/Desktop/quan_ly/giao_thong/src/main/resources/static/images/" + name));
            violate.setImage("/images/" + name);
        } else {
            if (violate.getId() != 0) {
                Violate violate1 = violateService.findById(violate.getId());
                violate.setImage(violate1.getImage());
            } else {
                violate.setImage("https://cdn.thuvienphapluat.vn/phap-luat/2022/M%E1%BB%B9%20Ng%E1%BB%8Dc/vi-pham-giao-thong.png");
            }
        }
        // Parse the String to LocalDateTime
        LocalDateTime dateTime = LocalDateTime.parse(timeV, formatter);
        violate.setTime(dateTime);
        violate.setStatus("chưa xử lý");
        Car car = carService.findById(violateCarId);
        violate.setCar(car);
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = accountService.findByUsername(userDetails.getUsername());
        violate.setAccount(account);
        violateService.save(violate);
        return "redirect:/gt/" + violateCarId;
    }
}
