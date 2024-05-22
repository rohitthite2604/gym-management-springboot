package com.example.SpringStarter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SpringStarter.models.GymMembership;

public interface GymMembershipRepository extends JpaRepository<GymMembership, Long> {
}

