package F21SF_Coursework2;



/**
 * Software Engineering foundations
 * @author Antoine DEGRANDI
 */
public class Cabin extends Accommodation {

	static final String TYPEensuitesmall = "ensuite small cabin";
	static final String TYPEensuitelarge = "ensuite large cabin";
	static final String TYPEsimplesmall = "simple small cabin";
	static final String TYPEsimplelarge = "simple large cabin";

	private int creationYear;
	private int numberOfDoubleBed;
	private Boolean TV;

	// Constructor
	/**
	 * Returns a boolean telling if there already is a cabin with the same number provided in parameter
	 * @param  numCab the number of the accommodation
	 * @param  numberOfBed int[] representing the number of room and beds in the accommodation
	 * @param  accoommodationOwner the Owner of the cabin
	 * @param  accommodationType String containing the type of the cabin
	 * @param  creationYear int representing the year of construction of the Cabin
	 * @param  numberOfDoubleBed the number of double bed
	 * @param  TV boolean representing the fact that there is a TV or not in the cabin
	 */
	public Cabin(int numCab, int[] numberOfBeds, Owner accommodationOwner, String accommodationType, int creationYear,
			int numberOfDoubleBed, Boolean TV) {
		this.accommodationNumber = numCab;
		this.numberOfBeds = numberOfBeds;
		this.accommodationOwner = accommodationOwner;
		this.accommodationType = accommodationType.toLowerCase();
		this.creationYear = creationYear;
		this.setNumberOfDoubleBed(numberOfDoubleBed);
		this.TV = TV;
	}
	
	
	/** 
	 * Method to ensure that the type given to the constructor is valid. Filling
	 * the accommodationType instance variable with a valid string or with "The
	 * type is not valid"
	 */
	public String getAccommodationType() {
		switch (this.accommodationType) {
		case (TYPEensuitesmall):
			return TYPEensuitesmall;
		case (TYPEensuitelarge):
			return TYPEensuitelarge;
		case (TYPEsimplesmall):
			return TYPEsimplesmall;
		case (TYPEsimplelarge):
			return TYPEsimplelarge;
		default:
			return "The type is not valid";
		}
	}

	/** 
	 * Method returning a boolean to know if a cabin has a valid
	 * accommodationType. Used in "listDetails" method in the "CabinList" class
	 * @return boolean 
	 */
	public boolean accommodationTypeValid() {
		if (getAccommodationType().equals("The type is not valid")) {
			return false;
		} else
			return true;
	}
	
	
	/** 
	 * Calculate the cost of the accommodation
	 * @return double representing the cost
	 */
	public double getCost() {
		double cost = 0;

		switch (this.accommodationType) {
		case (TYPEensuitesmall):
			cost = 30 + getNumberOfPeople() * 15;
			break;
		case (TYPEensuitelarge):
			cost = 30 + getNumberOfPeople() * 13;
			break;
		case (TYPEsimplesmall):
			cost = getNumberOfPeople() * 15;
			break;
		case (TYPEsimplelarge):
			cost = getNumberOfPeople() * 13;
			break;
		default:
			return cost;
		}

		if (TV) {
			cost += 10;
		}
		return cost;
	}

	/** 
	 * Returns the number of people that can live in this cabin (important for getCost())
	 * @return int the capacity of the accommodation
	 */
	public int getNumberOfPeople() {
		return (this.getNumberOfBeds() + this.numberOfDoubleBed);
	}
	
	/**
	 * returns the table of the rooms + beds
	 * @return int[] 
	 */
	public int[] getNbrOfBeds() {
		return numberOfBeds;
	}

	/**
	 * Returns the number of bed in the cabin
	 * @return int representing the number of bed in the cabin
	 */
	public int getNumberOfBeds() {
		int numberOfBed = 0;
		for (int i = 0; i < this.numberOfBeds.length; i++) {
			numberOfBed += numberOfBeds[i];
		}
		return numberOfBed;
	}

	/**
	 * Returns all the details about the cabin
	 * @return String containing all the details about the cabin
	 */
	public String getFullDetails() {
		String details = "";
		details += "This is a " + getCabinType() + ", it costs " + String.format("%.2f", getCost())
				+ "£ per night, it is composed of " + getNumberOfBeds() + " bed(s) (" + getNumberOfDoubleBed()
				+ " double bed) in " + numberOfBeds.length + " room(s) and has" + TVDetails()
				+ ". The number of this cabin is: " + getAccommodationNumber() + ", it was built in "
				+ getcreationYear() + " and it " + "belongs to " + getOwner().getFullName();
		return details;
	}

	/**
	 * Returns some details about the cabin
	 * @return String containing some details about the cabin
	 */
	public String getShortDetails() {
		String details = "The cabin n" + this.getAccommodationNumber() + " is composed of " + this.getNumberOfBeds()
				+ " bed(s) and has" + this.TVDetails() + ". It costs " + String.format("%.2f", getCost())
				+ "£ per night and belongs to " + this.getOwner().getInitials();
		return details;
	}

	/**
	 * Rturns a short String useful for the Details methods above so they don't look unreadable
	 * @return String
	 */
	private String TVDetails() {
		if (this.getTV()) {
			return " a TV in it";
		} else {
			return " no TV in it";
		}
	}

	/**
	 * @param numberOfBeds
	 */
	public void setnumberOfBeds(int[] numberOfBeds) {
		this.numberOfBeds = numberOfBeds;
	}

	/**
	 * @return int the year of creation
	 */
	public int getcreationYear() {
		return creationYear;
	}

	/**
	 * @param creationYear
	 */
	public void setcreationYear(int creationYear) {
		this.creationYear = creationYear;
	}

	/**
	 * @return int the number of double bed
	 */
	public int getNumberOfDoubleBed() {
		return numberOfDoubleBed;
	}

	/**
	 * @param numberOfDoubleBed
	 */
	public void setNumberOfDoubleBed(int numberOfDoubleBed) {
		this.numberOfDoubleBed = numberOfDoubleBed;
	}

	/**
	 * @return String the type of the accommodation
	 */
	public String getCabinType() {
		return accommodationType;
	}

	/**
	 * @param accommodationType
	 */
	public void setCabinType(String accommodationType) {
		this.accommodationType = accommodationType;
	}

	/**
	 * @return Boolean representing if there is a TV or not in the cabin
	 */
	public Boolean getTV() {
		return TV;
	}

	/**
	 * @param tv
	 */
	public void setTV(Boolean tv) {
		TV = tv;
	}

}
