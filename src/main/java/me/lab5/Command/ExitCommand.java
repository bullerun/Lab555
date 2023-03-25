package me.lab5.Command;

import me.lab5.Exception.MustBeEmptyException;
/**
 * commands the shutdown
 *
 * @author Nikita and Vlad
 * @version 0.1
 */
public class ExitCommand extends AbstractCommand{
    public ExitCommand(){
        super("exit", "завершает программу (без сохранения в файл)");
    }
    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new MustBeEmptyException();
            System.exit(0);
            return true;
        }catch (MustBeEmptyException e) {
            System.out.println("Команда вводится без аргумента");
            return false;
        }
    }
}
