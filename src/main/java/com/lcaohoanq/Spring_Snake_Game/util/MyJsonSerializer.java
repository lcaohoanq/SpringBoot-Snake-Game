package com.lcaohoanq.Spring_Snake_Game.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import com.lcaohoanq.Spring_Snake_Game.entity.Gender;
import com.lcaohoanq.Spring_Snake_Game.entity.Role;
import com.lcaohoanq.Spring_Snake_Game.entity.Status;
import java.io.IOException;

public class MyJsonSerializer {

    public static class RoleSerializer extends JsonSerializer<Role> {

        @Override
        public void serialize(Role role, JsonGenerator jsonGenerator,
            SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeString(role.getRoleName().name());
        }
    }

    public static class GenderSerializer extends JsonSerializer<Gender> {

        @Override
        public void serialize(Gender gender, JsonGenerator jsonGenerator,
            SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeString(gender.getGenderName().name());
        }
    }

    public static class StatusSerializer extends JsonSerializer<Status> {

        @Override
        public void serialize(Status status, JsonGenerator jsonGenerator,
            SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeString(status.getStatusName().name());
        }
    }
}

