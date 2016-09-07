package com.softserveinc.trainee.test;


import com.softserveinc.trainee.dao.Impl.UserDaoImpl;
import com.softserveinc.trainee.entity.administration.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class TestUserDaoImpl {

    @Mock
    private static EntityManager entityManager;

    @Mock
    private static TypedQuery<User> query;

    @InjectMocks
    private static UserDaoImpl userDao;

    @Before
    public void initialize(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetUserByUsername(){
        String username = "Vasul";
        User user = new User();
        user.setUsername("Vasul");
        Mockito.when(entityManager.createQuery("SELECT u FROM User WHERE u.username = ?1", User.class)).thenReturn(query);
        Mockito.when(query.setParameter(1, username)).thenReturn(query);
        Mockito.when(query.getSingleResult()).thenReturn(user);
        userDao.getUserByUsername(username);
    }
}
