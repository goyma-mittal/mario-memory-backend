package com.mario.memory.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document(collection = "levels")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Level {

    @Id
    private String id; // MongoDB auto-generates this field
    private int levelNumber; // Level number (e.g., 1, 2, 3, etc.)
    private int gridSize; // Grid size for the memory game (e.g., 2x2, 4x4, etc.)
    private int timeLimitSeconds; // Tim e limit for the level in seconds
    private Map<Integer, Integer> starThresholds; // Map of stars -> time thresholds (e.g., {1: 60, 2: 45, 3: 30})
    private boolean isLocked; // Whether the level is locked (locked = true by default for new levels)

}
