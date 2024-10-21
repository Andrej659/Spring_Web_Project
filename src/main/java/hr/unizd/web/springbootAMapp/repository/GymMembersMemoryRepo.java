package hr.unizd.web.springbootAMapp.repository;

import hr.unizd.web.springbootAMapp.model.GymMember;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;


@Repository
public class GymMembersMemoryRepo implements GymMembersRepo{        //this is a repo class, it holds a list of my objects,and other handling methods

    private List<GymMember> gymMemberList = new ArrayList<>(
            List.of(
                    new GymMember(1, "Livijo", "04-11-2000", "11-12-2023"),
                    new GymMember(2, "Lucijan", "04-11-2000", "04-05-2023"),
                    new GymMember(3, "Roko", "28-12-2000", "25-06-2023"),
                    new GymMember(4, "Karlo", "16-07-2000", "01-02-2024")
            )
    );

    @Override
    public List<GymMember> fetchAll() {
        return gymMemberList;
    }       //gets all the members of the list

    @Override
    public Optional<GymMember> fetchDetails(Integer id) {       //get particular member's details
        return gymMemberList.stream()
                .filter(gymMember -> gymMember.getId() == (id))
                .findFirst();
    }

    @Override
    public boolean create(GymMember gymMember) {        //creates a new member
        gymMember.setId(randIdSelector());              //this is used to make sure that none of them have the same id

        return gymMemberList.add(gymMember);
    }

    @Override
    public void edit(GymMember newGymMember) {      //editing member's details
        int listIndex = fetchObjectListIndex(newGymMember.getId());
        gymMemberList.set(listIndex, newGymMember);
    }

    @Override
    public void delete(Integer id) {        //deleting a member
        int listIndex = fetchObjectListIndex(id);
        gymMemberList.remove(listIndex);
    }

    private Integer fetchObjectListIndex(Integer id) {      //getting an index of an object
        int index = -1;

        for (int i = 0; i < gymMemberList.size(); i++) {
            if (gymMemberList.get(i).getId() == id) {
                index = i;
                break;
            }
        }

        return index;
    }

    private int randIdSelector(){                           //gets me a unique id
        List<Integer> validNumbers = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            validNumbers.add(i);
        }
        List<Integer> excludedNumbers = new ArrayList<>();
        for (int i = 0; i < 100;i++){
            for(GymMember gm : gymMemberList){
                if (i == gm.getId()){
                    excludedNumbers.add(gm.getId());
                }
            }
        }

        Random rand = new Random();
        int id = getRandomNumber(validNumbers, excludedNumbers, rand);

        return id;
    }

    //a recursive method that selects between the numbers that are not already someone's id
    private static int getRandomNumber(List<Integer> validNumbers, List<Integer> excludedNumbers, Random rand) {
        if (validNumbers.isEmpty()) {
            throw new IllegalArgumentException("No valid numbers to choose from.");
        }
        if (validNumbers.size() == 1) {
            return validNumbers.get(0);
        }
        int index = rand.nextInt(validNumbers.size());
        int num = validNumbers.get(index);
        if (excludedNumbers.contains(num)) {
            validNumbers.remove(index);
            return getRandomNumber(validNumbers, excludedNumbers, rand);
        }
        return num;
    }
}
