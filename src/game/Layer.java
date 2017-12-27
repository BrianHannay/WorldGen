package game;

import java.util.ArrayList;
import javax.swing.*;

class Layer{
	static final int TILEWIDTH = 32;
	private JFrame frame;
	//private TilePlayer player;
	private ArrayList<Tile> thingsToDraw = new ArrayList<Tile>();
	public Layer(JFrame frame){
		this.frame=frame;
	}
	public void draw(int leftOffset, int topOffset, int width, int height){
		for(Tile t: thingsToDraw){
        	t.getImage().setLocation(t.x-leftOffset,t.y-topOffset);
        	t.getImage().setSize(t.getImage().getPreferredSize());
			t.draw();
		}
	}
	public void addDrawable(Tile d){
		d.getImage().setSize(d.getImage().getPreferredSize());
		thingsToDraw.add(d);
		frame.add(d.getImage());
	}
	public int getMobCount() {
		int mobs = 0;
		for(Tile i: thingsToDraw){
			if(i.isMob()){
				if(++mobs == 20){
					break;
				}
			}
		}
		return mobs;
	}
}