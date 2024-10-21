package hr.unizd.web.springbootAMapp.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class GymMemberCreateForm {

    //this form must have these attributes,so that the page functions normally
    //@Size and @NotEmpty are validators and if they are triggered the error is thrown
    @Size(min = 3, max = 30, message = "Name should have at least 3 chars")
    private String name;

    @NotEmpty(message = "Date of birth is not allowed to be empty")
    private String gymMemberDateOfBirth;

    @NotEmpty(message = "Gym membership expiration date is not allowed to be empty")
    private String gymMembershipExpirationDate;

    //different getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGymMemberDateOfBirth() {
        return gymMemberDateOfBirth;
    }

    public void setGymMemberDateOfBirth(String gymMemberDateOfBirth) {
        this.gymMemberDateOfBirth = gymMemberDateOfBirth;
    }

    public String getGymMembershipExpirationDate() {
        return gymMembershipExpirationDate;
    }

    public void setGymMembershipExpirationDate(String gymMembershipExpirationDate) {
        this.gymMembershipExpirationDate = gymMembershipExpirationDate;
    }
}
