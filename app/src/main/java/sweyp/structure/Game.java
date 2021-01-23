package sweyp.structure;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

import sweyp.constants.GC;
import sweyp.constants.UC;
import sweyp.data.Config;
import sweyp.data.IOData;
import sweyp.graphics.GraphicsFrame;

public class Game {
	private GraphicsFrame graphicsFrame;
	private Engine engine;
	
	public Game() {
		Config config = new Config();
		IOData iodata = new IOData();
		engine = new Engine(config, iodata);
		setUpGraphics(config, iodata);
	}
	
	private void setUpGraphics(Config config, IOData iodata) {
		graphicsFrame = new GraphicsFrame(UC.GAME_TITLE, config, iodata);
		graphicsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		graphicsFrame.setBounds(0, 0, GC.SCREEN_WIDTH, 
				GC.SCREEN_HEIGHT + GC.WINDOW_BAR_HEIGHT);
		graphicsFrame.setLocationRelativeTo(null);
		graphicsFrame.setResizable(false);
		graphicsFrame.setVisible(true);
	}
	
	public void startGame() {
		Timer t = new Timer(UC.GAME_DELAY, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				engine.update();
				graphicsFrame.refresh();
			}
		});
		t.start();
	}
}
