package F21SF_Coursework2;


/**
 * Manager class
 * Reads the 3 input files and starts the GUI
 * @author Antoine Degrandi / Tom Feraud / Jérémie Koster
 *
 */
public class Manager {
	private AccommodationList accList; // Declaration
	
	public Manager() {
		accList = new AccommodationList(); // Initialization
	}
	
	public void run() throws Exception {
		
		accList.readFile("cabinList.csv","hotelRoomList.csv","studentFlatList2");
		
		AccommodationListGUI gui = new AccommodationListGUI(accList);
        gui.setVisible(true);
	}

}