package com.flatmates.theresistanceclone;

import java.util.List;
import java.util.Locale;

class Game {
    private static String role;
    private static String roomCode;
    private static String playerName;
    private static String allegiance;
    private static String[] leaderOrder;
    private static int leader;
    private static List<int[]> roundInfo;
    private static boolean[] roundCompletion;
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

    public static synchronized void setRoomCode(String roomCode) {
        Game.roomCode = roomCode;
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

    public static synchronized void setPlayerName(String playerName) {
        Game.playerName = playerName;
    }

    public static synchronized String[] getLeaderOrder() {
        return leaderOrder;
    }

    public static synchronized void setLeaderOrder(String[] leaderOrder) {
        Game.leaderOrder = leaderOrder;
    }

    public static synchronized int getLeader() {
        return leader;
    }

    public static synchronized void setLeader(int leader) {
        Game.leader = leader;
    }

    public static synchronized List<int[]> getRoundInfo() {
        return roundInfo;
    }

    public static synchronized void setRoundInfo(List<int[]> roundInfo) {
        Game.roundInfo = roundInfo;
    }

    public static synchronized boolean[] getRoundCompletion() {
        return roundCompletion;
    }

    public static synchronized void setRoundCompletion(boolean[] roundCompletion) {
        Game.roundCompletion = roundCompletion;
    }

    public static synchronized int getNumPlayers() {
        return numPlayers;
    }

    public static synchronized void setNumPlayers(int numPlayers) {
        Game.numPlayers = numPlayers;
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

    public static synchronized void setIdiotProof(boolean idiotProof) {
        Game.idiotProof = idiotProof;
    }

    public static synchronized boolean getBlindSpied() {
        return blindSpies;
    }

    public static synchronized void setBlindSpies(boolean blindSpies) {
        Game.blindSpies = blindSpies;
    }

    public static synchronized boolean getSpyReveal() {
        return spyReveal;
    }

    public static synchronized void setSpyReveal(boolean spyReveal) {
        Game.spyReveal = spyReveal;
    }

    public static synchronized boolean getColorBlind() {
        return colorBlind;
    }

    public static synchronized void setColorBlind(boolean colorBlind) {
        Game.colorBlind = colorBlind;
    }

    public static String createMessage(String type, String message) {
        return (type + String.format(Locale.US, "%03d", message.length()) + message);
    }
}
