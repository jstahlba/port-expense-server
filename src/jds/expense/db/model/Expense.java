package jds.expense.db.model;

import org.json.JSONException;
import org.json.JSONObject;

import jds.expense.JSON;

public class Expense implements JSON {

	private int id;
	private double amount;
	private String category;
	private String subcategory;
	private String desc;
	private long date;

	public Expense(int id, 
			double amount, 
			String category, 
			String sub,
			String desc, 
			long unixtime) {
		this.id = id;
		this.amount = amount;
		this.category = category;
		this.subcategory = sub;
		this.desc = desc;
		this.date = unixtime;
	}

	public Expense(JSONObject obj) throws JSONException {
		if(obj.has("id"))
			id = obj.getInt("id");
		amount = obj.getDouble("amount");
		category=obj.getString("category");
		if(obj.has("subcategory"))
			subcategory = obj.getString("subcategory");
		desc=obj.getString("desc");
		date=obj.getLong("date");
	}

	@Override
	public JSONObject asJSON() throws JSONException {
		JSONObject obj = new JSONObject();
		obj.put("id", id);
		obj.put("amount", amount);
		obj.put("category", category);
		if(subcategory!=null)
			obj.put("subcategory", subcategory);
		obj.put("desc", desc);
		obj.put("date", date);

		return obj;
	}

	public long getId() {
		return id;
	}
	public void setId(int newId) {
		id = newId;
	}
	
	public double getAmount() {
		return amount;
	}
	public String getCategory() {
		return category;
	}
	public String getSubCategory() {
		return subcategory;
	}
	public String getDesc() {
		return desc;
	}
	public long getDate() {
		return date;
	}


}
