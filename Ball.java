import java.awt.*;

class Ball {
	private Color color;
	private double x;
	private double y;
	private int radius;
	private double vx;
	private double vy;
	private boolean scored;
	
	public Ball(double x, double y, int radius, Color color) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.color = color;
		this.vx = 0;
		this.vy = 0;
		this.scored = false;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public int getRadius() {
		return radius;
	}
	
	public Color getColor() {
		return color;
	}
	
	public boolean isScored() {
		return false;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval((int) x, (int) y, Math.round(radius*2), Math.round(radius*2));
	}
	
	public void scoreRule() {
		
	}
	
}
