package com.softserveinc.trainee.entity.metadata;

import com.softserveinc.trainee.entity.EntityTimeStamp;
import com.softserveinc.trainee.entity.administration.PreviousStateField;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.codehaus.jackson.annotate.JsonAutoDetect;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;

@javax.persistence.Entity
@Table(name = "fields")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
public class Field extends EntityTimeStamp implements Serializable{

    private static final String VALIDATE_REGEX = "[a-zA-Z0-9\\_]+";

    @Id @Column(name = "id")
    @NotNull @Size(min = 1, max = 384)
    @Pattern(regexp = VALIDATE_REGEX)
    private String id;

    @Column(name = "name")
    @NotNull @Size(min = 1, max = 128)
    @Pattern(regexp = VALIDATE_REGEX)
    private String name;

    @Column(name = "column_name")
    @NotNull @Size(min = 1, max = 128)
    @Pattern(regexp = VALIDATE_REGEX)
    private String ColumnName;

    @Column(name = "type")
    @Enumerated(EnumType.STRING) @NotNull
    private FieldType type;

    @Column(name = "length") @Min(0)
    private Integer length;

    @Column(name = "is_unique")
    private boolean unique = false;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FieldType getType() {
        return type;
    }

    public void setType(FieldType type) {
        this.type = type;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getColumnName() {
        return ColumnName;
    }

    public void setColumnName(String columnName) {
        ColumnName = columnName;
    }

    public boolean isUnique() {
        return unique;
    }

    public void setUnique(boolean unique) {
        this.unique = unique;
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
        Field other = (Field)obj;
        return new EqualsBuilder()
                .append(getId(), other.getId())
                .append(getLength(), other.getLength())
                .append(getName(), other.getName())
                .append(getType(), other.getType())
                .append(getColumnName(), other.getColumnName())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(getId())
                                    .append(getLength())
                                    .append(getName())
                                    .append(getType())
                                    .append(getColumnName())
                                    .toHashCode();
    }

    @Override
    public String toString() {
        return "Field{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", ColumnName='" + ColumnName + '\'' +
                ", type=" + type +
                ", length=" + length +
                '}';
    }

    public PreviousStateField createPreviousStateField(){
        PreviousStateField previousStateField = new PreviousStateField();
        previousStateField.setId(getId());
        previousStateField.setName(getName());
        previousStateField.setColumnName(getColumnName());
        previousStateField.setType(getType());
        previousStateField.setLength(getLength());
        previousStateField.setUnique(isUnique());
        return previousStateField;
    }

    public String generateColumnSql(){
        if (getType() == FieldType.NVARCHAR) {
            return getColumnName() + " " + getType() + "(" + getLength() + ")";
        } else {
            return getColumnName() + " " + getType();
        }
    }

    public void addLastModifierDate(Field persistedField){
        long timeInMillis = System.currentTimeMillis();
        long timeInNanos = System.nanoTime();
        Timestamp timestamp = new Timestamp(timeInMillis);
        timestamp.setNanos((int) (timeInNanos % 1000000000));
        if(!this.equals(persistedField)){
            this.setLastModifier(timestamp);
        }else{
            this.setLastModifier(persistedField.getLastModifier());
        }
    }
}
