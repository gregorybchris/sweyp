package sweyp.data;

import java.util.HashSet;

public class IOData {
	private HashSet<Integer> keySet;
	private boolean mousePressed;
	private Point mouseLocation;
	//TODO: Add more IO properties to affect game
	
	public IOData() {
		keySet = new HashSet<Integer>();
		mousePressed = false;
		mouseLocation = new Point();
	}
	
	public void keyDown(int key) {
		keySet.add(key);
	}
	
	public void keyUp(int key) {
		keySet.remove(key);
	}
	
	public boolean keyPressed(int keyCode) {
		return keySet.contains(keyCode);
	}
	
	public boolean keyAccess(int keyCode) {
		boolean setContainsKey = keySet.contains(keyCode);
		keySet.remove(keyCode);
		return setContainsKey;
	}
	
	public void setMousePressed(boolean mousePressed) {
		this.mousePressed = mousePressed;
	}
	
	public boolean getMousePressed() {
		return mousePressed;
	}
	
	public void setMouseLocation(double x, double y) {
		mouseLocation.x = x;
		mouseLocation.y = y;
	}
	
	public double getMouseX() {
		return mouseLocation.x;
	}
	
	public double getMouseY() {
		return mouseLocation.y;
	}
}
