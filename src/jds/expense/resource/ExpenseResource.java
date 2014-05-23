package jds.expense.resource;

import jds.expense.db.ExpenseDB;
import jds.expense.db.model.Expense;

import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

public class ExpenseResource extends ServerResource { 
	@Post("json")
	public Representation addRepersentation(Representation entity) {
		Expense newExpense = null;
		try {
			JsonRepresentation represent = new JsonRepresentation(entity);

			JSONObject jObject = represent.getJsonObject();
			newExpense = new Expense(jObject);

		} catch (Exception e) {
			this.setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
			return new StringRepresentation("Error") ;
		}

		Expense insertedExpense = ExpenseDB.addExpense(newExpense);
		if(insertedExpense == null) { //Couldn't insert record
			this.setStatus(Status.SERVER_ERROR_INTERNAL);
			return new StringRepresentation("Error") ;
		}

		try {
			return new JsonRepresentation(insertedExpense.asJSON());
		} catch (JSONException e) {
			this.setStatus(Status.SERVER_ERROR_INTERNAL);
			return new StringRepresentation("Error") ;
		}
	}
}
