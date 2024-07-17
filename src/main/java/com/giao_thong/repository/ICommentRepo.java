package com.giao_thong.repository;

import com.giao_thong.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICommentRepo extends JpaRepository<Comment, Integer> {
    List<Comment> findAllByBlog_Id(int id);
}
