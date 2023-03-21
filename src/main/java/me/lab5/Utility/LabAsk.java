package me.lab5.Utility;

import me.lab5.Data.*;
import me.lab5.Exception.*;
import me.lab5.Run.RunMode;
import me.lab5.Run.RunModeEnum;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class LabAsk {
    private final Long MINIMAL_POINT = 0L;
    private final float MINIMAL_X_COORDINATES = -18;
    private Scanner scanner;
    private RunMode runMode;
    private LabWork labWork;

    public LabAsk(Scanner scanner) {
        this.scanner = scanner;
    }

    public void setRunMode(RunMode runMode) {
        this.runMode = runMode;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public void setLabWork(LabWork labWork) {
        this.labWork = labWork;
    }

    public LabWork addLabWork(long id) throws IncorrectScript {
        this.labWork = new LabWork(id);
        nameAsk();
        coordinatesAsk();
        minimalPointAsk();
        difficultyAsk();
        disciplineAsk();
        return labWork;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void nameAsk() throws IncorrectScript {
        while (true) {
            try {
                System.out.println("Введите название лабораторной");
                labWork.setName(scanner.nextLine().trim());
                break;
            } catch (MustBeNotEmptyException e) {
                System.out.println("Название не должно быть пустым");
                if (runMode.getMode().equals(RunModeEnum.FILE_MODE)) throw new IncorrectScript();
            }
        }
    }

    public void coordinatesAsk() throws IncorrectScript {
        XAsk(labWork.getCoordinates());
        YAsk(labWork.getCoordinates());
    }

    public void XAsk(Coordinates coordinates) throws IncorrectScript {
        while (true) {
            try {
                System.out.println("Введите X координату она должна быть больше " + MINIMAL_X_COORDINATES);
                coordinates.setX(Float.parseFloat(scanner.nextLine().trim()));
                break;
            } catch (NumberFormatException e) {
                System.out.println("при вводе не должно быть ничего кроме цифр и '.'");
                if (runMode.getMode().equals(RunModeEnum.FILE_MODE)) throw new IncorrectScript();
            } catch (RangeException e) {
                System.out.println("X должен быть больше -18");
                if (runMode.getMode().equals(RunModeEnum.FILE_MODE)) throw new IncorrectScript();
            } catch (IllegalArgumentException e) {
                System.out.println("Слишком большое число");
                if (runMode.getMode().equals(RunModeEnum.FILE_MODE)) throw new IncorrectScript();
            }
        }
    }

    public void YAsk(Coordinates coordinates) throws IncorrectScript {
        while (true) {
            try {
                System.out.println("Введите Y координату");
                coordinates.setY(Long.parseLong(scanner.nextLine().trim()));
                break;
            } catch (NumberFormatException e) {
                System.out.println("некорректно введено число, число должно содержать только цифры и должно быть меньше или равно " + Long.MAX_VALUE);
                if (runMode.getMode().equals(RunModeEnum.FILE_MODE)) throw new IncorrectScript();
            }
        }
    }

    public void minimalPointAsk() throws IncorrectScript {
        while (true) {
            try {
                System.out.println("Введите минимальный балл он должен быть больше " + MINIMAL_POINT);
                labWork.setMinimalPoint(Long.parseLong(scanner.nextLine().trim()));
                break;
            } catch (NumberFormatException e) {
                System.out.println("некорректно введено число, число должно содержать только цифры и должно быть меньше или равно " + Long.MAX_VALUE);
                if (runMode.getMode().equals(RunModeEnum.FILE_MODE)) throw new IncorrectScript();
            } catch (RangeException e) {
                System.out.println("минимальный балл должен быть больше 0");
                if (runMode.getMode().equals(RunModeEnum.FILE_MODE)) throw new IncorrectScript();
            }
        }
    }

    public void difficultyAsk() throws IncorrectScript {
        while (true) {
            try {
                System.out.println("Выберите сложность - " + Difficulty.allDifficulty());
                labWork.setDifficulty(scanner.nextLine().trim().toUpperCase());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("нет такой сложности, повторите ввод");
            }
        }
    }

    public void disciplineAsk() {
        while (true) {
            try {
                System.out.println("Вы хотите добавить дисциплину, введите 'yes', если да, no' или 'enter', если нет?");
                String line = scanner.nextLine().trim();
                if (line.equals("") | line.equals("no")) {
                    labWork.setDiscipline(null);
                }
                if (line.equals("yes")) {
                    break;
                }
                throw new IllegalArgumentException();
            } catch (IllegalArgumentException e) {
                System.out.println("Неверный ввод");
            }
        }
        nameDisciplineAsk(labWork.getDiscipline());
        practiceHoursDisciplineAsk(labWork.getDiscipline());

    }

    public void nameDisciplineAsk(Discipline discipline) {
        while (true) {
            try {
                System.out.println("Введите название дисциплины");
                discipline.setName(scanner.nextLine().trim());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Неверный ввод");
            }
        }
    }

    public void practiceHoursDisciplineAsk(Discipline discipline) {
        while (true) {
            try {
                System.out.println("Введите количество часов");
                discipline.setPracticeHours(Integer.parseInt(scanner.nextLine().trim()));
                break;
            } catch (NumberFormatException e) {
                System.out.println("некорректно введено число, число должно содержать только цифры и должно быть меньше или равно " + Integer.MAX_VALUE);
            }
        }
    }

    public boolean updateById(String ask) throws IncorrectScript {
        while (true) {
            try {
                String answer;
                System.out.println(ask);
                System.out.println("Если да, введите 'yes', если нет, то введите 'no' или нажмите 'enter'");
                answer = scanner.nextLine().trim().toLowerCase();
                if (answer.equals("yes")) return true;
                if (answer.equals("") | answer.equals("no")) return false;
                throw new IllegalArgumentException();
            } catch (IllegalArgumentException e) {
                System.out.println("Неверный ввод, введите только 'yes', если хотите сделать изменения, или 'no', если не хотите");
                if (runMode.getMode().equals(RunModeEnum.FILE_MODE)) throw new IncorrectScript();
            }
        }
    }
}
