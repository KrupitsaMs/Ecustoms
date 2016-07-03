package by.epam.authorization.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import by.epam.authorization.dao.DAODecl;
import by.epam.authorization.dao.conpool.exception.ConnectionPoolException;
import by.epam.authorization.dao.conpool.impl.ConnectionPool;
import by.epam.authorization.entity.Declaration;
import by.epam.authorization.entity.Good;

/**
 * DeclarationDAO.java
 * Class implemented interface DAODecl
 * Provides operations in Database and with objects of Declaration.java
 * @author MasSword
 */

public class DeclarationDAO implements DAODecl{
	private final static String SQL_EXPORT_DECLARATION_CHECK = "SELECT * FROM ecustoms.declarations WHERE Registration_number = ? AND declaration_type = 'EX'";
	private final static String SQL_ADMIN_DECLARATION_REQUEST = "SELECT * FROM ecustoms.declarations WHERE Registration_number = ?";
	private final static String SQL_ADMIN_DECLARATION_LIST_REQUEST = "SELECT * FROM ecustoms.declarations WHERE Confirmation = ?";
	private final static String SQL_DECLARATION_CHECK = "SELECT * FROM ecustoms.declarations WHERE Registration_number = ?";
	private final static String SQL_DECLARATION_SUBMISSION = "INSERT INTO ecustoms.declarations (Registration_number, declaration_type, UTN, Trade_country) VALUES (?,?,?,?)";
	private final static String SQL_GOOD_SUBMISSION = "INSERT INTO ecustoms.declaration_goods (Registration_number, Goods_number, Tariff_code, Good, Value, Currency, Origin) VALUES (?,?,?,?,?,?,?)";
	private final static String SQL_CHANGING_GOOD = "UPDATE ecustoms.declaration_goods set Tariff_code = ?, Good = ?, Value = ?, Currency = ?, Origin = ?  WHERE Registration_number = ?  AND Goods_number = ?";
	private final static String SQL_USER_DECLARATION = "SELECT Registration_number, declaration_type, Trade_country, Confirmation FROM ecustoms.declarations WHERE UTN = ?";
	private final static String SQL_DECLARATION_GOOD = "SELECT * FROM ecustoms.declaration_goods WHERE Registration_number = ?";
	private final static String SQL_CHANGING_DECLARATION = "UPDATE ecustoms.declarations set declaration_type = ?, Trade_country = ?, Confirmation = ?  WHERE Registration_number = ?";
	private final static String SQL_CHANGING_DECLARATION_STATUS = "UPDATE ecustoms.declarations set Confirmation = ?  WHERE Registration_number = ?";
	private final static String UTN ="UTN";
	private final static String TYPE ="declaration_type";
	private final static String STATUS ="Confirmation";
	
	/**
     * Method gets a declaration number as a parameter
     * and check if declaration with this number is situated
     * and declaration type is EX.
     * If it's true, method returns declaration object
     * @param String declNumber
     * @return Declaration
     */
	
