package com.route_manager.commands;

import com.route_manager.console.Console;
import com.route_manager.exceptions.NoArgsException;
import com.route_manager.exceptions.RecursionInScriptException;
import com.route_manager.manager.CommandManager;
import com.route_manager.util.InputProvider;
import com.route_manager.util.ScannerInputProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Класс, представляющий консольную команду execute_script {file_name}
 * @author Ivan Kirillov
 */
public final class ExecuteScript extends Command {
    private final CommandManager commandManager;
    private final Console console;

    /**
     * Конструктор класса команды execute_script
     */
    public ExecuteScript(CommandManager commandManager, Console console) {
        super("execute_script {файл}", "Считать и исполнить скрипт из указанного файла");
        this.commandManager = commandManager;
        this.console = console;
    }

    static HashSet<String> scriptStack = new HashSet<>();

    @Override
    public boolean execute(String argument, InputProvider inputProvider) {
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
                    commandManager.executeCommand(line, scriptProvider);
                    console.print("\n");
                }

                console.printSuccess("Скрипт '" + scriptFile.getName() + "' выполнен.");
                return true;

            } catch (FileNotFoundException e) {
                console.printErr("Файл скрипта не найден: " + argument);
                return false;
            } catch (Exception e) {
                console.printErr("Ошибка при выполнении скрипта: " + e.getMessage());
                return false;
            } finally {
                scriptStack.remove(scriptName);
            }
        } catch (NoArgsException | RecursionInScriptException e) {
            console.printErr(e.getMessage());
        }
        return false;
    }
}
