package com.route_manager.manager;

import com.route_manager.console.Console;

import java.io.IOException;

import com.route_manager.exceptions.FailedCommandExecutionException;
import com.route_manager.util.InputProvider;
import com.route_manager.util.JLineInputProvider;
import org.jline.reader.EndOfFileException;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.UserInterruptException;
import org.jline.reader.impl.completer.StringsCompleter;
import org.jline.terminal.Terminal;

/**
 * Мененджер приложения - управляет всем приложением
 * @author Ivan Kirillov
 */
public final class AppManager {
    private final Console console;
    private final CommandManager commandManager;
    private final Terminal terminal;
    
    /**
     * Конструктор мененджера приложения
     */
    public AppManager(Console console, CommandManager commandManager, Terminal terminal) {
        this.console = console;
        this.commandManager = commandManager;
        this.terminal = terminal;
    }

    /**
     * Запускает приложение
     *
     */
    public void run() throws IOException {

        StringsCompleter completer = new StringsCompleter(commandManager.getCommands().keySet());

        LineReader lineReader = LineReaderBuilder.builder()
                .terminal(terminal)
                .completer(completer)
                .build();

        InputProvider consoleProvider = new JLineInputProvider(lineReader);

        while (true) {
            String commandLine;

            try {
                commandLine = lineReader.readLine("> ");
            } catch (UserInterruptException | EndOfFileException e) {
                console.println("\nДо свиндания!");
                return;
            }

            if (commandLine == null || commandLine.trim().isEmpty()) {
                continue;
            }

            try {
                commandManager.executeCommand(commandLine.trim(), consoleProvider);
            } catch (FailedCommandExecutionException e) {
                console.printErr("Ошибка выполнения команды: " + e.getMessage());
            }
        }
    }
}