package com.softserveinc.trainee.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.io.Serializable;

@javax.persistence.Entity
@Table(name = "fields")
public class Field implements Serializable{

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "length", nullable = true)
    private int length;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
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
        return new EqualsBuilder().append(getId(), other.getId())
                            .append(getLength(), other.getLength())
                            .append(getName(), other.getName())
                            .append(getType(), other.getType())
                            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(getId())
                                    .append(getLength())
                                    .append(getName())
                                    .append(getType())
                                    .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append(getId())
                                        .append(getType())
                                        .append(getName())
                                        .append(getLength())
                                        .toString();
    }
}
