package game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;


class Listener implements KeyEventDispatcher,  MouseMotionListener{
	Window window;
	Insets t;
	public enum Keys{W,A,S,D};
		
	public Listener(Window window){
		this.window = window;
		this.t = window.frame.getInsets();
	}


	
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO keep this here to remember to not delete it.
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		window.gooey.mouseAt(e.getX()-t.left,e.getY()-t.top);
		
	}
		
    public boolean dispatchKeyEvent(KeyEvent e) {
        if(e.getID() == KeyEvent.KEY_PRESSED){
            if(e.getKeyCode()==KeyEvent.VK_W){
                window.pressedKey(0);
            }
            if(e.getKeyCode()==KeyEvent.VK_A){
                window.pressedKey(1);
            }
            if(e.getKeyCode()==KeyEvent.VK_S){
                window.pressedKey(2);
            }
            if(e.getKeyCode()==KeyEvent.VK_D){
                window.pressedKey(3);
            }
        }
        else if(e.getID() == KeyEvent.KEY_RELEASED){
            if(e.getKeyCode()==KeyEvent.VK_W){
                window.releasedKey(0);
            }
            if(e.getKeyCode()==KeyEvent.VK_A){
                window.releasedKey(1);
            }
            if(e.getKeyCode()==KeyEvent.VK_S){
                window.releasedKey(2);
            }
            if(e.getKeyCode()==KeyEvent.VK_D){
                window.releasedKey(3);
            }
        }
        return false;
    }
}