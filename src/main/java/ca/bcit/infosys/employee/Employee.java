package ca.bcit.infosys.employee;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A class representing a single Employee.
 *
 * @author Bruce Link
 *
 */
@XmlRootElement(name = "employee")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;
    /** The employee's name. */
    private String firstName;
    private String lastName;
    /** The employee's employee number. */
    private int empNumber;
    /** The employee's login ID. */
    private String userName;
    /** The employee's type. */
    private int type;
    private boolean editable;

    public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	/**
     * The no-argument constructor. Used to create new employees from within the
     * application.
     */
    public Employee() {
    }

    /**
     * The argument-containing constructor. Used to create the initial employees
     * who have access as well as the administrator.
     *
     * @param empName the name of the employee.
     * @param number the empNumber of the user.
     * @param id the loginID of the user.
     */
    public Employee(final String empName, final int number, final String id, final int empType) {
        firstName = empName;
        empNumber = number;
        userName = id;
        type = empType;
    }

    /**
     * The argument-containing constructor. Used to create the initial employees
     * who have access as well as the administrator.
     *
     * @param empName the name of the employee.
     * @param number the empNumber of the user.
     * @param id the loginID of the user.
     */
    public Employee(final String firstName, String lastName, final int number, final String id, final int empType) {
        this.firstName = firstName;
        this.lastName = lastName;
        empNumber = number;
        userName = id;
        type = empType;
    }

    /**
     * @return the empNumber
     */
    @XmlElement(name = "empNumber")
    public int getEmpNumber() {
        return empNumber;
    }

    /**
     * @param number the empNumber to set
     */
    public void setEmpNumber(final int number) {
        empNumber = number;
    }

    /**
     * @return the userName
     */
    @XmlElement(name = "userName")
    public String getUserName() {
        return userName;
    }

    /**
     * @param id the userName to set
     */
    public void setUserName(final String id) {
        userName = id;
    }
    @XmlElement(name = "type")
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	@XmlElement(name = "firstName")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	@XmlElement(name = "lastName")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


}
