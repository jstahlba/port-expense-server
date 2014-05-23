package jds.expense.util;

import java.util.Collection;
import java.util.Iterator;

import jds.expense.JSON;

import org.json.JSONArray;
import org.json.JSONException;

public class Util {

	public static JSONArray listAsJson(Collection<JSON> expense) {
		if(expense == null)
			return null;
		
		JSONArray result = new JSONArray();
		
		Iterator<JSON> it = expense.iterator();
		while(it.hasNext()) {
			JSON obj = it.next();
			try {
				result.put(obj.asJSON());
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

}
