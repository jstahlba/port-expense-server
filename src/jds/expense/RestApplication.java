package jds.expense;

import jds.expense.resource.CategoriesResource;
import jds.expense.resource.ExpenseResource;
import jds.expense.resource.ExpensesResource;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

public class RestApplication extends Application {

	    /**
	     * Creates a root Restlet that will receive all incoming calls.
	     */
	    @Override
	    public Restlet createInboundRoot() {
	        Router router = new Router(getContext());
	        
	        router.attach("/category/list", CategoriesResource.class);
	        
	        router.attach("/list", ExpensesResource.class);
	        
	        router.attach("/", ExpenseResource.class);
	        router.attach("/{id}", ExpenseResource.class);

	        return router;
	    }
	  
}
