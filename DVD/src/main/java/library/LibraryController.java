package library;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LibraryController {
	private LibraryView libraryView;
	private LibraryDao libraryDao;
	
//	"DVD Library Main Menu"
//	1.Display All  DVDs"
//	2.Add a DVD to the library"
//	3.Remove a DVD from the library"
//	4.Edit a DVD's entry in the Library"
//	5.Search for a DVD by title"
//	6.Exit"
	
	public void run() {
		boolean running = true;
		int choice = 0;
		
		try {
			while(running) {
				choice = getChoice();	
				switch(choice) {
				case 1:
					displayAll();
					break;
				case 2:
					addDVD();
					break;
				case 3:
					removeDVD();
					break;
				case 4:
					editDVD();
					break;
				case 5:
					searchDVDByTitle();
					break;
				case 6:
					running = false;
					goodBye();
					break;
				default:
					tryAgainMessage();
				}
			}
		} catch (Exception e) {
			libraryView.displayError(e.getMessage());
		}
	}
	
	private void goodBye() {
	libraryView.byeBye();
	
}

	private void tryAgainMessage() {
		libraryView.tryAgainMessage();
	
}

	//Receive menu selection from user
		private int getChoice() {
			return libraryView.menu();
		}

		// 1.Display All  DVDs
		private void displayAll() throws LibraryDaoException{
			libraryView.displayAllDVDs(libraryDao.getAll());
		}
		
		// 2.Add a DVD to the library"
		private void addDVD() throws LibraryDaoException{
			DVD tempDVD = libraryView.getDVDInfo();
			if(!tempDVD.getTitle().trim().equals("")) {
				if(libraryDao.add(tempDVD)) {
					libraryView.success();
					return;
				}
			}
			libraryView.failed();
		}
		
		
		
		// 3.Remove a DVD from the library"
		private void removeDVD() throws LibraryDaoException{
			String title = libraryView.request("Title");
			if (libraryDao.remove(title)) {
				libraryView.success();
				return;
			};
			libraryView.failed();
		}
		
		private void editDVD() throws LibraryDaoException{
			String title = libraryView.request("Title");
			int field = libraryView.getField();
			String change = libraryView.request("new value");
			if(libraryDao.edit(title, field, change) != null) {
				libraryView.success();
				return;
			}
			libraryView.failed();

		}
		// 5.Search for a DVD by title"
		private void searchDVDByTitle() throws LibraryDaoException{
			libraryView.displayDVD(libraryDao.get(libraryView.request("name")));
		}

}
