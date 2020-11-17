package examples.pubhub.dao;

import java.util.List;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import examples.pubhub.model.Book;
import examples.pubhub.model.BookTag;
import examples.pubhub.utilities.DAOUtilities;


public class BookTagDAOImpl implements BookTagDAO{
	
	Connection connection = null;	// Our connection to the database
	PreparedStatement stmt = null;	// We use prepared statements to help protect against SQL injection
	
	@Override
	public List<BookTag> getAllBookTags() {
		// TODO Auto-generated method stub
		List<BookTag> booktags = new ArrayList<>();

		try {
			connection = DAOUtilities.getConnection();	// Get our database connection from the manager
			String sql = "SELECT * FROM booktags";			// Our SQL query
			stmt = connection.prepareStatement(sql);	// Creates the prepared statement from the query
			
			ResultSet rs = stmt.executeQuery();			// Queries the database

			// So long as the ResultSet actually contains results...
			while (rs.next()) {
				// We need to populate a Book object with info for each row from our query result
				BookTag booktag = new BookTag();

				// Each variable in our Book object maps to a column in a row from our results.
				booktag.setId(rs.getString("id"));
				booktag.setTagName(rs.getString("tag_name"));
				booktag.setIsbn13(rs.getString("isbn_13"));

				// Finally we add it to the list of Book objects returned by this query.
				booktags.add(booktag);
			}
			
			rs.close();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			// We need to make sure our statements and connections are closed, 
			// or else we could wind up with a memory leak
			closeResources();
		}
		// return the list of Book objects populated by the DB.
		return booktags;
	}

	
	/*------------------------------------------------------------------------------------------------QUERY FROM ISBN12--*/


	@Override
	public List<BookTag> getBookTagByISBN(String isbn) {
		List<BookTag> booktags = new ArrayList<>();
		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM booktags WHERE isbn_13 = ?";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, isbn);
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				BookTag tag = new BookTag(rs.getString("id"),rs.getString("tag_name"),rs.getString("isbn_13"));
				booktags.add(tag);
			}

			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return (List<BookTag>) booktags;
	}
	/*------------------------------------------------------------------------------------------------ QUERY FROM ID--*/
	@Override
	public BookTag getBookTagById(String id) {
		BookTag tag = null;
		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM booktags WHERE id = ?";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, id);
			
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				tag = new BookTag();
				tag.setIsbn13(rs.getString("isbn_13"));
				tag.setId(rs.getString("id"));
				tag.setTagName(rs.getString("tag_name"));
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return tag;
	}

	/*------------------------------------------------------------------------------------------------ QUERY FROM SEARCH INPUT--*/
	@Override
	public List<BookTag> getBookTagByInput(String tagInput) {
		List<BookTag> booktags = new ArrayList<>();
		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM booktags WHERE tag_name LIKE '%"+ tagInput +"%'";
			stmt = connection.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				BookTag tag = new BookTag(rs.getString("id"),rs.getString("tag_name"),rs.getString("isbn_13"));
				booktags.add(tag);
			}

			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return (List<BookTag>) booktags;
		
	}
	
	/*------------------------------------------------------------------------------------------------ CRUD FOR DATABASE*/

	@Override
	public boolean addBookTag(BookTag booktag) {
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "INSERT INTO booktags VALUES (?, ?, ?)";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(2, booktag.getTagName());
			stmt.setString(1, booktag.getId());
			stmt.setString(3, booktag.getIsbn13());

	
			// If we were able to add our book to the DB, we want to return true. 
			// This if statement both executes our query, and looks at the return 
			// value to determine how many rows were changed
			if (stmt.executeUpdate() != 0)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
	}

	
	/*------------------------------------------------------------------------------------------------*/


	@Override
	public boolean editBookTag(BookTag booktag) {
		try {
			connection = DAOUtilities.getConnection();
			String sql = "UPDATE booktags SET tag_name=? WHERE id=?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, booktag.getTagName());
			stmt.setString(2, booktag.getId());
			
			System.out.println(stmt);
			
			if (stmt.executeUpdate() != 0)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
	}


	@Override
	public boolean deleteBookTagById(BookTag tag) {
		try {
			connection = DAOUtilities.getConnection();
			String sql = "DELETE FROM booktags WHERE id=?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, tag.getId());
			
			if (stmt.executeUpdate() != 0)
				return true;
			else
				return false;
		
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
	}
	
	@Override
	public boolean deleteBookTagById(String id) {
		try {
			connection = DAOUtilities.getConnection();
			String sql = "DELETE FROM booktags WHERE id=?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, id);
			
			if (stmt.executeUpdate() != 0)
				return true;
			else
				return false;
		
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
	}
	
	/*------------------------------------------------------------------------------------------------*/

	// Closing all resources is important, to prevent memory leaks. 
	// Ideally, you really want to close them in the reverse-order you open them
	private void closeResources() {
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException e) {
			System.out.println("Could not close statement!");
			e.printStackTrace();
		}
		
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			System.out.println("Could not close connection!");
			e.printStackTrace();
		}
	}
	
	}
