package com.flatmates.theresistanceclone;

import java.util.List;
import java.util.Locale;

class Game {
    private static String role;
    private static String roomCode;
    private static String playerName;
    private static String allegiance;
    private static String[] leaderOrder;
    private static List<int[]> roundInfo;
    private static int numPlayers;
    private static boolean targeting;
    private static boolean idiotProof;
    private static boolean blindSpies;
    private static boolean spyReveal;
    private static boolean colorBlind;

    public static synchronized String getRole() {
        return role;
    }

    public static synchronized void setRole(String role) {
        Game.role = role;
    }

    public static synchronized String getRoomCode() {
        return roomCode;
    }

    public static synchronized void setRoomCode(String room_code) {
        Game.roomCode = room_code;
    }

    public static synchronized String getAllegiance() {
        return allegiance;
    }

    public static synchronized void setAllegiance(String allegiance) {
        Game.allegiance = allegiance;
    }

    public static synchronized String getPlayerName() {
        return playerName;
    }

    public static synchronized void setPlayerName(String player_name) {
        Game.playerName = player_name;
    }

    public static synchronized String[] getLeaderOrder() {
        return leaderOrder;
    }

    public static synchronized void setLeaderOrder(String[] leader_order) {
        Game.leaderOrder = leader_order;
    }

    public static synchronized List<int[]> getRoundInfo() {
        return roundInfo;
    }

    public static synchronized void setRoundInfo(List<int[]> round_info) {
        Game.roundInfo = round_info;
    }

    public static synchronized int getNumPlayers() {
        return numPlayers;
    }

    public static synchronized void setNumPlayers(int num_players) {
        Game.numPlayers = num_players;
    }

    public static synchronized boolean getTargeting() {
        return targeting;
    }

    public static synchronized void setTargeting(boolean targeting) {
        Game.targeting = targeting;
    }

    public static synchronized boolean getIdiotProof() {
        return idiotProof;
    }

    public static synchronized void setIdiotProof(boolean idiot_proof) {
        Game.idiotProof = idiot_proof;
    }

    public static synchronized boolean getBlindSpied() {
        return blindSpies;
    }

    public static synchronized void setBlindSpies(boolean blind_spies) {
        Game.blindSpies = blind_spies;
    }

    public static synchronized boolean getSpyReveal() {
        return spyReveal;
    }

    public static synchronized void setSpyReveal(boolean spy_reveal) {
        Game.spyReveal = spy_reveal;
    }

    public static synchronized boolean getColorBlind() {
        return colorBlind;
    }

    public static synchronized void setColorBlind(boolean color_blind) {
        Game.colorBlind = color_blind;
    }

    public static String createMessage(String type, String message) {
        return (type + String.format(Locale.US, "%03d", message.length()) + message);
    }
}
