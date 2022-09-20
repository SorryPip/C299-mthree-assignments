package library;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class LibraryView {
	private UserIO userIO;
	
	public int menu() {
		userIO.print(
				"\nDVD Library Main Menu"
				+"\n1.\tDisplay All  DVDs"
				+"\n2.\tAdd a DVD to the library"
				+"\n3.\tRemove a DVD from the library"
				+"\n4.\tEdit a DVD's entry in the Library"
				+"\n5.\tSearch for a DVD by title"
				+"\n6.\tExit\n"
				);
		return userIO.readInt("Select an option please");
	}
	
	public DVD getDVDInfo() {
		userIO.readString("");	// java is fked, this is here cus im bad at IO
		userIO.print("====Adding DVD====");
		String name = userIO.readString("Please enter DVD Title");
        String date = userIO.readString("Please enter release date");
        String rating = userIO.readString("Please enter the DVD's MPAA Rating");
        String studio = userIO.readString("Please enter the Studio");
        String director = userIO.readString("Please enter the director's name");
        String note = userIO.readString("Please enter user rating");
        userIO.print("==================\n");
        return new DVD(name, date, rating, studio, director, note);
	}
	
	public void displayAllDVDs(List<DVD> DVDList) {
		userIO.print("====Entire DVD Library====");
		if(!DVDList.isEmpty()) {
			DVDList.stream().forEach(x -> userIO.print(x.toString()));
		}
		userIO.print("==========================");
	}

	public void displayError(String message) {
		// TODO Auto-generated method stub
		
	}

	public void tryAgainMessage() {
		userIO.print("Command not recognised, please try again");
		
	}

	public void byeBye() {
		userIO.print("Bye bye");
		
	}
	
	public void enterToContinue() {
		userIO.readString("Enter to Continue");
	}

	public void failed() {
		userIO.print("Operation failed, please enter commands correctly");
		
	}

	public void success() {
		userIO.print("Operation success");	
	}

	public String request(String msg) {
		userIO.readString("");	// here to eat up a line cus my java io prowess are nonexistent
		String string = userIO.readString("Enter "+msg+" of DVD");
		return string;
	}

	public int getField() {
		return userIO.readInt(
				"\nEnter 1 to change the title"
				+"\nEnter 2 to change Release Date"
				+"\nEnter 3 to change MPAA Rating"
				+"\nEnter 4 to change Director"
				+"\nEnter 5 to change Studio"
				+"\nEnter 6 to change Note");
	}

	public void displayDVD(DVD dvd) {
		// not sure if it is wrong to put code like this in the view instead of the controller.
		if(dvd != null) {
			userIO.print(dvd.toString());
			return;
		}
		failed();
	}
	
}
