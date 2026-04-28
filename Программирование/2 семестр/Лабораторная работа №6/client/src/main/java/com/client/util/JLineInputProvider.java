package com.client.util;

import org.jline.reader.LineReader;

public final class JLineInputProvider implements InputProvider {
    private final LineReader lineReader;

    public JLineInputProvider(LineReader lineReader) {
        this.lineReader = lineReader;
    }

    @Override
    public String readLine(String prompt) {
        return lineReader.readLine(prompt);
    }

    @Override
    public boolean isInteractive() {
        return true;
    }
}
