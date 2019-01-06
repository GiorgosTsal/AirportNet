import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AirportPageFrame extends JFrame {
	private JTextField name;
	private JTextField id;
	private JTextField city;
	private JTextField country;
	private JList companyList;
	private JPanel airportDetailsPanel;
	private JPanel findFlights;
	private JPanel centralPanel;
	private JTextField otherCity;
	private JButton findButton;
	private JPanel flightsDetails;
	private JTextArea directFlights;
	private JTextArea intermediateAirport;
	private ArrayList<Airport> airportlist;
	private ArrayList<Flight> flightlist;
	private JPanel back;
	private JButton backButton;
	public Airport selectedAirport1;

	public AirportPageFrame(Airport selectedAirport, ArrayList<Airport> airports, ArrayList<Flight> flights) {
		airportlist = airports;
		flightlist = flights;
		selectedAirport1 = selectedAirport;

		name = new JTextField(selectedAirport.getName());
		name.setEditable(false);
		id = new JTextField(selectedAirport.getId());
		id.setEditable(false);
		city = new JTextField(selectedAirport.getCity());
		city.setEditable(false);
		country = new JTextField(selectedAirport.getCountry());
		country.setEditable(false);
		companyList = new JList();
		airportDetailsPanel = new JPanel();
		findFlights = new JPanel();
		centralPanel = new JPanel();
		otherCity = new JTextField("City");
		otherCity.setPreferredSize(new Dimension(100, 25));
		findButton = new JButton("Find Flights");
		flightsDetails = new JPanel();
		directFlights = new JTextArea();
		directFlights.setPreferredSize(new Dimension(300, 250));
		intermediateAirport = new JTextArea();
		intermediateAirport.setPreferredSize(new Dimension(300, 250));
		back = new JPanel();
		backButton = new JButton("Back to Search Screen");
		
		//center jframe center, regardless of monitor resolution
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/4-this.getSize().width/2, dim.height/4-this.getSize().height/2);

		BorderLayout border = new BorderLayout();
		centralPanel.setLayout(border);

		centralPanel.add(airportDetailsPanel, BorderLayout.PAGE_START);
		centralPanel.add(findFlights, BorderLayout.AFTER_LINE_ENDS);
		centralPanel.add(flightsDetails, BorderLayout.CENTER);
		centralPanel.add(back, BorderLayout.SOUTH);

		DefaultListModel model = new DefaultListModel();
		for (String company : selectedAirport.getCompanies()) {
			model.addElement(company);
		}

		companyList.setModel(model);

		this.setContentPane(centralPanel);

		airportDetailsPanel.add(name);
		airportDetailsPanel.add(id);
		airportDetailsPanel.add(city);
		airportDetailsPanel.add(country);
		airportDetailsPanel.add(companyList);

		findFlights.add(otherCity);
		findFlights.add(findButton);

		flightsDetails.add(directFlights);
		flightsDetails.add(intermediateAirport);

		back.add(backButton);

		GridLayout grid = new GridLayout(1, 5);
		airportDetailsPanel.setLayout(grid);

		ButtonListener listener = new ButtonListener(selectedAirport1);
		findButton.addActionListener(listener);
		backButton.addActionListener(listener);

		this.setVisible(true);
		this.setSize(900, 600);
		this.setTitle("Airport Page");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	class ButtonListener implements ActionListener {

		public ButtonListener(Airport airport1) {
			Airport selectedAirport1 = airport1;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			String selectedCityName = otherCity.getText();

			if (e.getSource() == findButton) {
				Airport selectedAirport2 = null;
				boolean found = false;
				for (Airport airport : airportlist) {
					if (airport.getCity().equals(selectedCityName)) {
						selectedAirport2 = airport;
						if (selectedAirport2.equals(selectedAirport1)) {
							ErrorCityFrame eCf = new ErrorCityFrame();
						}
						found = true;
					
						
						directFlights.setText(CentralRegistry.getDirectFlightsDetails(selectedAirport1, selectedAirport2));
						intermediateAirport
								.setText(CentralRegistry.getInDirectFlightsDetails(selectedAirport1, selectedAirport2));
						// CentralRegistry.getDirectFlightsDetails(selectedAirport1, selectedAirport2);
						// CentralRegistry.getInDirectFlightsDetails(selectedAirport1,
						// selectedAirport2);
						
						
						String FileName =  selectedAirport1.getCity()+ "To"+ selectedAirport2.getCity()+".txt";
						File myFile= new File(FileName);
						try {
							FileWriter writer=new FileWriter(myFile);
							writer.write("CITY: "+selectedAirport1.getCity()+", "+selectedAirport1.getCountry());
							writer.write(System.lineSeparator());
							writer.write("Airport: "+selectedAirport1.getName()+"("+selectedAirport1.getId()+")");
							writer.write(System.lineSeparator());
							writer.write(System.lineSeparator());
							writer.write("DESTINATION: "+selectedAirport2.getCity());
							writer.write(System.lineSeparator());
							writer.write(System.lineSeparator());
							writer.write(CentralRegistry.getDirectFlightsDetails(selectedAirport1, selectedAirport2));
							writer.write(System.lineSeparator());
							writer.write(System.lineSeparator());
							writer.write(CentralRegistry.getInDirectFlightsDetails(selectedAirport1, selectedAirport2));
							
							writer.close();
							
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}


						break;

					}
				}

			} else if (e.getSource() == backButton) {

				FindAirportFrame fAf = new FindAirportFrame(CentralRegistry.airports, CentralRegistry.flights);

			}
		}

	}

}
