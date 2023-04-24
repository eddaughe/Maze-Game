package Main.Utils;

import java.io.BufferedReader;
import java.io.FileReader;

import Main.Objects.FinishLine;
import Main.Objects.Wall;

public class TrackReader {
	
	private Wall[] walls;
	private FinishLine finish;
	private int size;
	
	public TrackReader(int size) {
		this.size = size;
		this.walls = new Wall[this.size];
	}
	
	/**
	 * sets walls to whatever is read in from the given file
	 * @param filename - file name of track
	 */
	public void readTrack(String filename) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String st;
			double x = 0;
			double y = 0;
			double width = Wall.DEFAULT_WIDTH;
			double height = Wall.DEFAULT_HEIGHT;
			int wallCount = 0;
			while((st = br.readLine()) != null) {
				for(int i = 0; i < st.length(); i++) {
					char c = st.charAt(i);
					if(c == '0') {
						Wall wall = new Wall(x, y, width, height);
						this.walls[wallCount++] = wall;
					} else if(c == 'f') {
						FinishLine line = new FinishLine((int) x, (int) y);
						this.finish = line;
					}
					x += Wall.DEFAULT_WIDTH;
				}
				y += Wall.DEFAULT_HEIGHT;
				x = 0;
			}
			br.close();
		} catch (Exception e) {
			this.walls = null;
			e.printStackTrace();
		}
		
	}
	
	public Wall[] getWalls() {
		return this.walls;
	}
	
	public FinishLine getFinishLine() {
		return this.finish;
	}
}
