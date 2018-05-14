package epam.chernova.finalproject.command;


import epam.chernova.finalproject.command.impl.forward.*;
import epam.chernova.finalproject.command.impl.redirect.*;

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
        commands.put(CommandName.FIND_BY_TYPE,new FindByType());
        commands.put(CommandName.SIGN_OUT,new SignOut());
        commands.put(CommandName.ADD_PRODUCT_BASKET,new AddBasketProduct());
        commands.put(CommandName.SHOW_ORDER_CLIENT,new ShowOrderClient());
        commands.put(CommandName.REMOVE_PRODUCT_BASKET,new RemoveBasketProduct());
        commands.put(CommandName.PAY_FOR_ORDER,new PayOrder());
        commands.put(CommandName.CLIENT_PROFILE,new ClientProfile());
        commands.put(CommandName.DELETE_PRODUCT,new DeleteProduct());
        commands.put(CommandName.ADD_PRODUCT,new AddProduct());
        commands.put(CommandName.SHOW_CLIENT,new ShowClient());
        commands.put(CommandName.BAN_CLIENT,new BanClient());
        commands.put(CommandName.SHOW_ADMIN,new ShowAdmin());
        commands.put(CommandName.DELETE_ADMIN,new DeleteAdmin());
        commands.put(CommandName.ADD_ADMIN,new AddAdmin());
        commands.put(CommandName.SHOW_ORDER_ADMIN,new ShowOrderAdmin());
        commands.put(CommandName.EDIT_PROFILE,new EditProfile());
        commands.put(CommandName.CHANGE_PASSWORD,new ChangePassword());



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
