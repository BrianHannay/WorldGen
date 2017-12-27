package game;

import javax.swing.ImageIcon;

public class ImagesAndAnimations {
	public final static Animation waterAnimation = new Animation("images/sea",".png", 20, 4);
	public final static ImageIcon corruptGrassImage = ImageLoader.load("images/corruptGrass.png");
	public final static ImageIcon desertImage = ImageLoader.load("images/desert.png");
	public final static ImageIcon grassImage = ImageLoader.load("images/grass.png");
	public final static ImageIcon playerImage = ImageLoader.load("images/player.png");
	public final static ImageIcon treeImage = ImageLoader.load("images/tree.png");
	public final static ImageIcon slimeImage = ImageLoader.load("images/slime.png");
	public final static ImageIcon tileBox = ImageLoader.load("images/redBox.png");
}
