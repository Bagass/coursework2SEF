package F21SF_Coursework2;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Antoine Degrandi / Tom Feraud / Jérémie Koster 
 * F21SF_Assignment 2
 *
 */
public class AccommodationList {
	private ArrayList<Accommodation> accommodationList;

	
		/**
		 * Create a accommodationList object and "instanciate" the instance variable cabinList
		 */
		public AccommodationList() {
			accommodationList = new ArrayList<Accommodation> ();
		}
			
		/**
		 * @return String containing the list of the accommodations + the list of the most popular ones
		 * @throws Exception
		 */
		public String listDetails() throws Exception{
			
			String Table = "Here is the list of Accommodations on the site\nType         N  Price       Owner  Number of bed \n";
			int cabinCounter = 0;
			int hotelRoomCounter = 0;
			int studentFlatCounter = 0;
			
			for (Accommodation acc  : accommodationList){
				
				Table += String.format("%-13s", acc.getClass().getSimpleName());
				Table += String.format("%-4d", acc.getAccommodationNumber());
				Table += String.format("%-8.2f", acc.getCost());
				Table += String.format("%-3s", "£");
				Table += String.format("%-7s", acc.getOwner().getInitials());
				Table += String.format("%-15d", acc.getNumberOfBeds());
				Table += "\n";
				
				switch(acc.getClass().getSimpleName()){
					case("Cabin"):
						cabinCounter++;
					break;
					case("StudentFlat"):
						studentFlatCounter++;
					break;
					case("HotelRoom"):
						hotelRoomCounter++;
					break;
				}
			}
			return Table + "\n\n" + getFullDetailsList(max(cabinCounter, hotelRoomCounter, studentFlatCounter));
		}
		
		/**
		 * returns the type of the most popular accommodations of the list
		 * @param cab integer representing the number of cabin in the list
		 * @param hot integer representing the number of hotel room in the list
		 * @param stu integer representing the number of student flat in the list
		 * @return String the most popular type of accommodation
		 */
		private String max(int cab, int hot, int stu){
			String max = "Cabin";
			if(hot>cab && hot>stu)
				max = "HotelRoom";
			if(stu>cab && stu>hot)
				max = "StudentFlat";
			return max;
		}
		
		
		
