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

        commands.put(CommandName.SIGN_IN,new SignInCommand());
        commands.put(CommandName.SIGN_UP,new SignUpCommand());
        commands.put(CommandName.INDEX,new IndexCommand());
        commands.put(CommandName.CHANGE_LOCALE,new ChangeLocaleCommand());
        commands.put(CommandName.FIND_BY_TYPE,new FindByTypeCommand());
        commands.put(CommandName.SIGN_OUT,new SignOutCommand());
        commands.put(CommandName.ADD_PRODUCT_BASKET,new AddBasketProductCommand());
        commands.put(CommandName.SHOW_ORDER_CLIENT,new ShowOrderClientCommand());
        commands.put(CommandName.REMOVE_PRODUCT_BASKET,new RemoveBasketProductCommand());
        commands.put(CommandName.PAY_FOR_ORDER,new PayOrderCommand());
        commands.put(CommandName.CLIENT_PROFILE,new ClientProfileCommand());
        commands.put(CommandName.DELETE_PRODUCT,new DeleteProductCommand());
        commands.put(CommandName.ADD_PRODUCT,new AddProductCommand());
        commands.put(CommandName.SHOW_CLIENT,new ShowClientCommand());
        commands.put(CommandName.BAN_CLIENT,new BanClientCommand());
        commands.put(CommandName.SHOW_ADMIN,new ShowAdminCommand());
        commands.put(CommandName.DELETE_ADMIN,new DeleteAdminCommand());
        commands.put(CommandName.ADD_ADMIN,new AddAdminCommand());
        commands.put(CommandName.SHOW_ORDER_ADMIN,new ShowOrderAdminCommand());
        commands.put(CommandName.EDIT_PROFILE,new EditProfileCommand());
        commands.put(CommandName.CHANGE_PASSWORD,new ChangePasswordCommand());
        commands.put(CommandName.DELETE_ACCOUNT,new DeleteAccountCommand());
        commands.put(CommandName.ADD_ACCOUNT,new AddAccountCommand());
        commands.put(CommandName.EDIT_PRODUCT,new EditProductCommand());
        commands.put(CommandName.CLOSE_ORDER,new CloseOrderCommand());
        commands.put(CommandName.ADD_REVIEW,new AddReviewCommand());
        commands.put(CommandName.SHOW_REVIEWS,new ShowReviewsCommand());
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
