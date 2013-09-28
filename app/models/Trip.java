package models;

import java.util.ArrayList;
import java.util.List;

import play.data.validation.Constraints.Required;

public class Trip {
	
	  public Long id;
	  
	  @Required
	  public String title;
	  
	  public static List<Trip> all() {
	    return new ArrayList<Trip>();
	  }
	  
	  public static void create(Trip task) {
	  }
	  
	  public static void delete(Long id) {
	  }

}
