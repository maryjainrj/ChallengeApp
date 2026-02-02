package com.embarkx.challengeapp;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChallengeService {

    private List<Challenge> challenges = new ArrayList<>();
    private Long nextId = 1L;

    // Constructor (initial data)
    public ChallengeService() {

        Challenge challenge1 =
                new Challenge(nextId++, "January", "Learn Java");

        challenges.add(challenge1);
    }

    // Get all
    public List<Challenge> getAllChallenges() {
        return challenges;
    }

    // Get by ID
    public Challenge getChallengeById(Long id) {

        return challenges.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Create
    public boolean addChallenge(Challenge challenge) {

        if (challenge == null) {
            return false;
        }

        challenge.setId(nextId++);
        challenges.add(challenge);

        return true;
    }

    // Update
    public boolean updateChallenge(Long id, Challenge updatedChallenge) {

        for (Challenge c : challenges) {

            if (c.getId().equals(id)) {

                c.setMonth(updatedChallenge.getMonth());
                c.setDescription(updatedChallenge.getDescription());

                return true;
            }
        }

        return false;
    }

    // Delete
    public boolean deleteChallenge(Long id) {

        return challenges.removeIf(c -> c.getId().equals(id));
    }
}
