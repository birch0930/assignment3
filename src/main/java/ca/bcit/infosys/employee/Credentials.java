package ca.bcit.infosys.employee;

import java.io.Serializable;

/**
 * Login Credentials.
 * @author blink
 */
public class Credentials implements Serializable {

	private static final long serialVersionUID = 1L;
    /** The login ID. */
    private String userName;
    /** The password. */
    private String password;
    
	public Credentials() {}
	
    public Credentials(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
    /**
     * @return the loginID
     */
    public String getUserName() {
        return userName;
    }
    /**
     * @param id the loginID to set
     */
    public void setUserName(final String id) {
        userName = id;
    }
    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }
    /**
     * @param pw the password to set
     */
    public void setPassword(final String pw) {
        password = pw;
    }

}
