package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Trip extends Model {
	
	private static final long serialVersionUID = 1L;

	@Id
	  public Long id;
	  
	  @Required
	  public String title;
	  
	  public static Finder<Long,Trip> find = new Finder<Long, Trip>(Long.class, Trip.class);
	  
	  public static List<Trip> all() {
	    return find.all();
	  }
	  
	  public static void create(Trip trip) {
		  trip.save();
	  }
	  
	  public static void delete(Long id) {
		  find.ref(id).delete();
		}
	  
	  public static void update(Trip trip) {
		  trip.save();
		}

}
