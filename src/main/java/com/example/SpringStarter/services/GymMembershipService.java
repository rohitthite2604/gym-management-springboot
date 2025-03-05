package com.example.SpringStarter.services;

import java.util.List;
import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SpringStarter.models.GymMembership;
import com.example.SpringStarter.repositories.GymMembershipRepository;

@Service
public class GymMembershipService {

    private static GymMembershipRepository membershipRepository;

    // @Autowired
    public GymMembershipService(GymMembershipRepository membershipRepository) {
        GymMembershipService.membershipRepository = membershipRepository;
    }

    public GymMembership saveMembership(GymMembership membership) {
        return membershipRepository.save(membership);
    }

    public static List<GymMembership> getAllMemberships() {
        return membershipRepository.findAll();
    }

    public Optional<GymMembership> getGymMembershipById(Long id) {
        return membershipRepository.findById(id);
    }

    public void deleteGymMembership(Long id) {
        membershipRepository.deleteById(id);
    }
}

