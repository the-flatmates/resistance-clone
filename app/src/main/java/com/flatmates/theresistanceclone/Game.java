package com.flatmates.theresistanceclone;

import java.util.Locale;

public class Game {
    private static String role;
    private static String room_code;
    private static String player_name;
    private static String[] player_names;
    private static int num_players;
    private static boolean targeting;
    private static boolean idiot_proof;
    private static boolean blind_spies;
    private static boolean spy_reveal;
    private static boolean color_blind;

    public static synchronized String getRole() {
        return role;
    }

    public static synchronized void setRole(String role) {
        Game.role = role;
    }

    public static synchronized String getRoomCode() {
        return room_code;
    }

    public static synchronized void setRoomCode(String room_code) {
        Game.room_code = room_code;
    }

    public static synchronized String getPlayerName() {
        return player_name;
    }

    public static synchronized void setPlayerName(String player_name) {
        Game.player_name = player_name;
    }

    public static synchronized int getNumPlayers() {
        return num_players;
    }

    public static synchronized void setNumPlayers(int num_players) {
        Game.num_players = num_players;
    }

    public static synchronized String[] getPlayerNames() {
        return player_names;
    }

    public static synchronized void setPlayerNames(String[] player_names) {
        Game.player_names = player_names;
    }

    public static synchronized boolean getTargeting() {
        return targeting;
    }

    public static synchronized void setTargeting(boolean targeting) {
        Game.targeting = targeting;
    }

    public static synchronized boolean getIdiotProof() {
        return idiot_proof;
    }

    public static synchronized void setIdiotProof(boolean idiot_proof) {
        Game.idiot_proof = idiot_proof;
    }

    public static synchronized boolean getBlindSpied() {
        return blind_spies;
    }

    public static synchronized void setBlindSpies(boolean blind_spies) {
        Game.blind_spies = blind_spies;
    }

    public static synchronized boolean getSpyReveal() {
        return spy_reveal;
    }

    public static synchronized void setSpyReveal(boolean spy_reveal) {
        Game.spy_reveal = spy_reveal;
    }

    public static synchronized boolean getColorBlind() {
        return color_blind;
    }

    public static synchronized void setColorBlind(boolean color_blindg) {
        Game.color_blind = color_blind;
    }

    public static String createMessage(String type, String message) {
        return (type + String.format(Locale.US, "%03d", message.length()) + message);
    }
}
