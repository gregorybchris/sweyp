package sweyp.data;

public class Point {
	public double x;
	public double y;
	
	public Point() {
		this.x = 0;
		this.y = 0;
	}
	
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Point(Point other) {
		x = other.x;
		y = other.y;
	}
	
	public void set(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public void set(Point other) {
		x = other.x;
		y = other.y;
	}
	
	public double getX() {
		return x;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public double distance(Point other) {
		return Math.sqrt((this.x - other.x) * (this.x - other.x) + 
				(this.y - other.y) * (this.y - other.y));
	}
	
	public double distance(double x, double y) {
		return Math.sqrt((this.x - x) * (this.x - x) + 
				(this.y - y) * (this.y - y));
	}
	
	public void translate(double dx, double dy) {
		x += dx;
		y += dy;
	}
	
	public double angle(Point other) {
		return Math.atan2(y - other.y, x - other.x);
	}
	
	@Override
	public boolean equals(Object other) {
		Point point = (Point) other;
		return point.x == x && point.y == y;
	}
	
	@Override
	public String toString() {
		return "[Point](" + x + ", " + y + ")";
	}
}
