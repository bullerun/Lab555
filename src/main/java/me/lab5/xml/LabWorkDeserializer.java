package me.lab5.xml;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import me.lab5.Data.Coordinates;
import me.lab5.Data.LabWork;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class LabWorkDeserializer extends StdDeserializer<LabWork> {
    public LabWorkDeserializer() {
        super((Class<?>) null);
    }

    public LabWorkDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public LabWork deserialize(JsonParser jp, DeserializationContext ctx) throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);
        try {
            LabWork labWork = new LabWork(Long.parseLong(node.get("id").textValue().trim()));
            labWork.setName(node.get("name").textValue().trim());
            labWork.getCoordinates().setX(Float.parseFloat(node.get("coordinates").get("X_coordinates").textValue().trim()));
            labWork.getCoordinates().setY(Long.parseLong(node.get("coordinates").get("Y_coordinates").textValue().trim()));
            labWork.setCrationDate(LocalDate.parse(node.get("creationDate").textValue().trim()));
            labWork.setMinimalPoint(Long.parseLong(node.get("minimalPoint").textValue().trim()));
            if (node.has("difficulty")) labWork.setDifficulty(node.get("difficulty").textValue().trim());
            if (node.has("difficulty")) {
                labWork.getDiscipline().setName(node.get("discipline").get("name").textValue().trim());
                labWork.getDiscipline().setPracticeHours(Integer.parseInt(node.get("discipline").get("practice_hours").textValue().trim()));
            } else {
                labWork.setDiscipline(null);
            }
            return labWork;
        } catch (Exception e) {
            return null;
        }
    }
}
