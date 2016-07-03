package by.epam.authorization.entity;

import java.util.ArrayList;

/**
 * Declaration.java
 * Class defines customs declaration entity with it's parameters
 * declaration number, it's type, unique taxpayer number, trade country, status
 * and list of  declaration's goods
 * @author MasSword
 */

public class Declaration {
	private String number;
	private String type;
	private String UTN;
	private String trade_country;
	private String status;
	private ArrayList<Good> declarationGoods;
	
	public Declaration(String number, String type, String UTN, String trade_country){
		this.number=number;
		this.type=type;
		this.UTN=UTN;
		this.trade_country=trade_country;
	}
	
	public Declaration(String number, String type, String UTN, String trade_country, String status){
		this.number=number;
		this.type=type;
		this.UTN=UTN;
		this.trade_country=trade_country;
		this.status=status;
	}
	public Declaration(String number, String type, String UTN, String trade_country, String status, ArrayList<Good> declarationGoods){
		this.number=number;
		this.type=type;
		this.UTN=UTN;
		this.trade_country=trade_country;
		this.status=status;
		this.declarationGoods=declarationGoods;
	}
	public Declaration(){}
	public Declaration(String number) {
		this.number=number;
	}
	public Declaration(String number, String status) {
		this.number=number;
		this.status=status;
	}
	public Declaration(String number, String status, String UTN){
		this.number=number;
		this.status=status;
		this.UTN=UTN;
	}
	
	/**
     * Method-getter
     * @return String number
     */
	
	public String getNumber() {
		return number;
	}
	
	/**
     * Method-setter
     * @parameter String number
     */
	
	public void setNumber(String number) {
		this.number = number;
	}
	
	/**
     * Method-getter
     * @return String type
     */
	
	public String getType() {
		return type;
	}
	
	/**
     * Method-setter
     * @parameter String type
     */
	
	public void setType(String type) {
		this.type = type;
	}
	
	/**
     * Method-getter
     * @return String UTN
     */
	
	public String getUTN() {
		return UTN;
	}
	
	/**
     * Method-setter
     * @parameter String utn
     */
	
	public void setUTN(String uTN) {
		UTN = uTN;
	}
	
	/**
     * Method-getter
     * @return String trade_country
     */
	
	public String getTrade_country() {
		return trade_country;
	}
	
	/**
     * Method-setter
     * @parameter String trade_country
     */
	
	public void setTrade_country(String trade_country) {
		this.trade_country = trade_country;
	}
	
	/**
     * Method-getter
     * @return String status
     */
	
	public String getStatus() {
		return status;
	}
	
	/**
     * Method-setter
     * @parameter String status
     */
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
     * Method-getter
     * @return ArrayList<Good> declarationGoods
     */
	
	public ArrayList<Good> getDeclarationGoods() {
		return declarationGoods;
	}
	
	/**
     * Method-setter
     * @parameter ArrayList<Good> declarationGoods
     */
	
	public void setDeclarationGoods(ArrayList<Good> declarationGoods) {
		this.declarationGoods = declarationGoods;
	}
	
	/**
     * Returns a hash code value for the object.
     * @return int hash code
     */
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((UTN == null) ? 0 : UTN.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result
				+ ((trade_country == null) ? 0 : trade_country.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}
	
	/**
     * Indicates whether some other object is "equal to" this one.
     * @return boolean equality
     */
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Declaration other = (Declaration) obj;
		if (UTN == null) {
			if (other.UTN != null)
				return false;
		} else if (!UTN.equals(other.UTN))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (trade_country == null) {
			if (other.trade_country != null)
				return false;
		} else if (!trade_country.equals(other.trade_country))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	
}
