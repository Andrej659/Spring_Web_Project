package hr.unizd.web.springbootAMapp.repository;

import hr.unizd.web.springbootAMapp.model.GymMember;

import java.util.List;
import java.util.Optional;

public interface GymMembersRepo {

        List<GymMember> fetchAll();

        Optional<GymMember> fetchDetails(Integer id);

        boolean create(GymMember gymMember);

        void edit(GymMember gymMember);

        void delete(Integer id);
}
