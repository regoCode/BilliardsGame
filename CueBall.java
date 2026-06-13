import java.awt.*;

public class CueBall extends Ball{
	private boolean aiming;
	private int aimX;
	private int aimY;
	
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

        int centerX = (int)getX() + getRadius();
        int centerY = (int)getY() + getRadius();

        double vx = (centerX - aimX) * 0.15;
        double vy = (centerY - aimY) * 0.15;

        setVelocity(vx, vy);
        aiming = false;
    }
	
	public boolean isAiming() {
		return aiming;
	}
	
	public void drawAimLine(Graphics g) {
		if (!aiming) return;
		int centerX = (int)getX() + getRadius();
        int centerY = (int)getY() + getRadius();
        
        g.setColor(Color.RED);
        g.drawLine(centerX, centerY, aimX, aimY);
	}
}
