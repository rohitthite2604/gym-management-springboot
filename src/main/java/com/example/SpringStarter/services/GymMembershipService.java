package com.example.SpringStarter.services;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SpringStarter.models.GymMembership;
import com.example.SpringStarter.repositories.GymMembershipRepository;

@Service
public class GymMembershipService {

    private final GymMembershipRepository membershipRepository;

    // @Autowired
    public GymMembershipService(GymMembershipRepository membershipRepository) {
        this.membershipRepository = membershipRepository;
    }

    public void saveMembership(GymMembership membership) {
        membershipRepository.save(membership);
    }
}

