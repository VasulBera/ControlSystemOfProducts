package com.softserveinc.trainee.entity.metadata;

import com.softserveinc.trainee.entity.EntityTimeStamp;
import com.softserveinc.trainee.entity.administration.PreviousStateEntity;
import com.softserveinc.trainee.entity.administration.PreviousStateField;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.codehaus.jackson.annotate.JsonAutoDetect;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@javax.persistence.Entity
@Table(name = "entities")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
public class Entity extends EntityTimeStamp implements Serializable{

    private static final String VALIDATE_REGEX = "[a-zA-Z0-9\\_]+";
    private static final String FILE_EXTENSION = ".csv";
    private static final String DEFAULT_CUSTOM_DB_NAME = "CustomTables";

    @Id @Column(name = "id")
    @NotNull @Size(min = 2 , max = 256)
    @Pattern(regexp = VALIDATE_REGEX)
    private String id;

    @Column(name = "name")
    @NotNull @Size(min = 1 , max = 128)
    @Pattern(regexp = VALIDATE_REGEX)
    private String name;

    @Column(name = "schema_name")
    @NotNull @Size(min = 1 , max = 128)
    @Pattern(regexp = VALIDATE_REGEX)
    private String schemaName;

    @Column(name = "table_name")
    @NotNull @Size(min = 1 , max = 128)
    @Pattern(regexp = VALIDATE_REGEX)
    private String tableName;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "entity_id")
    private List<Field> fieldList;

    @Column(name = "full_upload_data")
    private boolean fullUploadData = false;

    public Entity(){}

    public Entity getTmpEntity(){
        Entity tmpEntity = new Entity();
        tmpEntity.id = getId();
        tmpEntity.schemaName = getSchemaName();
        tmpEntity.tableName = getTableName() + "_temporary";
        tmpEntity.fullUploadData = isFullUploadData();
        tmpEntity.name = getName();
        tmpEntity.fieldList = getFieldList();
        return tmpEntity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<Field> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<Field> fieldList) {
        this.fieldList = fieldList;
    }

    public boolean isFullUploadData() {
        return fullUploadData;
    }

    public void setFullUploadData(boolean fullUploadData) {
        this.fullUploadData = fullUploadData;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Entity other = (Entity)obj;
        return new EqualsBuilder().append(getId(), other.getId())
                    .append(getSchemaName(), other.getSchemaName())
                    .append(getTableName(), other.getTableName())
                    .append(getName(), other.getName())
                    .append(isFullUploadData(), other.isFullUploadData())
                    .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(getId())
                                    .append(getSchemaName())
                                    .append(getTableName())
                                    .append(getName())
                                    .toHashCode();
    }

    public PreviousStateEntity createPreviousStateEntity(){
        PreviousStateEntity previousStateEntity = new PreviousStateEntity();
        previousStateEntity.setId(this.getId());
        previousStateEntity.setSchemaName(this.getSchemaName());
        previousStateEntity.setName(this.getName());
        previousStateEntity.setTableName(this.getTableName());
        if(this.getFieldList() != null){
            List<PreviousStateField> list = new ArrayList();
            for(Field field: this.getFieldList()){
                list.add(field.createPreviousStateField());
            }
            previousStateEntity.setFieldList(list);
        }
        return previousStateEntity;
    }

    public String genereateShemaWithTable(){
        return getSchemaName() + "." + getTableName();
    }

    public void addLastModifierDate(Entity persistedEntity){
        long timeInMillis = System.currentTimeMillis();
        long timeInNanos = System.nanoTime();
        Timestamp timestamp = new Timestamp(timeInMillis);
        timestamp.setNanos((int) (timeInNanos % 1000000000));
        if(!this.equals(persistedEntity)){
            this.setLastModifier(timestamp);
        }else{
            this.setLastModifier(persistedEntity.getLastModifier());
        }
    }

    public String getFileName(){
        return this.getTableName() + FILE_EXTENSION;
    }

    public String getConstraintName(){
        return getSchemaName() + "_" + getTableName()+ "_Unique";
    }

    public String createFullTableName(){
        return "[" + DEFAULT_CUSTOM_DB_NAME +"].[" + getSchemaName() + "].[" + getTableName() + "]";
    }

    public String getUniqueFieldName(){
        String uniqueColumnName = null;
        for(Field field: getFieldList()){
            if(field.isUnique()){
               uniqueColumnName = field.getColumnName();
                break;
            }
        }
        return uniqueColumnName;
    }

    public String joinColumnNames(){
        StringBuilder columnNames = new StringBuilder();
        String prefix = "";
        for(Field field: getFieldList()){
            columnNames.append(prefix + field.getColumnName());
            prefix = ", ";
        }
        return columnNames.toString();
    }

    public String getTemporaryJoinColumnName(){
        StringBuilder columnNames = new StringBuilder();
        String prefix = "";
        for(Field field: getFieldList()){
            columnNames.append(prefix + "temporary." + field.getColumnName());
            prefix = ", ";
        }
        return columnNames.toString();
    }

    public boolean changeUniqueField(PreviousStateEntity previousStateEntity){
        for(Field field: getFieldList()){
            for(Field previousField: previousStateEntity.getFields()){
                if(field.getId().equals(previousField.getId()) && field.isUnique() && previousField.isUnique()){
                    return false;
                }
            }
        }
        return true;
    }
}


