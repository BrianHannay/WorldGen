package game;

import javax.swing.JLabel;

public class EntitySlime extends Entity{

	
	private JLabel image = new JLabel(ImagesAndAnimations.slimeImage);
	@SuppressWarnings("unused")
	private static final int SPEED=Layer.TILEWIDTH/8;
	
	public EntitySlime(){
		image.setMinimumSize(image.getPreferredSize());
	}
	public void onCollideWithPlayer(Entity player) {
		
	}

	public void draw() {
		this.image.repaint();
	}

	public JLabel getImage() {
		return this.image;
	}

}
