package F21SF_Coursework2;


/**
 * @author Antoine Degrandi / Tom Feraud / Jérémie Koster 
 */
public abstract class Accommodation{
	// instance variables
	protected Owner accommodationOwner;
	protected int accommodationNumber;
	protected String accommodationType;
	protected int[] numberOfBeds;

	/**
	 * Returns the cost of the accommodation
	 * @return double representing the cost of the accommodation
	 * @throws Exception
	 */
	abstract double getCost() throws Exception;
	
	/**
	 * Returns an array containing the informations about the number of bed in the accommodation and the number of room
	 * @return int[]
	 */
	abstract int[] getNbrOfBeds();
	
	/**
	 * Returns the number of bed in the accommodation
	 * @return int representing the number of bed
	 */
	abstract int getNumberOfBeds();
	
	/**
	 *Returns the type of the accommodation 
	 * @return String containing the type of the accommodation 
	 */
	abstract String getAccommodationType();
	
	
	
	
	/**
	 * Returns the full details of the accommodation
	 * @return String containing the full details of the accommodation
	 * @throws Exception
	 */
	abstract String getFullDetails() throws Exception;

	/**
	 * Returns the short details of the accommodation
	 * @return String containing the short details of the accommodation
	 * @throws Exception
	 */
	abstract String getShortDetails() throws Exception;
	

	/**
	 * Return the number of the accommodation
	 * @return int representing the number of the accommodation 
	 */
	public int getAccommodationNumber() {
		return accommodationNumber;
	}

	/**
	 * Sets the accommodation's number
	 * @param roomNumber integer
	 */
	public void setAccommodationNumber(int roomNumber) {
		this.accommodationNumber = roomNumber;
	}

	/**
	 * Return the name of the owner of the accommodation
	 * @return Owner
	 */
	public Owner getOwner() {
		return accommodationOwner;
	}
	
	/**
	 * Set the Owner
	 * @param accommodationOwner
	 */
	public void setOwner(Owner accommodationOwner) {
		this.accommodationOwner = accommodationOwner;
	}
}