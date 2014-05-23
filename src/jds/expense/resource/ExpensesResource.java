package jds.expense.resource;

import java.util.Collection;
import java.util.List;

import jds.expense.db.ExpenseDB;
import jds.expense.util.Util;

import org.restlet.data.Form;
import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class ExpensesResource extends ServerResource {
	@Get
	public Representation represent() {
		Form queryParams = getRequest().getResourceRef().getQueryAsForm(); 
		String searchKey = queryParams.getFirstValue("search");

		Collection expense = null;
		if(searchKey != null && !"".equals(searchKey)) {
			expense = ExpenseDB.searchExpenses(searchKey);
		} else {
			expense = ExpenseDB.getAllExpenses();
		} 
		if(expense ==  null) {
			this.setStatus(Status.SERVER_ERROR_INTERNAL);
			return new StringRepresentation("Error") ;
		}
		return new JsonRepresentation(Util.listAsJson(expense));
	}
}
