package com.example.demo;

public class GameResponse {

    private String game_id;
    private String game_name;
    private String story;

    public GameResponse(String game_id, String game_name, String story) {
        this.game_id = game_id;
        this.game_name = game_name;
        this.story = story;
    }

    public String getGame_id() {
        return game_id;
    }

    public String getGame_name() {
        return game_name;
    }

    public String getStory() {
        return story;
    }
}