package game;

import java.util.ArrayList;

import javax.swing.*;

class World{
	static final int CHUNKWIDTH = Layer.TILEWIDTH*8;
	static final int BIOMEWIDTH = CHUNKWIDTH * 2;
	private JFrame frame;
	private WorldGen worldGenerator;
	private ArrayList<Chunk> chunks = new ArrayList<Chunk>();
	public World(JFrame frame){
		super();
		this.worldGenerator = new WorldGen();
		System.out.println("Created generator for world");
		this.frame=frame;
	}
	public void resetWorldGen(){
		for(Chunk i : chunks){
			for(Tile j : i.blocksInChunk){
				frame.remove(j.getImage());
			}
		}
		chunks = new ArrayList<Chunk>();
		worldGenerator.reset();
	}
	public void draw(int leftOffset, int topOffset, int width, int height){
		int x=leftOffset-(leftOffset%CHUNKWIDTH)-CHUNKWIDTH;
		ArrayList<Chunk> chunksToRemove = new ArrayList<Chunk>();
		for(;x<=leftOffset+width;x+=CHUNKWIDTH){
			int y=topOffset-(topOffset%CHUNKWIDTH)-CHUNKWIDTH;
			for(;y<=topOffset+height;y+=CHUNKWIDTH){
				boolean chunkIsHere = false;
				for(Chunk i: chunks){
					if(i.x==x&&i.y==y){
						chunkIsHere=true;
						break;
					}
				}
				//generate tile for here
				if(!chunkIsHere){
					chunks.add(new Chunk(this,worldGenerator,x,y));
				}
			}
		}
		for(Chunk j: chunks){
			boolean allOffScreen = true;
			for(Tile i: j.blocksInChunk){
				boolean offScreen = (i.x+CHUNKWIDTH<leftOffset||i.x-CHUNKWIDTH>leftOffset+width||i.y+CHUNKWIDTH<topOffset||i.y-CHUNKWIDTH>topOffset+height);
				if(!offScreen){
					allOffScreen = false;
				}
				if(i.getShowing()&&offScreen){ // if showing, but off screen
					i.getImage().setVisible(false);
					i.setShowing(false);
					
				}
				else if(!offScreen&&!i.getShowing()){ //if on screen, but not showing
					i.getImage().setVisible(true);
					i.setShowing(true);
				}
				if(i.getShowing()){
		        	i.getImage().setSize(i.getImage().getPreferredSize());
		        	i.getImage().setLocation(i.x-leftOffset,i.y-topOffset);
					i.draw();
				}
			}
			if(allOffScreen){
				chunksToRemove.add(j);
			}
		}
		for(Chunk c:chunksToRemove){
			chunks.remove(c);
			for(Tile t: c.blocksInChunk){
				frame.remove(t.getImage());
			}
		}
	}
	
	public void addTile(Tile d){
		frame.add(d.getImage());
		d.getImage().setSize(d.getImage().getPreferredSize());
	}
	public boolean setTileAt(int x,int y, Tile type){
		int chunkX = x-(x%CHUNKWIDTH);
		int chunkY = y-(y%CHUNKWIDTH);
		Chunk c=null;
		for(Chunk i : chunks){
			if(i.x == chunkX && i.y == chunkY){
				c = i;
				break;
			}
		}
		if(c==null){
			return false;
		}
		for(Tile j : c.blocksInChunk){
			if(j.x == x && j.y == y){
				frame.remove(j.getImage());
				c.blocksInChunk.remove(j);
				j.getImage().setVisible(false);
				c.blocksInChunk.add(type);
				frame.add(type.getImage());
				type.x = x;
				type.y = y;
				return true;
			}
		}
		return false;
	}
	public boolean isPositionWalkable(int x, int y){
		for(Chunk j: chunks){
			for(Tile i:j.blocksInChunk){
				if((!i.isSolid())||i.x>=x+Layer.TILEWIDTH||i.x+Layer.TILEWIDTH<=x||i.y>=y+Layer.TILEWIDTH||i.y+Layer.TILEWIDTH<=y){
					continue;
				}
				else{
					return false;
				}
			}
		}
		return true;
	}
	public Tile getTileAt(int x, int y) {
		x=x-(x%Layer.TILEWIDTH); // ensure a tile actually can be there
		y=y-(y%Layer.TILEWIDTH);
		
		
		int chunkX = x-(x%CHUNKWIDTH);
		int chunkY = y-(y%CHUNKWIDTH);
		if(chunks.size() == 0){
			System.out.println("Panic! No tiles anywhere!");
			return null;
		}
		for(Chunk i : chunks){
			if(i.x == chunkX && i.y == chunkY){
				for(Tile j : i.blocksInChunk){
					if(j.x == x && j.y == y){
						return j;
					}
				}
				break;
			}
		}
		return null;
	}
	public Entity newMobAt(int x, int y) {
		x=x-(x%Layer.TILEWIDTH); // ensure a tile actually can be there
		y=y-(y%Layer.TILEWIDTH);
		
		Tile location =  getTileAt(x,y);
		if(location == null){
			//There is nothing here! =(
			return null;
		}
		@SuppressWarnings("rawtypes")
		ArrayList<Class> t = location.spawnableMobs();
		if(t.size() == 0){
			return null;
		}
		try {
			Entity e = (Entity) t.get((int)(t.size()*Math.random())).newInstance();
			e.x = x;
			e.y = y;
			return e;
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			System.out.println("Error creating new mob");
			System.exit(500);
			return null;
		}
		
	}
}