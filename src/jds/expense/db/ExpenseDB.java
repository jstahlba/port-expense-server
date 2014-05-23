package jds.expense.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import jds.expense.db.model.Expense;

public class ExpenseDB extends BaseDB{

	public static Collection<Expense> getAllExpenses() {
		try {
			List<Expense> results = new ArrayList<Expense>();

			Connection connection = connect();

			PreparedStatement ps = connection.prepareStatement("SELECT id,amount,category,subcategory,description,date FROM Expense");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int id = rs.getInt(1);
				double amount = rs.getDouble(2);
				String category = rs.getString(3);
				String sub = rs.getString(4);
				String desc = rs.getString(5);
				long unixtime = rs.getLong(6);

				Expense newExpense = new Expense(id, amount, category, sub, desc, unixtime);
				results.add(newExpense);
			}

			rs.close();
			connection.close();

			return results;			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static boolean updateCategory(String category) {
		boolean success = false;

		try {
			Connection connection = connect();
			PreparedStatement ps = 
					connection.prepareStatement("REPLACE INTO Category (name) VALUES (?)");
			ps.setString(1, category);
			ps.executeUpdate();
			success = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return success;
	}

	public static Expense addExpense(Expense newExpense) {
		Expense returnValue = null;
		if(!updateCategory(newExpense.getCategory())) {
			return null;
		}

		if(newExpense.getSubCategory() != null && !"".equals(newExpense.getSubCategory())) {
			if(!updateCategory(newExpense.getSubCategory())) {
				return null;
			}
		}

		try {
			Connection connection = connect();
			PreparedStatement ps = 
					connection.prepareStatement("INSERT INTO Expense (amount,category,subcategory,description,date) VALUES (?,?,?,?,?)",
							Statement.RETURN_GENERATED_KEYS);

			ps.setDouble(1, newExpense.getAmount());
			ps.setString(2, newExpense.getCategory());
			ps.setString(3, newExpense.getSubCategory());
			ps.setString(4, newExpense.getDesc());
			ps.setLong(5, newExpense.getDate());

			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();

			if (rs.next()) {
				int newId = rs.getInt(1);
				newExpense.setId(newId);
				returnValue = newExpense;
			} else {
				// throw an exception from here
			}
			rs.close();
			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnValue;
	}

	
	public static Collection<Expense> searchExpenses(String searchKey) {
		try {
			List<Expense> results = new ArrayList<Expense>();

			Connection connection = connect();

			PreparedStatement ps = connection.prepareStatement("SELECT id,amount,category,subcategory,description,date FROM Expense Where category like ? or subcategory like ? or description like ?");
			ps.setString(1, "%"+searchKey+"%");
			ps.setString(2, "%"+searchKey+"%");
			ps.setString(3, "%"+searchKey+"%");

			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int id = rs.getInt(1);
				double amount = rs.getDouble(2);
				String category = rs.getString(3);
				String sub = rs.getString(4);
				String desc = rs.getString(5);
				long unixtime = rs.getLong(6);

				Expense newExpense = new Expense(id, amount, category, sub, desc, unixtime);
				results.add(newExpense);
			}

			rs.close();
			connection.close();

			return results;			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static List<String> searchCategories(String searchKey) {
		try {
			List<String> results = new ArrayList<String>();

			Connection connection = connect();

			PreparedStatement ps = connection.prepareStatement("SELECT name FROM Category Where name like ?");
			ps.setString(1, "%"+searchKey+"%");

			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				results.add(rs.getString(1));
			}

			rs.close();
			connection.close();

			return results;			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static List<String> getAllCategories() {
		try {
			List<String> results = new ArrayList<String>();

			Connection connection = connect();
			PreparedStatement ps = connection.prepareStatement("SELECT name FROM Category ");

			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				results.add(rs.getString(1));
			}

			rs.close();
			connection.close();

			return results;			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
