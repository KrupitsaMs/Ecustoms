package by.epam.authorization.controller.helper;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import by.epam.authorization.command.Command;
import by.epam.authorization.command.CommandName;
import by.epam.authorization.command.impl.ADeclarationConfirm;
import by.epam.authorization.command.impl.ADeclarationDecline;
import by.epam.authorization.command.impl.ANewDeclarationsReview;
import by.epam.authorization.command.impl.ARegistration;
import by.epam.authorization.command.impl.AShowDeclarationCommand;
import by.epam.authorization.command.impl.AUserConfirmCommand;
import by.epam.authorization.command.impl.AUserDeclineCommand;
import by.epam.authorization.command.impl.AUserReviewCommand;
import by.epam.authorization.command.impl.AddNewGoodCommand;
import by.epam.authorization.command.impl.AdminPageCommand;
import by.epam.authorization.command.impl.ChangeNewGoodCommand;
import by.epam.authorization.command.impl.CreateDeclarationChangesCommand;
import by.epam.authorization.command.impl.CreateNewDeclarationCommand;
import by.epam.authorization.command.impl.DeclarationCheckCommand;
import by.epam.authorization.command.impl.DeclarationChooseCommand;
import by.epam.authorization.command.impl.ExportDeclarationCheckCommand;
import by.epam.authorization.command.impl.LocalizationCommand;
import by.epam.authorization.command.impl.LogOutCommand;
import by.epam.authorization.command.impl.LoginCommand;
import by.epam.authorization.command.impl.NoSuchCommand;
import by.epam.authorization.command.impl.RegistrationCommand;
import by.epam.authorization.command.impl.RestoreCommand;
import by.epam.authorization.command.impl.ShowAllDeclarations;
import by.epam.authorization.command.impl.ShowAllUserCommand;
import by.epam.authorization.command.impl.SubmitChangingDeclaration;
import by.epam.authorization.command.impl.SubmitNewDeclarationCommand;
import by.epam.authorization.command.impl.UserNewDeclarationCommand;
import by.epam.authorization.command.impl.UserPageCommand;

/**
 * CommandHelper.java
 * It's class-factory
 * that generates objects that implemented interface command
 * It contains method execute
 * @author MasSword
 */

public final class CommandHelper {
	private static CommandHelper  instance;
	private Map<CommandName, Command> commands = new ConcurrentHashMap<>();

	private CommandHelper(){
		commands.put(CommandName.LOGIN, new LoginCommand());
		commands.put(CommandName.RU, new LocalizationCommand());
		commands.put(CommandName.REGISTRATION, new RegistrationCommand());
		commands.put(CommandName.EN, new LocalizationCommand());
		commands.put(CommandName.RESTORE, new RestoreCommand());
		commands.put(CommandName.EX_DECLARATION_CHECK, new ExportDeclarationCheckCommand());
		commands.put(CommandName.USER_PAGE, new UserPageCommand());
		commands.put(CommandName.DECLARATION_CHECK, new DeclarationCheckCommand());
		commands.put(CommandName.LOG_OUT, new LogOutCommand());
		commands.put(CommandName.USER_NEW_DECLARATION, new UserNewDeclarationCommand());
		commands.put(CommandName.CREATE_NEW_DECLARATION, new CreateNewDeclarationCommand());
		commands.put(CommandName.SUBMIT_NEW_DECLARATION, new SubmitNewDeclarationCommand());
		commands.put(CommandName.ADD_NEW_GOOD, new AddNewGoodCommand());
		commands.put(CommandName.SHOW_ALL_DECLARATIONS, new ShowAllDeclarations());
		commands.put(CommandName.DECLARATION_CHOOSE, new DeclarationChooseCommand());
		commands.put(CommandName.CREATE_DECLARATION_CHANGES, new CreateDeclarationChangesCommand());
		commands.put(CommandName.SUBMIT_CHANGING_DECLARATION, new SubmitChangingDeclaration());
		commands.put(CommandName.CHANGE_NEW_GOOD, new ChangeNewGoodCommand());
		commands.put(CommandName.A_SHOW_ALL_USERS, new ShowAllUserCommand());
		commands.put(CommandName.A_USER_REVIEW, new AUserReviewCommand());
		commands.put(CommandName.A_USER_CONFIRM, new AUserConfirmCommand());
		commands.put(CommandName.A_USER_DECLINE, new AUserDeclineCommand());
		commands.put(CommandName.A_SHOW_DECLARATION, new AShowDeclarationCommand());
		commands.put(CommandName.ADMIN_PAGE, new AdminPageCommand());
		commands.put(CommandName.A_NEW_DECLARATIONS_REVIEW, new ANewDeclarationsReview());
		commands.put(CommandName.A_DECLARATION_CONFIRM, new ADeclarationConfirm());
		commands.put(CommandName.A_DECLARATION_DECLINE, new ADeclarationDecline());
		commands.put(CommandName.A_REGISTRATION, new ARegistration());
		commands.put(CommandName.NO_SUCH_COMMAND, new NoSuchCommand());
	}
	
	/**
     * Method returns CommandHelper object
     * if it is not created, method creates it 
     * @return CommandHelper object
     */
	
	public static CommandHelper getCommandHelperInstance(){
		CommandHelper localInstance = instance;
		if (localInstance == null) {
			synchronized (CommandHelper.class) {
				localInstance = instance;
				if (localInstance == null) {
					instance = new CommandHelper();
				}
			}
		}
		return instance;
	}
	
	/**
     * Method get String object, processes it, and returns
     * suitable object of Command.class
     * @param String commandName
     * @return object of Command.class
     */
	
	public Command getCommand(String commandName){
		Command command = null;
		CommandName key = null;
		commandName = commandName.replace('-', '_').toUpperCase();
		key = CommandName.valueOf(commandName);
		command = commands.get(key);
		if(command == null){
			
		}
		return command;		
	}	
}
