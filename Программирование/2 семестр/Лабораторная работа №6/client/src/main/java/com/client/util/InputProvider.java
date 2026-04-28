package com.client.util;

public interface InputProvider {
    /**
     * Читает следующую строку из источника.
     */
    String readLine(String prompt);

    boolean isInteractive();
}
