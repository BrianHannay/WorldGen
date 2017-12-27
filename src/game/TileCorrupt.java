package game;

import javax.swing.JLabel;

public class TileCorrupt extends Tile{

	private static final boolean SOLID = false;
	JLabel image;
	public TileCorrupt(){
		image = new JLabel(ImagesAndAnimations.corruptGrassImage);

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
