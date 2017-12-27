package game;

import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Animation {

	ArrayList<URL> frames = new ArrayList<URL>();
	JLabel image;
	ImageIcon icon;
	int frameCounter = 0;
	int updateFrequency;
	public Animation(String firstPart, String extension, int updateFrequency, int totalFrames){
		this.updateFrequency = updateFrequency;
		for(int i=0; i<totalFrames; i++){
			frames.add(this.getClass().getResource(firstPart+i+extension));	
		}
		this.icon = new ImageIcon(frames.get(0));
		this.image = new JLabel(icon);
	}
	public void draw(){
		frameCounter++;
		frameCounter %= updateFrequency * frames.size();
		if(frameCounter % updateFrequency == 0){
			URL frameId=frames.get(frameCounter/updateFrequency);
			icon=new ImageIcon(frameId);
			this.image.setIcon(icon);
		}
	}
	
	public JLabel getImage(){
		return this.image;
	}
}
