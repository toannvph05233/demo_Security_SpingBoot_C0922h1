package com.giao_thong.repository;

import com.giao_thong.model.Payment;
import com.giao_thong.model.Violate;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface IPaymentRepo extends PagingAndSortingRepository<Payment, Integer> {
    List<Payment> findAllByCar_Id(int idCart);
}
