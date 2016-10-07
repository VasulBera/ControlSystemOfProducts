package com.softserveinc.trainee.entity.administration;

import com.softserveinc.trainee.entity.EntityTimeStamp;
import com.softserveinc.trainee.entity.metadata.Field;
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
@Table(name = "previous_state_entities")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
public class PreviousStateEntity extends EntityTimeStamp implements Serializable {

    private static final String VALIDATE_REGEX = "[a-zA-Z0-9\\_]+";

    @Id
    @Column(name = "id")
    @NotNull
    @Size(min = 2 , max = 255)
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
    private List<PreviousStateField> fieldList;

    @Column(name = "full_upload_data")
    private boolean fullUploadData = false;

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

    public List<PreviousStateField> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<PreviousStateField> fieldList) {
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
        PreviousStateEntity other = (PreviousStateEntity)obj;
        return new EqualsBuilder().append(getId(), other.getId())
                .append(getSchemaName(), other.getSchemaName())
                .append(getTableName(), other.getTableName())
                .append(getName(), other.getName())
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

    @Override
    public String toString() {
        return "PreviousStateEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", schemaName='" + schemaName + '\'' +
                ", tableName='" + tableName + '\'' +
                ", fieldList=" + fieldList +
                '}';
    }

    public void addLastModifierDate(PreviousStateEntity persistedPreviousStateEntity){
        long timeInMillis = System.currentTimeMillis();
        long timeInNanos = System.nanoTime();
        Timestamp timestamp = new Timestamp(timeInMillis);
        timestamp.setNanos((int) (timeInNanos % 1000000000));
        if(!this.equals(persistedPreviousStateEntity)){
            this.setLastModifier(timestamp);
        }else{
            this.setLastModifier(persistedPreviousStateEntity.getLastModifier());
        }
    }

    public List<Field> getFields(){
        List<Field> list = new ArrayList();
        for(PreviousStateField pSfield: this.getFieldList()){
            Field field = new Field();
            field.setId(pSfield.getId());
            field.setColumnName(pSfield.getColumnName());
            field.setLength(pSfield.getLength());
            field.setName(pSfield.getName());
            field.setType(pSfield.getType());
            field.setUnique(pSfield.isUnique());
            list.add(field);
        }
        return list;
    }
}
