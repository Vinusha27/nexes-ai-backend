package com.example.demo;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GameController {

    private final GameRepository gameRepository;

    public GameController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @PostMapping("/create_game")
    public ResponseEntity<?> createGame(@RequestBody GameRequest request) {
        try {
            String idea = (request.getIdea() != null) ? request.getIdea().toLowerCase() : "default";
            String name = (request.getName() != null) ? request.getName() : "New Game";

            // AI Genre Detection
            String genre = "RPG"; 
            if(idea.contains("fight") || idea.contains("ninja")) genre = "Fighting";
            if(idea.contains("car") || idea.contains("race")) genre = "Racing";

            // Asset & Code Generation
            String characterUrl = "https://api.dicebear.com/7.x/pixel-art/svg?seed=" + UUID.randomUUID();
            String story = "NEXES AI: In a world of " + idea + ", a legend is born.";
            String tutorial = "Step 1: Import code. Step 2: Set assets.";
            
            String generatedCode = "";
            if(genre.equals("Fighting")) {
                generatedCode = "// NEXES FIGHTING ENGINE\nconst player = { hp: 100 };\nfunction attack() { return 'Strike!'; }";
            } else if(genre.equals("Racing")) {
                generatedCode = "// NEXES RACING ENGINE\nconst car = { speed: 0 };\nfunction gas() { car.speed += 10; }";
            } else {
                generatedCode = "// NEXES RPG ENGINE\nconst hero = { lvl: 1 };\nfunction talk() { return 'Hello!'; }";
            }

            // Creating the Game object with 7 parameters
            Game newGame = new Game();
            newGame.setName(name);
            newGame.setIdea(idea);
            newGame.setGenre(genre);
            newGame.setCharacterUrl(characterUrl);
            newGame.setStory(story);
            newGame.setTutorial(tutorial);
            newGame.setGeneratedCode(generatedCode);

            Game savedGame = gameRepository.save(newGame);
            return ResponseEntity.ok(savedGame);

        } catch (Exception e) {
            // Error-ah message-ah anuppuvom
            return ResponseEntity.status(500).body("Backend Logic Error: " + e.getMessage());
        }
    }

    @GetMapping("/all_games")
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }
}