package controllers;

import models.Trip;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import be.objectify.deadbolt.java.actions.Group;
import be.objectify.deadbolt.java.actions.Restrict;

public class TripController extends Controller {
	
	static Form<Trip> createTripForm = Form.form(Trip.class);
	
	@Restrict(@Group(Application.USER_ROLE))
	public static Result myTrips(){
		return ok(views.html.trip.mytrips.render());
	}
	
	@Restrict(@Group(Application.USER_ROLE))
	public static Result createTrip(){
		return ok(views.html.trip.createTrip.render(createTripForm));
	}
	
	public static Result doCreateTrip() {
		  Form<Trip> filledForm = createTripForm.bindFromRequest();
		  if(filledForm.hasErrors()) {
		    return badRequest(
		      views.html.trip.createTrip.render(filledForm)
		    );
		  } else {
		    Trip.create(filledForm.get());
		    return redirect(routes.TripController.myTrips());  
		  }
		}

}
