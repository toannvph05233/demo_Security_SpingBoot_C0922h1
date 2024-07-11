package com.giao_thong.repository;

import com.giao_thong.model.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IAccountRepo extends PagingAndSortingRepository<Account, Integer> {
    Account findAccountByUsername(String username);

    @Query(nativeQuery = true, value = "select account.* from account join account_roles on account.id = account_roles.account_id join role on role.id = account_roles.roles_id where role.id = :idRole")
    List<Account> findAllByRolesId(@Param("idRole") int idRole);
}
