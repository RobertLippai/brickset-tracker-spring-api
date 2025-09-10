package com.robertlippai.brickset_tracker_api.service;

import com.robertlippai.brickset_tracker_api.api.dto.BrickSetSummaryDto;
import com.robertlippai.brickset_tracker_api.api.model.BrickSet;
import com.robertlippai.brickset_tracker_api.api.model.User;
import com.robertlippai.brickset_tracker_api.repository.BrickSetRepository;
import com.robertlippai.brickset_tracker_api.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BrickSetRepository brickSetRepository;

    public UserService(UserRepository userRepository, BrickSetRepository brickSetRepository) {
        this.userRepository = userRepository;
        this.brickSetRepository = brickSetRepository;
    }

    @Transactional(readOnly = true)
    public Set<BrickSetSummaryDto> getUserInventory() {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.getUsername();

        User user = userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("User not found: " + username));

        return user.getOwnedSets().stream().map(BrickSetSummaryDto::fromEntity).collect(Collectors.toSet());
    }

    @Transactional
    public BrickSetSummaryDto addSetToUser(Integer brickSetId) {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.getUsername();

        User user = userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("User not found: " + username));

        BrickSet  brickSet = brickSetRepository.findById(brickSetId)
                .orElseThrow(() -> new EntityNotFoundException("BrickSet not found with id: " + brickSetId));

        user.getOwnedSets().add(brickSet);

        return BrickSetSummaryDto.fromEntity(brickSet);
    }

    @Transactional
    public void removeSetFromUser(Integer setId) {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.getUsername();

        User user = userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("User not found: " + username));

        BrickSet setToRemove = user.getOwnedSets().stream()
                .filter(set -> set.getSid() == setId)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Set with id " + setId + " not found in user's collection."));

        user.getOwnedSets().remove(setToRemove);
    }
}
