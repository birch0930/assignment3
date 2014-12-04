package ca.bcit.infosys.access;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import ca.bcit.infosys.access.TimesheetManager;

@ApplicationPath("/services")
public class TimesheetApplication extends Application {
	   private Set<Class<?>> classes = new HashSet<Class<?>>();

	   public TimesheetApplication()
	   {
		   classes.add(TimesheetManager.class);
		   classes.add(EmployeeManager.class);
	   }

	   @Override
	   public Set<Class<?>> getClasses()
	   {
	      return classes;
	   }
}
