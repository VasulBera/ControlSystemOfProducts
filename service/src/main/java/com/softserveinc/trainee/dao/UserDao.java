package com.softserveinc.trainee.dao;


import com.softserveinc.trainee.entity.administration.User;
import org.springframework.transaction.annotation.Transactional;

public interface UserDao {

    @Transactional("transactionManagerAdministration")
    public User getUserByUsername(String username);
}
