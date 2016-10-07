package com.softserveinc.trainee.loader.Impl;

import com.softserveinc.trainee.entity.metadata.Entity;
import com.softserveinc.trainee.generator.TableGenerator;
import com.softserveinc.trainee.loader.DataLoader;
import com.softserveinc.trainee.loader.DataSource;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Iterator;

@Component
public class CsvDataLoader implements DataLoader{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    TableGenerator tableGenerator;

    @Autowired
    DataSource dataSource;

    private static final String DB_NAME = "CustomTables";
    private static final String DEFAULT_SCHEMA_NAME = "client";
    private static final short RECORD_AMOUNT = 1_000;
    private static final short RECORD_ORIGIN = 1;

    @Override
    public void load(Entity entity) throws IOException {
        if(entity.isFullUploadData()) {
            deleteAllRecord(entity);
            fullLoadData(entity);
        }else{
            Entity tmpEntity = entity.getTmpEntity();
            tableGenerator.createTable(tmpEntity);
            fullLoadData(tmpEntity);
            loadRemainderData(entity, tmpEntity);
            tableGenerator.deleteTable(DEFAULT_SCHEMA_NAME + "." + tmpEntity.getTableName());
        }
    }

    private void deleteAllRecord(Entity entity){
        jdbcTemplate.execute("DELETE FROM " + entity.genereateShemaWithTable());
    }

    private void fullLoadData(Entity entity)throws IOException {
        InputStream input = new FileInputStream("C:\\Users\\vberv\\Desktop\\CARS.csv");
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        //BufferedReader reader = dataSource.getReader(entity.getFileName());
        Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(reader);
        int recordIndex = RECORD_ORIGIN;
        StringBuilder stringBuilder = new StringBuilder();
        String recordInsertPrefix = "";
        for(CSVRecord record : records) {
            Iterator<String> it = record.iterator();
            String prefix = "";
            stringBuilder.append(recordInsertPrefix);
            stringBuilder.append("(");
            for(int i = 0; i < record.size(); i++) {
                stringBuilder.append(prefix);
                stringBuilder.append("'" + it.next() + "'");
                prefix = ",";
            }
            stringBuilder.append(")\n");
            recordInsertPrefix = ",";
            if(recordIndex == RECORD_AMOUNT) {
                jdbcTemplate.execute("INSERT INTO " + DEFAULT_SCHEMA_NAME + "." + entity.getTableName() + " VALUES\n" + stringBuilder);
                stringBuilder = new StringBuilder();
                recordIndex = RECORD_ORIGIN;
                recordInsertPrefix = "";
            }
            recordIndex++;
        }
        if(!stringBuilder.toString().isEmpty()) {
            jdbcTemplate.execute("INSERT INTO client." + entity.getTableName() + " VALUES\n" + stringBuilder);
        }
    }

    private void loadRemainderData(Entity entity, Entity tmpEntity){
        StringBuilder query = new StringBuilder()
                .append("INSERT INTO [" + DB_NAME + "].[" + DEFAULT_SCHEMA_NAME + "].[" + entity.getTableName() + "] ( " + entity.joinColumnNames() + " )\n")
                .append("SELECT " + entity.getTemporaryJoinColumnName() +" FROM [" + DB_NAME + "].[" + DEFAULT_SCHEMA_NAME + "].[" + tmpEntity.getTableName() + "] temporary ")
                .append("LEFT JOIN [" + DB_NAME + "].[" + DEFAULT_SCHEMA_NAME + "].[" + entity.getTableName() + "] original ")
                .append("ON temporary." + entity.getUniqueFieldName() + " = original." + entity.getUniqueFieldName()  + " WHERE original." + entity.getUniqueFieldName() + " IS NULL");
        jdbcTemplate.execute(query.toString());
    }
}
