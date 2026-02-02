package com.embarkx.challengeapp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/challenges")
public class ChallengeController {

    private final ChallengeService challengeService;

    public ChallengeController(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    // GET ALL
    @GetMapping
    public List<Challenge> getAllChallenges() {

        return challengeService.getAllChallenges();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Challenge> getById(@PathVariable Long id) {

        Challenge challenge =
                challengeService.getChallengeById(id);

        if (challenge == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(challenge);
    }

    // CREATE
    @PostMapping
    public ResponseEntity<String> addChallenge(
            @RequestBody Challenge challenge) {

        boolean added =
                challengeService.addChallenge(challenge);

        if (added) {
            return ResponseEntity.status(201)
                    .body("Challenge added successfully");
        }

        return ResponseEntity.badRequest()
                .body("Invalid challenge data");
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<String> updateChallenge(
            @PathVariable Long id,
            @RequestBody Challenge challenge) {

        boolean updated =
                challengeService.updateChallenge(id, challenge);

        if (updated) {
            return new ResponseEntity<>(
                    "Challenge updated successfully",
                    HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>(
                    "Challenge not found",
                    HttpStatus.NOT_FOUND
            );
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteChallenge(
            @PathVariable Long id) {

        boolean deleted =
                challengeService.deleteChallenge(id);

        if (deleted) {
            return new ResponseEntity<>(
                    "Challenge deleted successfully",
                    HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>(
                    "Challenge not found",
                    HttpStatus.NOT_FOUND
            );
        }
    }

}
