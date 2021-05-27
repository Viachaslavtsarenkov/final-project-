package by.tsarenkov.shop.controller;

import by.tsarenkov.shop.controller.impl.GoToMainPage;
import by.tsarenkov.shop.controller.impl.GoToRegistrationPage;
import by.tsarenkov.shop.controller.impl.SaveNewUser;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CommandProvider {

    private Map<CommandName, Command> commands = new HashMap<>();

    public CommandProvider() {
        commands.put(CommandName.REGISTRATION, new GoToMainPage());
        commands.put(CommandName.GOTOMAINPAGE, new GoToMainPage());
        commands.put(CommandName.GOTOREGISTRATIONPAGE, new GoToRegistrationPage());
        commands.put(CommandName.SAVENEWUSER, new SaveNewUser());
    }

    public Command takeCommand(String name) {
        CommandName commandName;

        commandName = CommandName.valueOf(name.toUpperCase());

        return commands.get(commandName);
    }

}
