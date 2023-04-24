package Main.Managers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import Main.Game;
import Main.Track;
import Main.Objects.FinishLine;
import Main.Objects.Player;
import Main.Objects.Wall;

public class UIManager {

	public static boolean youWin = false;
	
	public static final double DEFAULT_WIDTH = 800;
	public static final double DEFAULT_HEIGHT = 600;
	
	public static double WIDTH_SCALE = 1;
	public static double HEIGHT_SCALE = 1;
	
	private JFrame frame;
	private Graphics2D graphics;
	private BufferStrategy buff;
	private Player player;
	
	public UIManager(Dimension size, Player player) {
		frame = new JFrame();
		frame.setSize(size);
		frame.setLocation(500, 0);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.player = player;
	}
	
	public void setupBuff() {
		if(this.buff == null) { this.frame.createBufferStrategy(4); }
		this.buff = this.frame.getBufferStrategy();
		this.graphics = (Graphics2D) this.buff.getDrawGraphics();
		InputManager handler = InputManager.getInput();
		frame.addKeyListener(handler);
		frame.addMouseListener(handler);
		frame.addMouseMotionListener(handler);
	}
	
	public void disposeAndShow() {
		this.graphics.dispose();
		this.buff.show();
	}
	
	
	public void draw() {
		setupBuff();
		//---------------------------------------------------------------
		WIDTH_SCALE = ((double) this.frame.getWidth() / DEFAULT_WIDTH);
		HEIGHT_SCALE = ((double) this.frame.getHeight() / DEFAULT_HEIGHT);
		AffineTransform at = new AffineTransform(1.0, 0, 0, 1.0, 0, 0);
		at.scale(WIDTH_SCALE, HEIGHT_SCALE);
		graphics.setTransform(at);
		
		graphics.setColor(Color.white);
		graphics.fillRect(0, 0, 800, 600);
		Track track = Game.currentTrack;
		
		graphics.setColor(Color.gray);
		for(int i = 0; i < track.getWalls().length; i++) {
			Wall wall = track.getWalls()[i];
			if(wall == null) break;
			Rectangle2D rect = new Rectangle2D.Double(wall.getX(), wall.getY(), wall.getWidth(), wall.getHeight());
			graphics.fill(rect);
		}
		
		graphics.setColor(Color.green);
		graphics.fill(player.getRect());
		
		graphics.setColor(Color.magenta);
		FinishLine line = track.getFinishLine();
		if(line != null) {
			Rectangle2D rect = new Rectangle2D.Double(line.getX(), line.getY(), line.getWidth(), line.getHeight());
			graphics.fill(rect);
		}
		graphics.setFont(new Font("EricSmall", 12, 14));
		graphics.setColor(Color.BLACK);
		graphics.drawString("score: " + Game.score, 50, 100);
		
		
		if(youWin) {
			graphics.setFont(new Font("EricBig", 12, 36));
			graphics.drawString("winner", 100, 200);
			graphics.drawString("score: " + Game.score, 100, 300);
			graphics.drawString("high score: " + SaveManager.getHighscore(), 100, 400);
			graphics.drawString("wins: " + SaveManager.getWins(), 100, 500);
		}
		
		
		//--------------------------------------------------------------
		disposeAndShow();
	}
	
}
