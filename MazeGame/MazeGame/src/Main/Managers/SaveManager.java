package Main.Managers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class SaveManager {

	
	public static final String file = "src/save.csv";
	
	
	/** 
	 *  [["highscore", "wins"], [1, 1]]
	 */
	
	public static String[][] saveData = new String[10][10];
	
	public SaveManager() {
	}

	
	public static void load() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			int lineCount = 0;
			int colCount = 0;
			while((line = br.readLine()) != null) {
				String[] data = line.split(",");
				colCount = 0;
				for(String d : data) {
					saveData[lineCount][colCount] = d;
					colCount++;
				}
				lineCount++;
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void save() {
		try {
			BufferedWriter wr = new BufferedWriter(new FileWriter(file));
			for(String[] line : saveData) {
				if(line[0] == null || line[1] == null) continue;
				wr.write(line[0] + "," + line[1] + "\n");
			}
			wr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void setNewHighscore(int highscore) {
		saveData[1][0] = highscore + "";
	}
	
	public static int getHighscore() {
		return Integer.parseInt(saveData[1][0]);
	}
	
	public static int getWins() {
		return Integer.parseInt(saveData[1][1]);
	}
	
	public static void setNewWins(int wins) {
		saveData[1][1] = wins + "";
	}
	
}
