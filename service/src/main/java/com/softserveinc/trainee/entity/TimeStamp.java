package com.softserveinc.trainee.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

@MappedSuperclass
public class TimeStamp {

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "last_modifier")
    private Date lastModifier;

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifier() {
        return lastModifier;
    }

    public void setLastModifier(Date lastModifier) {
        this.lastModifier = lastModifier;
    }


    @PreUpdate
    public void setLastModifierOnChange(){
        this.setLastModifier(new Date());
    }

    @PrePersist
    public void onCreate(){
        this.setCreatedDate(new Date());
    }
}
