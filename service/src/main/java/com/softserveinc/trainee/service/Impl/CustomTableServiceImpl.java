package com.softserveinc.trainee.service.Impl;

import com.google.gson.Gson;
import com.softserveinc.trainee.customObject.GenericTableRow;
import com.softserveinc.trainee.customObject.GenericTableRowCallbackHandler;
import com.softserveinc.trainee.customObject.Line;
import com.softserveinc.trainee.dao.EntityDao;
import com.softserveinc.trainee.entity.metadata.Entity;
import com.softserveinc.trainee.service.CustomTableService;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;
@JsonIgnoreProperties(ignoreUnknown=true)
public class CustomTableServiceImpl implements CustomTableService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    EntityDao entityDao;

    @Override
    public List<Line> getAllObjects(String id) {
        Entity entity = entityDao.getEntity(id);
        GenericTableRowCallbackHandler handler = new GenericTableRowCallbackHandler(entity);
        jdbcTemplate.query("SELECT * FROM [client].[CARS]", handler);
        List<Line> list = handler.getGenericTableRows();
        return list;
    }
}
