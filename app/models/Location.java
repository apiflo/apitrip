package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;

@Entity
public class Location extends Model {

	private static final long serialVersionUID = 1L;

	@Id
	public Long id;

	public String title;
	
	public Float latitude;
	
	public Float longitude;

	public String descripion;

	public static Finder<Long, Location> find = new Finder<Long, Location>(
			Long.class, Location.class);

	public static void create(Location location) {
		location.save();
	}

	public static void delete(Long id) {
		find.ref(id).delete();
	}

	public static void update(Location location) {
		location.save();
	}

	public Location duplicate() {
		Location clone =  new Location();
		clone.title = this.title;
		clone.latitude = this.latitude;
		clone.longitude = this.longitude;
		clone.descripion = this.descripion;
		
		return clone;
	}

}
