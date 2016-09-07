package com.softserveinc.trainee.entity.metadata;

import com.softserveinc.trainee.entity.TimeStamp;
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
import java.util.ArrayList;
import java.util.List;

@javax.persistence.Entity
@Table(name = "entities")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
public class Entity extends TimeStamp implements Serializable{

    private static final String VALIDATE_REGEX = "[a-zA-Z0-9\\_]+";

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
                    .append(getFieldList(), other.getFieldList())
                    .append(getName(), other.getName())
                    .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(getId())
                                    .append(getSchemaName())
                                    .append(getTableName())
                                    .append(getFieldList())
                                    .append(getName())
                                    .toHashCode();
    }


    public PreviousStateEntity createPreviousStateEntity(){
        PreviousStateEntity previousStateEntity = new PreviousStateEntity();
        previousStateEntity.setId(this.getId());
        previousStateEntity.setSchemaName(this.getSchemaName());
        previousStateEntity.setName(this.getName());
        previousStateEntity.setTableName(this.getTableName());
        //previousStateEntity.setCreatedDate(this.getCreatedDate());
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
}


