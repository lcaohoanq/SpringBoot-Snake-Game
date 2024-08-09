package com.lcaohoanq.Spring_Snake_Game.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.lcaohoanq.Spring_Snake_Game.entity.Gender;
import com.lcaohoanq.Spring_Snake_Game.entity.Role;
import com.lcaohoanq.Spring_Snake_Game.entity.Status;
import com.lcaohoanq.Spring_Snake_Game.util.MyJsonDeserializer.GenderDeserializer;
import com.lcaohoanq.Spring_Snake_Game.util.MyJsonDeserializer.RoleDeserializer;
import com.lcaohoanq.Spring_Snake_Game.util.MyJsonDeserializer.StatusDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Role.class, new RoleDeserializer());
        module.addDeserializer(Gender.class, new GenderDeserializer());
        module.addDeserializer(Status.class, new StatusDeserializer());
        mapper.registerModule(module);
        return mapper;
    }
}

