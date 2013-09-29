package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Trip extends Model {

	private static final long serialVersionUID = 1L;

	@Id
	public Long id;

	@ManyToOne
	public User author;

	@Required
	public String title;
	
	public String description;
	
	@OneToMany(cascade = CascadeType.ALL)
	public List<Itineray> itineries = new ArrayList<Itineray>();

	public static Finder<Long, Trip> find = new Finder<Long, Trip>(Long.class,
			Trip.class);

	public static List<Trip> findByAuthor(final User author) {
		return find.where().eq("author", author).findList();
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
