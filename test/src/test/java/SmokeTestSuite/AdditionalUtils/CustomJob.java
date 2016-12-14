package SmokeTestSuite.AdditionalUtils;


/**
 * Created by sriznych on 22.11.2016.
 */
public class CustomJob {

    private String owner;
    private String aim;
    private String description;

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setAim(String aim) {
        this.aim = aim;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getOwner() {
        return owner;
    }

    public String getAim() {
        return aim;
    }

    public String getDescription() {
        return description;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomJob jobs = (CustomJob) o;
        if (owner != null ? !owner.equals(jobs.owner) : jobs.owner != null) return false;
        if (aim != null ? !aim.equals(jobs.aim) : jobs.aim != null) return false;
        return description != null ? description.equals(jobs.description) : jobs.description == null;

    }

    @Override
    public String toString() {
        return "CustomJob{" +
                "owner='" + owner + '\'' +
                ", aim='" + aim + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
