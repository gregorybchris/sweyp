package sweyp.data;

public class Vector {
	private double x;
	private double y;
	
	public Vector() {
		this(0, 0);
	}
	
	public Vector(double x, double y) {
		this.x = x;
		this.x = y;
	}
	
	public Vector(double angle, double magnitude, boolean token) {
		this.x = Math.cos(angle) * magnitude;
		this.y = Math.sin(angle) * magnitude;
	}
	
	public Vector(Point p1, Point p2) {
		this.x = p2.x - p1.x;
		this.y = p2.y - p1.y;
	}
	
	public void set(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public void set(Point p1, Point p2) {
		set(p2.x - p1.x, p2.y - p1.y);
	}
	
	public double getAngle() {
		return Math.atan2(y, x);
	}
	
	public double getMagnitude() {
		return Math.sqrt(x * x + y * y);
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
	
	public void add(Vector other) {
		this.x += other.getX();
		this.y += other.getY();
	}
	
	public void add(double x, double y) {
		this.x += x;
		this.y += y;
	}
	
	public void multiply(double scalar) {
		this.x *= scalar;
		this.y *= scalar;
	}
	
	public void setMagnitude(double magnitude) {
		double angle = Math.atan2(y, x);
		this.x = Math.cos(angle) * magnitude;
		this.y = Math.sin(angle) * magnitude;
	}
	
	public void setAngle(double angle) {
		double magnitude = Math.sqrt(x * x + y * y);
		this.x = Math.cos(angle) * magnitude;
		this.y = Math.sin(angle) * magnitude;
	}
}
