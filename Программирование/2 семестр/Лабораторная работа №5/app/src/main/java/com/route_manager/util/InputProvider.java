package com.route_manager.util;

public interface InputProvider {
    /**
     * Читает следующую строку из источника.
     */
    String readLine(String prompt);

    boolean isInteractive();
}
