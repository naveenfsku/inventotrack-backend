package com.inventotrack.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public final class JsonUtil {

    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.registerModule(new JavaTimeModule());
    }

    private JsonUtil() {
    }

    public static ObjectMapper getMapper() {
        return mapper;
    }

    public static <T> T readJson(HttpServletRequest request,
                                 Class<T> clazz)
            throws IOException {

        return mapper.readValue(request.getInputStream(), clazz);
    }

    public static void writeJson(HttpServletResponse response,
                                 Object object)
            throws IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        mapper.writeValue(response.getWriter(), object);
    }
}