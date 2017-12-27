package game;

import javax.swing.*;

public class TileTree extends Tile {

	private static final boolean SOLID = true;	
	private JLabel image;
	
	public TileTree() {
			image = new JLabel(ImagesAndAnimations.treeImage);
	}
	
	public void draw() {

	}

	public JLabel getImage() {
		// TODO Auto-generated method stub
		return image;
	}

	public boolean isSolid() {
		// TODO Auto-generated method stub
		return SOLID;
	}

}
