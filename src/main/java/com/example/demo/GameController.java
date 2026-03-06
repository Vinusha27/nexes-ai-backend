package com.example.demo;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "https://meek-sprinkles-4bafe6.netlify.app") // Indha line-ah mattum add pannunga
public class GameController {

    private final GameRepository gameRepository;

    public GameController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @PostMapping("/create_game")
    public Game createGame(@RequestBody GameRequest request) {
        String idea = request.getIdea().toLowerCase();

        // AI Genre Detection
        String genre = "RPG"; 
        if(idea.contains("fight") || idea.contains("ninja")) genre = "Fighting";
        if(idea.contains("car") || idea.contains("race")) genre = "Racing";

        // Asset & Narrative Generation
        String characterUrl = "https://api.dicebear.com/7.x/pixel-art/svg?seed=" + UUID.randomUUID();
        String story = "AI AGENT: In a world of " + idea + ", a new legend is born.";
        String tutorial = "Step 1: Import generated code. Step 2: Set assets.";

        // Engine Code Generation
        String generatedCode = "";
        if(genre.equals("Fighting")) {
            generatedCode = "// NEXES FIGHTING ENGINE\nconst player = { hp: 100 };\nfunction attack() { return 'Strike!'; }";
        } else if(genre.equals("Racing")) {
            generatedCode = "// NEXES RACING ENGINE\nconst car = { speed: 0 };\nfunction gas() { car.speed += 10; }";
        } else {
            generatedCode = "// NEXES RPG ENGINE\nconst hero = { lvl: 1 };\nfunction talk() { return 'Hello!'; }";
        }

        // Save with 7 parameters
        Game newGame = new Game(request.getName(), request.getIdea(), genre, characterUrl, story, tutorial, generatedCode);
        return gameRepository.save(newGame);
    }

    @GetMapping("/all_games")
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    @GetMapping("/test")
    public String test() {
        return "Backend is Pakka! 🦾";
    }
}