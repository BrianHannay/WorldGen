package game;

import javax.swing.JLabel;

abstract public class Entity extends Tile{

	protected boolean attractedToPlayer;
	abstract public void onCollideWithPlayer(Entity player);
	protected boolean isMob = true;
	protected int health;
	abstract public void draw();

	abstract public JLabel getImage();
	
	public boolean isSolid() {
		return false;
	}
	
	@Override
	public void setShowing(boolean showing){
		if(showing == false){
			
		}
	}
	@Override
	public boolean isMob(){
		return isMob;
	}
	public String toString(){
		return "Instance of "+this.getClass()+ " at x: "+x+", y: "+y;		
	}

}
