import java.awt.*;

class Ball {
	private static final double FRICTION = 0.98;
	private static final double MIN_SPEED = 0.04;

	private Color color;
	private double x;
	private double y;
	private int radius;
	private double vx;
	private double vy;
	private boolean scored;
	private double prevX;
	private double prevY;
	
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
	
	public int getCenterX() {
		return (int) x + radius;
	}
	
	public int getCenterY() {
		return (int) y + radius;
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
	
	public void setScored(boolean b) {
		scored = b;
	}
	public void update() {
		prevX = x;
		prevY = y;
		
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

	public void bounceOffWalls(int wallX, int wallY, int width, int height) {
		if ( x + radius * 2 > wallX && x < wallX + width &&
        y + radius * 2 > wallY && y < wallY + height) {
			// Came from left
		    if (prevX + radius * 2 <= wallX) {
		        x = prevX;
		        vx = -vx;
		    }

		    // Came from right
		    else if (prevX >= wallX + width) {
		        x = prevX;
		        vx = -vx;
		    }

		    // Came from top
		    else if (prevY + radius * 2 <= wallY) {
		        y = prevY;
		        vy = -vy;
		    }

		    // Came from bottom
		    else if (prevY >= wallY + height) {
		        y = prevY;
		        vy = -vy;
		    }
		}
	}
	
	public void scoreRules(int wallX, int wallY, int width, int height) {
		if ( x + radius * 2 > wallX && x < wallX + width &&
		        y + radius * 2 > wallY && y < wallY + height) {
			scored = true;
			
		}
	}

	public boolean containsPoint(int mouseX, int mouseY) {
		double dx = mouseX - getCenterX();
		double dy = mouseY - getCenterY();
		return dx * dx + dy * dy <= radius * radius;
	}

	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval((int) x, (int) y, radius * 2, radius * 2);
	}
	
	public boolean isCollision(Ball b) {
		double dx = getCenterX() - b.getCenterX();
		double dy = getCenterY() - b.getCenterY();
		double dCollision = radius + b.getRadius();
		
		return dx * dx + dy * dy <= dCollision * dCollision;
	}
	
	public void collision(Ball b) {
		double dx = getCenterX() - b.getCenterX();
		double dy = getCenterY() - b.getCenterY();
		double distance = Math.sqrt(dx * dx + dy * dy);
		
		if (isCollision(b)) {
			double overlap = (radius + b.getRadius() - distance);
			
			if (overlap > 0 && distance > 0) {
				double moveX = dx / distance * overlap / 2;
				double moveY = dy / distance * overlap / 2;
				x += moveX;
				y += moveY;
				b.x -= moveX;
				b.y -= moveY;
			}
			
			double angle = Math.atan2(dy,dx);
			double sin = Math.sin(angle);
			double cos = Math.cos(angle);
			
			double vx1 = vx * cos + vy * sin;
			double vy1 = vy * cos - vx * sin;
			double vx2 = b.getVx() * cos + b.getVy() * sin;
			double vy2 = b.getVy() * cos - b.getVx() * sin;
			
			double vx1rotated = vx2;
			double vx2rotated = vx1;
			double vy1rotated = vy1;
			double vy2rotated = vy2;
			
			double vx1final = vx1rotated * cos - vy1rotated * sin;
			double vy1final = vy1rotated * cos + vx1rotated * sin;
			
			double vx2final = vx2rotated * cos - vy2rotated * sin;
			double vy2final = vy2rotated * cos + vx2rotated * sin;
			
			setVelocity(vx1final, vy1final);
			b.setVelocity(vx2final, vy2final);
		}
	}
}
