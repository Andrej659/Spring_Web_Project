package hr.unizd.web.springbootAMapp.service;

import java.util.List;
import hr.unizd.web.springbootAMapp.dto.GymMemberCreateForm;
import hr.unizd.web.springbootAMapp.dto.GymMemberEditForm;
import hr.unizd.web.springbootAMapp.dto.GymMembershipExpirationDateEditForm;
import hr.unizd.web.springbootAMapp.model.GymMember;
import hr.unizd.web.springbootAMapp.passwordCheck.PasswordCheckForm;


public interface AppService {

    List<GymMember> fetchAll();

    GymMember fetchDetails(Integer id);

    List<GymMember> create(GymMemberCreateForm gymMemberCreateForm);

    List<GymMember> edit(GymMemberEditForm gymMemberEditForm);

    List<GymMember> delete(Integer id);

    String check(PasswordCheckForm psf,String str);

    List<GymMember> changePaidStatus(Integer id);

    List<GymMember> editMembershipExpirationDate(GymMembershipExpirationDateEditForm gmedef);

}
