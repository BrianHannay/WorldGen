package game;


import java.net.URL;

import javax.swing.ImageIcon;

public class ImageLoader {
	public static ImageIcon load(String s){
		return load(s,true);
	}
	public static ImageIcon load(String s, boolean dieOnError){
		URL res = ImageLoader.class.getResource(s);
		if(res == null){
			if(dieOnError){
				System.out.println("Cannot find image: "+s);
				System.exit(404);
			}
			else{
				new NullPointerException().printStackTrace();
			}
		}
		ImageIcon i = new ImageIcon(res);
		return i;
	}
}
