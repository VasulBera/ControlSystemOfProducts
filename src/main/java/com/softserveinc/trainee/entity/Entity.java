package com.softserveinc.trainee.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@javax.persistence.Entity
@Table(name = "entities")
public class Entity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "schema_name", nullable = false)
    private String SchemaName;

    @Column(name = "table_name", nullable = false)
    private String TableName;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "entity_id")
    private List<Field> fieldList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSchemaName() {
        return SchemaName;
    }

    public void setSchemaName(String schemaName) {
        SchemaName = schemaName;
    }

    public String getTableName() {
        return TableName;
    }

    public void setTableName(String tableName) {
        TableName = tableName;
    }

    public List<Field> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<Field> fieldList) {
        this.fieldList = fieldList;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "id=" + id +
                ", SchemaName='" + SchemaName + '\'' +
                ", TableName='" + TableName + '\'' +
                ", fieldList=" + fieldList +
                '}';
    }
}


