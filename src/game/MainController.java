package game;

import java.awt.KeyboardFocusManager;

public class MainController{
	
	
	public static void main(String[] args){
		System.out.println("Game started.");
		Window window = new Window();
		System.out.println("Initilised window.");
		Listener listener = new Listener(window);
		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
	    manager.addKeyEventDispatcher(listener);
	    window.frame.addMouseMotionListener(listener);
	    System.out.println("Added keyboard listener");
	}
	public MainController(){
		
	}
}