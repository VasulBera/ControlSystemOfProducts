package com.softserveinc.trainee.entity.administration;

import com.softserveinc.trainee.entity.TimeStamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@javax.persistence.Entity
@Table(name = "request_job")
public class RequestJob extends TimeStamp{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "owner")
    private String owner;

    @Column(name = "aim")
    private String aim;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    @Enumerated(EnumType.STRING) @NotNull
    private RequestJobStatus status = RequestJobStatus.ACTUAL;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAim() {
        return aim;
    }

    public void setAim(String aim) {
        this.aim = aim;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RequestJobStatus getStatus() {
        return status;
    }

    public void setStatus(RequestJobStatus status) {
        this.status = status;
    }
}
