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

    // 1. Home Check (Direct URL open panna idhu work aagum)
    @GetMapping("/")
    public String home() {
        return "Welcome to NEXES AI Backend! Use /api/all_games to see data.";
    }

    // 2. Test Endpoint (Neenga ketta link)
    @GetMapping("/test")
    public String test() {
        return "Backend is Pakka! 🦾 Server is Live.";
    }

    @PostMapping("/create_game")
    public ResponseEntity<?> createGame(@RequestBody GameRequest request) {
        try {
            String idea = (request.getIdea() != null) ? request.getIdea().toLowerCase() : "default";
            String name = (request.getName() != null) ? request.getName() : "New Game";

            String genre = "RPG"; 
            if(idea.contains("fight") || idea.contains("ninja")) genre = "Fighting";
            if(idea.contains("car") || idea.contains("race")) genre = "Racing";

            String characterUrl = "https://api.dicebear.com/7.x/pixel-art/svg?seed=" + UUID.randomUUID();
            String story = "NEXES AI: In a world of " + idea + ", a legend is born.";
            String tutorial = "Step 1: Import code. Step 2: Set assets.";
            
            String generatedCode = "";
            if(genre.equals("Fighting")) {
                generatedCode = "// NEXES FIGHTING ENGINE\nconst player = { hp: 100 };";
            } else if(genre.equals("Racing")) {
                generatedCode = "// NEXES RACING ENGINE\nconst car = { speed: 0 };";
            } else {
                generatedCode = "// NEXES RPG ENGINE\nconst hero = { lvl: 1 };";
            }

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
            return ResponseEntity.status(500).body("Backend Logic Error: " + e.getMessage());
        }
    }

    @GetMapping("/all_games")
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }
}