package game;

import java.awt.*;

import javax.swing.*;

public class GUI extends JComponent{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1927413075105614647L;
	JLabel coords = new JLabel();
	Window window;
	JLabel tileBox;
	public GUI(Window w, JFrame frame){
		this.window = w;
		frame.add(this.coords);
		coords.setLocation(10,0);
		coords.setSize(500, 60);
		System.out.println("Created GUI");
		//tileBox = new JLabel(ImagesAndAnimations.tileBox);
		//tileBox.setSize(tileBox.getPreferredSize());
		//frame.add(tileBox);
		//frame.getRootPane().setCursor(new Cursor(""));

		
		  Toolkit toolkit = Toolkit.getDefaultToolkit();
		  Image image = ImagesAndAnimations.tileBox.getImage();
		  Cursor c = toolkit.createCustomCursor(image , new Point(frame.getX(),
		  frame.getY()), "img");
		  frame.setCursor (c);
	}
	public void draw(){
		coords.setText("<html>Player x: "+this.window.player.x+"<br>Player y: "+ this.window.player.y+"</html>");
	}
	public void mouseAt(int x, int y) {
		
		//tileBox.setLocation(x - Layer.TILEWIDTH/2,y - Layer.TILEWIDTH/2);
	}
}
