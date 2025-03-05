package com.example.SpringStarter.Controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.SpringStarter.models.AttendanceLog;
import com.example.SpringStarter.models.GymMembership;
import com.example.SpringStarter.services.AttendanceLogService;
import com.example.SpringStarter.services.GymMembershipService;

@Controller
public class GymMembershipController {

    private final GymMembershipService membershipService;

    @Autowired
    private AttendanceLogService attendanceLogService;

    // @Autowired
    public GymMembershipController(GymMembershipService membershipService) {
        this.membershipService = membershipService;
    }

    @GetMapping("/membership")
    public String showMembershipForm(Model model) {
        model.addAttribute("membership", new GymMembership());
        return "membership-form";
    }

    @SuppressWarnings("static-access")
    @GetMapping("/members")
    public String viewHomePage(Model model) {
        model.addAttribute("members", membershipService.getAllMemberships());
        return "members";
    }

    @PostMapping("/membership")
    public String submitMembershipForm(GymMembership membership) {
        GymMembership savedMembership = membershipService.saveMembership(membership);
        return "redirect:/membership/success?id=" + savedMembership.getId();
    }

    @GetMapping("/membership/success")
    public String showSuccessPage(@RequestParam("id") Long id, Model model) {
        Optional<GymMembership> membershipOpt = membershipService.getGymMembershipById(id);
        if(membershipOpt.isPresent()){
            GymMembership membership = membershipOpt.get();
        model.addAttribute("id", id);
        model.addAttribute("message", "Welcome, " + membership.getName() + "! Your membership ID is: "+ membership.getId());
        return "success";
    }
    return "redirect:/";
}
    @GetMapping("/members/delete/{id}")
    public String deleteMembership(@PathVariable(value = "id") Long id) {
        membershipService.deleteGymMembership(id);
        return "redirect:/";
    }
    @GetMapping("/members/update/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") Long id, Model model) {
        Optional<GymMembership> membership = membershipService.getGymMembershipById(id);
        model.addAttribute("membership", membership);
        return "update_membership";
    }
    @GetMapping("/attendance")
    public String showValidateForm(Model model) {
        List<AttendanceLog> attendanceLogs = attendanceLogService.getAllLogs();
        model.addAttribute("attendanceLogs", attendanceLogs);
        return "validate";
    }
     @PostMapping("/attendance")
    public String validateMembership(@RequestParam Long id, Model model) {
        Optional<GymMembership> membershipOpt = membershipService.getGymMembershipById(id);
        if(membershipOpt.isPresent()){
            GymMembership membership = membershipOpt.get();
            LocalDate currentDate = LocalDate.now();

            // Create an attendance log
        AttendanceLog log = new AttendanceLog(membership);
        attendanceLogService.save(log);

        // Fetch logs associated with the membership
        List<AttendanceLog> logs = attendanceLogService.findByMembershipId(id);
        model.addAttribute("logs", logs);

        List<AttendanceLog> attendanceLogs = attendanceLogService.getAllLogs();
        model.addAttribute("attendanceLogs", attendanceLogs);

            if (membership.getEndDate().isBefore(currentDate)) {
                model.addAttribute("message", "Membership has expired.");
                model.addAttribute("expired", true);
                model.addAttribute("success", false);
                model.addAttribute("pendingFees", false);
            } else if (membership.getRemaining() > 0) {
                model.addAttribute("message", "Welcome, " + membership.getName() + "! You have remaining amount, Rs."+membership.getRemaining() + ". Please pay the remaining amount.");
                model.addAttribute("expired", false);
                model.addAttribute("success", false);
                model.addAttribute("pendingFees", true);
            } else {
                model.addAttribute("message", "Welcome, " + membership.getName() + "!");
                model.addAttribute("expired", false);
                model.addAttribute("success", true);
                model.addAttribute("pendingFees", false);
            }
        } else {
            model.addAttribute("message", "Invalid membership ID.");
            model.addAttribute("expired", false);
            model.addAttribute("success", false);
            model.addAttribute("pendingFees", false);
        }
        return "validate";
    }
}
