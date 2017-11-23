package F21SF_Coursework2;

/**
 * Class corresponding to the Student flat (equivalent of the Cabin class in CW1)
 * @author Jérémie Koster
 *
 */
public class StudentFlat extends Accommodation {
	

	// accommodationType; // category of the flat : standard, medium, deluxe

	private int numberOfRoom;
	private boolean internetConnection; // extra information : is Internet
										// provided ?
	private boolean beddingPack; // extra information : is a bedding pack
									// provided ?

	private static double priceInternetConnection = 15.7; // price of the
															// Internet
															// Connection per
															// room
	private static double priceBeddingPack = 30.5; // price of the bedding pack
													// per room

	private static int priceStandardRoom = 200;
	private static int priceMediumRoom = 250;
	private static int priceDeluxeRoom = 300;

	// Constructor
	public StudentFlat(int accommodationNumber, Owner warden, String accommodationType, int nbRoom, int[] nbBeds,
			boolean internetConnection, boolean beddingPack) {
		this.accommodationNumber = accommodationNumber;
		this.accommodationType = accommodationType;
		this.numberOfRoom = nbRoom;
		this.numberOfBeds = nbBeds;
		this.internetConnection = internetConnection;
		this.beddingPack = beddingPack;
		this.accommodationOwner = warden;

	}
	
	public StudentFlat(){
	}
	
	/**
	 * Getter for the accommodation type
	 * @return The accommodation type as a string
	 */
	public String getAccommodationType() {
		return accommodationType;
	}

	/**
	 * Setter of the accommodation type
	 * @param accommodationType The accommodation type to be set
	 */
	public void setTypeOfFlat(String accommodationType) {
		this.accommodationType = accommodationType;
	}

	/**
	 * Getter of the number of rooms
	 * @return the number of room as an integer
	 */
	public int getNumberOfRoom() {
		return numberOfRoom;
	}

	/**
	 * Getter of the number of beds per room for a specific flat
	 * @return An array of integer corresponding to the number of beds per room
	 */
	public int[] getNbrOfBeds() {
		return numberOfBeds;
	}

	/**
	 * Getter for the number of beds in total for the flat
	 * @return An integer corresponding of the sum of the number of beds of all rooms in the flat
	 */
	public int getNumberOfBeds() {
		int res = 0;
		for (int index = 0; index < this.numberOfBeds.length; index++) {
			res += this.numberOfBeds[index];
		}
		return res;
	}

	/**
	 * Getter for the size of the bed
	 * @return The string corresponding to the size of the bed
	 * @throws Exception If the type of the flat is different from standard/deluxe/medium, it throws an exception
	 */
	public String getSizeOfBed() throws Exception {
		if (this.accommodationType.equals("standard")) {
			return "single";
		} else if (this.accommodationType.equals("medium")) {
			return "medium";
		} else if (this.accommodationType.equals("deluxe")) {
			return "king";
		} else {
			throw new Exception("The type of flat entered in input is not valid. "
					+ "It should be either 'standard' or 'medium' or 'deluxe'.");
		}
	}

	/**
	 * Getter for the extra attribute : the internet connection
	 * @return A boolean whether the internet connection is provided or not
	 */
	public boolean isInternetConnection() {
		return internetConnection;
	}

	/**
	 * Setter for the extra attribute: the internet connection
	 * @param internetConnection The boolean to set the internet connection to true or false
	 */
	public void setInternetConnection(boolean internetConnection) {
		this.internetConnection = internetConnection;
	}

	/**
	 * Getter for the extra attribute : the bedding pack
	 * @return The boolean corresponding to the extra attribute
	 */
	public boolean isBeddingPack() {
		return beddingPack;
	}

	/**
	 * Setter for the extra attribute : the bedding pack
	 * @param beddingPack The boolean to set the bedding pack to true or false
	 */
	public void setBeddingPack(boolean beddingPack) {
		this.beddingPack = beddingPack;
	}

	/**
	 * Getter for the price of the internet connection
	 * @return A double corresponding to the price
	 */
	public double getPriceInternetConnection() {
		return priceInternetConnection;
	}

