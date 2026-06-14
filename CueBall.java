import java.awt.*;

public class CueBall extends Ball{
	private boolean aiming;
	private int aimX;
	private int aimY;
	private double initialX;
	private double initialY;
	
	public CueBall(double x, double y, int radius) {
		super(x,y,radius,Color.WHITE);
	}
	
	public void startAim(int mouseX, int mouseY) {
		aiming = true;
		aimX = mouseX;
		aimY = mouseY;
	}
	
	public void updateAim(int mouseX, int mouseY) {
		aimX = mouseX;
		aimY = mouseY;
	}
	
	public void shoot() {
		if (!aiming) return;
		
		initialX = getX();
		initialY = getY();	
		
        int centerX = (int)getX() + getRadius();
        int centerY = (int)getY() + getRadius();

        double vx = (centerX - aimX) * 0.1;
        double vy = (centerY - aimY) * 0.1;
        
        setVelocity(vx, vy);
        aiming = false;
    }
	
	public void scoreRules(int wallX, int wallY, int width, int height) {
		super.scoreRules(wallX, wallY, width, height);
		if (isScored()) {
			setX(initialX);
			setY(initialY);
			setScored(false);
			setVelocity(0,0);
		}
		
	}
	
	public boolean isAiming() {
		return aiming;
	}
	
	public void drawAimLine(Graphics g) {
		if (!aiming) return;
        g.setColor(Color.WHITE);
        g.drawLine(getCenterX(), getCenterY(), aimX, aimY);
	}
}
