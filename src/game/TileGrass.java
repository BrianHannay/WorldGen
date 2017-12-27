package game;

import javax.swing.*;

class TileGrass extends Tile{
	private static final boolean SOLID = false;
	JLabel image;
	public TileGrass(){
		spawnableMobs.add(EntitySlime.class);
		image = new JLabel(ImagesAndAnimations.grassImage);

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
