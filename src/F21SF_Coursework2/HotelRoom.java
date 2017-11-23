package F21SF_Coursework2;


/**
 * @author Tom Feraud
 * @version 1.0
 */

public class HotelRoom extends Accommodation {
	/**
	 * The type possible for our hotel room
	 */
	static final String TYPEsingle = "single";
	static final String TYPEdouble = "double";
	static final String TYPEfamily = "family";
	static final String TYPEpresidential = "presidential";

	/**
	 * Constructor of HotelRoom
	 * 
	 * @param room
	 * @param accommodationType
	 * @param accommodationOwner
	 */
	public HotelRoom(int room, String accommodationType, Owner accommodationOwner) {
		this.accommodationNumber = room;
		this.accommodationType = accommodationType.toLowerCase(); // to ensure
																	// the
																	// syntax
		this.accommodationOwner = accommodationOwner;
	}

	/**
	 * Method checking the type of our hotel room
	 * 
	 * @return text consisting of the accommodation type
	 */
	public String getAccommodationType() {
		switch (this.accommodationType) {
		case (TYPEsingle):
			return TYPEsingle;
		case (TYPEdouble):
			return TYPEdouble;

		case (TYPEfamily):
			return TYPEfamily;

		case (TYPEpresidential):
			return TYPEpresidential;
		default:
			return "error";
		}
	}

	/**
	 * Sets the room's type
	 * 
	 * @param accommodationType
	 */
	public void setTypeRoom(String accommodationType) {
		this.accommodationType = accommodationType;
	}

	/**
	 * Get the number of each bed for a room
	 * 
	 * @return a array of int as {simple,double,extra large}
	 */
	public int[] getNbrOfBeds() {
		numberOfBeds = new int[3];
		switch (this.accommodationType) {
		case (TYPEsingle):
			numberOfBeds[0] = 1; // 1 simple
			numberOfBeds[1] = 0; // 0 double
			numberOfBeds[2] = 0; // 0 XL
			break;
		case (TYPEdouble):
			numberOfBeds[0] = 0;
			numberOfBeds[1] = 1;
			numberOfBeds[2] = 0;
			break;
		case (TYPEfamily):
			numberOfBeds[0] = 2;
			numberOfBeds[1] = 1;
			numberOfBeds[2] = 1;
			break;
		case (TYPEpresidential):
			numberOfBeds[0] = 1;
			numberOfBeds[1] = 2;
			numberOfBeds[2] = 2;
			break;
		default:
			numberOfBeds[0] = 0;
			numberOfBeds[1] = 0;
			numberOfBeds[2] = 0;
			break;
		}

		return numberOfBeds;
	}

	/**
	 * Set the number of each bed
	 * 
	 * @param numberOfBeds
	 */
	public void setNbrOfEachBed(int[] numberOfBeds) {
		this.numberOfBeds = numberOfBeds;
	}

	/**
	 * Get the number of single bed
	 * 
	 * @return an int corresponding to the number of single bed
	 */
	public int getNbrSingleBed() {
		return getNbrOfBeds()[0];
	}

	/**
	 * Get the number of double bed
	 * 
	 * @return an int corresponding to the number of double bed
	 */
	public int getNbrDoubleBed() {
		return getNbrOfBeds()[1];
	}

	/**
	 * Get the number of extra large bed
	 * 
	 * @return an int corresponding to the number of extra large bed
	 */
	public int getNbrExtraLargeBed() {
		return getNbrOfBeds()[2];
	}

	/**
	 * Get the total number of beds
	 * 
	 * @return an int representing the total number of beds
	 */
	public int getNumberOfBeds() {
		int total;
		total = getNbrOfBeds()[0] + getNbrOfBeds()[1] + getNbrOfBeds()[2];
		return total;
	}

