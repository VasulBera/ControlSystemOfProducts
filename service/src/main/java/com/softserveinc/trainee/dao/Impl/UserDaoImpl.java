package com.softserveinc.trainee.dao.Impl;


import com.softserveinc.trainee.dao.UserDao;
import com.softserveinc.trainee.entity.administration.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository("userdao")
public class UserDaoImpl implements UserDao{

    @PersistenceContext(unitName = "administration")
    private EntityManager entityManager;

    public User getUserByUsername(String username){
        return entityManager.createQuery("SELECT u FROM User WHERE u.username = ?1", User.class).setParameter(1, username).getSingleResult();
    }
}
