package com.mario.memory.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Document(collection = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private String id;
    private String username;
    private String password;
    private String email;

    private List<Integer> completedLevels;
    private Map<Integer, Integer> starsEarned;         // level -> stars
    private Map<Integer, Integer> timeTakenPerLevel;   // level -> timeTaken
}
