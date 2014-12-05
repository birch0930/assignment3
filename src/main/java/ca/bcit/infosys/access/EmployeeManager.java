package ca.bcit.infosys.access;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import ca.bcit.infosys.employee.Credentials;
import ca.bcit.infosys.employee.Employee;
import ca.bcit.infosys.employee.EmployeeList;

/**
 * Handle CRUD actions for Product class.
 * 
 * @author blink
 * @version 1.0
 * 
 */

@Path("/employee")
public class EmployeeManager implements EmployeeList {
	@Resource(mappedName = "java:jboss/datasources/TIMESHEET")
	private DataSource dataSource;
	private Employee employee;
	private Credentials credential;

	public EmployeeManager() {

	}

	/**
	 * Return Employee table for all Employees.
	 * 
	 * @return List<Employee>of all records of all Employees from
	 * 
	 *         Employee table
	 * 
	 */
	@GET
	@Produces("application/xml")
	@Override
	public List<Employee> getEmployees() {
		ArrayList<Employee> employeeList = new ArrayList<Employee>();
		Connection connection = null;
		Statement stmt = null;
		try {
			try {
				connection = dataSource.getConnection();
				try {
					stmt = connection.createStatement();
					ResultSet result = stmt
							.executeQuery("SELECT * FROM EMPLOYEE "
									+ "ORDER BY EMPLOYEE_ID");
					while (result.next()) {
						Employee e = new Employee();
						e.setFirstName(result.getString("FIRST_NAME"));
						e.setLastName(result.getString("LAST_NAME"));
						e.setEmpNumber(result.getInt("EMPLOYEE_ID"));
						e.setUserName(result.getString("USER_NAME"));
						e.setType(result.getInt("AUTHORITY"));
						employeeList.add(e);

					}
				} finally {
					if (stmt != null) {
						stmt.close();
					}
				}
			} finally {
				if (connection != null) {
					connection.close();
				}
			}
		} catch (SQLException ex) {
			System.out.println("Error in getAll");
			ex.printStackTrace();
			return null;
		}
		return employeeList;
	}

	/**
	 * Find Inventory record from database.
	 * 
	 * @param id
	 *            primary key of record to be returned.
	 * @return the Inventory record with key = id, null if not
	 * 
	 *         found.
	 */
	@GET
	@Path("/{username}")
	@Produces("application/xml")
	@Override
	public Employee getEmployee(@PathParam("username") String name) {
		Connection connection = null;
		Statement stmt = null;
		try {
			try {
				connection = dataSource.getConnection();
				try {
					stmt = connection.createStatement();
					ResultSet result = stmt
							.executeQuery("SELECT * FROM EMPLOYEE where USER_NAME = '"
									+ name + "'");
					if (result.next()) {
						Employee e = new Employee();
						e.setFirstName(result.getString("FIRST_NAME"));
						e.setLastName(result.getString("LAST_NAME"));
						e.setEmpNumber(result.getInt("EMPLOYEE_ID"));
						e.setUserName(result.getString("USER_NAME"));
						e.setType(result.getInt("AUTHORITY"));
						return e;
					} else {
						return null;
					}
				} finally {
					if (stmt != null) {
						stmt.close();
					}
				}
			} finally {
				if (connection != null) {
					connection.close();
				}
			}
		} catch (SQLException ex) {
			System.out.println("Error in find " + name);
			ex.printStackTrace();
			return null;
		}
	}

