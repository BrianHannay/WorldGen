package game;

import javax.swing.JLabel;

public class TileDesert extends Tile{

	private static final boolean SOLID = false;
	JLabel image;
	public TileDesert(){

		image = new JLabel(ImagesAndAnimations.desertImage);

	}
	public JLabel getImage(){
		return image;
	}
	
	public void draw() {
	}
	public boolean isSolid(){
		return SOLID;
	}

}
