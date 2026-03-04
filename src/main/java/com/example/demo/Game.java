package com.example.demo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.UUID;

@Document(collection = "games")
public class Game {
    @Id
    private String id;
    private String name;
    private String idea;
    private String genre;
    private String characterUrl;
    private String story;
    private String tutorial;
    private String generatedCode;

    public Game() {}

    public Game(String name, String idea, String genre, String characterUrl, String story, String tutorial, String generatedCode) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.idea = idea;
        this.genre = genre;
        this.characterUrl = characterUrl;
        this.story = story;
        this.tutorial = tutorial;
        this.generatedCode = generatedCode;
    }

    // Getters to fix "Field not used" warnings
    public String getId() { return id; }
    public String getName() { return name; }
    public String getIdea() { return idea; }
    public String getGenre() { return genre; }
    public String getCharacterUrl() { return characterUrl; }
    public String getStory() { return story; }
    public String getTutorial() { return tutorial; }
    public String getGeneratedCode() { return generatedCode; }
}