		/**
		 * Returns the List of the most popular accommodations in the list
		 * @param accType String containing the type of accommodation
		 * @return String containing the list of the most popular accommodation in the list
		 * @throws Exception
		 */
		/**
		 * @param accType
		 * @return
		 * @throws Exception
		 */
		private String getFullDetailsList(String accType) throws Exception{
			String Table = "Here is the list of the most popular accomodations:\n";
			switch(accType){
			case("Cabin"):
				Table += "Type         N   Price      Owner  Number of bed  Number of double bed  Year of creation  TV  Ensuite \n";
				for (Accommodation acc  : accommodationList){
					if(acc.getClass().getSimpleName().equals(accType)){
						Table += String.format("%-13s", acc.getClass().getSimpleName());
						Table += String.format("%-4d", acc.getAccommodationNumber());
						Table += String.format("%-8.2f", acc.getCost());
						Table += String.format("%-3s", "£");
						Table += String.format("%-7s", acc.getOwner().getInitials());
						Table += String.format("%-15d", acc.getNumberOfBeds());
						Table += String.format("%-22d", ((Cabin) acc).getNumberOfDoubleBed());
						Table += String.format("%-18d", ((Cabin) acc).getcreationYear());
						if(((Cabin) acc).getTV()){
							Table += String.format("%-5s", "YES");
						}
						else
							Table += String.format("%-5s", "NO");
						if(((Cabin) acc).getCabinType().equals("ensuite small cabin") || ((Cabin) acc).getCabinType().equals("ensuite large cabin")){
							Table += String.format("%s", "Ensuite");
						}
						else if(((Cabin) acc).getCabinType().equals("simple small cabin") || ((Cabin) acc).getCabinType().equals("simple large cabin")){
							Table += String.format("%s", "Non Ensuite");
						}
						else{
							Table += "Undefined";
						}
						Table += "\n";
					}
				}
				break;
				
			case("HotelRoom"):
				Table += "Type         NÂ°	TYPE		VIEW  		 BEDS	 COST		COMMENTS\n";
				for (Accommodation acc  : accommodationList){
					if(acc.getClass().getSimpleName().equals(accType)){
						Table += String.format("%-13s", acc.getClass().getSimpleName());
						Table += String.format("%-8s", ((HotelRoom) acc).getAccommodationNumber());
						Table += String.format("%-16s", ((HotelRoom) acc).getAccommodationType());
						Table += String.format("%-18s", ((HotelRoom) acc).getRoomView());
						Table += String.format("%-7d" + "£", ((HotelRoom) acc).getNumberOfBeds());
						Table += String.format("%.2f", ((HotelRoom) acc).getCost());

						if (((HotelRoom) acc).getInfoJacuzzi()) {
							Table += String.format("%-10s", "	Jacuzzi");
						}
						Table += "\n";
					}
				}
				break;
				
			case("StudentFlat"):
				Table += "Type         FLAT_NB  COST     WARDEN  ROOM_NB  BEDS_NB \n";
				for (Accommodation acc  : accommodationList){
					if(acc.getClass().getSimpleName().equals(accType)){
						Table += String.format("%-13s", acc.getClass().getSimpleName());
						Table += String.format("%-9d", acc.getAccommodationNumber());
						Table += String.format("%-9.2f", acc.getCost() );
						Table += String.format("%-8s", acc.getOwner().getInitials());
						Table += String.format("%-9d", ((StudentFlat) acc).getNumberOfRoom());
						Table += String.format("%-4s", acc.getNumberOfBeds());
						Table += "\n";
					}
				}
			}
			return Table;
		}
		
		
		
		
		
		
		/**
		 * Returns the list of accommodations with the number of bed provided in parameter
		 * @param numberOfBed integer representing the number of bed in the accommodation
		 * @return String containing the list of accommodations with the number of bed provided in parameter
		 * @throws Exception
		 */
		public String findByNumberOfBed(int numberOfBed) throws Exception{
			String Table = "Here is the list of the accommodations with "+ numberOfBed +" bed(s):\nType                N      Price    Owner    Number of bed(s) \n";
			for (Accommodation acc  : accommodationList){
				
				if(acc.getNumberOfBeds() == numberOfBed){
					Table += String.format("%-13s", acc.getClass().getSimpleName());
					Table += String.format("%8d", acc.getAccommodationNumber());
					Table += String.format("%10.2f", acc.getCost());
					Table += String.format("%-6s", "£");
					Table += String.format("%-16s", acc.getOwner().getInitials());
					Table += String.format("%-15d", acc.getNumberOfBeds());
					Table += "\n";
				}
			}
			return Table;
		}
		
		
		
