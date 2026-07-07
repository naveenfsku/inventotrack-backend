package com.inventotrack.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inventotrack.dto.ApiResponse;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public final class ResponseUtil {

    private ResponseUtil() {
    }

    public static void success(HttpServletResponse response,
                               String message,
                               Object data)
            throws IOException {

        response.setContentType("application/json");

        response.setCharacterEncoding("UTF-8");

        ObjectMapper mapper = JsonUtil.getMapper();

        mapper.writeValue(
                response.getWriter(),
                ApiResponse.success(
                        response.getStatus(),
                        message,
                        data,
                        "")
        );

    }

    public static void error(HttpServletResponse response,
                             String message)
            throws IOException {

        response.setContentType("application/json");

        response.setCharacterEncoding("UTF-8");

        ObjectMapper mapper = JsonUtil.getMapper();

        mapper.writeValue(
                response.getWriter(),
                ApiResponse.failure(
                        response.getStatus(),
                        message,
                        "")
        );

    }
}