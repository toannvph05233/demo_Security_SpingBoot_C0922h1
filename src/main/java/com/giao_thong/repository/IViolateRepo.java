package com.giao_thong.repository;

import com.giao_thong.model.Account;
import com.giao_thong.model.Violate;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface IViolateRepo extends PagingAndSortingRepository<Violate, Integer> {
    List<Violate> findAllByCar_Id(Integer idCar);
}
