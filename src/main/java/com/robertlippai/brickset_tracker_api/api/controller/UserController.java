package com.robertlippai.brickset_tracker_api.api.controller;

import com.robertlippai.brickset_tracker_api.api.dto.set.AddSetToCollectionRequestDto;
import com.robertlippai.brickset_tracker_api.api.dto.set.BrickSetSummaryDto;
import com.robertlippai.brickset_tracker_api.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/me")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/sets")
    public ResponseEntity<Set<BrickSetSummaryDto>> getUserInventory() {
        return ResponseEntity.ok(userService.getUserInventory());
    }

    @PostMapping("/sets")
    public ResponseEntity<BrickSetSummaryDto> addSetToMyInventory(@RequestBody AddSetToCollectionRequestDto requestDto) {
        BrickSetSummaryDto addedSet = userService.addSetToUser(requestDto.getBrickSetId());

        return ResponseEntity.ok(addedSet);
    }

    @DeleteMapping("/sets/{setId}")
    public ResponseEntity<Void> removeSetFromMyInventory(@PathVariable Integer setId) {
        userService.removeSetFromUser(setId);

        return ResponseEntity.noContent().build();
    }
}
