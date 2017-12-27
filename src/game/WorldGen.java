package game;


import java.util.ArrayList;
import java.util.Random;

public class WorldGen {
	private static final int FEATUREFREQUENCY = 1500;
	private static final int MAXFEATURERADIUS = 10;

	Random random;
	private Feature feature = new Feature();
	private int numFeatures = 1;
	@SuppressWarnings("rawtypes")
	private ArrayList<Class> forestTiles = new ArrayList<Class>();
	@SuppressWarnings("rawtypes")
	private ArrayList<Class> plainsTiles = new ArrayList<Class>();
	@SuppressWarnings("rawtypes")
	private ArrayList<Class> corruptTiles = new ArrayList<Class>();
	@SuppressWarnings("rawtypes")
	private ArrayList<Class> desertTiles = new ArrayList<Class>();
	@SuppressWarnings("rawtypes")
	private ArrayList<Class> thickForestTiles = new ArrayList<Class>();
	@SuppressWarnings("rawtypes")
	private ArrayList<Class> seaTiles = new ArrayList<Class>();
	@SuppressWarnings("rawtypes")
	private ArrayList<ArrayList<Class>> biomes = new ArrayList<ArrayList<Class>>();

	private long seed;

	private Random featureRandom;
	public WorldGen(){
		try{
			
			forestTiles.add(Class.forName("game.TileGrass"));
			forestTiles.add(Class.forName("game.TileGrass"));
			forestTiles.add(Class.forName("game.TileGrass"));
			forestTiles.add(Class.forName("game.TileTree"));

			plainsTiles.add(Class.forName("game.TileGrass"));

			seaTiles.add(Class.forName("game.TileSea"));	
			seaTiles.add(Class.forName("game.TileSea"));			
			seaTiles.add(Class.forName("game.TileDesert"));

			corruptTiles.add(Class.forName("game.TileCorrupt"));

			desertTiles.add(Class.forName("game.TileDesert"));
			
			thickForestTiles.add(Class.forName("game.TileTree"));

			biomes.add(forestTiles);
			biomes.add(forestTiles);
			biomes.add(plainsTiles);
			biomes.add(corruptTiles);
			biomes.add(desertTiles);
			biomes.add(thickForestTiles);
			biomes.add(seaTiles);
			System.out.println("Added all the tiles and biomes to world generator");
			
		}
		catch(ClassNotFoundException e){
			System.out.println(e);
			System.exit(404); // couldn't find class with a certain name
		}

		this.random = new Random();
		this.featureRandom = new Random();
		do{
			this.random.setSeed((long) Math.random());
			this.seed = random.nextLong();
			System.out.println("Set random for world generator to new random.");
		}
		while(!testWorldGen()); // while it fails a test, make a new one.
		System.out.println("World generator checked and is working...");
	}
	private long getSeedFrom(int x, int y){
		random.setSeed(x);
		long newx=random.nextLong()+seed;
		random.setSeed(y);
		long newy=random.nextLong()+seed;
		random.setSeed(x);
		
		random.setSeed(newx*newy+this.seed+x-y);
		return random.nextLong();
	}
	
	/**
	 * 
	 * @return if tile at x, y is part of a feature such as a town or temple
	 */
	private int getFeatureAt(int x, int y){
		featureRandom.setSeed(x);
		int xRand = featureRandom.nextInt(FEATUREFREQUENCY);
		featureRandom.setSeed(y);
		int yRand = featureRandom.nextInt(FEATUREFREQUENCY);
		if((xRand*y+yRand*x+x+y>>3) % FEATUREFREQUENCY == 1){
			return Math.abs(featureRandom.nextInt())%numFeatures;
		}
		return -1;
	}

	private int getFeatureTouching(int x, int y){
		for(int i=-MAXFEATURERADIUS; i<MAXFEATURERADIUS; i++){
			for(int j = -MAXFEATURERADIUS; j<MAXFEATURERADIUS; j++){
				int featureIndex=getFeatureAt(x+i,y+j);
				if(featureIndex!=-1){					
					return featureIndex;
				}
			}
		}
		return -1;
	}
	
	private static int dist(int x1, int y1, int x2, int y2){
		int xDist = x1-x2;
		int yDist = y1-y2;
		return (int) Math.sqrt(xDist*xDist+yDist*yDist);
	}
	
