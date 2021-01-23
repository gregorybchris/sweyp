package sweyp.data;

import sweyp.constants.UC;

public class Location {
	public int x;
	public int y;
	
	public Location() {
		this.x = 0;
		this.y = 0;
	}
	
	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void set(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public boolean neighbors(Location other) {
		return (!other.equals(UC.DEFAULT_TILE_LOCATION)) && 
				(!this.equals(UC.DEFAULT_TILE_LOCATION)) &&
				(x != other.x || y != other.y) && 
				(Math.abs(x - other.x) <= 1 && Math.abs(y - other.y) <= 1);
	}
	
	@Override
	public boolean equals(Object other) {
		Location location = (Location) other;
		return location.x == x && location.y == y;
	}
	
	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}
