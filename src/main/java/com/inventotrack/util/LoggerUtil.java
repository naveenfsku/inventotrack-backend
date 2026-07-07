package com.inventotrack.util;

import java.util.logging.Logger;

public final class LoggerUtil {

    private LoggerUtil() {
    }

    public static Logger getLogger(Class<?> clazz) {
        return Logger.getLogger(clazz.getName());
    }
}