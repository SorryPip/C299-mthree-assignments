package library;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * We're about to start building the application, but before we do, we need to review the MVC rules
 * of the game. Keep these in mind not only as we build this application but also as you build your
 * other applications throughout the course.
 * 
 * The Controller is the "brains of the operation." It knows what needs to be done, when it needs 
 * to be done, and what component can do it. It acts like a general contractor, directing work but 
 * never doing the work itself.
 * 
 * The View (and any helper classes) is responsible for all user interaction. No other component is
 * allowed to interact with the user.
 * 
 * The DAO is responsible for the persistence and retrieval of Student data.
 * 
 * The DTO is the container for Student data. The DAO and DTO comprise the Model.
 * 
 * All components (Model, View, and Controller) can use DTOs.
 * 
 * The Controller can talk with both the View and the DAO.
 * 
 * The DAO cannot access the View.
 * 
 * The View cannot access the DAO.
 * 
 * @author Yu
 *
 */
public class Main {

	public static void main(String[] args) throws LibraryDaoException {
		// TODO Auto-generated method stub
		System.out.println("DVD Library APP");
		
		//createJsonFile();
		//loadFile();
		
		UserIO consoleIO = new UserIOConsoleImpl();
		LibraryDao libraryDao = new LibraryDaoJsonFileImpl();
		LibraryView libraryView = new LibraryView(consoleIO);
		LibraryController controller = new LibraryController(libraryView, libraryDao);
		controller.run();
	}
	/*
	 * private String title;
	private LocalDate releaseDate;
	private String mpaaRating;
	private String directorName;
	private String studio;
    private String userRatingNote;
	 */
	@SuppressWarnings("unchecked")
	public static void createJsonFile() {
		List<DVD> library = Arrays.asList(
			new DVD("Star Wars: A New Hope", "1977-12-27", "PG", "George Lucas", "Lucas Film", "Best bible clone"),
			new DVD("Star Wars: The Empire Strikes Back", "1980-04-20", "PG", "George Lucas", "Lucas Film", "Best sequal"),
			new DVD("Star Wars: Return of the Jedi", "1983-06-02", "PG", "George Lucas", "Lucas Film", "Best conclusion to a trillogy")
			);
		
		JSONArray jsonArray = new JSONArray();
		for(DVD dvd: library) {
			jsonArray.add(toJSONObject(dvd));
		}
		jsonArray.forEach(System.out::println);
		
		//Write JSON file
        try (FileWriter file = new FileWriter("DvdLibrary.json")) {
            //We can write any JSONArray or JSONObject instance to the file
            file.write(jsonArray.toJSONString()); 
            file.flush();
            System.out.println("done b");
 
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
//	private String title;
//	private LocalDate releaseDate;
//	private String mpaaRating;
//	private String directorName;
//	private String studio;
//    private String userRatingNote;
	
	@SuppressWarnings("unchecked")
	public static JSONObject toJSONObject(DVD dvd) {
        JSONObject obj = new JSONObject();
        try {
        	obj.put("title", dvd.getTitle());
            obj.put("releaseDate", dvd.getReleaseDate());
            obj.put("mpaaRating", dvd.getMpaaRating());
            obj.put("directorName", dvd.getDirectorName());
            obj.put("studio", dvd.getStudio());
            obj.put("userRatingNote", dvd.getUserRatingNote());
        } catch (Exception e) {
            System.out.println("You done fucked up b");
        }
        return obj;
    }
	
	@SuppressWarnings("unchecked")
	private static void loadFile() throws LibraryDaoException {
		JSONParser jsonParser = new JSONParser();
		
		try (FileReader reader = new FileReader("DvdLibrary.json")){
			Object obj = jsonParser.parse(reader);
			 
            JSONArray DVDList = (JSONArray) obj;
			//JSONArray DVDList = (JSONArray) jsonParser.parse(reader);
			DVDList.forEach(x -> parseDVD((JSONObject) x));
		} catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
	}



	private static void parseDVD(JSONObject dvd) {
		System.out.println(dvd);
		DVD tempDVD = new DVD(
				(String)dvd.get("title"),
				(String)dvd.get("releaseDate"),
				(String)dvd.get("mpaaRating"),
				(String)dvd.get("directorName"),
				(String)dvd.get("studio"),
				(String)dvd.get("userRatingNote")
				);
	}

}
