import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.visualization.VisualizationImageServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;

public class Graphs extends JFrame {
	//private JPanel panel;

	public Graphs() {
		Graph<String, Flight> g = new SparseMultigraph<String, Flight>();

		for (Airport airport : CentralRegistry.airports) {
			g.addVertex(airport.getCity());
		}

		int count = 1;
		for (Flight flight : CentralRegistry.flights) {
			
			g.addEdge(flight, flight.getDepAirport().getCity(), flight.getArrAirport().getCity());
			count++;

		}

		VisualizationImageServer vs = new VisualizationImageServer(new CircleLayout(g), new Dimension(600, 600));
		vs.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
		
		//panel = new JPanel();
		
		//this.setContentPane(panel);

		this.setVisible(true);
		this.setSize(900, 600);
		this.setTitle("City Airport Connctions Network");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/3-this.getSize().width/2, dim.height/3-this.getSize().height/2);
	
		JFrame frame = new JFrame();
	    frame.getContentPane().add(vs);
	    frame.setSize(900,800);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.pack();
	    frame.setTitle("City Airport Connctions Network");
	    frame.setVisible(true);

	}

}
