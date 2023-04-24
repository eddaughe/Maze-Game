package Main;

import Main.Objects.FinishLine;
import Main.Objects.Wall;

public class Track {

	private Wall[] walls;
	private FinishLine line;
	
	public Track(Wall[] walls, FinishLine line) {
		this.walls = walls;
		this.line = line;	
	}
	
	public Wall[] getWalls() {
		return this.walls;
	}
	
	public FinishLine getFinishLine() {
		return line;
	}

}
