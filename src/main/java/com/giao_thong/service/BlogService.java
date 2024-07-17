package com.giao_thong.service;

import com.giao_thong.model.Blog;
import com.giao_thong.repository.IBlogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BlogService {
    @Autowired
    IBlogRepo iBlogRepo;

    public Page<Blog> getAll(Pageable pageable){
        return iBlogRepo.findAll(pageable);
    }

    public List<Blog> getAll(){
        return (List<Blog>) iBlogRepo.findAll();
    }
    public void save(Blog blog){
        iBlogRepo.save(blog);
    }

    public void delete(int id){
        iBlogRepo.deleteById(id);
    }

    public Blog findById(int id){
        return iBlogRepo.findById(id).get();
    }

    public void deleteById(int id){
        iBlogRepo.deleteById(id);
    }
}
