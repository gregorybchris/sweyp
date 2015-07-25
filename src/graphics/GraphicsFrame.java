package graphics;

import javax.swing.JFrame;

import data.Config;
import data.IOData;

public class GraphicsFrame extends JFrame {
	private static final long serialVersionUID = 1175325062L;

	private GraphicsPanel graphicsPanel;

	public GraphicsFrame(String title, Config config, IOData iodata) {
		super(title);
		
		graphicsPanel = new GraphicsPanel(config, iodata);
		this.getContentPane().add(graphicsPanel);
	}
	
	public void refresh() {
		graphicsPanel.repaint();
	}
}