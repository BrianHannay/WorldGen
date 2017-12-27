package game;

import javax.swing.JLabel;

public class TileSea extends Tile {

	private static final boolean SOLID = true;
	Animation image;
	public TileSea(){
		try{
			image = new Animation("images/sea",".png", 20	, 4);
		}
		catch(Exception e){
			System.out.println(e);
			System.exit(-500);
		}
	}

	public void draw() {
		image.draw();
	}

	public JLabel getImage() {
		return image.getImage();
	}

	public boolean isSolid() {
		return SOLID;
	}

}
