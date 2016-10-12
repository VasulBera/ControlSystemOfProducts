package com.softserveinc.trainee.service.Impl;

import com.softserveinc.trainee.generic.GenericTableRowCallbackHandler;
import com.softserveinc.trainee.generic.GenericTableRow;
import com.softserveinc.trainee.dao.EntityDao;
import com.softserveinc.trainee.entity.metadata.Entity;
import com.softserveinc.trainee.generic.RowCountCallbackHandler;
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
    public List<GenericTableRow> getAllObjects(String id, Integer from, Integer amount) {
        Entity entity = entityDao.getEntity(id);
        GenericTableRowCallbackHandler handler = new GenericTableRowCallbackHandler(entity);
        jdbcTemplate.query("SELECT * FROM [client].[" + entity.getTableName() + "] ORDER BY id OFFSET " + from + " ROWS FETCH NEXT " + amount + " ROWS ONLY", handler);
        return handler.getGenericTableRows();
    }

    @Override
    public int getRowCount(String id) {
        Entity entity = entityDao.getEntity(id);
        RowCountCallbackHandler countCallback =  new RowCountCallbackHandler();
        jdbcTemplate.query("SELECT count(*) FROM [client].[" + entity.getTableName() + "]", countCallback);
        return  countCallback.getCount();
    }
}
