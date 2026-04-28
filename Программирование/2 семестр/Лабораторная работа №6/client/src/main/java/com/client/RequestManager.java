package com.client;

import com.client.asker.RouteAsker;
import com.client.commands.ExecuteScript;
import com.client.console.Console;
import com.client.network.UDPClient;
import com.client.util.InputProvider;
import com.common.network.Request;
import com.common.network.Response;
import com.common.model.Route;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

public final class RequestManager {
    private Console console;
    private UDPClient udpClient;
    private HashSet<String> clientCommands = new HashSet<>();

    public RequestManager(UDPClient udpClient, Console console) throws IOException {
        this.console = console;
        this.udpClient = udpClient;

        clientCommands.add("add");
        clientCommands.add("update");
        clientCommands.add("add_if_max");
        clientCommands.add("remove_lower");
    }

    public void processCommand(String userInput, InputProvider inputProvider) throws IOException, InterruptedException, ClassNotFoundException {
        String[] tokens = userInput.trim().split("\\s", 2);
        String command = tokens[0];
        String argument = (tokens.length > 1) ? tokens[1] : "";

        if (clientCommands.contains(command)) {
            Route route = new RouteAsker(console, inputProvider).builder();
            Request request = new Request(command, argument, route);
            udpClient.sendRequest(request);
        } else if (command.equals("exit")) {
            console.printByProgram("До свидания!");
            System.exit(0);
            return;
        } else if (command.equals("execute_script")) {
            new ExecuteScript(this, console).execute(argument);
            return;
        } else {
            Request request = new Request(command, argument, null);
            udpClient.sendRequest(request);
        }

        Response response = udpClient.receiveResponse();

        switch (response.getRequestStatus()) {
            case ERROR -> console.printErr(response.getTextMessage());
            case SUCCESS -> {
                if (response.getTextMessage() != null) {
                    console.printSuccess(response.getTextMessage());
                }

                if (response.getResult() != null) {
                    if (response.getResult() instanceof List<?> resultList) {
                        resultList.forEach(console::println);
                    } else {
                        console.println(response.getResult());
                    }
                }
            }
        }
    }
}
