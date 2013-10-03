package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import play.db.ebean.Model;

@Entity
public class Itineray extends Model {

	private static final long serialVersionUID = 1L;

	@Id
	public Long id;

	public String title;
	
	@OneToMany(cascade = CascadeType.ALL)
	public List<Location> locations = new ArrayList<Location>();

	public Itineray duplicate() {
		Itineray clone = new Itineray();
		clone.title = this.title;
		clone.locations = new ArrayList<Location>();
		
		for(Location location : this.locations){
			clone.locations.add(location.duplicate());
		}
		
		return clone;
	}



}
