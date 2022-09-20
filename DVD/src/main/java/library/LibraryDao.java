package library;

import java.util.List;

/**
 * This interface defines the methods that must be implemented by any class that wants to play the
 * role of DAO in the application.
 * 
 * @author Yu
 *
 */
public interface LibraryDao {
	
	public boolean add(DVD dvd) throws LibraryDaoException;
	
	public boolean remove(String title) throws LibraryDaoException;
	
	public DVD get(String title) throws LibraryDaoException;
	
	public List<DVD> getAll() throws LibraryDaoException;

	public DVD edit(String title, int field, String change) throws LibraryDaoException;
}
