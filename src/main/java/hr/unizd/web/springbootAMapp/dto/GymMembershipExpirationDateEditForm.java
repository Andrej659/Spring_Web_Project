package hr.unizd.web.springbootAMapp.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class GymMembershipExpirationDateEditForm {

    @NotNull
    private String name;        //this form must have these attributes,so that the page functions normally
    @NotNull                    //@NotNull and @NotEmpty are validators and if they are triggered the error is thrown
    private Integer id;

    @NotEmpty(message = "Gym membership expiration date is not allowed to be empty")
    private String gymMembershipExpirationDate;

    //different getters and setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGymMembershipExpirationDate() {
        return gymMembershipExpirationDate;
    }

    public void setGymMembershipExpirationDate(String gymMembershipExpirationDate) {
        this.gymMembershipExpirationDate = gymMembershipExpirationDate;
    }
}
