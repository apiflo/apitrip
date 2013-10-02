package controllers;

import models.Trip;
import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import be.objectify.deadbolt.java.actions.Group;
import be.objectify.deadbolt.java.actions.Restrict;

public class TripController extends Controller {

	@Restrict(@Group(Application.USER_ROLE))
	public static Result myTrips() {
		final User user = Application.getLocalUser(session());
		return ok(views.html.trip.mytrips.render(Trip.findByAuthor(user)));
	}

	static Form<Trip> createTripForm = Form.form(Trip.class);

	@Restrict(@Group(Application.USER_ROLE))
	public static Result createTrip() {
		return ok(views.html.trip.createTrip.render(createTripForm));
	}

	@Restrict(@Group(Application.USER_ROLE))
	public static Result doCreateTrip() {
		Form<Trip> filledForm = createTripForm.bindFromRequest();
		if (filledForm.hasErrors()) {
			return badRequest(views.html.trip.createTrip.render(filledForm));
		} else {
			Trip tripToCreate = filledForm.get();
			tripToCreate.author = Application.getLocalUser(session());
			
			Trip.create(tripToCreate);
			return redirect(routes.TripController.myTrips());
		}
	}

	@Restrict(@Group(Application.USER_ROLE))
	public static Result doDeleteTrip(Long id) {
		Trip.delete(id);
		return redirect(routes.TripController.myTrips());
	}


	@Restrict(@Group(Application.USER_ROLE))
	public static Result editTrip(Long id) {
		Form<Trip> editTripForm = Form.form(Trip.class);
		return ok(views.html.trip.editTrip.render(id, editTripForm.fill(Trip.find.byId(id))));
	}

	@Restrict(@Group(Application.USER_ROLE))
	public static Result doEditTrip(Long id) {
		Form<Trip> tripForm = Form.form(Trip.class).bindFromRequest();
		
        if(tripForm.hasErrors()) {
            return badRequest(views.html.trip.editTrip.render(id, tripForm.fill(Trip.find.byId(id))));
        }
        
        Trip updatedTrip = tripForm.get();
        updatedTrip.id = id;
        
        Trip.update(updatedTrip);
        
        flash("success", "Computer " + tripForm.get().title + " has been updated");
		
		return redirect(routes.TripController.myTrips());
	}

}