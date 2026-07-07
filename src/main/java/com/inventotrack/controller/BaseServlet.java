package com.inventotrack.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inventotrack.dto.ApiResponse;
import com.inventotrack.util.ExceptionHandlerUtil;
import com.inventotrack.util.JsonUtil;

import com.inventotrack.util.ResponseUtil;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public abstract class BaseServlet extends HttpServlet {

    protected static final ObjectMapper mapper = JsonUtil.getMapper();

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

        ResponseUtil.success(
                response,
                message,
                data);

    }

    protected void handleException(HttpServletResponse response,
                                   Exception exception)
            throws IOException {

        ExceptionHandlerUtil.handle(exception, response);

    }
}