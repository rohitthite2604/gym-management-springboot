package com.example.SpringStarter.Controller;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.SpringStarter.models.GymMembership;
import com.example.SpringStarter.services.GymMembershipService;

@Controller
public class GymMembershipController {

    private final GymMembershipService membershipService;

    // @Autowired
    public GymMembershipController(GymMembershipService membershipService) {
        this.membershipService = membershipService;
    }

    @GetMapping("/membership")
    public String showMembershipForm(Model model) {
        model.addAttribute("membership", new GymMembership());
        return "membership-form";
    }

    @PostMapping("/membership")
    public String submitMembershipForm(GymMembership membership) {
        membershipService.saveMembership(membership);
        return "redirect:/membership/success";
    }

    @GetMapping("/membership/success")
    public String showSuccessPage() {
        return "success";
    }
}
