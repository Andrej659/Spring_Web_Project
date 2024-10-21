package hr.unizd.web.springbootAMapp.controller;

import hr.unizd.web.springbootAMapp.dto.GymMemberCreateForm;
import hr.unizd.web.springbootAMapp.dto.GymMemberEditForm;
import hr.unizd.web.springbootAMapp.dto.GymMembershipExpirationDateEditForm;
import hr.unizd.web.springbootAMapp.model.GymMember;
import hr.unizd.web.springbootAMapp.passwordCheck.Password;
import hr.unizd.web.springbootAMapp.passwordCheck.PasswordCheckForm;
import hr.unizd.web.springbootAMapp.service.AppService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppController {    //this is a controller class,it's main purpose is to open different templates and to do post methods

    private final AppService appService;    //initiating a service for access to the database,in this case GymMembersMemoryRepo

    @Autowired
    public AppController(AppService appService) {
        this.appService = appService;
    }   //constructor

    @GetMapping("gymMembers")       //when a certain link is triggered it activates this method
    public String fetchAll(Model model) {
        model.addAttribute("gymMemberList", appService.fetchAll());

        return "gymMemberListTemplate";
    }

    @GetMapping("/gymMembers/{id}")     //when a certain link is triggered it activates this method
    public String fetchDetails(Model model, @PathVariable Integer id) {
        GymMember gymMember = appService.fetchDetails(id);
        model.addAttribute("gymMemberDetails", gymMember);

        return "gymMemberTemplate";
    }

    @GetMapping("gymMembers/create")        //when a certain link is triggered it activates this method
    public String fetchCreateForm(Model model) {
        model.addAttribute("gymMember", new GymMember());
        return "gymMemberCreateTemplate";
    }

    @GetMapping("/")        //when a certain link is triggered it activates this method
    public String index(Model model) {
        model.addAttribute("password", new Password());
        return "index";
    }

    @GetMapping("/gymMembers/changePaidStatus/{id}")        //when a certain link is triggered it activates this method
    public String changePaidStatus(@PathVariable Integer id) {
        appService.changePaidStatus(id);
        return "redirect:/gymMembers";
    }

    @GetMapping("/gymMembers/gymMembershipExpirationDateEdit/{id}")     //when a certain link is triggered it activates this method
    public String gymMembershipExpirationDateEdit(Model model, @PathVariable Integer id) {
        GymMember gymMember = appService.fetchDetails(id);
        model.addAttribute("gymMember", gymMember);

        return "gymMembershipExpirationDateEdit";
    }

    @GetMapping("gymMembers/edit/{id}")     //when a certain link is triggered it activates this method
    public String fetchEditForm(Model model, @PathVariable Integer id) {
        GymMember gymMember = appService.fetchDetails(id);
        model.addAttribute("gymMember", gymMember);

        return "gymMemberEditTemplate";
    }

    @GetMapping("/gymMembers/delete/{id}")      //when a certain link is triggered it activates this method
    public String delete(@PathVariable Integer id) {
        appService.delete(id);

        return "redirect:/gymMembers";
    }

    @PostMapping("/gymMembers")     //when an action in html file is triggered, this method is activated
    public String create(@ModelAttribute(name = "gymMember") @Valid GymMemberCreateForm gymMember, BindingResult validationResult) {
        if (validationResult.hasErrors()) {     //validator validate if there are any errors in the forms
            return "gymMemberCreateTemplate";
        }

        appService.create(gymMember);       //creating a GymMember

        return "redirect:/gymMembers";
    }

    @PostMapping("/gymMembers/edit")     //when an action in html file is triggered, this method is activated
    public String edit(@ModelAttribute(name="gymMember") @Valid GymMemberEditForm gymMember, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            return "gymMemberEditTemplate";
        }

        appService.edit(gymMember);     //editing GymMember's data

        return "redirect:/gymMembers";
    }

    @PostMapping("/checkPassword")     //when an action in html file is triggered, this method is activated
    public String checkPassword(@ModelAttribute("password") @Valid PasswordCheckForm pcf, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            return "index";
        }

        if(appService.check(pcf,"admin").equals("false")){      //checks for a correct password through a form
            return "index";
        }

        return "redirect:/gymMembers";
    }

    @PostMapping("/gymMembers/changeGymMembershipExpirationDate")     //when an action in html file is triggered, this method is activated
    public String changeGymMembershipExpirationDate(@ModelAttribute(name="gymMember") @Valid GymMembershipExpirationDateEditForm gmedef, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            return "gymMembershipExpirationDateEdit";
        }

        appService.editMembershipExpirationDate(gmedef);        //edits Membership expiration date through a form

        return "redirect:/gymMembers";
    }
}
