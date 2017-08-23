import java.util.ArrayList;

import data.*;
import visual.AnalizerWindow;

public class Main {

	public static void main(String[] args) {
		AnalizerWindow window = new AnalizerWindow();
		GdfAnalizer gdf = window.getGdfAnalizer();
		ArrayList<GdfNode> nodeList = gdf.getNodeList();
		for(GdfNode node : nodeList) {
			window.println(node.toString());
		}
		
	}

}
