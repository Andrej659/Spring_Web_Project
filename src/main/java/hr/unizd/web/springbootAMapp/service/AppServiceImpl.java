package hr.unizd.web.springbootAMapp.service;

import hr.unizd.web.springbootAMapp.dto.GymMemberCreateForm;
import hr.unizd.web.springbootAMapp.dto.GymMemberEditForm;
import hr.unizd.web.springbootAMapp.dto.GymMembershipExpirationDateEditForm;
import hr.unizd.web.springbootAMapp.exception.EntityNotFoundException;
import hr.unizd.web.springbootAMapp.model.GymMember;
import hr.unizd.web.springbootAMapp.passwordCheck.PasswordCheckForm;
import hr.unizd.web.springbootAMapp.repository.GymMembersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.List;
import java.util.Optional;

@Service
public class AppServiceImpl implements AppService{          // this is a service class, it manages the data that goes into or out of the repo

    private final GymMembersRepo gymMembersRepo;

    @Autowired
    public AppServiceImpl(GymMembersRepo gymMembersRepo) {
        this.gymMembersRepo = gymMembersRepo;
    }

    @Override
    public List<GymMember> fetchAll() {
        return gymMembersRepo.fetchAll();
    }       // gets all gym members from the repo

    @Override
    public GymMember fetchDetails(Integer id) {         // gets the details of selected member
        Optional<GymMember> gymMemberOptional = gymMembersRepo.fetchDetails(id);
        if (gymMemberOptional.isEmpty()) {
            throw new EntityNotFoundException("Entity with id " + id + " not found!");
        }
        return gymMemberOptional.get();
    }

    @Override
    public List<GymMember> create(GymMemberCreateForm gymMemberCreateForm) {        // creates a new member based of a form
        Random rand = new Random();

        GymMember gymMember = new GymMember();
        gymMember.setId(rand.nextInt(0,100));
        gymMember.setName(gymMemberCreateForm.getName());
        gymMember.setGymMemberDateOfBirth(gymMemberCreateForm.getGymMemberDateOfBirth());
        gymMember.setGymMembershipExpirationDate(gymMemberCreateForm.getGymMembershipExpirationDate());
        gymMembersRepo.create(gymMember);

        return gymMembersRepo.fetchAll();
    }

    @Override
    public List<GymMember> edit(GymMemberEditForm gymMemberEditForm) {      // edits a members detail based of a form
        GymMember gymMember = fetchDetails(gymMemberEditForm.getId());
        gymMember.setName(gymMemberEditForm.getName());
        gymMember.setGymMemberDateOfBirth(gymMemberEditForm.getGymMemberDateOfBirth());

        gymMembersRepo.edit(gymMember);

        return gymMembersRepo.fetchAll();
    }

    @Override
    public List<GymMember> delete(Integer id) {         //deletes selected member
        gymMembersRepo.delete(id);
        return gymMembersRepo.fetchAll();
    }

    @Override
    public String check(PasswordCheckForm psf, String str){     //checks if the password is correct

        if (psf.getPassword().equals(str)){
            return "true";
        }
        else{
            return "false";
        }
    }

    @Override
    public List<GymMember> changePaidStatus(Integer id) {       //changes paid status from true to false and vice versa
        for (GymMember gm : gymMembersRepo.fetchAll()){
            if (id.equals(gm.getId())){
                if(gm.getPaidStatus().equals("True")){
                    gm.setPaidStatus("False");
                } else if (gm.getPaidStatus().equals("False")) {
                    gm.setPaidStatus("True");
                }
            }
        }
        return gymMembersRepo.fetchAll();
    }

    //edits members membership expiration date based of a form
    @Override
    public List<GymMember> editMembershipExpirationDate(GymMembershipExpirationDateEditForm gmedef) {
        GymMember gymMember = fetchDetails(gmedef.getId());
        gymMember.setGymMembershipExpirationDate(gmedef.getGymMembershipExpirationDate());

        gymMembersRepo.edit(gymMember);

        return gymMembersRepo.fetchAll();
    }
}
