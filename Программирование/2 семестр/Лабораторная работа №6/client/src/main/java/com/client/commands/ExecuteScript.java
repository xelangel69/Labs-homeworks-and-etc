package com.client.commands;

import com.client.RequestManager;
import com.client.console.Console;
import com.common.exceptions.NoArgsException;
import com.common.exceptions.RecursionInScriptException;
import com.client.util.InputProvider;
import com.client.util.ScannerInputProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Класс, представляющий консольную команду execute_script {file_name}
 * @author Ivan Kirillov
 */
public final class ExecuteScript {
    private final Console console;
    private final RequestManager requestManager;

    /**
     * Конструктор класса команды execute_script
     */
    public ExecuteScript(RequestManager requestManager, Console console) {
        this.requestManager = requestManager;
        this.console = console;
    }

    static HashSet<String> scriptStack = new HashSet<>();

    public void execute(String argument) {
        try {
            if (argument.isEmpty()) throw new NoArgsException("Укажите имя скрипта!");

            File scriptFile = new File(argument);
            String scriptName = scriptFile.getAbsolutePath();

            if (scriptStack.contains(scriptName)) throw new RecursionInScriptException("В скрипте обнаружена рекурсия: " + scriptFile.getName());

            scriptStack.add(scriptName);

            try (Scanner scriptScanner = new Scanner(scriptFile)) {

                InputProvider scriptProvider = new ScannerInputProvider(scriptScanner);

                console.printByProgram("Выполнение скрипта: " + scriptFile.getName());

                while (scriptScanner.hasNextLine()) {
                    String line = scriptScanner.nextLine().trim();

                    if (line.isEmpty()) continue;

                    console.printByProgram("Выполнение команды: '" + line + "'");
                    requestManager.processCommand(line, scriptProvider);
                    console.print("\n");
                }

                console.printSuccess("Скрипт '" + scriptFile.getName() + "' выполнен.");

            } catch (FileNotFoundException e) {
                console.printErr("Файл скрипта не найден: " + argument);
            } catch (Exception e) {
                console.printErr("Ошибка при выполнении скрипта: " + e.getMessage());
            } finally {
                scriptStack.remove(scriptName);
            }
        } catch (NoArgsException | RecursionInScriptException e) {
            console.printErr(e.getMessage());
        }
    }
}
