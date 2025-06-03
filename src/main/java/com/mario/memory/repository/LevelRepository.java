package com.mario.memory.repository;

import com.mario.memory.model.Level;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelRepository extends MongoRepository<Level, String> {

	  Optional<Level> findById(String id);
	  Optional<Level> findByLevelNumber(int levelNumber);

    // Custom query methods can be added here, if needed
}
