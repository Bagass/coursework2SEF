package F21SF_Coursework2;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * @author Antoine Degrandi / Tom Feraud / Jérémie Koster F21SF_Assignment 2
 */
public class hotelGUI extends JFrame implements ActionListener {

	private AccommodationList list;

	JButton submitButton;
	JTextField roomNumber = new JTextField(3);
	JTextField roomType = new JTextField(5);
	JTextField owner = new JTextField(5);
	JTextArea infos;

	/**
	 * Create a "hotelGUI" object and fill the instance variable "list" with the
	 * list provided in parameter and set up the Panels
	 */
	public hotelGUI(AccommodationList list) {

		this.list = list;

		setupNorthPanel();
		setupCenterPanel();
		setupEasthPanel();
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
		message.add(new JLabel("Add a hotel room for the current night"));

		//
		JPanel northPanel = new JPanel();
		// northPanel.setLayout(new GridLayout(2, 1));
		northPanel.add(message);

		this.add(northPanel, BorderLayout.NORTH);

	}

	/**
	 * Set Up the centre Panel of the GUI:
	 */
	private void setupCenterPanel() {

		JPanel constructor = new JPanel();
		constructor.setLayout(new GridLayout(4, 2));

		constructor.add(new JLabel("Room number"));
		constructor.add(roomNumber);

		constructor.add(new JLabel("Room Type"));
		constructor.add(roomType);

		constructor.add(new JLabel("Owner"));

		constructor.add(owner);

		JPanel centerPanel = new JPanel();
		centerPanel.add(constructor);
		this.add(centerPanel, BorderLayout.CENTER);

	}

	/**
	 * Set up the east panel of the GUI
	 */
	private void setupEasthPanel() {
		infos = new JTextArea(40, 30);
		infos.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
		infos.setEditable(false);
		infos.setText("To add a cabin : \n" + "Room number : between 101 & 199 \n"
				+ "Room Type = single, double, family, presidential \n"
				+ "Owner : please enter at least the first and last names \n");

		JScrollPane scrollList = new JScrollPane(infos);
		this.add(scrollList, BorderLayout.EAST);
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
			String accommodationType = "";
			if (roomType.getText().equals("single") || roomType.getText().equals("double")
					|| roomType.getText().equals("family") || roomType.getText().equals("presidential")) {
				accommodationType = roomType.getText();

				try {
					int room = Integer.parseInt(roomNumber.getText());
					Owner owner = new Owner(this.owner.getText());
					HotelRoom newHotelRoom = new HotelRoom(room, accommodationType, owner);

					list.addAccommodation(newHotelRoom);
					System.out.println("The hotel room was well added to the list.");

				} catch (NumberFormatException e1) {
					System.err.println("You have to enter an integer for the room id.");
				} catch (StringIndexOutOfBoundsException e1) {
					System.err.println("You have to enter a full name for the owner (last and first name)");
				}

			} else {
				System.err.println(
						"Type of the room mistaped. Please choice between : \"single\", \"double\", \"family\", \"presidential\"");
			}

			dispose();
		}
	}

}