	@GET
	@Path("/{id}")
	@Produces("application/xml")
	public Employee getEmployeeById(@PathParam("id") int id) {
		Connection connection = null;
		Statement stmt = null;
		try {
			try {
				connection = dataSource.getConnection();
				try {
					stmt = connection.createStatement();
					ResultSet result = stmt
							.executeQuery("SELECT * FROM EMPLOYEE where EMPLOYEE_ID = '"
									+ id + "'");
					if (result.next()) {
						Employee e = new Employee();
						e.setFirstName(result.getString("FIRST_NAME"));
						e.setLastName(result.getString("LAST_NAME"));
						e.setEmpNumber(result.getInt("EMPLOYEE_ID"));
						e.setUserName(result.getString("USER_NAME"));
						e.setType(result.getInt("AUTHORITY"));
						return e;
					} else {
						return null;
					}
				} finally {
					if (stmt != null) {
						stmt.close();
					}
				}
			} finally {
				if (connection != null) {
					connection.close();
				}
			}
		} catch (SQLException ex) {
			System.out.println("Error in find " + id);
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * return the login combos.
	 */
	@Override
	public Map<String, String> getLoginCombos() {
		Connection connection = null;
		Statement stmt = null;
		Map<String, String> combos = null;
		try {
			combos = new HashMap<String, String>();
			try {
				connection = dataSource.getConnection();
				stmt = connection.createStatement();
				ResultSet result = stmt
						.executeQuery("SELECT * FROM CREDENTIALS ");
				while (result.next()) {
					combos.put(result.getString("USER_NAME"),
							result.getString("PASSWORD"));
				}
				return combos;
			} finally {
				if (connection != null) {
					connection.close();
				}
			}
		} catch (SQLException ex) {

			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * get the current employee.
	 */
	@Override
	public Employee getCurrentEmployee() {
		// TODO Auto-generated method stub
		return employee;
	}

	/**
	 * Return the super user which is just the first one stored in the list.
	 */
	@Override
	public Employee getAdministrator() {
		Connection connection = null;
		Statement stmt = null;

		try {
			try {
				connection = dataSource.getConnection();
				stmt = connection.createStatement();
				ResultSet result = stmt
						.executeQuery("SELECT * FROM EMPLOYEE WHERE  AUTHORITY = 0 ");
				if (result.next()) {
					Employee e = new Employee();
					e.setFirstName(result.getString("FIRST_NAME"));
					e.setLastName(result.getString("LAST_NAME"));
					e.setEmpNumber(result.getInt("EMPLOYEE_ID"));
					e.setUserName(result.getString("USER_NAME"));
					e.setType(result.getInt("AUTHORITY"));
					return e;
				} else {
					return null;
				}
			} finally {
				if (connection != null) {
					connection.close();
				}
			}
		} catch (SQLException ex) {

			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * Verify if the user name match the password.
	 */
	@Override
	public boolean verifyUser(Credentials credential) {
		Map<String, String> combos = getLoginCombos();

		if (combos.containsKey(credential.getUserName())) {
			String pw = combos.get(credential.getUserName());
			if (credential.getPassword().equals(pw)) {
				employee = getEmployee(credential.getUserName());
				return true;
			} else
				return false;
		}
		return false;
	}

	/**
	 * logout and set the current employee to null.
	 */
	@Override
	public String logout(Employee employee) {
		this.employee = null;
		return "login";
	}

	/**
	 * delete the employee.
	 */
	@DELETE
	@Path("/{id}")
	@Consumes("application/xml")
	public void deleteEmpoyee(@PathParam("id") int empId) {
		Connection connection = null;
		PreparedStatement stmt = null;
		String userName = this.getEmployeeById(empId).getUserName();
		try {
			try {
				connection = dataSource.getConnection();
				try {
					stmt = connection
							.prepareStatement("DELETE FROM TIMESHEETS WHERE EMPLOYEE_ID =  ?");
					stmt.setInt(1, empId);
					stmt.executeUpdate();

				} finally {
					if (stmt != null) {
						stmt.close();
					}
				}
				try {
					stmt = connection
							.prepareStatement("DELETE FROM EMPLOYEE WHERE EMPLOYEE_ID =  ?");
					stmt.setInt(1, empId);
					stmt.executeUpdate();
				} finally {
					if (stmt != null) {
						stmt.close();
					}
				}
				try {
					stmt = connection
							.prepareStatement("DELETE FROM CREDENTIALS WHERE USER_NAME =  ?");

					stmt.setString(1, userName);
					stmt.executeUpdate();
				} finally {
					if (stmt != null) {
						stmt.close();
					}
				}
			} finally {
				if (connection != null) {
					connection.close();
				}
			}
		} catch (SQLException ex) {
			System.out.println("Error in remove " + employee);
			ex.printStackTrace();
		}
	}

	/**
	 * add employee to the list.
	 */
	@POST
	@Path("/{newPass}/{confirmPass}")
	@Consumes("application/xml")
	public void addEmployee(Employee newEmployee,
			@PathParam("newPass") String newPass,
			@PathParam("confirmPass") String confirmPass) {
		final int FIRST_NAME = 1;
		final int LAST_NAME = 2;
		final int USER_NAME = 3;
		final int AUTHORITY = 4;
		if (confirmPass != null && newPass != null
				&& confirmPass.equals(newPass)) {
			Connection connection = null;
			PreparedStatement stmt = null;
			try {
				try {
					connection = dataSource.getConnection();

					try {

						stmt = connection
								.prepareStatement("INSERT INTO EMPLOYEE(EMPLOYEE_ID,FIRST_NAME,LAST_NAME,USER_NAME,AUTHORITY)"
										+ "VALUES (0, ?, ?, ?, ?)");
						stmt.setString(FIRST_NAME, newEmployee.getFirstName());
						stmt.setString(LAST_NAME, newEmployee.getLastName());
						stmt.setString(USER_NAME, newEmployee.getUserName());
						stmt.setInt(AUTHORITY, newEmployee.getType());
						stmt.executeUpdate();
					} finally {
						if (stmt != null) {
							stmt.close();
						}
					}

					try {
						stmt = connection
								.prepareStatement("INSERT INTO CREDENTIALS(USER_NAME,PASSWORD)"
										+ "VALUES (?, ?)");

						stmt.setString(1, newEmployee.getUserName());
						stmt.setString(2, newPass);
						stmt.executeUpdate();
					} finally {
						if (stmt != null) {
							stmt.close();
						}
					}
				} finally {
					if (connection != null) {
						connection.close();
					}
				}
			} catch (SQLException ex) {
				System.out.println("Error in persist employee");
				ex.printStackTrace();
			}
		}
	}

	@PUT
	@Consumes("application/xml")
	public void merge(Employee employee) {
		final int FIRST_NAME = 1;
		final int LAST_NAME = 2;
		final int EMPLOYEE_ID = 3;

		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			try {
				connection = dataSource.getConnection();
				try {
					stmt = connection
							.prepareStatement("UPDATE EMPLOYEE "
									+ "SET FIRST_NAME = ? , LAST_NAME = ?  WHERE EMPLOYEE_ID = ?");
					stmt.setString(FIRST_NAME, employee.getFirstName());
					stmt.setString(LAST_NAME, employee.getLastName());
					stmt.setInt(EMPLOYEE_ID, employee.getEmpNumber());
					stmt.executeUpdate();
				} finally {
					if (stmt != null) {
						stmt.close();
					}
				}
			} finally {
				if (connection != null) {
					connection.close();
				}
			}
		} catch (SQLException ex) {
			System.out.println("Error in merge " + employee);
			ex.printStackTrace();
		}
	}

	@PUT
	@Path("/{newPass}/{confirmPass}")
	@Consumes("application/xml")
	public void changePass(Employee employee,
			@PathParam("newPass") String newPass,
			@PathParam("confirmPass") String confirmPass) {
		final int PASSWORD = 1;
		final int USER_NAME = 2;

		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			try {
				connection = dataSource.getConnection();
				if (confirmPass != null && newPass != null
						&& confirmPass.equals(newPass)) {
					try {
						stmt = connection
								.prepareStatement("UPDATE CREDENTIALS "
										+ "SET PASSWORD = ? WHERE USER_NAME = ?");
						stmt.setString(PASSWORD, newPass);
						stmt.setString(USER_NAME, employee.getUserName());
						stmt.executeUpdate();
					} finally {
						if (stmt != null) {
							stmt.close();
						}
					}
				}
			} finally {
				if (connection != null) {
					connection.close();
				}
			}
		} catch (SQLException ex) {
			System.out.println("Error in changing password " + employee);
			ex.printStackTrace();
		}
	}

	public Credentials getCredential() {
		return credential;
	}

	public void setCredential(Credentials credential) {
		this.credential = credential;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public void deleteEmpoyee(Employee userToDelete) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addEmployee(Employee newEmployee) {
		// TODO Auto-generated method stub

	}

}
