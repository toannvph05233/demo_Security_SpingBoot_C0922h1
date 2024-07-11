package com.giao_thong.repository;

import com.giao_thong.model.Account;
import com.giao_thong.model.Car;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ICarRepo extends PagingAndSortingRepository<Car, Integer> {
    Car findCarByLicensePlate(String licensePlate);

    List<Car> findAllByCategory(String category);
}
