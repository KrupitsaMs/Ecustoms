package by.epam.authorization.command.impl;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import by.epam.authorization.command.Command;
import by.epam.authorization.command.exception.CommandException;
import by.epam.authorization.controller.PageName;
import by.epam.authorization.entity.Declaration;
import by.epam.authorization.entity.Good;

public class AddNewGoodCommand implements Command{
	private static final String CODE = "tar_code";
	private static final String GOOD = "good";
	private static final String VALUE = "value";
	private static final String CURRENCY = "currency";
	private static final String ORIGIN = "origin";
	private static final String NEW_DECLARATION = "new_declaration";

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
		request.getSession().setAttribute(NEW_DECLARATION, newDeclaration);
		return PageName.ADD_NEW_GOOD;
	}

}
