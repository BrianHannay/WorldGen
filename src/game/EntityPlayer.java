package game;

import javax.swing.JLabel;

public class EntityPlayer extends Entity{

	Window window;
	JLabel image;
	Boolean controllable = false;
	World groundLayer;
	private static final int SPEED=Layer.TILEWIDTH/4;
	private boolean spawnedFlag = true;
	public EntityPlayer(Window window, World groundLayer){
		image = new JLabel(ImagesAndAnimations.playerImage);
		this.window=window;
		this.groundLayer=groundLayer;
		this.controllable=true;
		System.out.println("Player spawned at "+x+", "+y);
	}
	public JLabel getImage(){
		return image;
	}
	
	public void draw() {
		if(spawnedFlag){
			while(spawnedFlag){
				spawnedFlag = false;
				int i=0;
				while((!groundLayer.isPositionWalkable(x, y)) && ++i < 10){
					int xDiff = Layer.TILEWIDTH * (int) (Math.random()*30);
					int yDiff = Layer.TILEWIDTH * (int) (Math.random()*30);
					this.x+=xDiff;
					window.moveScreenX(xDiff);
					this.y+=yDiff;
					window.moveScreenY(yDiff);
				}
				if(i==10){
					System.out.println("This world sucks, making a new one.");
					groundLayer.resetWorldGen();
					spawnedFlag = true;
				}
				i=0;
				
			}
			window.frame.setVisible(true);
		}
	}
	public void moved(){
		if(!groundLayer.isPositionWalkable(x, y)){
			this.y+=Layer.TILEWIDTH;
			window.moveScreenY(Layer.TILEWIDTH);
		}
	}
	public boolean moveUp() {
		moved();
		if(controllable && groundLayer.isPositionWalkable(x,y-SPEED)){
			this.y-=SPEED;
			window.moveScreenY(-SPEED);
			return true;
		}
		return false;
	}
	public boolean moveLeft() {
		moved();
		if(controllable && groundLayer.isPositionWalkable(x-SPEED,y)){
			this.x-=SPEED;
			window.moveScreenX(-SPEED);
			return true;
		}
		return false;
	}
	public boolean moveRight() {
		moved();
		if(controllable && groundLayer.isPositionWalkable(x+SPEED,y)){
			this.x+=SPEED;
			window.moveScreenX(SPEED);
			return true;
		}
		return false;
	}
	public boolean moveDown() {
		moved();
		if(controllable && groundLayer.isPositionWalkable(x,y+SPEED)){
			this.y+=SPEED;
			window.moveScreenY(SPEED);
			return true;
		}
		return false;
	}
	
	public void onCollideWithPlayer(Entity player) {
	}
}
