package me.lab5.Command;

import me.lab5.Data.LabWork;
import me.lab5.Exception.MustBeEmptyException;
import me.lab5.Manager.CollectionManager;

public class AverageOfMinimalPointCommand extends AbstractCommand {
    CollectionManager collectionManager;

    public AverageOfMinimalPointCommand(CollectionManager collectionManager) {
        super("average_of_minimal_point", "выводит среднее значение поля minimalPoint для всех элементов коллекции");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new MustBeEmptyException();
            long SumOfMinimalPoints = 0;
            long count = 0;
            for (LabWork i : collectionManager.getLabWork()) {
                SumOfMinimalPoints += i.getMinimalPoint();
                count += 1;
            }
            System.out.println("среднее значение поля minimalPoint для всех элементов коллекции = " + SumOfMinimalPoints / count);
            return true;
        } catch (MustBeEmptyException e) {
            System.out.println("Команда вводится без аргумента");
            return false;
        }catch (ArithmeticException e){
            System.out.println("Нет лабораторных работ");
            return false;
        }
    }
}