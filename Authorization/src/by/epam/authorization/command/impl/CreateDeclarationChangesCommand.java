package by.epam.authorization.command.impl;

import javax.servlet.http.HttpServletRequest;

import by.epam.authorization.command.Command;
import by.epam.authorization.command.exception.CommandException;
import by.epam.authorization.controller.PageName;
import by.epam.authorization.entity.Declaration;
import by.epam.authorization.entity.Good;

public class CreateDeclarationChangesCommand implements Command{
	private static final String TYPE = "declaration_type";
	private static final String TRADE_COUNTRY = "trade_country";
	private static final String GOOD_NUM = "good_number";
	private static final String CODE = "tar_code";
	private static final String GOOD = "good";
	private static final String VALUE = "value";
	private static final String CURRENCY = "currency";
	private static final String ORIGIN = "origin";
	private static final String SEARCHED_DECL = "searched_decl";
	private static final String STATUS = "not yet examined";

	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		String page;
		String type = request.getParameter(TYPE);
		String tradeCountry = request.getParameter(TRADE_COUNTRY);
		Declaration changingDeclaration = (Declaration) request.getSession().getAttribute(SEARCHED_DECL);
		if (!(type.isEmpty())){
			changingDeclaration.setType(type);
		}
		if(!(tradeCountry.isEmpty())){
			changingDeclaration.setTrade_country(tradeCountry);
		}
		changingDeclaration.setStatus(STATUS);
		String goodNumber = request.getParameter(GOOD_NUM);
		if (Integer.parseInt(goodNumber)<=changingDeclaration.getDeclarationGoods().size()){
			if (!(goodNumber.isEmpty())){
				Good changingGood = changingDeclaration.getDeclarationGoods().get(Integer.parseInt(goodNumber)-1);
				String code = request.getParameter(CODE);
				if (!(code.isEmpty())){
					changingGood.setTariffCode(code);
				}
				String good = request.getParameter(GOOD);
				if(!(good.isEmpty())){
					changingGood.setName(good);
				}
				String value = request.getParameter(VALUE);
				if(!(value.isEmpty())){
					changingGood.setValue(value);
				}
				String currency = request.getParameter(CURRENCY);
				if(!(currency.isEmpty())){
					changingGood.setCurrency(currency);
				}
				String origin = request.getParameter(ORIGIN);
				if(!(currency.isEmpty())){
					changingGood.setOrigin(origin);
				}
				changingDeclaration.getDeclarationGoods().set(Integer.parseInt(goodNumber)-1, changingGood);
			}
			request.getSession().setAttribute(SEARCHED_DECL, changingDeclaration);
			page = PageName.DECLARATION_CHANGINGS_ADD_GOOD;
		} else {
			page = PageName.DECLARATION_CHANGINGS_FAIL;
		}
		return page;
	}

}
