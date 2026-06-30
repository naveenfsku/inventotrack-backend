package com.inventotrack.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inventotrack.dto.ApiResponse;
import com.inventotrack.exception.AuthenticationException;
import com.inventotrack.exception.BusinessException;
import com.inventotrack.exception.ResourceNotFoundException;
import com.inventotrack.exception.ValidationException;
import com.inventotrack.util.JsonUtil;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public abstract class BaseServlet extends HttpServlet {

    protected final ObjectMapper mapper = JsonUtil.getMapper();

    protected <T> T readJson(HttpServletRequest request,
                             Class<T> clazz)
            throws IOException {

        return mapper.readValue(request.getInputStream(), clazz);
    }

    protected void writeSuccess(HttpServletResponse response,
                                String message,
                                Object data)
            throws IOException {

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");

        mapper.writeValue(
                response.getWriter(),
                ApiResponse.success(message, data)
        );
    }

    protected void handleException(
            HttpServletResponse response,
            Exception exception)
            throws IOException {

        int status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;

        if (exception instanceof ValidationException) {
            status = HttpServletResponse.SC_BAD_REQUEST;
        } else if (exception instanceof AuthenticationException) {
            status = HttpServletResponse.SC_UNAUTHORIZED;
        } else if (exception instanceof ResourceNotFoundException) {
            status = HttpServletResponse.SC_NOT_FOUND;
        } else if (exception instanceof BusinessException) {
            status = HttpServletResponse.SC_BAD_REQUEST;
        }

        response.setStatus(status);
        response.setContentType("application/json");

        mapper.writeValue(
                response.getWriter(),
                ApiResponse.failure(exception.getMessage())
        );
    }
}