		/**
		 * Returns the list of accommodation sorted by Price
		 * @return String containing the list of accommodation sorted by Price
		 * @throws Exception
		 */
		public String sortedListCost() throws Exception{
			String Table = "Here is the list of the accommodations sorted by price:\nType                N      Price    Owner    Number of bed(s) \n";
			ArrayList<Double> copyList = new ArrayList<Double> ();
			
			for (Accommodation acc : accommodationList){
				copyList.add(acc.getCost());
			}
			
			while(copyList.size() != 0){
				double mostExpensive = 0;
				for(double element : copyList){
					if(mostExpensive < element){
						mostExpensive = element;
					}
				}
				
				for(Accommodation acc : accommodationList){
					if(acc.getCost() == mostExpensive){
						Table += String.format("%-13s", acc.getClass().getSimpleName());
						Table += String.format("%8d", acc.getAccommodationNumber());
						Table += String.format("%10.2f", acc.getCost());
						Table += String.format("%-6s", "£");
						Table += String.format("%-16s", acc.getOwner().getInitials());
						Table += String.format("%-15d", acc.getNumberOfBeds());
						Table += "\n";
						for(int i = 0; i<copyList.size(); i++){
							if(mostExpensive == copyList.get(i)){
								copyList.remove(i);
							}
						}
					}
				}
			}
			return Table;
		}
		
		
		/**
		 * Returns the list of accommodations sorted by number of bed
		 * @return String containing the list of accommodations sorted by number of bed
		 * @throws Exception
		 */
		public String sortedListByNumberOfBed() throws Exception{
			String Table = "Here is the list of the accommodations sorted by number of bed:\nType                N      Price    Owner    Number of bed(s) \n";
			ArrayList<Integer> copyList = new ArrayList<Integer> ();
			
			for (Accommodation acc : accommodationList){
				copyList.add(acc.getNumberOfBeds());
			}
			
			while(copyList.size() != 0){
				double biggestNumberOfBed = 0;
				for(double element : copyList){
					if(biggestNumberOfBed < element){
						biggestNumberOfBed = element;
					}
				}
				
				for(Accommodation acc : accommodationList){
					if(acc.getNumberOfBeds() == biggestNumberOfBed){
						Table += String.format("%-13s", acc.getClass().getSimpleName());
						Table += String.format("%8d", acc.getAccommodationNumber());
						Table += String.format("%10.2f", acc.getCost());
						Table += String.format("%-6s", "£");
						Table += String.format("%-16s", acc.getOwner().getInitials());
						Table += String.format("%-15d", acc.getNumberOfBeds());
						Table += "\n";
						for(int i = 0; i<copyList.size(); i++){
							if(biggestNumberOfBed == copyList.get(i)){
								copyList.remove(i);
							}
						}
					}
				}
			}
			return Table;
		}
		
		
		/**
		 * Returns the short informations about the accommodation identified by the number provided in parameter
		 * @param nbr integer representing the accommodation
		 * @return String containing the short informations about the accommodation identified by the number provided in parameter
		 * @throws Exception
		 */
		public String GUIShortDetailsByNumber(int nbr) throws Exception{
			String Table = "Here are the informations about the accommodation you asked (N " + nbr +")\n";
			Table += "Type                N      Price    Owner    Number of bed(s) \n";
			Table += String.format("%-13s", this.findByNumber(nbr).getClass().getSimpleName());
			Table += String.format("%8d", this.findByNumber(nbr).getAccommodationNumber());
			Table += String.format("%10.2f", this.findByNumber(nbr).getCost());
			Table += String.format("%-6s", "£");
			Table += String.format("%-16s", this.findByNumber(nbr).getOwner().getInitials());
			Table += String.format("%-15d", this.findByNumber(nbr).getNumberOfBeds());
			return Table;
		}
		
		
		
