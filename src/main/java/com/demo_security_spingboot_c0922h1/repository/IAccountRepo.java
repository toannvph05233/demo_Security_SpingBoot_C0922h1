package com.demo_security_spingboot_c0922h1.repository;

import com.demo_security_spingboot_c0922h1.model.Account;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IAccountRepo extends PagingAndSortingRepository<Account, Integer> {
    Account findAccountByUsername(String username);
}