	/**
	 * getter for the price of the bedding pack
	 * @return A double corresponding to the price
	 */
	public double getPriceBeddingPack() {
		return priceBeddingPack;
	}

	/**
	 * Method that prints a message saying which extra attribute a student flat has
	 * @return The message as a string
	 */
	public String printExtra() {
		if (isInternetConnection() == true && isBeddingPack() == true) {
			return "\nAn internet connection and a bedding pack are provided.";
		} else if (isInternetConnection() == true && isBeddingPack() == false) {
			return "\nAn internet connection is provided but the bedding pack is not.";
		} else if (isInternetConnection() == false && isBeddingPack() == true) {
			return "\nA bedding pack is provided but the internet connection is not.";
		} else {
			return "\nNeither an internet connection nor a bedding pack are provided.";
		}

	}

	// This method is not a duplication of getCost() because it is used to
	// compute the max income possible of the flat.
	// To do this, it is necessary to separate the calculation of the cost of
	// the flat based its type and the price of the extras
	/**
	 * Method that analyses the parameter and returns the price of the accommodation type
	 * It is used to compute the maximum income possible
	 * @param accommodationType The accommodation type 
	 * @return The price as an integer
	 */
	public int getPriceOfRoom(String accommodationType) {
		int result = 0;
		if (accommodationType.equals("standard")) {
			result += priceStandardRoom;
		} else if (accommodationType.equals("medium")) {
			result += priceMediumRoom;
		} else if (accommodationType.equals("deluxe")) {
			result += priceDeluxeRoom;
		}
		return result;
	}
	
	/**
	 * Method that computes the cost of the flat according to its type and the extra attributes
	 * @return The price as a double
	 */
	public double getCost() throws Exception {
		double price = 0;
		if (this.accommodationType.equals("standard")) {
			price += priceStandardRoom * numberOfRoom;
		} else if (this.accommodationType.equals("medium")) {
			price += priceMediumRoom * numberOfRoom;
		} else if (this.accommodationType.equals("deluxe")) {
			price += priceDeluxeRoom * numberOfRoom;
		} else { // if the type of room input isn't written correctly and does
					// not fit to neither standard, nor medium nor deluxe
			throw new Exception(
					"The price couldn't have been calculated because the type of the room if not correctly spelled.");
		}
		if (this.isInternetConnection()) {
			price += priceInternetConnection * numberOfRoom;
		}
		if (this.isBeddingPack()) {
			price += priceBeddingPack * numberOfRoom;
		}
		return price;
	}

	public StudentFlat getStudentFlat(int accommodationNumber) {
		return this.getStudentFlat(accommodationNumber);
	}
	
	/**
	 * Method that makes the full details sentence of a flat according to its characteristics
	 * @return The full details sentence as a string
	 */
	public String getFullDetails() throws Exception {
		return "The flat #" + this.getAccommodationNumber() + " is led by the warden called "
				+ this.getOwner().getFullName() + ". \nThere are " + this.getNumberOfRoom() + " rooms and "
				+ this.getNumberOfBeds() + " " + this.getSizeOfBed() + " beds in the flat. " + printExtra()
				+ "\nThe overall cost for this flat is ï¿œ" + this.getCost();
	}

	/**
	 * Method that prints the full details sentence (above method)
	 */
	public void printFullDetails() throws Exception {
		System.out.println(this.getFullDetails());
	}
	
	/**
	 * Method that makes the full details sentence of a flat according to its characteristics
	 * @return the short details string 
	 */
	public String getShortDetails() throws Exception {
		return "The flat #" + this.getAccommodationNumber() + " is led by the warden whose initials are "
				+ this.getOwner().getInitials() + ", it costs ï¿œ" + this.getCost() + " and there are "
				+ this.getNumberOfBeds() + " " + this.getSizeOfBed() + " beds in the flat. ";
	}
	
	/**
	 * Method that prints the short details sentence (above method)
	 */
	public void printShortDetails() throws Exception {
		System.out.println(this.getShortDetails());
	}

}