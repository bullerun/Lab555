package me.lab5.Data;

import com.fasterxml.jackson.annotation.JsonProperty;
import me.lab5.Exception.RangeException;

/**
 * an auxiliary class for working with LabWork coordinates
 *
 * @author Nikita and Vlad
 * @version 0.1
 */
public class Coordinates {
    private final float MINIMAL_X_COORDINATES = -18;
    @JsonProperty("X_coordinates")
    private float x; //Значение поля должно быть больше -18
    @JsonProperty("Y_coordinates")
    private Long y; //Поле не может быть null
    public Coordinates(){

    }
    public void setX(float x) throws RangeException, IllegalArgumentException{
        if (x <= MINIMAL_X_COORDINATES) throw new RangeException();
        if (x == Float.POSITIVE_INFINITY) throw new IllegalArgumentException();
        this.x = x;
    }

    public void setY(Long y) throws IllegalArgumentException{
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public Long getY() {
        return y;
    }

    @Override
    public String toString() {
        return x + ", " + y;
    }
}
