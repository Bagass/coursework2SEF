package F21SF_Coursework2;


public class Owner {
	private String firstName;
	private String middleName;
	private String lastName;

	/**
	 * Constructor of Owner with a first name & a last name
	 * @param firstName
	 * @param lastName
	 */
	public Owner(String firstName, String lastName) {
		this.firstName = firstName;
		this.middleName = "";
		this.lastName = lastName;
	}

	/**
	 * Constructor of Owner with a first name, a middle name & a last name
	 * @param fName
	 * @param mName
	 * @param lName
	 */
	public Owner(String fName, String mName, String lName) {
		firstName = fName;
		middleName = mName;
		lastName = lName;
	}

	/**
	 * Constructor to create name from full name in the format first name then
	 * space then last name or first name then space then middle name then space
	 * then last name
	 */
	public Owner(String fullName) {
		int spacePos1 = fullName.indexOf(' ');
		firstName = fullName.substring(0, spacePos1);
		int spacePos2 = fullName.lastIndexOf(' ');
		if (spacePos1 == spacePos2)
			middleName = "";
		else
			middleName = fullName.substring(spacePos1 + 1, spacePos2);
		lastName = fullName.substring(spacePos2 + 1);
	}

	/**
	 *  Returns the first name
	 * @return firstName String
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 *  Returns the last name
	 * @return lastName String
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 *  Changes the last name to the value provided in the parameter
	 * @param lastName String
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Returns the first name
	 * @return String containing the first and the last name
	 */
	public String getFirstAndLastName() {
		return firstName + " " + lastName;
	}

	/**
	 * Returns the last name
	 * @return String containing the last name, a coma, and then the first name
	 */
	public String getLastCommaFirst() {
		return lastName + ", " + firstName;
	}

	/**
	 * Returns the full name either first name then space then last name or
	 * first name then space then middle name then space and then last name
	 * @return String containing the full name
	 */
	public String getFullName() {
		String result = firstName + " ";
		if (!middleName.equals("")) {
			result += middleName + " ";
		}
		result += lastName;
		return result;
	}

	/**
	 * Returns the initials of the Owner
	 * @return String containing the initials of the owner
	 */
	public String getInitials() {
		String name = getFullName();
		// this array will contains the initials of our name
		char[] initials = new char[3];
		initials[2] =' ';
		int counter = 0;
		// we add a space before the first name in order to use the char " " as
		// a separation
		name = " " + name;
		for (int i = 0; i < name.length(); i++) {
			char c1 = name.charAt(i);
			// if there is a space
			if (c1 == ' ') {
				// the next character is an initial
				initials[counter] = name.charAt(i + 1);
				counter++;
			}
		}
		return "" + initials[0] + "." + initials[1] + "." + initials[2];
	}
}