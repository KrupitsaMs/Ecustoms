package by.epam.authorization.command.impl;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import by.epam.authorization.command.Command;
import by.epam.authorization.command.exception.CommandException;
import by.epam.authorization.controller.PageName;
import by.epam.authorization.entity.Declaration;
import by.epam.authorization.entity.Good;

/**
 * ChangeNewGoodCommand.java
 * Class implemented interface Command
 * One of the classes intended to amend the existing declarations
 * Reads the information from JSP and rewrites the changed good attributes
 * It contains method execute
 * @author MasSword
 */

public class ChangeNewGoodCommand implements Command{
	private static final String CODE = "tar_code";
	private static final String GOOD = "good";
	private static final String VALUE = "value";
	private static final String CURRENCY = "currency";
	private static final String ORIGIN = "origin";
	private static final String SEARCHED_DECL = "searched_decl";
	private static final String GOOD_NUM = "good_number";

	private final static String TYPE_VALIDATOR = "[A-Z]{2}";
	private final static String GOOD_NUM_VALIDATOR = "[\\d]{1,3}";
	private final static String CODE_VALIDATOR = "[\\d]{2}";
	private final static String GOOD_VALIDATOR = "[A-Za-z\\d\\s\\p{Punct}]{1,40}";
	private final static String VALUE_VALIDATOR = "[\\d]{1,20}";
	private final static String CURRENCY_VALIDATOR = "[A-Z]{3}";
	/**
     * Method reads a command from the request
     * and processes it. The result will be given as
     * a forward page
     * @param request request to read the command from
     * @return forward page - String class object
     */
	
	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		String page;
		Declaration changingDeclaration =  (Declaration) request.getSession().getAttribute(SEARCHED_DECL);
		ArrayList<Good> goodList = changingDeclaration.getDeclarationGoods();
		int goodQuantity = goodList.size();
		String goodNumber = request.getParameter(GOOD_NUM);
		if (goodNumber.matches(GOOD_NUM_VALIDATOR) && Integer.parseInt(goodNumber) <= goodQuantity){
			Good changingGood = goodList.get(Integer.parseInt(goodNumber)-1);
			String code = request.getParameter(CODE);
			if(code.matches(CODE_VALIDATOR)){
				changingGood.setTariffCode(code);
			}
			String good = request.getParameter(GOOD);
			if(good.matches(GOOD_VALIDATOR)){
				changingGood.setName(good);
			}
			String value = request.getParameter(VALUE);
			if(value.matches(VALUE_VALIDATOR)){
				changingGood.setValue(value);
			}
			String currency = request.getParameter(CURRENCY);
			if(currency.matches(CURRENCY_VALIDATOR)){
				changingGood.setCurrency(currency);
			}
			String origin = request.getParameter(ORIGIN);
			if(origin.matches(TYPE_VALIDATOR)){
				changingGood.setOrigin(origin);
			}
			goodList.set(Integer.parseInt(goodNumber)-1, changingGood);
			request.getSession().setAttribute(SEARCHED_DECL, changingDeclaration);
			page = PageName.DECLARATION_CHANGINGS_ADD_GOOD;
		} else{
			page = PageName.DECLARATION_CHANGINGS_FAIL;
		}
		
		return page;
	}
}