		/**
		 * Returns the full informations about the accommodation identified by the number provided in parameter
		 * @param nbr integer representing the accommodation
		 * @return String containing the full informations about the accommodation identified by the number provided in parameter
		 * @throws Exception
		 */
		public String GUIFullDetailsByNumber(int nbr) throws Exception{
			String Table = "Here are all the informations we have about the accommodation you asked (N " + nbr +")\n";
			switch(this.findByNumber(nbr).getClass().getSimpleName()){
			case("Cabin"):
				Table += "Type                N      Price    Owner    Number of bed(s)  Number of double bed  Year of creation  TV     Ensuite \n";
				Table += String.format("%-13s", this.findByNumber(nbr).getClass().getSimpleName());
				Table += String.format("%8d", this.findByNumber(nbr).getAccommodationNumber());
				Table += String.format("%10.2f", this.findByNumber(nbr).getCost());
				Table += String.format("%-6s", "£");
				Table += String.format("%-16s", this.findByNumber(nbr).getOwner().getInitials());
				Table += String.format("%-17d", this.findByNumber(nbr).getNumberOfBeds());
				Table += String.format("%-20d", ((Cabin) this.findByNumber(nbr)).getNumberOfDoubleBed());
				Table += String.format("%-13d", ((Cabin) this.findByNumber(nbr)).getcreationYear());
				if(((Cabin) this.findByNumber(nbr)).getTV()){
					Table += String.format("%-7s", "YES");
				}
				else
					Table += String.format("%-7s", "NO");
				if(((Cabin) this.findByNumber(nbr)).getCabinType().equals("ensuite small cabin") || ((Cabin) this.findByNumber(nbr)).getCabinType().equals("ensuite large cabin")){
					Table += String.format("%s", "Ensuite");
				}
				else if(((Cabin) this.findByNumber(nbr)).getCabinType().equals("simple small cabin") || ((Cabin) this.findByNumber(nbr)).getCabinType().equals("simple large cabin")){
					Table += String.format("%s", "Non Ensuite");
				}
				else{
					Table += "Undefined";
				}
				Table += "\n";
				break;
				
			case("HotelRoom"):
				Table += "Type         N°	      TYPE	 	VIEW  	   BEDS	       COST		COMMENTS\n";
				Table += String.format("%-13s", this.findByNumber(nbr).getClass().getSimpleName());
				Table += String.format("%-8s", ((HotelRoom) this.findByNumber(nbr)).getAccommodationNumber());
				Table += String.format("%-18s", ((HotelRoom) this.findByNumber(nbr)).getAccommodationType());
				Table += String.format("%-13s", ((HotelRoom) this.findByNumber(nbr)).getRoomView());
				Table += String.format("%-10d" + "£", ((HotelRoom) this.findByNumber(nbr)).getNumberOfBeds());
				Table += String.format("%.2f", ((HotelRoom) this.findByNumber(nbr)).getCost());
 
				if (((HotelRoom) this.findByNumber(nbr)).getInfoJacuzzi()) {
					Table += String.format("%-10s", "	Jacuzzi");
				}
				Table += "\n";
				break;
				
			case("StudentFlat"):
				Table += "Type            FLAT_NB      COST       WARDEN     ROOM_NB  BEDS_NB \n";
				Table += String.format("%-10s", this.findByNumber(nbr).getClass().getSimpleName());
				Table += String.format("%8d", this.findByNumber(nbr).getAccommodationNumber());
				Table += String.format("%15.2f", this.findByNumber(nbr).getCost() );
				Table += String.format("%-6s", "£");
				Table += String.format("%-14s", this.findByNumber(nbr).getOwner().getInitials());
				Table += String.format("%-9d", ((StudentFlat) this.findByNumber(nbr)).getNumberOfRoom());
				Table += String.format("%-4s", this.findByNumber(nbr).getNumberOfBeds());
				Table += "\n";
			}
			return Table;
		}
		
		
		/**
		 * Returns the list of Cabins in the list of accommodations
		 * @return String containing the list of Cabins in the list of accommodations
		 * @throws Exception
		 */
		public String onlyCabin() throws Exception{
			String Table = "Type                N      Price    Owner    Number of bed(s) \n";
			for (Accommodation acc  : accommodationList){
				if(acc.getClass().getSimpleName().equals("Cabin")){
					Table += String.format("%-13s", acc.getClass().getSimpleName());
					Table += String.format("%8d", acc.getAccommodationNumber());
					Table += String.format("%10.2f", acc.getCost());
					Table += String.format("%-6s", "£");
					Table += String.format("%-16s", acc.getOwner().getInitials());
					Table += String.format("%-15d", acc.getNumberOfBeds());
					Table += "\n";
				}
			}
			return Table;
		}
		
		/**
		 * Returns the list of Hotel Rooms in the list of accommodations
		 * @return String containing the list of Hotel Rooms in the list of accommodations
		 * @throws Exception
		 */
		public String onlyHotelRoom() throws Exception{
			String Table = "Type                N      Price    Owner    Number of bed(s) \n";
			for (Accommodation acc  : accommodationList){
				if(acc.getClass().getSimpleName().equals("HotelRoom")){
					Table += String.format("%-13s", acc.getClass().getSimpleName());
					Table += String.format("%8d", acc.getAccommodationNumber());
					Table += String.format("%10.2f", acc.getCost());
					Table += String.format("%-6s", "£");
					Table += String.format("%-16s", acc.getOwner().getInitials());
					Table += String.format("%-15d", acc.getNumberOfBeds());
					Table += "\n";
				}
			}
			return Table;
		}
		
		/**
		 * Returns the list of Student Flat in the list of accommodations
		 * @return String containing the list of Student Flat in the list of accommodations
		 * @throws Exception
		 */
		public String onlyStudentFlat() throws Exception{
			String Table = "Type                N      Price    Owner    Number of bed(s) \n";
			for (Accommodation acc  : accommodationList){
				if(acc.getClass().getSimpleName().equals("StudentFlat")){
					Table += String.format("%-13s", acc.getClass().getSimpleName());
					Table += String.format("%8d", acc.getAccommodationNumber());
					Table += String.format("%10.2f", acc.getCost());
					Table += String.format("%-6s", "£");
					Table += String.format("%-16s", acc.getOwner().getInitials());
					Table += String.format("%-15d", acc.getNumberOfBeds());
					Table += "\n";
				}
			}
			return Table;
		}
		
		
		/**
		 * Returns the list of accommodation with a price under the one provided
		 * @param budget integer representing the maximum budget the customer have
		 * @return String containing the list of accommodation with a price under the one provided
		 * @throws Exception
		 */
		public String listMaxBudget(int budget) throws Exception{
			String Table = "Accommodations under " + budget + "£\nType                N      Price    Owner    Number of bed(s) \n";
				for (Accommodation acc  : accommodationList){
					if(acc.getCost() <= budget ){
						Table += String.format("%-13s", acc.getClass().getSimpleName());
						Table += String.format("%8d", acc.getAccommodationNumber());
						Table += String.format("%10.2f", acc.getCost());
						Table += String.format("%-6s", "£");
						Table += String.format("%-16s", acc.getOwner().getInitials());
						Table += String.format("%-15d", acc.getNumberOfBeds());
						Table += "\n";
					}
				}
			return Table;
		}
		
		
		
