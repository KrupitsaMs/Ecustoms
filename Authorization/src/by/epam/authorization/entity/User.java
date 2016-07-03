package by.epam.authorization.entity;

import java.io.Serializable;

/**
 * User.java
 * Class defines entity of service's User with it's parameters
 * name, password, role, unique taxpayer number, organization name, address, status, mail
 * @author MasSword
 */

public class User implements Serializable{
	 private static final long serialVersionUID = 1L;
	 private String name;
	 private String password;
	 private String role;
	 private String UTN;
	 private String organizationName;
	 private String address;
	 private String status;
	 private String mail;
	 
	 
	 public User(){}

	 public User (String name){
		 this.name=name;
	 }
	 
	 public User (String name, String password, String role, String UTN, String organizationName, String address, String mail){
		 this.name=name;
		 this.password=password;
		 this.role=role;
		 this.UTN=UTN;
		 this.organizationName=organizationName;
		 this.address=address;
		 this.mail=mail;
	 }
	 
	 public User (String name, String role, String UTN, String organizationName, String mail){
		 this.name=name;
		 this.role=role;
		 this.UTN=UTN;
		 this.organizationName=organizationName;
		 this.mail=mail;
	 }
	 
	/**
    * Method-getter
	* @return String role
	*/
	 
	public String getRole() {
		return role;
	}
	
	/**
     * Method-setter
     * @parameter String role
     */
	
	public void setRole(String role) {
		this.role = role;
	}
	
	/**
	* Method-getter
    * @return String name
	*/
	
	public String getName() {
		return name;
	}
	
	/**
     * Method-setter
     * @parameter String name
     */
	
	public void setName(String name) {
		this.name = name;
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
    * @return String organizationName
	*/
	
	public String getOrganizationName() {
		return organizationName;
	}
	
	/**
     * Method-setter
     * @parameter String organizationName
     */
	
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	
	/**
	* Method-getter
    * @return String mail
	*/
	
	public String getMail() {
		return mail;
	}

	/**
     * Method-setter
     * @parameter String mail
     */
	
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	* Method-getter
    * @return String address
	*/
	
	public String getAddress() {
		return address;
	}

	/**
     * Method-setter
     * @parameter String address
     */
	
	public void setAddress(String address) {
		this.address = address;
	}
    
	/**
	* Method-getter
    * @return String  password
	*/
	
	public String getPassword() {
		return password;
	}

	/**
     * Method-setter
     * @parameter String password
     */
	
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	* Method-getter
    * @return String  status
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
     * Returns a hash code value for the object.
     * @return int hash code
     */
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((UTN == null) ? 0 : UTN.hashCode());
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((mail == null) ? 0 : mail.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime
				* result
				+ ((organizationName == null) ? 0 : organizationName.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		User other = (User) obj;
		if (UTN == null) {
			if (other.UTN != null)
				return false;
		} else if (!UTN.equals(other.UTN))
			return false;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (mail == null) {
			if (other.mail != null)
				return false;
		} else if (!mail.equals(other.mail))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (organizationName == null) {
			if (other.organizationName != null)
				return false;
		} else if (!organizationName.equals(other.organizationName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}
}
