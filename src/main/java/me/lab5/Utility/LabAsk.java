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

    public LabAsk(Scanner scanner) {
        this.scanner = scanner;
    }

    public void setRunMode(RunMode runMode) {
        this.runMode = runMode;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public String nameAsk() throws IncorrectScript {
        String name;
        while (true) {
            try {
                System.out.println("Введите название лабораторной");
                name = scanner.nextLine().trim();
                if (name.equals("")) throw new MustBeNotEmptyException();
                break;
            } catch (MustBeNotEmptyException e) {
                System.out.println("Название не должно быть пустым");
                if (runMode.getMode().equals(RunModeEnum.FILE_MODE)) throw new IncorrectScript();
            } catch (IllegalStateException exception) {
                System.out.println("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return name;
    }

    public Coordinates coordinatesAsk() throws IncorrectScript {
        return new Coordinates(XAsk(), YAsk());
    }

    public float XAsk() throws IncorrectScript {
        float x;
        while (true) {
            try {
                System.out.println("Введите X координату она должна быть больше " + MINIMAL_X_COORDINATES);
                x = Float.parseFloat(scanner.nextLine().trim());
                if (x <= MINIMAL_X_COORDINATES) throw new RangeException();
                break;
            } catch (NumberFormatException e) {
                System.out.println("при вводе не должно быть ничего кроме цифр и '.'");
                if (runMode.getMode().equals(RunModeEnum.FILE_MODE)) throw new IncorrectScript();
            } catch (RangeException e) {
                System.out.println("X должен быть больше -18");
                if (runMode.getMode().equals(RunModeEnum.FILE_MODE)) throw new IncorrectScript();
            }
        }
        return x;
    }

    public Long YAsk() throws IncorrectScript {
        long y;
        while (true) {
            try {
                System.out.println("Введите Y координату");
                y = Long.parseLong(scanner.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("при вводе не должно быть ничего кроме цифр");
                if (runMode.getMode().equals(RunModeEnum.FILE_MODE)) throw new IncorrectScript();
            }
        }
        return y;
    }

    public long minimalPointAsk() throws IncorrectScript {
        long minimalPoint;
        while (true) {
            try {
                System.out.println("Введите минимальный балл он должен быть больше " + MINIMAL_POINT);
                minimalPoint = Long.parseLong(scanner.nextLine().trim());
                if (minimalPoint <= MINIMAL_POINT) throw new RangeException();
                break;
            } catch (NumberFormatException e) {
                System.out.println("при вводе не должно быть ничего кроме цифр");
                if (runMode.getMode().equals(RunModeEnum.FILE_MODE)) throw new IncorrectScript();
            } catch (RangeException e) {
                System.out.println("минимальный балл должен быть больше 0");
                if (runMode.getMode().equals(RunModeEnum.FILE_MODE)) throw new IncorrectScript();
            }
        }
        return minimalPoint;
    }

    public Difficulty difficultyAsk() throws IncorrectScript {
        Difficulty difficulty = null;
        while (true) {
            try {
                System.out.println("Выберите сложность - " + Difficulty.allDifficulty());
                String line = scanner.nextLine().trim().toUpperCase();
                if (line.equals("")) return null;
                difficulty = Difficulty.valueOf(line.toUpperCase());
                break;
            } catch (IllegalArgumentException e) {
                if (runMode.getMode().equals(RunModeEnum.FILE_MODE)) return difficulty;
                System.out.println("нет такой сложности, повторите ввод");
            } catch (NoSuchElementException e) {
                return difficulty;
            }
        }
        return difficulty;
    }

    public Discipline disciplineAsk() {
        while (true) {
            try {
                System.out.println("Вы хотите добавить дисциплину, введите 'yes', если да, no' или 'enter', если нет?");
                String line = scanner.nextLine().trim();
                if (line.equals("") | line.equals("no")) {
                    return null;
                }
                if (line.equals("yes")) {
                    break;
                }
                throw new IllegalArgumentException();
            } catch (IllegalArgumentException e) {
                System.out.println("Неверный ввод");
            }
        }
        try {
            Discipline discipline;
            String nameDiscipline = nameDisciplineAsk();
            int practiceHours = practiceHoursDisciplineAsk();
            discipline = new Discipline(nameDiscipline, practiceHours);
            return discipline;
        } catch (NoSuchElementException | NullPointerException e) {
            return null;
        }
    }

    public String nameDisciplineAsk() {
        String discipline;
        while (true) {
            try {
                System.out.println("Введите название дисциплины");
                discipline = scanner.nextLine().trim();
                if (discipline.equals("")) throw new IllegalArgumentException();;
                return discipline;
            } catch (IllegalArgumentException e) {
                System.out.println("Неверный ввод");
            }
        }
    }

    public int practiceHoursDisciplineAsk() throws NullPointerException {
        int practiceHours;
        while (true) {
            try {
                System.out.println("Введите количество часов");
                practiceHours = Integer.parseInt(scanner.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("при вводе не должно быть ничего кроме цифр");
            } catch (NoSuchElementException e) {
                throw new NullPointerException();
            }
        }
        return practiceHours;
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
