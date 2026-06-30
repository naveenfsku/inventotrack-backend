package com.inventotrack.util;

import com.inventotrack.response.ApiResponse;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public final class ResponseUtil {

    private ResponseUtil() {
    }

    public static void success(HttpServletResponse response,
                               String message,
                               Object data)
            throws IOException {

        ApiResponse<Object> apiResponse =
                new ApiResponse<>(true, message, data);

        JsonUtil.writeJson(response, apiResponse);
    }

    public static void error(HttpServletResponse response,
                             String message)
            throws IOException {

        ApiResponse<Object> apiResponse =
                new ApiResponse<>(false, message, null);

        JsonUtil.writeJson(response, apiResponse);
    }
}