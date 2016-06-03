package by.epam.authorization.command.impl;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import by.epam.authorization.command.Command;
import by.epam.authorization.command.exception.CommandException;
import by.epam.authorization.controller.PageName;
import by.epam.authorization.entity.Declaration;
import by.epam.authorization.entity.Good;
import by.epam.authorization.entity.User;

public class CreateNewDeclarationCommand implements Command{
	private static final String TYPE = "declaration_type";
	private static final String TRADE_COUNTRY = "trade_country";
	private static final String LOGIN = "login";
	private static final String NUMBER = "declaration_number";
	private static final String CODE = "tar_code";
	private static final String GOOD = "good";
	private static final String VALUE = "value";
	private static final String CURRENCY = "currency";
	private static final String ORIGIN = "origin";
	private static final String NEW_DECLARATION = "new_declaration";
	private static final String ONE = "1";
	
	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		String type = request.getParameter(TYPE);
		String tradeCountry = request.getParameter(TRADE_COUNTRY);
		String declarationNumber = (String) request.getSession().getAttribute(NUMBER);
		User user = (User) request.getSession().getAttribute(LOGIN);
		String utn = user.getUTN();
		Declaration newDeclaration = new Declaration(declarationNumber, type, utn, tradeCountry);
		
		String goodNumber = ONE;
		String code = request.getParameter(CODE);
		String good = request.getParameter(GOOD);
		String value = request.getParameter(VALUE);
		String currency = request.getParameter(CURRENCY);
		String origin = request.getParameter(ORIGIN);
		Good newDeclarationGood = new Good(declarationNumber, goodNumber, code, good, value, currency, origin);
		ArrayList <Good> goodsList = new ArrayList<Good>(1);
		goodsList.add(newDeclarationGood);
		newDeclaration.setDeclarationGoods(goodsList);
		
		request.getSession().setAttribute(NEW_DECLARATION, newDeclaration);
		return PageName.ADD_NEW_GOOD;
	}

}
