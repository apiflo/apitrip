package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Itineray extends Model {

	private static final long serialVersionUID = 1L;

	@Id
	public Long id;

	public String title;

	@ManyToOne
	public Trip trip;

	public static Finder<Long, Itineray> find = new Finder<Long, Itineray>(
			Long.class, Itineray.class);

	public static void create(Itineray itinery) {
		itinery.save();
	}

	public static void delete(Long id) {
		find.ref(id).delete();
	}

	public static void update(Itineray itinery) {
		itinery.save();
	}

}
