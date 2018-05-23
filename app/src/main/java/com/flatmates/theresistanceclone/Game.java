package com.flatmates.theresistanceclone;

import java.util.List;
import java.util.Locale;

class Game {
    private static String role;
    private static String roomCode;
    private static String playerName;
    private static String allegiance;
    private static String[] leaderOrder;
    private static int leader = 0;
    private static List<int[]> missionInfo;
    private static int[] missionResults = {0, 0, 0, 0, 0};
    private static String[] currentTeam;
    private static int mission = 1;
    private static int voteTrack = 1;
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

    public static synchronized List<int[]> getMissionInfo() {
        return missionInfo;
    }

    public static synchronized void setMissionInfo(List<int[]> missionInfo) {
        Game.missionInfo = missionInfo;
    }

    public static synchronized int[] getMissionResults() {
        return missionResults;
    }

    public static synchronized void setMissionResults(int[] roundCompletion) {
        Game.missionResults = missionResults;
    }

    public static synchronized String[] getCurrentTeam() {
        return currentTeam;
    }

    public static void setCurrentTeam(String[] currentTeam) {
        Game.currentTeam = currentTeam;
    }

    public static synchronized int getMission() {
        return mission;
    }

    public static synchronized void setMission(int mission) {
        Game.mission = mission;
    }

    public static synchronized int getVoteTrack() {
        return voteTrack;
    }

    public static synchronized void setVoteTrack(int voteTrack) {
        Game.voteTrack = voteTrack;
    }

    public static synchronized int getNumPlayers() {
        return numPlayers;
    }

    public static synchronized void setNumPlayers(int numPlayers) {
        Game.numPlayers = numPlayers;
    }

    public static synchronized boolean isTargeting() {
        return targeting;
    }

    public static synchronized void setTargeting(boolean targeting) {
        Game.targeting = targeting;
    }

    public static synchronized boolean isIdiotProof() {
        return idiotProof;
    }

    public static synchronized void setIdiotProof(boolean idiotProof) {
        Game.idiotProof = idiotProof;
    }

    public static synchronized boolean isBlindSpies() {
        return blindSpies;
    }

    public static synchronized void setBlindSpies(boolean blindSpies) {
        Game.blindSpies = blindSpies;
    }

    public static synchronized boolean isSpyReveal() {
        return spyReveal;
    }

    public static synchronized void setSpyReveal(boolean spyReveal) {
        Game.spyReveal = spyReveal;
    }

    public static synchronized boolean isColorBlind() {
        return colorBlind;
    }

    public static synchronized void setColorBlind(boolean colorBlind) {
        Game.colorBlind = colorBlind;
    }

    public static String createMessage(String type, String message) {
        return (type + String.format(Locale.US, "%03d", message.length()) + message);
    }
}
