package com.client.util;

import java.util.Scanner;

public final class ScannerInputProvider implements InputProvider {
    private final Scanner scanner;

    public ScannerInputProvider(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String readLine(String prompt) {
        return scanner.nextLine();
    }

    @Override
    public boolean isInteractive() {
        return false;
    }
}
