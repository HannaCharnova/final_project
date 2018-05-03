package epam.chernova.finalproject.command;


import epam.chernova.finalproject.command.impl.*;

import javax.servlet.http.HttpServletRequest;
import java.util.EnumMap;
import java.util.Map;


public class CommandProvider {
    private final static CommandProvider INSTANCE = new CommandProvider();
    private Map<CommandName, ICommand> commands = new EnumMap<CommandName,ICommand>(CommandName.class);

    public CommandProvider() {

        commands.put(CommandName.SIGN_IN,new SignIn());
        commands.put(CommandName.SIGN_UP,new SignUp());
        commands.put(CommandName.INDEX,new Index());
        commands.put(CommandName.CHANGE_LOCALE,new ChangeLocale());
        commands.put(CommandName.PRODUCT_LIST,new ProductList());
    }

    public static CommandProvider getInstance() {
        return INSTANCE;
    }


    public ICommand getCommand(HttpServletRequest request) {
        ICommand iCommand = commands.get(CommandName.WRONG_REQUEST);
        String command = request.getRequestURI();
        command=command.replace("/epam.by/","");
        try {
            CommandName commandName = CommandName.valueOf(command.toUpperCase());
            iCommand = commands.get(commandName);
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }
        return iCommand;
    }
}
