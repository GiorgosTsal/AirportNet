import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FindAirportFrame extends JFrame {
	private JPanel panel;
	private JButton button;
	private JTextField airportField;
	private ArrayList<Airport> airports;
	private ArrayList<Flight> flights;
	private JButton buttonGraph;

	public FindAirportFrame(ArrayList<Airport> someAirports, ArrayList<Flight> someFlights) {
		airports = someAirports;
		flights = someFlights;

		panel = new JPanel();
		airportField = new JTextField("Please enter a CITY name");
		airportField.setPreferredSize(new Dimension(100, 25));
		panel.add(airportField);
		button = new JButton("Find");
		buttonGraph = new JButton("Visualize Network");
		panel.add(button);
		panel.add(buttonGraph);

		this.setContentPane(panel);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/3-this.getSize().width/2, dim.height/3-this.getSize().height/2);

		ButtonListener listener = new ButtonListener();
		button.addActionListener(listener);
		buttonGraph.addActionListener(listener);

		this.setVisible(true);
		this.setSize(300, 150);
		this.setTitle("Find Airport");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			String selectedAirportName = airportField.getText();
			if (e.getSource() == button) {

				Airport selectedAirport = null;
				boolean found = false;
				for (Airport airport : airports) {
					if (airport.getName().equals(selectedAirportName)) {
						selectedAirport = airport;
						AirportPageFrame ap = new AirportPageFrame(selectedAirport, airports, flights);
						found = true;
						break;

					}
				}

				if (!found) {
					ErrorFrame ef = new ErrorFrame(selectedAirportName);
				}
			} else if (e.getSource() == buttonGraph) {
				Graphs gr = new Graphs();
			}

		}

	}

}
