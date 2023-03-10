package me.lab5.Command;

public interface Command {
    String getDescription();
    String getName();
    boolean execute(String argument);
}
