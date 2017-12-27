package game;


import java.util.*;
/**
 * 
 * Should make getPartAt(x,y) a required static method
 * @author Brian
 */
public class Feature {

	@SuppressWarnings("rawtypes")
	protected static final Class grass = TileSea.class;
	@SuppressWarnings("rawtypes")
	protected static final Class tree = TileTree.class;
	@SuppressWarnings("rawtypes")
	protected static List<List<List<Class>>> layouts;

	protected int width, height, x, y;
	
	public Feature(){
		layouts = Arrays.asList(
			Arrays.asList(
				Arrays.asList(TileCorrupt.class ,grass,tree ,tree ,tree),
				Arrays.asList(tree ,grass,tree ,grass,grass),
				Arrays.asList(tree ,tree ,tree ,tree ,tree),
				Arrays.asList(grass,grass,tree ,grass,tree),
				Arrays.asList(tree, tree, tree, grass,tree)
			)
		);
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Class<Tile> getPartAt(int x, int y, int which){
		List<List<Class>> layout = layouts.get(which);
		Class<Tile> tileToReturn = null;
		x+=this.width-Math.floor(this.width/2);
		y+=this.width-Math.floor(this.width/2);
		if(y<0||x<0||y>layout.size()-1||x>layout.get(0).size()-1){
			return grass;
		}
		System.out.println(x+","+y+", width: "+layout.get(0).size());
		List<Class> a = layout.get(y);
		if(a!=null){
			tileToReturn = a.get(x);
		}
		if(tileToReturn == null){
			tileToReturn = grass;
		}
		return tileToReturn;
	}
}
