package com.giao_thong.controller;

import com.giao_thong.model.Car;
import com.giao_thong.model.Payment;
import com.giao_thong.model.Violate;
import com.giao_thong.repository.IPaymentRepo;
import com.giao_thong.service.CarService;
import com.giao_thong.service.ViolateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/tracuu")
public class TraCuuController {
    @Autowired
    private CarService carService;
    @Autowired
    private ViolateService violateService;

    @Autowired
    private IPaymentRepo iPaymentRepo;


    @GetMapping
    public ModelAndView traCuu(@RequestParam String bsx) {
        ModelAndView modelAndView;
        Car car = carService.findCarByLicensePlate(bsx);
        if (car != null) {
            modelAndView = new ModelAndView("tracuu");
            List<Violate> violates = violateService.findAllByCar_Id(car.getId());
            List<Payment> payments = iPaymentRepo.findAllByCar_Id(car.getId());
            modelAndView.addObject("car", car);
            modelAndView.addObject("violates", violates);
            modelAndView.addObject("payments", payments);
        } else {
            modelAndView = new ModelAndView("index");
            modelAndView.addObject("message", "Không Có Xe Có Biển : " + bsx);
        }
        return modelAndView;
    }

}
