package by.epam.authorization.command.impl;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import by.epam.authorization.command.Command;
import by.epam.authorization.command.exception.CommandException;
import by.epam.authorization.controller.PageName;
import by.epam.authorization.entity.Declaration;
import by.epam.authorization.entity.Good;

/**
 * AddNewGoodCommand.java
 * Class implemented interface Command
 * Command adds new Good in database
 * It contains method execute
 * @author MasSword
 */

public class AddNewGoodCommand implements Command{
	private static final String CODE = "tar_code";
	private static final String GOOD = "good";
	private static final String VALUE = "value";
	private static final String CURRENCY = "currency";
	private static final String ORIGIN = "origin";
	private static final String NEW_DECLARATION = "new_declaration";
	
	private final static String TYPE_VALIDATOR = "[A-Z]{2}";
	private final static String CODE_VALIDATOR = "[\\d]{2}";
	private final static String GOOD_VALIDATOR = "[A-Za-z\\d\\s\\p{Punct}]{1,40}";
	private final static String VALUE_VALIDATOR = "[\\d]{1,20}";
	private final static String CURRENCY_VALIDATOR = "[A-Z]{3}";
	/**
     * Method reads a command from the request
     * and processes it. The result will be given as
     * a forward page
     * forwarded page depends on successful or not adding good
     * @param request request to read the command from
     * @return forward page - String class object
     */
	
	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		Declaration newDeclaration =  (Declaration) request.getSession().getAttribute(NEW_DECLARATION);
		String declNumber = newDeclaration.getNumber();
		ArrayList<Good> declList = newDeclaration.getDeclarationGoods();
		int goodQuantity = declList.size();
		String code = request.getParameter(CODE);
		String good = request.getParameter(GOOD);
		String value = request.getParameter(VALUE);
		String currency = request.getParameter(CURRENCY);
		String origin = request.getParameter(ORIGIN);
		Good newDeclarationGood = new Good(declNumber, Integer.toString(goodQuantity), code, good, value, currency, origin);
		declList.add(newDeclarationGood);
		newDeclaration.setDeclarationGoods(declList);
		if(!(code.matches(CODE_VALIDATOR) && good.matches(GOOD_VALIDATOR) && value.matches(VALUE_VALIDATOR)
				&& currency.matches(CURRENCY_VALIDATOR) && origin.matches(TYPE_VALIDATOR))){
			return PageName.DECLARATION_SUB_FAILED;
		} else{
			request.getSession().setAttribute(NEW_DECLARATION, newDeclaration);
			return PageName.ADD_NEW_GOOD;
		}

	}

}
