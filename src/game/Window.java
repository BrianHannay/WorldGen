package game;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window{
    static final int WIDTH = 800;
    static final int HEIGHT = 600;
	JFrame frame;
	EntityPlayer player;
	public boolean[] keysDown = {false,false,false,false};
	protected int leftOffset = -WIDTH/2;
	protected int topOffset = -HEIGHT/2;	
	private Layer entityLayer;
	private World groundLayer;
	protected GUI gooey;
	public Window(){
		frame = new JFrame("This is supposed to be a game when its done");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(WIDTH, HEIGHT);
        frame.setVisible(true);
        frame.setResizable(false);
		System.out.println("Created frame");
		entityLayer = new Layer(frame);
		System.out.println("Added playerLayer");
		groundLayer = new World(frame);
		System.out.println("Added world");
       	player = new EntityPlayer(this,groundLayer);
       	System.out.println("Added player");
       	entityLayer.addDrawable(player);
       	gooey = new GUI(this,frame);
       	Timer t = new Timer(1000/60, new ActionListener(){
            public void actionPerformed(ActionEvent e) {
            	groundLayer.draw(leftOffset,topOffset,WIDTH,HEIGHT);
            	entityLayer.draw(leftOffset,topOffset,WIDTH,HEIGHT);
            	tryCreateEntity();
            	gooey.draw();
        		if(keysDown[0]){
        			player.moveUp();
        		}
        		if(keysDown[1]){
        			player.moveLeft();
        		}
        		if(keysDown[2]){
        			player.moveDown();
        		}
        		if(keysDown[3]){
        			player.moveRight();
        		}
	       	}
       	});
       	t.start();
	}
	public void moveScreenX(int by){
		leftOffset+=by;
	}
	public void moveScreenY(int by){
		topOffset+=by;
	}
	
	public void tryCreateEntity(){
		if(Math.random() > 0.05){
			return;
		}
		int xLocation, yLocation;
		if(entityLayer.getMobCount() < 20){
			if(Math.random() < 0.5){
				if(Math.random() < 0.5){
					xLocation = 0 - Layer.TILEWIDTH * 5;
				}
				else{
					xLocation = WIDTH + Layer.TILEWIDTH * 5;
				}
				yLocation = (int)(Math.random()*WIDTH);
			}
			else{
				if(Math.random() < 0.5){
					yLocation = 0 - Layer.TILEWIDTH * 5;
				}
				else{
					yLocation = HEIGHT + Layer.TILEWIDTH * 5;
				}
				xLocation = (int)(Math.random()*HEIGHT);
			}
			xLocation += leftOffset;
			yLocation += topOffset;
			Entity entityToSpawn = groundLayer.newMobAt(xLocation, yLocation);
			if(entityToSpawn == null){ // can't spawn anything here
				return;
			}
			entityLayer.addDrawable(entityToSpawn);
			//frame.setComponentZOrder(entityToSpawn.getImage(), 0);
			
		}
	}
	public void pressedKey(int i) {
		keysDown[i]=true;
	}
	public void releasedKey(int i){
		keysDown[i]=false;
	}
}