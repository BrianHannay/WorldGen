package game;

import java.util.ArrayList;

public class Chunk {
	ArrayList<Tile> blocksInChunk = new ArrayList<Tile>();
	//layer
	int x=0;
	int y=0;
	World world;
	public Chunk(World world, WorldGen worldGenerator, int x, int y) {
		this.x=x;
		this.y=y;
		this.world = world;
		@SuppressWarnings("rawtypes")
		ArrayList<Class> biome =  worldGenerator.getChunkType(x,y);
		ArrayList<Tile> tilesToAdd = worldGenerator.getChunk(x, y, biome);
		int numTiles = tilesToAdd.size();
		for(int i=0;i<numTiles;i++){
			add(tilesToAdd.get(i));
		}
	}
	void add(Tile t){
		world.addTile(t);
		blocksInChunk.add(t);
	}
}
