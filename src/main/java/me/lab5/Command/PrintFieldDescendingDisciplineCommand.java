package me.lab5.Command;

import me.lab5.Data.LabWork;
import me.lab5.Exception.MustBeEmptyException;
import me.lab5.Exception.MustBeNotEmptyException;
import me.lab5.Manager.CollectionManager;

import java.util.NavigableSet;

public class PrintFieldDescendingDisciplineCommand extends AbstractCommand {
    CollectionManager collectionManager;

    public PrintFieldDescendingDisciplineCommand(CollectionManager collectionManager) {
        super("print_field_descending_discipline", "выводит значения поля discipline всех элементов в порядке убывания");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new MustBeEmptyException();
            NavigableSet<LabWork> labWork = collectionManager.getLabWork().descendingSet();
            if (labWork.isEmpty()) throw new MustBeNotEmptyException();
            for (LabWork i: labWork) {
                System.out.println(i.getDiscipline());
            }
            return true;
        } catch (MustBeEmptyException e) {
            System.out.println("Команда вводится без аргумента");
            return false;
        } catch (MustBeNotEmptyException e) {
            System.out.println("Коллекция пуста");
            return true;
        }
    }
}