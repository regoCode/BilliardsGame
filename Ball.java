import java.awt.*;

class Ball {
	private static final double FRICTION = 0.98;
	private static final double MIN_SPEED = 0.05;

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

	public double getVx() {
		return vx;
	}

	public double getVy() {
		return vy;
	}

	public boolean isScored() {
		return scored;
	}

	public boolean isMoving() {
		return Math.abs(vx) > MIN_SPEED || Math.abs(vy) > MIN_SPEED;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setVelocity(double vx, double vy) {
		this.vx = vx;
		this.vy = vy;
	}

	public void update() {
		x += vx;
		y += vy;

		vx *= FRICTION;
		vy *= FRICTION;

		if (Math.abs(vx) < MIN_SPEED) {
			vx = 0;
		}
		if (Math.abs(vy) < MIN_SPEED) {
			vy = 0;
		}
	}

	public void bounceOffWalls(double minX, double minY, double maxX, double maxY) {
		if (x < minX) {
			x = minX;
			vx = -vx;
		} else if (x + radius * 2 > maxX) {
			x = maxX - radius * 2;
			vx = -vx;
		}

		if (y < minY) {
			y = minY;
			vy = -vy;
		} else if (y + radius * 2 > maxY) {
			y = maxY - radius * 2;
			vy = -vy;
		}
	}

	public boolean containsPoint(int mouseX, int mouseY) {
		double centerX = x + radius;
		double centerY = y + radius;
		double dx = mouseX - centerX;
		double dy = mouseY - centerY;
		return dx * dx + dy * dy <= radius * radius;
	}

	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval((int) x, (int) y, radius * 2, radius * 2);
	}

	public void scoreRule() {

	}
}
