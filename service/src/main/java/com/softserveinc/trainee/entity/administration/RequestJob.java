package com.softserveinc.trainee.entity.administration;

import com.softserveinc.trainee.entity.EntityTimeStamp;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@javax.persistence.Entity
@Table(name = "request_job")
public class RequestJob extends EntityTimeStamp {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RequestJob that = (RequestJob) o;

        return new EqualsBuilder().append(this.getId(), that.getId())
                .append(getAim(), that.getAim())
                .append(getDescription(), that.getDescription())
                .append(getOwner(), that.getOwner())
                .append(getStatus(), that.getStatus())
                .append(getCreatedDate(), that.getCreatedDate())
                .append(getLastModifier(), that.getLastModifier())
                .build();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(getId())
                .append(getStatus())
                .append(getAim())
                .append(getDescription())
                .append(getOwner())
                .append(getCreatedDate())
                .append(getLastModifier()).build();
    }
}
