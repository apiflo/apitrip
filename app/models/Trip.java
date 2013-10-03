package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Expr;
import com.avaje.ebean.Expression;

@Entity
public class Trip extends Model {

	private static final long serialVersionUID = 1L;

	@Id
	public Long id;

	@ManyToOne(cascade = CascadeType.ALL)
	public User author;

	@Required
	public String title;
	
	public String description;
	
	public Date requestPublishDate;
	
	@OneToMany(cascade = CascadeType.ALL)
	public List<Itineray> itineries = new ArrayList<Itineray>();

	public static Finder<Long, Trip> find = new Finder<Long, Trip>(Long.class,
			Trip.class);

	public static List<Trip> findCustomTrip(final User author) {
		return find.where().and(Expr.eq("author", author), Expr.isNull("requestPublishDate")).findList();
	}
	
	public static List<Trip> findRequestPublishedTrip(final User author) {
		return find.where().and(Expr.eq("author", author), Expr.isNotNull("requestPublishDate")).findList();
	}

	public static void create(Trip trip) {
		trip.save();
	}

	public static void delete(Long id) {
		find.ref(id).delete();
	}
	
	public static void update(Trip trip) {
		Ebean.saveAssociation(trip, "itineries");
		Ebean.update(trip);
	}
	
	public Trip duplicate()  {
		
		Trip clone = new Trip();
		clone.author = this.author;
		clone.title = this.title;
		clone.description = this.description;
		this.itineries = new ArrayList<Itineray>();
		for(Itineray itineray : this.itineries){
			clone.itineries.add(itineray.duplicate());
		}
		
		return clone;
		
	
	}
	
}