	@Override
	public Declaration exDeclarationRequest(String declNumber) throws ConnectionPoolException {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ConnectionPool con = ConnectionPool.getInstance();
			connection = con.takeConnection();
			ps = connection.prepareStatement(SQL_EXPORT_DECLARATION_CHECK);
			ps.setString(1, declNumber);
			rs = ps.executeQuery();
			if (rs.next()){
				con.returnConnection(connection);
				return declarationStatusCheck(declNumber);
			} else {
				return null;
			}
		} catch (SQLException ex){
            throw new ConnectionPoolException("unable to check EX declaration status", ex);
		} finally{
			try{
				if(ps!=null){
					ps.close();
				}
				if(rs!=null){
					rs.close();
				}
			} catch(SQLException sqlee) {
	            sqlee.printStackTrace();
	        }
		}
	}
	
	/**
     * Method gets a declaration number as a parameter
     * and check if declaration with this number is situated.
     * If it's situated, method returns declaration object with initializer parameter Status
     * @param String declNumber
     * @return Declaration
     */
	
	private static Declaration declarationStatusCheck(String declNumber) throws ConnectionPoolException{
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
	try {
		  ConnectionPool con = ConnectionPool.getInstance();
		  connection = con.takeConnection();
		  ps = connection.prepareStatement(SQL_DECLARATION_CHECK);
		  ps.setString(1, declNumber);
		  rs = ps.executeQuery();
		  if (rs.next()){
			con.returnConnection(connection);
			return new Declaration(declNumber, rs.getString(STATUS));
		  } else {
			con.returnConnection(connection);
			return null;
		  }
		} catch (SQLException ex){
            throw new ConnectionPoolException("unable to check EX declaration status", ex);
		} finally{
			try{
				if(ps!=null){
					ps.close();
				}
				if(rs!=null){
					rs.close();
				}
			} catch(SQLException sqlee) {
	            sqlee.printStackTrace();
	        }
		}
	}

	/**
     * Method gets a declaration number as a parameter
     * and check if declaration with this number is situated.
     * If it's situated, method returns declaration object
     * with initializer parameters UTN and TYPE 
     * @param String declNumber
     * @return Declaration
     */
	
	@Override
	public Declaration declarationRequest(String declNumber)throws ConnectionPoolException {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ConnectionPool con = ConnectionPool.getInstance();
			connection = con.takeConnection();
			ps = connection.prepareStatement(SQL_DECLARATION_CHECK);
			ps.setString(1, declNumber);
			rs = ps.executeQuery();
			if (rs.next()){
				Declaration declaration = declarationStatusCheck(declNumber);
				String curUTN = rs.getString(UTN);
				declaration.setUTN(curUTN);
				String curType = rs.getString(TYPE);
				declaration.setType(curType);
				con.returnConnection(connection);
				return declaration;
			} else {
				return null;
			}
		} catch (SQLException ex){
            throw new ConnectionPoolException("unable to check declaration", ex);
		} finally{
			try{
				if(ps!=null){
					ps.close();
				}
				if(rs!=null){
					rs.close();
				}
			} catch(SQLException sqlee) {
	            sqlee.printStackTrace();
	        }
		}
	}

	/**
     * Method gets a Declaration object as a parameter
     * adds it's declaration in database and returns
     * it's number
     * @param String declNumber
     * @return Declaration
     */
	
	@Override
	public String declarationSubmission(Declaration newDeclaration) throws ConnectionPoolException {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			String declarationNumber = newDeclaration.getNumber();
			String declarationType = newDeclaration.getType();
			String tradeCountry = newDeclaration.getTrade_country();
			String utn = newDeclaration.getUTN();
			ConnectionPool con = ConnectionPool.getInstance();
			connection = con.takeConnection();
			ps = connection.prepareStatement(SQL_DECLARATION_SUBMISSION);
			ps.setString(1, declarationNumber);
			ps.setString(2, declarationType);
			ps.setString(3, utn);
			ps.setString(4, tradeCountry);
			ps.executeUpdate();
			int goods = newDeclaration.getDeclarationGoods().size();
			for (int currentGoodNumber = 1; currentGoodNumber <=goods; currentGoodNumber++){
				Good currentGood = newDeclaration.getDeclarationGoods().get(currentGoodNumber-1);
				String code = currentGood.getTariffCode();
				String goodName = currentGood.getName();
				String value = currentGood.getValue();
				String currency = currentGood.getCurrency();
				String originCountry = currentGood.getOrigin();
				ps = connection.prepareStatement(SQL_GOOD_SUBMISSION);
				ps.setString(1, declarationNumber);
				ps.setString(2, Integer.toString(currentGoodNumber));
				ps.setString(3, code);
				ps.setString(4, goodName);
				ps.setString(5, value);
				ps.setString(6, currency);
				ps.setString(7, originCountry);
				ps.executeUpdate();
			}
			con.returnConnection(connection);
			return declarationNumber;

		} catch (SQLException ex){
            throw new ConnectionPoolException("unable to add declaration to database", ex);
		} finally{
			try{
				if(ps!=null){
					ps.close();
				}
			} catch(SQLException sqlee) {
	            sqlee.printStackTrace();
	        }
		}
	}
	
	/**
     * Method gets a Declaration object as a parameter
     * makes amendments to this declaration in database
     * (it defines needed declaration by the parameter's Declaration number)
     * @param Declaration changingDeclaration
     */
	
	@Override
	public void changingDeclaration(Declaration changingDeclaration) throws ConnectionPoolException {
		Connection connection = null;
		PreparedStatement ps = null;
		try{
			String declarationNumber = changingDeclaration.getNumber();
			String declarationType = changingDeclaration.getType();
			String tradeCountry = changingDeclaration.getTrade_country();
			String status = changingDeclaration.getStatus();
			ConnectionPool con = ConnectionPool.getInstance();
			connection = con.takeConnection();
			ps = connection.prepareStatement(SQL_CHANGING_DECLARATION);
			ps.setString(1, declarationType);
			ps.setString(2, tradeCountry);
			ps.setString(3, status);
			ps.setString(4, declarationNumber);
			ps.executeUpdate();
			int goods = changingDeclaration.getDeclarationGoods().size();
			for (int currentGoodNumber = 1; currentGoodNumber <=goods; currentGoodNumber++){
				Good currentGood = changingDeclaration.getDeclarationGoods().get(currentGoodNumber-1);
				String code = currentGood.getTariffCode();
				String goodName = currentGood.getName();
				String value = currentGood.getValue();
				String currency = currentGood.getCurrency();
				String originCountry = currentGood.getOrigin();
				ps = connection.prepareStatement(SQL_CHANGING_GOOD);
				ps.setString(1, code);
				ps.setString(2, goodName);
				ps.setString(3, value);
				ps.setString(4, currency);
				ps.setString(5, originCountry);
				ps.setString(6, declarationNumber);
				ps.setString(7, Integer.toString(currentGoodNumber));
				ps.executeUpdate();
			}
			con.returnConnection(connection);
		} catch (SQLException ex){
            throw new ConnectionPoolException("unable to make ammendments in declaration", ex);
		} finally{
			try{
				if(ps!=null){
					ps.close();
				}
			} catch(SQLException sqlee) {
	            sqlee.printStackTrace();
	        }
		}
		
	}
	
	/**
     * Method gets UTN as a parameter
     * operates it, and returns list of declarations for this organization 
     * @param String UTN
     * @return ArrayList<Declaration>
     */
	
	@Override
	public ArrayList<Declaration> userDeclarationListRequest(String UTN) throws ConnectionPoolException {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			ArrayList <Declaration> declList = new ArrayList<Declaration>();
			ConnectionPool con = ConnectionPool.getInstance();
			connection = con.takeConnection();
			ps = connection.prepareStatement(SQL_USER_DECLARATION);
			ps.setString(1, UTN);
			rs = ps.executeQuery();
			while (rs.next()){
				Declaration declaration = new Declaration();
				declaration.setNumber(rs.getString(1));
				declaration.setType(rs.getString(2));
				declaration.setUTN(UTN);
				declaration.setTrade_country(rs.getString(3));
				declaration.setStatus(rs.getString(4));
				declList.add(declaration);
			}
			for(int goodNumber=1; goodNumber<=declList.size();goodNumber++){
				Declaration currentDecl = declList.get(goodNumber-1);
				ArrayList<Good> goodList = new ArrayList<Good>();
				ps = connection.prepareStatement(SQL_DECLARATION_GOOD);
				ps.setString(1, currentDecl.getNumber());
				rs = ps.executeQuery();
				while (rs.next()){
					  Good currentGood = new Good();
					  currentGood.setDeclarationNumber(rs.getString(1));
					  currentGood.setNumber(rs.getString(2));
					  currentGood.setTariffCode(rs.getString(3));
					  currentGood.setName(rs.getString(4));
					  currentGood.setValue(rs.getString(5));
					  currentGood.setCurrency(rs.getString(6));
					  currentGood.setOrigin(rs.getString(7));
					  goodList.add(currentGood);
				}
				currentDecl.setDeclarationGoods(goodList);
				declList.set(goodNumber-1, currentDecl);
			}
			con.returnConnection(connection);
			return declList;
		} catch (SQLException ex){
            throw new ConnectionPoolException("unable to get list of declarations", ex);
		} finally{
			try{
				if(ps!=null){
					ps.close();
				}
				if(rs!=null){
					rs.close();
				}
			} catch(SQLException sqlee) {
	            sqlee.printStackTrace();
	        }
		}
		
	}

	/**
     * Method gets a declaration number as a parameter
     * and check if declaration with this number is situated.
     * If it's situated, method returns declaration object
     * initialized with all parameters
     * @param String declNumber
     * @return Declaration
     */
	
	@SuppressWarnings("resource")
	@Override
	public Declaration adminDeclarationRequest(String declNumber) throws ConnectionPoolException {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ConnectionPool con = ConnectionPool.getInstance();
			connection = con.takeConnection();
			ps = connection.prepareStatement(SQL_ADMIN_DECLARATION_REQUEST);
			ps.setString(1, declNumber);
			rs = ps.executeQuery();
			if (rs.next()){
				Declaration declaration = new Declaration();
				declaration.setNumber(declNumber);
				declaration.setType(rs.getString(2));
				declaration.setUTN(rs.getString(3));
				declaration.setTrade_country(rs.getString(4));
				declaration.setStatus(rs.getString(5));
				ArrayList<Good> goodList = new ArrayList<Good>();
				ps = connection.prepareStatement(SQL_DECLARATION_GOOD);
				ps.setString(1, declNumber);
				rs = ps.executeQuery();
				while (rs.next()){
					  Good currentGood = new Good();
					  currentGood.setDeclarationNumber(rs.getString(1));
					  currentGood.setNumber(rs.getString(2));
					  currentGood.setTariffCode(rs.getString(3));
					  currentGood.setName(rs.getString(4));
					  currentGood.setValue(rs.getString(5));
					  currentGood.setCurrency(rs.getString(6));
					  currentGood.setOrigin(rs.getString(7));
					  goodList.add(currentGood);
				}
				declaration.setDeclarationGoods(goodList);
				con.returnConnection(connection);
				return declaration;
			} else {
				return null;
			}
		} catch (SQLException ex){
            throw new ConnectionPoolException("unable to check the declaration", ex);
		} finally{
			try{
				if(ps!=null){
					ps.close();
				}
				if(rs!=null){
					rs.close();
				}
			} catch(SQLException sqlee) {
	            sqlee.printStackTrace();
	        }
		}
	}

	/**
     * Method gets a declaration status as a parameter
     * process it, and returns list of Declaration's with this status
     * @param String status
     * @return ArrayList<Declaration>
     */
	
	@Override
	public ArrayList<Declaration> adminDeclarationListRequest(String status) throws ConnectionPoolException {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ArrayList <Declaration> declList = new ArrayList<Declaration>();
			ConnectionPool con = ConnectionPool.getInstance();
			connection = con.takeConnection();
			ps = connection.prepareStatement(SQL_ADMIN_DECLARATION_LIST_REQUEST);
			ps.setString(1, status);
			rs = ps.executeQuery();
			while (rs.next()){
				Declaration declaration = new Declaration();
				declaration.setNumber(rs.getString(1));
				declaration.setType(rs.getString(2));
				declaration.setUTN(rs.getString(3));
				declaration.setTrade_country(rs.getString(4));
				declaration.setStatus(status);
				declList.add(declaration);
			}
			for(int goodNumber=1; goodNumber<=declList.size();goodNumber++){
				Declaration currentDecl = declList.get(goodNumber-1);
				ArrayList<Good> goodList = new ArrayList<Good>();
				ps = connection.prepareStatement(SQL_DECLARATION_GOOD);
				ps.setString(1, currentDecl.getNumber());
				rs = ps.executeQuery();
				while (rs.next()){
					  Good currentGood = new Good();
					  currentGood.setDeclarationNumber(rs.getString(1));
					  currentGood.setNumber(rs.getString(2));
					  currentGood.setTariffCode(rs.getString(3));
					  currentGood.setName(rs.getString(4));
					  currentGood.setValue(rs.getString(5));
					  currentGood.setCurrency(rs.getString(6));
					  currentGood.setOrigin(rs.getString(7));
					  goodList.add(currentGood);
				}
				currentDecl.setDeclarationGoods(goodList);
				declList.set(goodNumber-1, currentDecl);
			}
			con.returnConnection(connection);
			return declList;
		} catch (SQLException ex){
            throw new ConnectionPoolException("unable to return list of declarations", ex);
		} finally{
			try{
				if(ps!=null){
					ps.close();
				}
				if(rs!=null){
					rs.close();
				}
			} catch(SQLException sqlee) {
	            sqlee.printStackTrace();
	        }
		}
	}

	/**
     * Method gets a declaration status  and number as a parameters
     * and changes status of declaration with this number in database
     * @param String status, String declNumber
     */
	
	@Override
	public void declarationStatusChange(String declNumber, String status) throws ConnectionPoolException {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			ConnectionPool con = ConnectionPool.getInstance();
			connection = con.takeConnection();
			ps = connection.prepareStatement(SQL_CHANGING_DECLARATION_STATUS);
			ps.setString(1, status);
			ps.setString(2, declNumber);
			ps.executeUpdate();
			con.returnConnection(connection);
		} catch (SQLException ex){
            throw new ConnectionPoolException("unable to change status of the declaration", ex);
		} finally{
			try{
				if(ps!=null){
					ps.close();
				}
			} catch(SQLException sqlee) {
	            sqlee.printStackTrace();
	        }
		}
		
	}
}
