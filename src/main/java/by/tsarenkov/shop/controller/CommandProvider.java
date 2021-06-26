package by.tsarenkov.shop.controller;

import by.tsarenkov.shop.controller.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {

    private Map<CommandName, Command> commands = new HashMap<>();

    public CommandProvider() {
        commands.put(CommandName.REGISTRATION, new GoToMainPage());
        commands.put(CommandName.ACTIVATION, new ActivationAccount());
        commands.put(CommandName.LOGIN, new Login());

        commands.put(CommandName.GOTOMAINPAGE, new GoToMainPage());
        commands.put(CommandName.GOTOREGISTRATIONPAGE, new GoToRegistrationPage());
        commands.put(CommandName.SAVENEWUSER, new SaveNewUser());

        commands.put(CommandName.GOTOPERSONALPAGE, new GoToPersonalPage());
        commands.put(CommandName.CHANGELANGUAGE, new ChangeLanguage());
        commands.put(CommandName.SAVENEWPRODUCT, new SaveProduct());
        commands.put(CommandName.PRODUCTVIEW, new ProductsView());
        commands.put(CommandName.SAVEPRODUCTINBASKET, new SaveProductInBasket());
        commands.put(CommandName.GOTOPAGE, new GoToPage());
        commands.put(CommandName.PARTICULAREBOOKVIEW, new ParticularProductView());
        commands.put(CommandName.CHANGEEBOOK, new ChangeProduct());
        commands.put(CommandName.SAVECHANGEDEBOOK, new SaveProduct());
        commands.put(CommandName.DELETEPRODUCTFROMBASKET, new DeleteProductFromBasket());
        commands.put(CommandName.GOTOBASKETPAGE, new GoToBasketPage());

        commands.put(CommandName.GOTOORDERPAGE, new GoToOrderPage());
        commands.put(CommandName.ADDNEWORDER, new AddNewOrder());
        commands.put(CommandName.ORDERVIEW, new OrderView());
    }

    public Command takeCommand(String name) {
        CommandName commandName;
        commandName = CommandName.valueOf(name.toUpperCase());
        return commands.get(commandName);
    }

}
