package com.webjournal.Entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class TeacherConverter implements AttributeConverter <List<Map<String, Object>>, String> {

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<Map<String, Object>> teacherInfo) {

        String teacherInfoJson = null;
        try {
            teacherInfoJson = objectMapper.writeValueAsString(teacherInfo);
        } catch (final JsonProcessingException e) {
            e.printStackTrace();
        }

        return teacherInfoJson;
    }

    @Override
    public List<Map<String, Object>> convertToEntityAttribute(String teacherInfoJSON) {

        List<Map<String, Object>> teacherInfo = null;
        try {
            teacherInfo = objectMapper.readValue(teacherInfoJSON, List.class);
        } catch (final IOException e) {
            e.printStackTrace();
        }

        return teacherInfo;
    }

}