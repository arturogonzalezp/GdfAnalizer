package data;
import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class GdfAnalizer {
    private BufferedReader br = null;
	private FileReader fr = null;
	private ArrayList<GdfNode> nodeList;
	
	public GdfAnalizer(File file){
		nodeList = new ArrayList<GdfNode>();
		System.out.println("Opening: " + file.getAbsolutePath());
		initialize(file);
		
	}
	public GdfNode getMainNode(){
		return nodeList.get(0);
	}
	public ArrayList<GdfNode> getNodeList() {
		return nodeList;
	}
	public void setNodeList(ArrayList<GdfNode> nodeList) {
		this.nodeList = nodeList;
	}
	public GdfNode linearSearchNode(String name){
		for(GdfNode node : nodeList){
			if(node.getName().equals(name)){
				return node;
			}
		}
		return null;
	}
	public void resetNodes(){
		for(GdfNode node : nodeList){
			node.resetNode();
		}
	}
	private void initialize(File file){
		try {
			fr = new FileReader(file.getAbsolutePath());
			br = new BufferedReader(fr);
			String sCurrentLine;
			int step = 0;
			while ((sCurrentLine = br.readLine()) != null) {
				if(sCurrentLine.substring(0, 8).equals("nodedef>") || sCurrentLine.substring(0, 8).equals("edgedef>")){
					step++;
				}else{
					if(step == 1){
						nodeList.add(new GdfNode(sCurrentLine));
					}else if(step == 2){
						// Add unions
						String[] values = sCurrentLine.split(",");
						GdfNode parent = linearSearchNode(values[0]);
						GdfNode child = linearSearchNode(values[1]);
						parent.addNode(child);
						child.addParent(parent);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
	}
}
