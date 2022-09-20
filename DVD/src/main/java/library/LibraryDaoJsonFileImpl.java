package library;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
 
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * text file implementation of the LibraryDao interface
 * 
 * @author Yu
 *
 */
public class LibraryDaoJsonFileImpl implements LibraryDao {
	
	private Map<String, DVD> DVDLibrary = new HashMap<>();
	
	public static final String FILE_NAME = "DvdLibrary.json";
	
	public boolean add(DVD dvd) throws LibraryDaoException {
		boolean complete = false;
		loadFile();
		if(!DVDLibrary.containsKey(dvd.getTitle())) {
			DVDLibrary.put(dvd.getTitle(), dvd);
			complete = true;
		}
		persistToFile();
		return complete;
	}

	public boolean remove(String title) throws LibraryDaoException {
		boolean complete = false;
		loadFile();
		if(DVDLibrary.containsKey(title)){
			DVDLibrary.remove(title);
			complete = true;
		}	
		persistToFile();
		return complete;
	}

	public DVD get(String title) throws LibraryDaoException {
		loadFile();
		return DVDLibrary.get(title);
	}

	public List<DVD> getAll() throws LibraryDaoException {
		loadFile();
		return new ArrayList<DVD>(DVDLibrary.values());
	}

	@Override
	public DVD edit(String title, int field, String change) throws LibraryDaoException {
		loadFile();
		DVD tempDVD = DVDLibrary.get(title);
		switch(field){
		case 1:
			tempDVD.setTitle(change);
			break;
		case 2:
			tempDVD.setReleaseDate(String.valueOf(LocalDate.parse(change)));
			break;
		case 3:
			tempDVD.setMpaaRating(change);
			break;
		case 4:
			tempDVD.setDirectorName(change);
			break;
		case 5:
			tempDVD.setStudio(change);
			break;
		case 6:
			tempDVD.setUserRatingNote(change);
			break;
		default:
			tempDVD = null;
		}
		
		if(tempDVD != null) {
			DVDLibrary.remove(title);
			DVDLibrary.put(tempDVD.getTitle(), tempDVD);
			persistToFile();
		}
		
		return tempDVD;
	}

	@SuppressWarnings("unchecked")
	public void persistToFile() {
		JSONArray jsonArray = new JSONArray();
		for(DVD dvd: DVDLibrary.values()) {
			jsonArray.add(toJSONObject(dvd));
		}
		
		//Write JSON file
        try (FileWriter file = new FileWriter("DvdLibrary.json")) {
            //We can write any JSONArray or JSONObject instance to the file
            file.write(jsonArray.toJSONString()); 
            file.flush();
            // System.out.println("done b");
 
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	private void loadFile() throws LibraryDaoException {
		JSONParser jsonParser = new JSONParser();
		try (FileReader reader = new FileReader("DvdLibrary.json")){
			JSONArray DVDList = (JSONArray) jsonParser.parse(reader);
			for(Object jsonDVD: DVDList) {
				DVD tempDVD = parseDVD((JSONObject) jsonDVD);
				DVDLibrary.put(tempDVD.getTitle(), tempDVD);
			}
		} catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
	}

	private DVD parseDVD(JSONObject dvd) {
		return new DVD(
				(String)dvd.get("title"),
				(String)dvd.get("releaseDate"),
				(String)dvd.get("mpaaRating"),
				(String)dvd.get("directorName"),
				(String)dvd.get("studio"),
				(String)dvd.get("userRatingNote")
				);
	}
	
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
}
