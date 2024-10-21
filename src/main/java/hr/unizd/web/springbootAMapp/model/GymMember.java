package hr.unizd.web.springbootAMapp.model;

public class GymMember {

    private Integer id;
    private String name;
    private String gymMemberDateOfBirth;
    private String gymMembershipExpirationDate;
    private String paidStatus = "False";

    public GymMember() {
    }

    public GymMember(int id, String name, String gymMemberDateOfBirth, String gymMembershipExpirationDate) {
        this.id = id;
        this.name = name;
        this.gymMemberDateOfBirth = gymMemberDateOfBirth;
        this.gymMembershipExpirationDate = gymMembershipExpirationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getPaidStatus() {
        return paidStatus;
    }

    public void setPaidStatus(String paidStatus) {
        this.paidStatus = paidStatus;
    }
}
