package me.lab5.Command;

import me.lab5.Data.LabWork;
import me.lab5.Exception.IncorrectScript;
import me.lab5.Exception.MustBeNotEmptyException;
import me.lab5.Manager.CollectionManager;
import me.lab5.Utility.LabAsk;

public class UpdateByIdCommand extends AbstractCommand {
    CollectionManager collectionManager;
    LabAsk labAsk;

    public UpdateByIdCommand(CollectionManager collectionManager, LabAsk labAsk) {
        super("update id {element}", "Обновляет значение элемента коллекции, id которого равен заданному");
        this.collectionManager = collectionManager;
        this.labAsk = labAsk;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new MustBeNotEmptyException();
            long updateLabWorkId = Long.parseLong(argument.trim());
            LabWork updateLabWork = collectionManager.getElementById(updateLabWorkId);
            if(updateLabWork == null) throw new NullPointerException();
            if (labAsk.updateById("Хотите изменить имя?")) updateLabWork.setName(labAsk.nameAsk());
            if (labAsk.updateById("Хотите изменить координаты?")) updateLabWork.setCoordinates(labAsk.coordinatesAsk());
            if (labAsk.updateById("Хотите изменить минимальный балл?")) updateLabWork.setMinimalPoint(labAsk.minimalPointAsk());
            if (labAsk.updateById("Хотите изменить сложность?")) updateLabWork.setDifficulty(labAsk.difficultyAsk());
            if (labAsk.updateById("Хотите изменить дисциплину?")) updateLabWork.setDiscipline(labAsk.disciplineAsk());
            return true;
        } catch (MustBeNotEmptyException e){
            System.out.println("Id не введен");
            return false;
        } catch (NullPointerException e){
            System.out.println("Лабораторной работы с таким Id отсутствует");
            return false;
        } catch (IncorrectScript e) {
            return false;
        }
    }
}