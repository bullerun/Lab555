package me.lab5.Data;

public class Discipline {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private int practiceHours;

    public Discipline(String s, int i) {
        name = s;
        practiceHours = i;
    }

    @Override
    public String toString() {
        return name + ", " + practiceHours;
    }
}
