package SmokeTestSuite.AdditionalUtils;

/**
 * Created by sriznych on 22.11.2016.
 */

public class CustomJobBuilder {

    private String owner_B;
    private String aim_B;
    private String description_B;

    public CustomJobBuilder BuildOwnerName(String owner_B) {
        this.owner_B = owner_B;
        return this;
    }

    public CustomJobBuilder BuildAim_B(String aim_B) {
        this.aim_B = aim_B;
        return this;
    }

    public CustomJobBuilder BuildDescription_B(String description_B) {
        this.description_B = description_B;
        return this;
    }

    public SmokeTestSuite.AdditionalUtils.CustomJob build() {
        SmokeTestSuite.AdditionalUtils.CustomJob customJob = new SmokeTestSuite.AdditionalUtils.CustomJob();
        customJob.setOwner(owner_B);
        customJob.setAim(aim_B);
        customJob.setDescription(description_B);
        return customJob;
    }
}