		/**
		 * Returns the accommodation with the number provided
		 * @param accNumber integer representing the accommodation
		 * @return Accommodation 
		 */
		public Accommodation findByNumber(int accNumber){
			//We browse the list in search of the good cabin
			for (Accommodation acc : accommodationList)
	    	{
	    		if (acc.getAccommodationNumber() == accNumber)
	    		{
	    			return acc;
	    		}
	    	}
	    	return null;
		}
		
		/**
		 * Returns the accommodation with the number provided
		 * @param accNumber String representing the accommodation
		 * @return Accommodation 
		 */
		public Accommodation findByNumber(String accNumber){
			//We browse the list in search of the good cabin
			for (Accommodation acc : accommodationList)
	    	{
	    		if (accNumber.equals(acc.getAccommodationNumber()))
	    		{
	    			return acc;
	    		}
	    	}
	    	return null;
		}
		
		
		/**
		 * Add an accommodation to the list
		 * @param acc the accommodation to add to the list
		 */
		public void addAccommodation(Accommodation acc){
			accommodationList.add(acc);
		}
		
		/**
		 * Returns the full details of the accommodation with the number provided
		 * @param accNumber
		 * @return String containing the full details of the accommodation with the number provided
		 * @throws Exception
		 */
		public String accommodationDetails(int accNumber) throws Exception{
				return this.findByNumber(accNumber).getFullDetails();
		}
		
		/**
		 * Returns the maximum income the site could get in one night
		 * @return double representing the maximum income the site could get in one night
		 * @throws Exception
		 */
		public double maxIncome() throws Exception{
			double income = 0;
			for (Accommodation acc  : accommodationList){
				income += acc.getCost();
			}
			return income;
		}
		
		
		
		/**
		 * Returns the price of the cheapest accommodation of the site
		 * @return double representing the price of the cheapest accommodation of the site
		 * @throws Exception
		 */
		public double cheapestAccommodationCost() throws Exception{
			double cheapest = 1000000; //Do not like the way I made it but it works
			for (Accommodation acc : accommodationList){
				if(acc.getCost() != 0){
					cheapest = acc.getCost();
				}
			}
			double cost;
			for (Accommodation acc : accommodationList) {
				cost = acc.getCost();
				if (cost <= cheapest || cost != 0) {
					cheapest = cost;
				}
			}	
			return cheapest;
		}
		
		
		/**
		 * Returns the most expensive accommodation of the list
		 * @return double representing the most expensive accommodation of the list
		 * @throws Exception
		 */
		public double mostExpensiveAccommodationCost() throws Exception{
			double price = accommodationList.get(0).getCost();
			for (Accommodation acc : accommodationList) {
				double cost = acc.getCost();
				if (cost >= price) {
					price= cost;
				}
			}	
			return price;
		}
		
		
		
