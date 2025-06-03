package com.mario.memory.service;

import com.mario.memory.model.Level;
import com.mario.memory.repository.LevelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LevelService {

    private final LevelRepository levelRepository;

    // Get all levels
    public List<Level> getAllLevels() {
        return levelRepository.findAll();
    }

    // Get a specific level by ID
    public Optional<Level> getLevelById(String id) {
        return levelRepository.findById(id);
    }
    
    public Optional<Level> getLevelByLevelNumber(int levelNumber) {
        return levelRepository.findByLevelNumber(levelNumber);
    }

    // Add a new level
    public Level addNewLevel(Level level) {
        level.setLocked(true); // By default, new levels are locked
        return levelRepository.save(level);
    }

    // Lock or unlock a level
    public Level lockUnlockLevel(String id, boolean isLocked) {
        Level level = levelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Level not found"));
        level.setLocked(isLocked);
        return levelRepository.save(level);
    }
}
