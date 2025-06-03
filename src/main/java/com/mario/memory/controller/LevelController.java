package com.mario.memory.controller;

import com.mario.memory.model.Level;
import com.mario.memory.service.LevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/levels")
@RequiredArgsConstructor
public class LevelController {

    private final LevelService levelService;

    // Get all levels (for level selection)
    @GetMapping
    public ResponseEntity<List<Level>> getAllLevels() {
        List<Level> levels = levelService.getAllLevels();
        return ResponseEntity.ok(levels);
    }

    // Get details for a specific level
    @GetMapping("/id/{id}")
    public ResponseEntity<Level> getLevelById(@PathVariable String id) {
        Optional<Level> level = levelService.getLevelById(id);
        return level.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    
    @GetMapping("/number/{levelNumber}")
    public ResponseEntity<Level> getLevelByNumber(@PathVariable int levelNumber) {
        return levelService.getLevelByLevelNumber(levelNumber)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    // Add a new level (admin use)
    @PostMapping
    public ResponseEntity<Level> addNewLevel(@RequestBody Level level) {
        Level createdLevel = levelService.addNewLevel(level);
        return new ResponseEntity<>(createdLevel, HttpStatus.CREATED);
    }

    // Lock or unlock a level (admin use)
    @PutMapping("/{id}/lock")
    public ResponseEntity<Level> lockUnlockLevel(@PathVariable String id, @RequestParam boolean isLocked) {
        Level updatedLevel = levelService.lockUnlockLevel(id, isLocked);
        return ResponseEntity.ok(updatedLevel);
    }
}
