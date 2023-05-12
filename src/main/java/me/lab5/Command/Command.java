package me.lab5.Command;

/**
 * interface from which all commands are implemented
 *
 * @author Nikita and Vlad
 * @version 0.1
 */
public interface Command {
    String getDescription();
    String getName();
    boolean execute(String argument);
}
