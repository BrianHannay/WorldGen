package game;

import java.util.ArrayList;

public abstract class Tile implements Drawable{
	public int x=0,y=0;
	private boolean showing=true;
	private boolean isMob = false;
	@SuppressWarnings("rawtypes")
	protected ArrayList<Class> spawnableMobs = new ArrayList<Class>();
	abstract public boolean isSolid();

	public boolean getShowing() {
		return showing;
	}

	public void setShowing(boolean showing) {
		this.showing = showing;
	}
	@SuppressWarnings("rawtypes")
	public ArrayList<Class> spawnableMobs(){
		return spawnableMobs;
	}
	public boolean isMob(){
		return this.isMob;
	}

}
