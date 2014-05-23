package jds.expense.resource;

import java.util.List;

import jds.expense.db.ExpenseDB;

import org.json.JSONArray;
import org.restlet.data.Form;
import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class CategoriesResource extends ServerResource {

	
	@Get
	public Representation represent() {
		Form queryParams = getRequest().getResourceRef().getQueryAsForm(); 
		String searchKey = queryParams.getFirstValue("search");

		List<String> categories = null;
		if(searchKey != null && !"".equals(searchKey)) {
			categories = ExpenseDB.searchCategories(searchKey);
		} else {
			categories = ExpenseDB.getAllCategories();
		} 
		if(categories ==  null) {
			this.setStatus(Status.SERVER_ERROR_INTERNAL);
			return new StringRepresentation("Error") ;
		}
		
		JSONArray data = new JSONArray();
		for(String cat: categories) {
			data.put(cat);
		}
		return new JsonRepresentation(data);
	}
}
