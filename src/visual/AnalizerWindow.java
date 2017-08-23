package visual;

import java.awt.TextArea;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import data.GdfAnalizer;

public class AnalizerWindow extends JFrame{

	final static JFileChooser fc = new JFileChooser();
	final static TextArea textArea = new TextArea();
	private GdfAnalizer gdf;
	public AnalizerWindow() {
		this("Arturo se la rifó");
	}
	public AnalizerWindow(String message){
		super(message);
		create();
	}
	public void create(){
		setSize(600, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(textArea);
		setVisible(true);
		int returnVal = fc.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            gdf = new GdfAnalizer(file);
        } else {
        	println("Open command cancelled by user");
        }
	}
	public GdfAnalizer getGdfAnalizer(){
		return gdf;
	}
	public void println(String str) {
		textArea.append(str + "\n");
	}
}
