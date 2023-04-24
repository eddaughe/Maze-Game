package Main;

import java.awt.Dimension;

import Main.Managers.FrameRateManager;
import Main.Managers.InputManager;
import Main.Managers.SaveManager;
import Main.Managers.UIManager;
import Main.Objects.Player;
import Main.Utils.TrackReader;

public class Game implements Runnable {

	public static int score = 1000000;
	
	public static final double GRAPHICS_FRAME_RATE = 16.66667; // 60 fps
	public static final double LOGIC_FRAME_RATE = 16.66667; // 60 fps
	public InputManager inputManager = new InputManager();
	public static Player player = new Player();
	
	public static Game graphics = new Game(new UIManager(new Dimension(800, 600), player), new FrameRateManager(GRAPHICS_FRAME_RATE));
	public static Game logic = new Game(null, new FrameRateManager(LOGIC_FRAME_RATE));
	public static Thread graphicsThread;
	public static Thread logicThread;
	
	public static Track currentTrack;
	public static TrackReader reader;
	
	private UIManager uim;
	private FrameRateManager frm;
	
	
	public Game(UIManager uim, FrameRateManager frm) {
		this.uim = uim;
		this.frm = frm;
	}
	
	public static void main(String[] args) {
		SaveManager.load();
		reader = new TrackReader(500);
		reader.readTrack("src/maze");
		currentTrack = new Track(reader.getWalls(), reader.getFinishLine());
		graphicsThread = new Thread(graphics, "graphics");
		graphicsThread.start();
		logicThread = new Thread(logic, "logic");
		logicThread.start();
	}
	
	@Override
	public void run() {
		UIManager ui = this.getUIManager();
		FrameRateManager fr = this.getFrameRateManager();
		while(true) {
			if(ui == null) {
				fr.setStartingTime(System.currentTimeMillis());
				doLogic();
				fr.setEndingTime(System.currentTimeMillis());
			} else {
				fr.setStartingTime(System.currentTimeMillis());
				ui.draw();
				fr.setEndingTime(System.currentTimeMillis());
			}
		}
	}
	
	public void doLogic() {
		if(!UIManager.youWin) {
			score -= 1;
			player.doLogic();
		} else {
			SaveManager.save();
		}
	}
	
	public void terminate() {
		System.exit(0);
	}
	
	public UIManager getUIManager() {
		return this.uim;
	}
	
	public FrameRateManager getFrameRateManager() {
		return this.frm;
	}
	
	public Player getPlayer() {
		return player;
	}
	
}
