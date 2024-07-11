package com.giao_thong.service;

import com.giao_thong.model.Violate;
import com.giao_thong.repository.IViolateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViolateService {
    @Autowired
    IViolateRepo iViolateRepo;

    public List<Violate> findAllByCar_Id(Integer idCar) {
        return iViolateRepo.findAllByCar_Id(idCar);
    }

    public List<Violate> getAll() {
        return (List<Violate>) iViolateRepo.findAll();
    }

    public void delete(int id) {
        iViolateRepo.deleteById(id);
    }

    public void save(Violate violate) {
        iViolateRepo.save(violate);

    }
}
