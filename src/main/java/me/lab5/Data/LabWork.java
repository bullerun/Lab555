package me.lab5.Data;

import me.lab5.Exception.MustBeNotEmptyException;
import me.lab5.Exception.RangeException;

import java.time.LocalDate;

public class LabWork implements Comparable<LabWork> {
    private final Long MINIMAL_POINT = 0L;
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private long minimalPoint; //Значение поля должно быть больше 0
    private Difficulty difficulty; //Поле может быть null
    private Discipline discipline; //Поле может быть null

    public LabWork(Long id) {
        this.id = id;
        this.coordinates = new Coordinates();
        this.discipline = new Discipline();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public long getMinimalPoint() {
        return minimalPoint;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setName(String name) throws MustBeNotEmptyException {
        if (name.equals("")) throw new MustBeNotEmptyException();
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setMinimalPoint(long minimalPoint) throws RangeException {
        if (minimalPoint <= MINIMAL_POINT) throw new RangeException();
        this.minimalPoint = minimalPoint;
    }

    public void setDifficulty(String difficulty) throws IllegalArgumentException{
        if (difficulty.equals("")) this.difficulty = null;
        else {
            this.difficulty = Difficulty.valueOf(difficulty);
        }
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    @Override
    public String toString() {
        return "id=" + id +
                " имя=" + name +
                " координаты=" + coordinates +
                " дата создания=" + creationDate +
                " минимальный балл=" + minimalPoint +
                " трудность=" + difficulty +
                " дисциплина=" + discipline;
    }

    @Override
    public int compareTo(LabWork other) {
        return id.compareTo(other.getId());
    }
}
