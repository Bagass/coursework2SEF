package F21SF_Coursework2;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Antoine Degrandi / Tom Feraud / Jérémie Koster F21SF_Assignment 2
 */
public class flatGUI extends JFrame implements ActionListener {

	private AccommodationList list;

	JButton submitButton;
	JPanel constructor = new JPanel();
	JTextField flatNumber = new JTextField(3);
	JTextField owner = new JTextField(5);
	JTextField flatType = new JTextField(5);
	JTextField numberRooms = new JTextField(5);
	JTextField numberOfBeds = new JTextField(5);
	JTextField internet = new JTextField(5);
	JTextField bedPack = new JTextField(5);

	/**
	 * Create a "flatGUI" object and fill the instance variable "list" with the
	 * list provided in parameter and set up the Panels
	 */
	public flatGUI(AccommodationList list) {

		this.list = list;

		setupNorthPanel();
		setupCenterPanel();
		setupSouthPanel();

		// pack and set visible
		pack();
		setVisible(true);

	}

	/**
	 * Set Up the north Panel of the GUI:
	 */
	private void setupNorthPanel() {
		//
		JPanel message = new JPanel();
		message.add(new JLabel("Add a student flat for the current night"));

		//
		JPanel northPanel = new JPanel();
		northPanel.add(message);

		this.add(northPanel, BorderLayout.NORTH);

	}

	/**
	 * Set up the centre panel of the GUI
	 */
	private void setupCenterPanel() {

		constructor.setLayout(new GridLayout(8, 2));
		constructor.add(new JLabel("Flat number"));

		constructor.add(flatNumber);

		constructor.add(new JLabel("Owner"));
		constructor.add(owner);

		constructor.add(new JLabel("Flat Type"));
		constructor.add(flatType);

		constructor.add(new JLabel("Number of rooms"));
		constructor.add(numberRooms);

		constructor.add(new JLabel("Number of beds"));
		constructor.add(numberOfBeds);

		constructor.add(new JLabel("Internet connection ? (true or false)"));
		constructor.add(internet);

		constructor.add(new JLabel("Beding pack ? (true or false)"));
		constructor.add(bedPack);

		JPanel centerPanel = new JPanel();
		centerPanel.add(constructor);
		this.add(centerPanel, BorderLayout.CENTER);
	}

	/**
	 * Set up the south panel with two buttons to interact with the user. A
	 * listener is set on each button
	 */
	private void setupSouthPanel() {
		JPanel end = new JPanel();
		end.setLayout(new GridLayout(1, 2));
		submitButton = new JButton("Submit");
		end.add(submitButton);
		submitButton.addActionListener(this);

		JPanel southPanel = new JPanel();
		southPanel.add(end);
		this.add(southPanel, BorderLayout.SOUTH);

	}

	/**
	 * Manage the event when the user clicks on the submit button. calls the
	 * Cabin constructor and build a Cabin with the elements provided in the
	 * textfields
	 * 
	 * @param e
	 *            ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == submitButton) {
			int accommodationNumber = Integer.parseInt(flatNumber.getText());
			Owner warden = new Owner(owner.getText());
			String accommodationType = flatType.getText();
			int nbRoom = Integer.parseInt(numberRooms.getText());
			int[] nbBeds = convertStringToIntArray(this.numberOfBeds.getText());
			boolean internetConnection;

			if (this.internet.getText() == "true") {
				internetConnection = true;
			} else {
				internetConnection = false;
			}

			boolean bedingPack;
			if (this.bedPack.getText() == "true") {
				bedingPack = true;
			} else {
				bedingPack = false;
			}
			StudentFlat newFlat = new StudentFlat(accommodationNumber, warden, accommodationType, nbRoom, nbBeds,
					internetConnection, bedingPack);
			list.addAccommodation(newFlat);
			System.out.println("The student flat was well added to the list.");
			dispose();

		}
	}

	/**
	 * Converts the string provided in the textfield into an array of int in
	 * order to give the constructor the right format of data
	 * 
	 * @param String
	 *            containing elements provided in the textfield
	 * @return strNb int[]
	 */
	public int[] convertStringToIntArray(String abc) {
		abc = abc.replace("{", ""); // We replace "{", "}" and ":" to fit to the
									// format in input.
		abc = abc.replace("}", "");
		abc = abc.replace(":", " ");
		String[] sepNb = abc.split(" ");
		int i = 0;
		int[] strNb = new int[sepNb.length];
		for (i = 0; i < sepNb.length; i++) {
			strNb[i] += Integer.parseInt(sepNb[i]);
		}
		return strNb;
	}

}