		/**
		 * Returns the number of accommodation in the list
		 * @return double representing the number of accommodation in the list
		 */
		public double numberOfAccommodation(){
			return accommodationList.size();
		}
		
		
		/**
		 * Read the element of the text files provided in parameters and fill the accommodation list
		 * @param fileCabin text file containing the cabins
		 * @param fileHotelRoom text file containing the hotel rooms
		 * @param fileStudentFlat text file containing the student flat
		 * @throws Exception
		 */
		public void readFile(String fileCabin, String fileHotelRoom, String fileStudentFlat) throws Exception{
			try {
				File fileCab = new File(fileCabin);
				Scanner scannerCabin = new Scanner(fileCab);
				while (scannerCabin.hasNextLine()) {
					//read first line and process it
					String inputLine = scannerCabin.nextLine(); 
					if (inputLine.length() != 0) {//ignored if there is nothing in the line 
						processCabinLine(inputLine);
					}
				}
				
				File fileHotel = new File(fileHotelRoom);
				Scanner scannerHotel = new Scanner(fileHotel);
				while (scannerHotel.hasNextLine()) {
					// read first line and process it
					String inputLine = scannerHotel.nextLine();
					if (inputLine.length() != 0) {// ignored if blank line
						processHotelRoomLine(inputLine);
					}
				}
				
				File fileSF = new File(fileStudentFlat);
				Scanner scannerSF = new Scanner(fileSF);
				while (scannerSF.hasNextLine()) {
					//read first line and process it
					String inputLine = scannerSF.nextLine(); 
					if (inputLine.length() != 0) {//ignored if there is nothing in the line 
						processStudentFlatLine(inputLine);
					}
				}
				
			}
			//if the file is not found, stop with system exit
			catch (FileNotFoundException fnf){
				 System.out.println("File not found ");
				 System.exit(0);
			 }
		}
		
		
		/**
		 * Process the line provided in parameter (only for the cabin file)
		 * @param line
		 */
		private void processCabinLine(String line) {
			try {
				//Split the different element in the .csv file in an array of String
				String parts [] = line.split(",");
				
				//Then Convert every String in the type needed for the Cabin constructor
				String numCab = parts[0];
				numCab = numCab.trim();
				int cabinNumber = Integer.parseInt(numCab);
				
				Owner cabinOwner = new Owner(parts[1]);
				
				String cabinType = parts[2];
				
				String year = parts[3];
				year = year.trim();
				int creationYear = Integer.parseInt(year);
				
				String numberDoubleBed = parts[4];
				numberDoubleBed = numberDoubleBed.trim();
				int numberOfDoubleBed = Integer.parseInt(numberDoubleBed);
				
				String tv = parts[5];
				Boolean TV;
				if(tv.equals("YES")){
					TV = true;
				}
				else
					TV = false;
				
				String beds[] = parts[6].split(";");
				int[] bedRooms = new int[beds.length];
				for(int i=0; i<beds.length; i++){
					bedRooms[i] = Integer.parseInt(beds[i]);
				}
				
				//create Student object and add to the list
				Cabin cab = new Cabin(cabinNumber, bedRooms, cabinOwner, cabinType, creationYear, numberOfDoubleBed, TV);
				this.addAccommodation(cab);
			}
			//this catches trying to convert a String to an integer
			catch (NumberFormatException nfe) {
				String error = "Number conversion error in '" + line + "'  - " 
				                  + nfe.getMessage();
				System.out.println(error);
			}
			//this catches missing items if only one or two items
			//other omissions will result in other errors
			catch (ArrayIndexOutOfBoundsException air) {
				String error = "Not enough items in  : '" + line
				                        + "' index position : " + air.getMessage();
				System.out.println(error);
			}
			//This catches trying to convert an Integer to a String
			catch(StringIndexOutOfBoundsException sioob){
				String error = "String conversion error in aaaaaa '" + line + "'  - " 
		                  + sioob.getMessage();
				System.out.println(error);
			}
		}
		
		/**
		 * Process the line provided in parameter (only for the hotel room file)
		 * @param line
		 */
		private void processHotelRoomLine(String line) {
			try {
				String parts[] = line.split(",");
				Owner owner = new Owner(parts[1]);
				String room = parts[0];
				int roomNumber = Integer.parseInt(room);
				//If the room number is missing 
				if (room.equals("")) {
					String error = "The number of the room on the line:" + line + " is missing";
					System.err.println(error);
				} else {
					String typeRoom = parts[2];
					// create HotelRoom object and add it to the list
					HotelRoom hr = new HotelRoom(roomNumber, typeRoom, owner);
					this.addAccommodation(hr);
				}

			}
			// for these two formatting errors, ignore lines in error and try and
			// carry on
			// this catches trying to convert a String to an integer
			catch (NumberFormatException nfe) {
				String error = "Number conversion error in '" + line + "'  - " + nfe.getMessage();
				System.err.println(error);
			}
			// this catches missing items if only one or two items
			// other omissions will result in other errors
			catch (ArrayIndexOutOfBoundsException air) {
				String error = "Not enough items in line:" + line + " index position : " + air.getMessage();
				System.err.println(error);
			}
			// if the name is incomplete or absent in our .csv an error message is
			// displayed
			catch (StringIndexOutOfBoundsException e) {
				String error = "The name part of the line: " + line + " is incomplete. Please add the missing information";
				System.err.println(error);

			}

		}
		
