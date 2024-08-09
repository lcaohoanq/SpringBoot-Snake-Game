package com.lcaohoanq.Spring_Snake_Game.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import com.lcaohoanq.Spring_Snake_Game.entity.Gender;
import com.lcaohoanq.Spring_Snake_Game.entity.Role;
import com.lcaohoanq.Spring_Snake_Game.entity.Status;
import com.lcaohoanq.Spring_Snake_Game.enums.UserGenderEnum;
import com.lcaohoanq.Spring_Snake_Game.enums.UserRoleEnum;
import com.lcaohoanq.Spring_Snake_Game.enums.UserStatusEnum;
import java.io.IOException;

public class MyJsonDeserializer {
    public static class RoleDeserializer extends JsonDeserializer<Role> {

        @Override
        public Role deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {
            String roleName = jsonParser.getText();
            return new Role(UserRoleEnum.valueOf(roleName));
        }
    }

    public static class GenderDeserializer extends JsonDeserializer<Gender> {

        @Override
        public Gender deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {
            String genderName = jsonParser.getText();
            return new Gender(UserGenderEnum.valueOf(genderName));
        }
    }

    public static class StatusDeserializer extends JsonDeserializer<Status> {

        @Override
        public Status deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {
            String statusName = jsonParser.getText();
            return new Status(UserStatusEnum.valueOf(statusName));
        }
    }
}