	@SuppressWarnings("rawtypes")
	public ArrayList<Class> getChunkType(int x, int y) {
		
		int xOfBiomeStart = x, yOfBiomeStart = y, realXBiomeStart, realYBiomeStart;
		xOfBiomeStart = x - (x%World.BIOMEWIDTH);
		yOfBiomeStart = y - (y%World.BIOMEWIDTH);
		
		realXBiomeStart = xOfBiomeStart;
		realYBiomeStart = yOfBiomeStart;
		int smallestDist = dist(x,y,xOfBiomeStart, yOfBiomeStart);
		if(smallestDist > dist(x,y,xOfBiomeStart+World.BIOMEWIDTH, yOfBiomeStart)){
			realXBiomeStart = xOfBiomeStart + World.BIOMEWIDTH;
		}
		if(smallestDist > dist(x,y,xOfBiomeStart, yOfBiomeStart+World.BIOMEWIDTH)){
			realYBiomeStart = yOfBiomeStart + World.BIOMEWIDTH;
		}
		if(smallestDist > dist(x,y,xOfBiomeStart+World.BIOMEWIDTH, yOfBiomeStart+World.BIOMEWIDTH)){
			realXBiomeStart = xOfBiomeStart + World.BIOMEWIDTH;
			realYBiomeStart = yOfBiomeStart + World.BIOMEWIDTH;
		}
		
		long biomeWidth = (long)(getSeedFrom(realXBiomeStart,realYBiomeStart)%World.BIOMEWIDTH);
		if(dist(x,y,realXBiomeStart, realYBiomeStart) < biomeWidth){
			x=realXBiomeStart;
			y=realYBiomeStart;
			random.setSeed(getSeedFrom(x,y));
			return biomes.get(random.nextInt(biomes.size()));
		}
		else{
			return plainsTiles;
		}
	}
	public ArrayList<Tile> getChunk(int x, int y, @SuppressWarnings("rawtypes") ArrayList<Class> biome) {
		Tile result;
		random.setSeed(getSeedFrom(x,y));
		int yStart = y;
		ArrayList<Tile> resultList = new ArrayList<Tile>();
		for(int xStart = x; x<xStart+World.CHUNKWIDTH;x+=Layer.TILEWIDTH){
			for(y=yStart; y<yStart+World.CHUNKWIDTH;y+=Layer.TILEWIDTH){
				int featureIndex = getFeatureTouching(x,y);
				try{
					if(featureIndex!=-1){
						result =  feature.getPartAt(x,y,featureIndex).newInstance();
					}
					else{	
						result =(Tile) biome.get(random.nextInt(biome.size())).newInstance();
					}
				}
				catch(InstantiationException|IllegalAccessException e){
					System.out.println(e);
					result=null;
				}
				result.x=x;
				result.y=y;
				resultList.add(result);
			}
		}
		return resultList;
	}
	public boolean testWorldGen() {
		
		@SuppressWarnings("rawtypes")
		ArrayList<Class> chunkToCheckAgainst, chunkType = this.getChunkType((int)(Math.random()*World.BIOMEWIDTH*50), (int)(Math.random()*World.BIOMEWIDTH*50));
		System.out.println("Checking to make sure world works, got test chunk");
		for(int i=0;i<5;i++){ //check 5 chunks
			chunkToCheckAgainst = this.getChunkType((int)(Math.random()*World.BIOMEWIDTH*50),(int)(Math.random()*World.BIOMEWIDTH*50));
			int size = chunkType.size();
			if(size != chunkToCheckAgainst.size()){
				System.out.println("World works after "+ i + " tries!");
				return true;
			}
			for(int j=0;j<size;j++){
				if(chunkToCheckAgainst.get(j) != chunkType.get(j)){ //if it is a different type
					System.out.println("World works after "+ i + " tries!");
					return true; // world works!
				}
			}
		}
		System.out.println("Bad world...");
		return false;
	}
	public void reset() {
		this.random = new Random();
		do{
			this.random.setSeed(new Random().nextLong());
			this.seed = random.nextLong();
		}
		while(!testWorldGen()); // while it fails a test, make a new one.
	}
}
