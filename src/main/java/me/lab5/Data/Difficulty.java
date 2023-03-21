package me.lab5.Data;

public enum Difficulty {
    VERY_EASY,
    EASY,
    VERY_HARD,
    TERRIBLE;
    public static String allDifficulty(){
        StringBuilder allDifficulty = new StringBuilder();
        for (Difficulty i: Difficulty.values()) {
            allDifficulty.append(i).append(" ");
        }
        return allDifficulty.toString();
    }
}