package com.softserveinc.trainee.dao.Impl;

import com.softserveinc.trainee.EntityUtil.EntityUtil;
import com.softserveinc.trainee.dao.RequestTaskDao;
import com.softserveinc.trainee.entity.administration.RequestTask;
import com.softserveinc.trainee.entity.metadata.Entity;
import com.softserveinc.trainee.entity.metadata.PreviousStateEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

@Repository("requestTaskDao")
public class RequestTaskDaoImpl implements RequestTaskDao{

    @PersistenceContext(unitName = "administration")
    private EntityManager entityManager;

    private static final String PATH_TO_DATABASE_PROPERTIES = "database.properties";
    private static final String MS_SQL_SERVER_ADDRES = "jdbc:sqlserver://localhost;";
    private static final String USERNAME_KEY_PROPERTIES = "javax.persistence.jdbc.user";
    private static final String PASSWORD_KEY_PROPERTIES = "javax.persistence.jdbc.password";

    @Override
    public void createRequestTask(RequestTask requestTask) {
        entityManager.persist(requestTask);
    }

    @Override
    public void createEntityTable(Entity entity) {
        EntityUtil.generateTable(entity);
    }

    @Override
    public void updateTable(PreviousStateEntity previousStateEntity, Entity entity) {
        EntityUtil.updateTable(previousStateEntity , entity);
    }
}