	/**
	 * Get the information if a room contains a jacuzzi
	 * 
	 * @return true if there is a jacuzzi, false otherwise
	 */
	// Here 2 rooms have a one
	public boolean getInfoJacuzzi() {
		if (this.accommodationType.equals(TYPEpresidential) && this.getAccommodationNumber() == 114) {
			return true;
		} else if (this.accommodationType.equals(TYPEfamily) && this.getAccommodationNumber() == 113) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Get the cost of a room in function of it's type,view & if there is a
	 * jacuzzi or not
	 * 
	 * @return the price
	 */
	public double getCost() {
		if (this.accommodationType.equals(TYPEsingle)) {
			if (this.getRoomView().equals("street view")) {
				return 50.0;
			} else if (this.getRoomView().equals("garden view")) {
				return 70.0;
			} else {
				return 110.0;
			}
		} else if (this.accommodationType.equals(TYPEdouble)) {
			if (this.getRoomView().equals("street view")) {
				return 70.0;
			} else if (this.getRoomView().equals("garden view")) {
				return 98.0;
			} else {
				return 154.0;
			}
		} else if (this.accommodationType.equals(TYPEfamily)) {
			if (this.getRoomView().equals("street view")) {
				return 86.0;
			} else if (this.getRoomView().equals("garden view")) {
				if (getInfoJacuzzi() == true) {
					return 180.6;
				} else {
					return 120.4;
				}
			} else {
				if (getInfoJacuzzi() == true) {
					return 283.8;
				} else {
					return 189.2;
				}
			}
		} else if (this.accommodationType.equals(TYPEpresidential)) {
			if (this.getRoomView().equals("street view")) {
				return 200.0;
			} else if (this.getRoomView().equals("garden view")) {
				if (getInfoJacuzzi() == true) {
					return 420.0;
				} else {
					return 280.0;
				}
			} else {
				if (getInfoJacuzzi() == true) {
					return 660.0;
				} else {
					return 440.0;
				}
			}
		} else {
			return 0.0;
		}
	}

	/**
	 * Get the room's view in function of the number of the room
	 * 
	 * @return a string containing the room's view
	 */
	public String getRoomView() {
		// From room 1 to 4 (include)
		if (this.getAccommodationNumber() == 101 || this.getAccommodationNumber() == 102
				|| this.getAccommodationNumber() == 103 || this.getAccommodationNumber() == 104) {
			return "street view";
		}
		// From room 5 to 8 (include)
		else if (this.getAccommodationNumber() == 105 || this.getAccommodationNumber() == 106
				|| this.getAccommodationNumber() == 107 || this.getAccommodationNumber() == 108) {
			return "garden view";
		} else {
			return "sea view";
		}
	}

	/**
	 * @return a string containing all the details of a room
	 */
	public String getFullDetails() {
		String fullDetails;
		if (this.accommodationType.equals(TYPEfamily) || this.accommodationType.equals(TYPEpresidential)) {
			if (getInfoJacuzzi() == true) {
				fullDetails = "The room number " + getAccommodationNumber() + " is a " + getAccommodationType()
						+ " with a " + getRoomView() + " and " + getNumberOfBeds() + " bed(s) :" + getNbrSingleBed()
						+ " single, " + getNbrDoubleBed() + " double & " + getNbrExtraLargeBed() + " XL "
						+ "which has a jacuzzi " + "at the price of " + getCost() + "£ attributed to "
						+ getOwner().getFullName() + ".";
			} else {
				fullDetails = "The room number " + getAccommodationNumber() + " is a " + getAccommodationType()
						+ " with a " + getRoomView() + " and " + getNumberOfBeds() + " bed(s) :" + getNbrSingleBed()
						+ " single, " + getNbrDoubleBed() + " double & " + getNbrExtraLargeBed() + " XL "
						+ "wich has no jacuzzi " + "at the price of " + getCost() + "£ attributed to "
						+ getOwner().getFullName() + ".";
			}

		} else {
			fullDetails = "The room number " + getAccommodationNumber() + " is a " + getAccommodationType() + " with a "
					+ getRoomView() + " and " + getNumberOfBeds() + " bed(s) :" + getNbrSingleBed() + " single, "
					+ getNbrDoubleBed() + " double & " + getNbrExtraLargeBed() + " XL " + "at the price of " + getCost()
					+ "£ attributed to " + getOwner().getFullName() + ".";
		}

		return fullDetails;
	}

	/**
	 * @return a string containing all the short details of a room
	 */
	public String getShortDetails() {
		String shortDetails = getAccommodationNumber() + "; {" + getNbrSingleBed() + "," + getNbrDoubleBed() + ","
				+ getNbrExtraLargeBed() + "}; " + getCost() + "£; " + getOwner().getInitials() + ".";
		return shortDetails;
	}

}
