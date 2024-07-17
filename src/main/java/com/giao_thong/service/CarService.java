package com.giao_thong.service;

import com.giao_thong.model.Blog;
import com.giao_thong.model.Car;
import com.giao_thong.repository.ICarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    @Autowired
    ICarRepo iCarRepo;

    public List<Car> getAll() {
        return (List<Car>) iCarRepo.findAll();
    }

    public void deleteById(int id){
        iCarRepo.deleteById(id);
    }

    public List<Car> getAllByCategory(String category) {
        return iCarRepo.findAllByCategory(category);
    }

    public void save(Car car) {
        iCarRepo.save(car);
    }

    public void delete(int id) {
        iCarRepo.deleteById(id);
    }

    public Car findById(int id) {
        return iCarRepo.findById(id).get();
    }

    public Car findCarByLicensePlate(String licensePlate){
        return iCarRepo.findCarByLicensePlate(licensePlate);
    }

}
