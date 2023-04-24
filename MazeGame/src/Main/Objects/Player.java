package Main.Objects;

import java.awt.geom.Rectangle2D;

import Main.Game;
import Main.Track;
import Main.Managers.InputManager;
import Main.Managers.SaveManager;
import Main.Managers.UIManager;

public class Player extends Object {

	private static InputManager input = InputManager.getInput();
	
	private Rectangle2D.Double rect;
	
	public Player() {
		this.rect = new Rectangle2D.Double(50,50,25,25);
	
	}
	
	/**
	 * Runs player logic
	 */
	public void doLogic() {
		move();
	}
	
	public void killPlayer() {
		UIManager.youWin = true;
		if(SaveManager.getHighscore() < Game.score) {
			SaveManager.setNewHighscore(Game.score);
		}
		SaveManager.setNewWins(SaveManager.getWins() + 1);
	}
	
	
	/**
	 * Handles player movement
	 */
	public void move() {
		Track track = Game.currentTrack;
		FinishLine line = track.getFinishLine();
		if((input.getKeysDown().contains(38)) || (input.getKeysDown().contains(87))) {
			for(Wall wall : track.getWalls()) {
				if(wall != null) {
					if(wall.checkIfHit(rect.x, rect.y-2, rect.width, rect.height)) {
						return;
					}
				}
			}
			if(line.checkIfHit(rect.x, rect.y-2, rect.width, rect.height)) {
				killPlayer();
			}
			rect.y -= 2;
		}
		if ((input.getKeysDown().contains(40)) || (input.getKeysDown().contains(83))) {
			for(Wall wall : track.getWalls()) {
				if(wall != null) {
					if(wall.checkIfHit(rect.x, rect.y+2, rect.width, rect.height)) {
						return;
					}
				}
			}
			if(line.checkIfHit(rect.x, rect.y+2, rect.width, rect.height)) {
				killPlayer();
			}
			rect.y += 2;
		}
		if ((input.getKeysDown().contains(39)) || (input.getKeysDown().contains(68))) {
			for(Wall wall : track.getWalls()) {
				if(wall != null) {
					if(wall.checkIfHit(rect.x+2, rect.y, rect.width, rect.height)) {
						return;
					}
				}
			}
			if(line.checkIfHit(rect.x+2, rect.y, rect.width, rect.height)) {
				killPlayer();
			}
			rect.x += 2;
		}
		if ((input.getKeysDown().contains(37)) || (input.getKeysDown().contains(65))) {
			for(Wall wall : track.getWalls()) {
				if(wall != null) {
					if(wall.checkIfHit(rect.x-2, rect.y, rect.width, rect.height)) {
						return;
					}
				}
			}
			if(line.checkIfHit(rect.x-2, rect.y, rect.width, rect.height)) {
				killPlayer();
			}
			rect.x -= 2;
		}
	}
	
	@Override
	public boolean checkIfHit(double x, double y, double width, double height) {
		return false;
	}
	
	// Getters
	
	public Rectangle2D.Double getRect() {
		return this.rect;
	}
}