		/**
		 * Process the line provided in parameter (only for the student flat  file)
		 * @param line
		 */
		private void processStudentFlatLine(String line) throws Exception {
			
			String parts [] = line.split(",");
			if (parts.length == 8) { // if there are 8 elements, it means that there are only first name and last name
				int flatNb = Integer.parseInt(parts[0]);
				String fName = parts[1];
				String lName = parts[2];
				String typeFlat = parts[3].toLowerCase(); // we want to force the lowercase to avoid mistakes
				String strRooms = parts[4];
				int nbRooms = Integer.parseInt(strRooms);
				int[] nbrBeds = convertStringToIntArray(parts[5]);
				boolean internetCo = stringToBool(parts[6].toLowerCase());
				boolean beddingPack = stringToBool(parts[7].toLowerCase());
				
				Owner warden = new Owner(fName, lName);
				StudentFlat flat = new StudentFlat(flatNb, warden, typeFlat, nbRooms, nbrBeds, internetCo, beddingPack);
				addAccommodation(flat);

			}
			else if (parts.length == 9){ // if there are 9 elements, it means that there are first, middle and last name
				int flatNb = Integer.parseInt(parts[0]);
				String fName = parts[1];
				String mName = parts[2];
				String lName = parts[3];
				String typeFlat = parts[4].toLowerCase(); // we want to force the lowercase to avoid mistakes
				String strRooms = parts[5];
				int nbRooms = Integer.parseInt(strRooms);
				int[] nbrBeds = convertStringToIntArray(parts[6]);
				boolean internetCo = stringToBool(parts[7].toLowerCase());
				boolean beddingPack = stringToBool(parts[8].toLowerCase());
				
				Owner warden = new Owner(fName, mName, lName);
				StudentFlat flat = new StudentFlat(flatNb, warden, typeFlat, nbRooms, nbrBeds, internetCo, beddingPack);
				this.addAccommodation(flat);
			}
			else { // means that there is a problem in the input file
				throw new Exception ("You didn't enter the correct number of arguments");
			}
		}
		
		/** This methods allows us to convert the string that corresponds to the number of beds in the file in input to an array of int
	     * so that it can be used in the constructor of StudentFlat.
	     * This method is useful to convert the number of rooms and the number of beds in each one.
	     * @param abc The string to be converted
	     * @return The int array
	     */
		public int[] convertStringToIntArray(String abc) {
			abc = abc.replace("{", ""); // We replace "{", "}" and ":" to fit to the format in input.
			abc = abc.replace("}", "");
			abc = abc.replace(":"," ");
			String[] sepNb = abc.split(" ");
			int i = 0;
			int [] strNb = new int [sepNb.length] ;
			for (i = 0; i < sepNb.length; i++) {
				strNb[i] += Integer.parseInt(sepNb[i]);
			}
			return strNb;
		}
		
		/** This method allows us to convert a string that is either "true" or "false" to a boolean object
		 *  which is useful for my two extras (internetConnection and beddingPack)
		 *  @param def The string to be converted
		 *  @throws Exception
		 *  @return The boolean
		 */
		public boolean stringToBool(String def) throws Exception {
			 boolean ret = true;
			 if (def.equals("true")) {
			     ret = true;
			 }
			 else if (def.equals("false")) {
			     ret = false;
			 }
			 else { // If the parameter entered is neither "true" nor "false", then we throw an exception
				 throw new Exception ("You must enter either \"true\" or \"false\" for the internet connection and the bedding pack");
			 }
			return ret;
		}
		
		
		/**
		 * Write the informations in the file provided in parameters
		 * @param filename the file in which to write
		 * @param text the text to write in the file
		 */
		public  void writeToFile(String filename, String text) {
			 FileWriter fw;
			 try {
			    fw = new FileWriter(filename);
			    fw.write(text);
			 	fw.close();
			 }
			 //message and stop if file not found
			 catch (FileNotFoundException fnf){
				 System.out.println(filename + " not found ");
				 System.exit(0);
			 }
			 //stack trace here because we don't expect to come here
			 catch (IOException ioe){
			    ioe.printStackTrace();
			    System.exit(1);
			 }
		}
}
