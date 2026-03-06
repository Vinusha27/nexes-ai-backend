package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;

@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    
    @Column(columnDefinition = "TEXT")
    private String idea;
    
    private String genre;
    private String characterUrl;
    
    @Column(length = 1500)
    private String story;
    
    @Column(length = 1500)
    private String tutorial;
    
    @Column(columnDefinition = "TEXT")
    private String generatedCode;

    // Default Constructor (Required by JPA)
    public Game() {}

    // Constructor with parameters
    public Game(String name, String idea, String genre, String characterUrl, String story, String tutorial, String generatedCode) {
        this.name = name;
        this.idea = idea;
        this.genre = genre;
        this.characterUrl = characterUrl;
        this.story = story;
        this.tutorial = tutorial;
        this.generatedCode = generatedCode;
    }

    // GETTERS AND SETTERS
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getIdea() { return idea; }
    public void setIdea(String idea) { this.idea = idea; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public String getCharacterUrl() { return characterUrl; }
    public void setCharacterUrl(String characterUrl) { this.characterUrl = characterUrl; }

    public String getStory() { return story; }
    public void setStory(String story) { this.story = story; }

    public String getTutorial() { return tutorial; }
    public void setTutorial(String tutorial) { this.tutorial = tutorial; }

    public String getGeneratedCode() { return generatedCode; }
    public void setGeneratedCode(String generatedCode) { this.generatedCode = generatedCode; }
}