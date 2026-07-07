package com.inventotrack.util;

import com.inventotrack.exception.*;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public final class ExceptionHandlerUtil {

    private ExceptionHandlerUtil() {
    }

    public static void handle(Exception e,
                              HttpServletResponse resp)
            throws IOException {

        if (e instanceof ValidationException) {

            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        }

        else if (e instanceof ResourceNotFoundException) {

            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);

        }

        else if (e instanceof DuplicateResourceException) {

            resp.setStatus(HttpServletResponse.SC_CONFLICT);

        }

        else if (e instanceof InsufficientStockException) {

            resp.setStatus(HttpServletResponse.SC_CONFLICT);

        }

        else if (e instanceof BusinessException) {

            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        }

        else {

            resp.setStatus(
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

        }

        ResponseUtil.error(resp, e.getMessage());

    }

}