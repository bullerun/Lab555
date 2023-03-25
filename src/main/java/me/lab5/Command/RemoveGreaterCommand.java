package me.lab5.Command;

import me.lab5.Data.LabWork;
import me.lab5.Exception.MustBeNotEmptyException;
import me.lab5.Manager.CollectionManager;

/**
 * command that deletes all laboratory works is greater than the entered
 *
 * @author Nikita and Vlad
 * @version 0.1
 */
public class RemoveGreaterCommand extends AbstractCommand {
    CollectionManager collectionManager;

    public RemoveGreaterCommand(CollectionManager collectionManager) {
        super("remove_greater {element}", "удаляет из коллекции все элементы, превышающие заданный");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new MustBeNotEmptyException();
            long removeLabWorksId = Long.parseLong(argument.trim());
            LabWork removeLabWorks = collectionManager.getElementById(removeLabWorksId);
            if (removeLabWorks == null) throw new NullPointerException();
            collectionManager.removeGreater(removeLabWorks);
            System.out.println("Удаления завершены успешно");
            return true;
        } catch (MustBeNotEmptyException e) {
            System.out.println("Id не введен");
            return false;
        } catch (NullPointerException e) {
            System.out.println("Лабораторной работы с таким Id отсутствует");
            return false;
        } catch (NumberFormatException e) {
            System.out.println("Некорректный ввод");
            return false;
        }
    }
}