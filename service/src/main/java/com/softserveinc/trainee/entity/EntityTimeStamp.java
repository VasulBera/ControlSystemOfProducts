package com.softserveinc.trainee.entity;

        import javax.persistence.Column;
        import javax.persistence.MappedSuperclass;
        import javax.persistence.PrePersist;
        import java.sql.Timestamp;

@MappedSuperclass
public class EntityTimeStamp {


    @Column(name = "created_date")
    private Timestamp createdDate;

    @Column(name = "last_modifier")
    private Timestamp lastModifier;

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getLastModifier() {
        return lastModifier;
    }

    public void setLastModifier(Timestamp lastModifier) {
        this.lastModifier = lastModifier;
    }

    @PrePersist
    public void onCreate(){
        long timeInMillis = System.currentTimeMillis();
        long timeInNanos = System.nanoTime();
        Timestamp timestamp = new Timestamp(timeInMillis);
        timestamp.setNanos((int) (timeInNanos % 1000000000));
        this.setCreatedDate(timestamp);
    }
}
