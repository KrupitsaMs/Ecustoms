package by.epam.authorization.entity;

public class Good {
  private String declarationNumber;	
  private String number;
  private String tariffCode;
  private String name;
  private String value;
  private String currency;
  private String origin;
  
  public Good(){};
  
  public Good (String number){
	  this.number=number;
  }
  
  public Good (String declarationNumber, String number, String tariffCode, String name,
		  String value, String currency, String origin){
	  this.declarationNumber=declarationNumber;
	  this.number=number;
	  this.tariffCode=tariffCode;
	  this.name=name;
	  this.value=value;
	  this.currency=currency;
	  this.origin=origin;
  }

  public String getDeclarationNumber() {
	return declarationNumber;
  }

  public void setDeclarationNumber(String declarationNumber) {
	this.declarationNumber = declarationNumber;
  }

  public String getNumber() {
	return number;
  }

  public void setNumber(String number) {
	this.number = number;
  }

  public String getTariffCode() {
	return tariffCode;
  }

  public void setTariffCode(String tariffCode) {
	this.tariffCode = tariffCode;
  }

  public String getName() {
	return name;
  }

  public void setName(String name) {
	this.name = name;
  }

  public String getValue() {
	return value;
  }

  public void setValue(String value) {
	this.value = value;
  }

  public String getCurrency() {
	return currency;
  }

  public void setCurrency(String currency) {
	this.currency = currency;
  }

  public String getOrigin() {
	return origin;
  }

  public void setOrigin(String origin) {
	this.origin = origin;
  }

  @Override
  public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((currency == null) ? 0 : currency.hashCode());
	result = prime * result
			+ ((declarationNumber == null) ? 0 : declarationNumber.hashCode());
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	result = prime * result + ((number == null) ? 0 : number.hashCode());
	result = prime * result + ((origin == null) ? 0 : origin.hashCode());
	result = prime * result
			+ ((tariffCode == null) ? 0 : tariffCode.hashCode());
	result = prime * result + ((value == null) ? 0 : value.hashCode());
	return result;
  }

  @Override
  public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Good other = (Good) obj;
	if (currency == null) {
		if (other.currency != null)
			return false;
	} else if (!currency.equals(other.currency))
		return false;
	if (declarationNumber == null) {
		if (other.declarationNumber != null)
			return false;
	} else if (!declarationNumber.equals(other.declarationNumber))
		return false;
	if (name == null) {
		if (other.name != null)
			return false;
	} else if (!name.equals(other.name))
		return false;
	if (number == null) {
		if (other.number != null)
			return false;
	} else if (!number.equals(other.number))
		return false;
	if (origin == null) {
		if (other.origin != null)
			return false;
	} else if (!origin.equals(other.origin))
		return false;
	if (tariffCode == null) {
		if (other.tariffCode != null)
			return false;
	} else if (!tariffCode.equals(other.tariffCode))
		return false;
	if (value == null) {
		if (other.value != null)
			return false;
	} else if (!value.equals(other.value))
		return false;
	return true;
  }
}
