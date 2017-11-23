package F21SF_Coursework2;


/***
 * Class corresponding to the main GUI
 * @author Antoine Degrandi / Tom Feraud / Jérémie Koster
 *
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AccommodationListGUI extends JFrame implements ActionListener {

	private AccommodationList accoList;

	// GUI components
	JTextField searchField, searchIdField, searchBudgetField;
	JButton searchButton, searchBudgetButton, sortByPriceButton, sortByBedsButton, searchShortDetailsButton,
			searchFullDetailsButton, cabinButton, hotelButton, flatButton, closeButton, displayStudentFlatButton,
			displayHotelRoomButton, displayCabinButton;
	JTextArea displayTextArea;

	public AccommodationListGUI(AccommodationList list) {
		this.accoList = list;

		// set up title of the window
		this.setTitle("Accommodation list GUI");

		// call the setup functions for the three panels
		setupNorthPanel();
		setupCenterPanel();
		setupSouthPanel();

		setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);

		// pack and set visible
		pack();
		setVisible(true);

	}

	/**
	 * Method that sets up the north panel
	 * 
	 */
	private void setupNorthPanel() {
		// set up the top line to search acco by the number of beds
		JPanel searchBedsPanel = new JPanel();
		searchBedsPanel.setLayout(new GridLayout(1, 3));
		searchBedsPanel.add(new JLabel("Enter number of beds"));
		searchField = new JTextField(5);
		searchBedsPanel.add(searchField);
		searchButton = new JButton("Search");
		searchBedsPanel.add(searchButton);
		// specify action when button 'Search' is pressed
		searchButton.addActionListener(this);

		// set the 2nd line that has 2 buttons to sort the list of acco by price
		// or nb of beds
		JPanel sortByPanel = new JPanel();
		sortByPanel.setLayout(new GridLayout(1, 2));
		// add the two buttons
		sortByPriceButton = new JButton("Room by price");
		sortByBedsButton = new JButton("Room by number of beds");
		sortByPriceButton.addActionListener(this);
		sortByBedsButton.addActionListener(this);
		sortByPanel.add(sortByPriceButton);
		sortByPanel.add(sortByBedsButton);

		// set up the line to enter the id of a room and display short and full
		// details
		JPanel searchAccoDetailsPanel = new JPanel();
		searchAccoDetailsPanel.setLayout(new GridLayout(1, 4));
		// add label
		searchAccoDetailsPanel.add(new JLabel("Enter id of the accommodation"));
		searchIdField = new JTextField(3);
		searchAccoDetailsPanel.add(searchIdField);
		// add the two buttons
		searchShortDetailsButton = new JButton("Short Details");
		searchFullDetailsButton = new JButton("Full Details");
		searchAccoDetailsPanel.add(searchShortDetailsButton);
		searchAccoDetailsPanel.add(searchFullDetailsButton);
		searchShortDetailsButton.addActionListener(this);
		searchFullDetailsButton.addActionListener(this);

		// set up the line to search acco by specifying the maximum budget of
		// the client
		JPanel searchRoomsForBudget = new JPanel();
		searchRoomsForBudget.setLayout(new GridLayout(1, 3));
		searchRoomsForBudget.add(new JLabel("Enter max budget (£)"));
		searchBudgetField = new JTextField(5);
		searchRoomsForBudget.add(searchBudgetField);
		searchBudgetButton = new JButton("Search");
		searchRoomsForBudget.add(searchBudgetButton);
		// specify action when button 'Search' is pressed
		searchBudgetButton.addActionListener(this);

		// set the line that has 3 buttons to display all student flats or
		// cabins or hotel rooms
		// or nb of beds
		JPanel displayAccoOfOneType = new JPanel();
		displayAccoOfOneType.setLayout(new GridLayout(1, 3));
		// add the three buttons
		displayStudentFlatButton = new JButton("Display student flats");
		displayHotelRoomButton = new JButton("Display hotel room");
		displayCabinButton = new JButton("Display cabins");
		displayStudentFlatButton.addActionListener(this);
		displayHotelRoomButton.addActionListener(this);
		displayCabinButton.addActionListener(this);
		displayAccoOfOneType.add(displayStudentFlatButton);
		displayAccoOfOneType.add(displayHotelRoomButton);
		displayAccoOfOneType.add(displayCabinButton);

		// set up the whole north panel containing the 2 previous elements
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new GridLayout(5, 1));
		northPanel.add(searchBedsPanel);
		northPanel.add(sortByPanel);
		northPanel.add(searchAccoDetailsPanel);
		northPanel.add(searchRoomsForBudget);
		northPanel.add(displayAccoOfOneType);

		this.add(northPanel, BorderLayout.NORTH);
	}

	/**
	 * Method that sets up the centre panel
	 * 
	 */
	private void setupCenterPanel() {

		// set up the large text field area where the information will be
		// displayed
		displayTextArea = new JTextArea(30, 50);
		displayTextArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
		displayTextArea.setEditable(false);
		JScrollPane scrollList = new JScrollPane(displayTextArea);
		this.add(scrollList, BorderLayout.CENTER);

	}

	/**
	 * Method that sets up the south panel
	 * 
	 */
	private void setupSouthPanel() {
		//
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(1, 4));
		//
		cabinButton = new JButton("Cabin");
		hotelButton = new JButton("Hotel Room");
		flatButton = new JButton("Student Flat");
		closeButton = new JButton("Close");
		//
		buttonsPanel.add(cabinButton);
		buttonsPanel.add(hotelButton);
		buttonsPanel.add(flatButton);
		buttonsPanel.add(closeButton);
		//
		cabinButton.addActionListener(this);
		hotelButton.addActionListener(this);
		flatButton.addActionListener(this);
		closeButton.addActionListener(this);

		JPanel northPanel = new JPanel();
		northPanel.add(buttonsPanel);

		this.add(northPanel, BorderLayout.SOUTH);
	}

	/**
	 * 
	 * Method that executes an action for each button
	 * 
	 * @param e the action event
	 * 
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == searchButton) {
			try {
				displayTextArea.setText(accoList.findByNumberOfBed(Integer.parseInt(searchField.getText())));
			} catch (NumberFormatException e1) {
				System.err.println(
						"You have to enter an integer for the number of beds. Try with a number between 1 and 9");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource() == sortByBedsButton) {
			try {
				displayTextArea.setText(accoList.sortedListByNumberOfBed());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource() == sortByPriceButton) {
			try {
				displayTextArea.setText(accoList.sortedListCost());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource() == searchFullDetailsButton) {
			try {
				displayTextArea.setText(accoList.GUIFullDetailsByNumber(Integer.parseInt(searchIdField.getText())));
			} catch (NumberFormatException e1) {
				System.err.println("You have to enter an integer for the room id.");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource() == searchShortDetailsButton) {
			try {
				displayTextArea.setText(accoList.GUIShortDetailsByNumber(Integer.parseInt(searchIdField.getText())));
			} catch (NumberFormatException e1) {
				System.err.println("You have to enter an integer for the room id.");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource() == searchBudgetButton) {
			try {
				displayTextArea.setText(accoList.listMaxBudget(Integer.parseInt(searchBudgetField.getText())));
			} catch (NumberFormatException e1) {
				System.err.println(
						"You have to enter an integer for the maximum budget. Try with a number between 30 and 1500.");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource() == displayStudentFlatButton) {
			try {
				displayTextArea.setText(accoList.onlyStudentFlat());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource() == displayHotelRoomButton) {
			try {
				displayTextArea.setText(accoList.onlyHotelRoom());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource() == displayCabinButton) {
			try {
				displayTextArea.setText(accoList.onlyCabin());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource() == cabinButton) {
			cabinGUI testCabin = new cabinGUI(accoList);
			testCabin.setVisible(true);
		} else if (e.getSource() == hotelButton) {
			hotelGUI testCabin = new hotelGUI(accoList);
			testCabin.setVisible(true);
		} else if (e.getSource() == flatButton) {
			flatGUI testCabin = new flatGUI(accoList);
			testCabin.setVisible(true);
		} else if (e.getSource() == closeButton) {
			try {
				accoList.writeToFile("Report.txt", accoList.listDetails());
				System.exit(0);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

	}

}
