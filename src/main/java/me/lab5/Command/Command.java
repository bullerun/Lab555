package me.lab5.Command;

import me.lab5.Exception.IncorrectScript;

import java.io.IOException;

public interface Command {
    String getDescription();
    String getName();
    boolean execute(String argument);
}
