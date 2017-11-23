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
public class cabinGUI extends JFrame implements ActionListener {

	private AccommodationList list;

	JButton submitButton;
	JTextField cabinNumber = new JTextField(3);
	JTextField numberOfBeds = new JTextField(5);
	JTextField owner = new JTextField(5);
	JTextField creationYear = new JTextField(5);
	JTextField cabinType = new JTextField(5);
	JTextField numberDoubleBeds = new JTextField(5);
	JTextField TV = new JTextField(5);
	JTextArea infos;

	/**
	 * Create a "cabinGUI" object and fill the instance variable "list" with the
	 * list provided in parameter and set up the Panels
	 */
	public cabinGUI(AccommodationList list) {

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
		message.add(new JLabel("Add a cabin for the current night"));

		//
		JPanel northPanel = new JPanel();
		// northPanel.setLayout(new GridLayout(2, 1));
		northPanel.add(message);

		this.add(northPanel, BorderLayout.NORTH);

	}

	/**
	 * Set up the centre panel of the GUI
	 */
	private void setupCenterPanel() {

		JPanel constructor = new JPanel();
		constructor.setLayout(new GridLayout(8, 2));

		constructor.add(new JLabel("Cabin number"));
		constructor.add(cabinNumber);

		constructor.add(new JLabel("Number of beds"));
		constructor.add(numberOfBeds);

		constructor.add(new JLabel("Owner"));
		constructor.add(owner);

		constructor.add(new JLabel("Cabin Type"));
		constructor.add(cabinType);

		constructor.add(new JLabel("Creation (year)"));
		constructor.add(creationYear);

		constructor.add(new JLabel("Number of double bed"));
		constructor.add(numberDoubleBeds);

		constructor.add(new JLabel("TV ? (true or false)"));
		constructor.add(TV);

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
		infos.setText("To add a cabin : \n" + "Cabin number : between 1 & 100 \n"
				+ "Numbers of beds : syntax = x:y:z where x,y,y are the numbers\n"
				+ "Cabin Type = ensuite small cabin, ensuite large cabin, simple small cabin, simple large cabin\n"
				+ "Creation year = 2000 for example \n" + "Number of double bed = 2 for example \n"
				+ "TV : enter \"true\" or \"false\" \n");
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
			String accommodationType = cabinType.getText();
			int[] numberOfBeds = convertStringToIntArray(this.numberOfBeds.getText());
			int numCab = Integer.parseInt(cabinNumber.getText());
			Owner accommodationOwner = new Owner(this.owner.getText());
			int creationYear = Integer.parseInt(this.creationYear.getText());
			int numberOfDoubleBed = Integer.parseInt(this.numberDoubleBeds.getText());
			boolean TV;

			if (this.TV.getText() == "true") {
				TV = true;
			} else {
				TV = false;
			}

			Cabin newCab = new Cabin(numCab, numberOfBeds, accommodationOwner, accommodationType, creationYear,
					numberOfDoubleBed, TV);
			list.addAccommodation(newCab);
			System.out.println("The cabin was well added to the list.)");

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
