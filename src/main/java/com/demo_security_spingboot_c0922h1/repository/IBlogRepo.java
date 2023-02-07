package com.demo_security_spingboot_c0922h1.repository;

import com.demo_security_spingboot_c0922h1.model.Blog;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IBlogRepo extends PagingAndSortingRepository<Blog, Integer> {
}
