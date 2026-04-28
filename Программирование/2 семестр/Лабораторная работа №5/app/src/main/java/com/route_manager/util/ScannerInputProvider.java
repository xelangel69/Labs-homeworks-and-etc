package com.route_manager.util;

import java.util.Scanner;

public class ScannerInputProvider implements InputProvider {
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
