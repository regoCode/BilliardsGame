import java.awt.*;
import javax.swing.*;
import java.util.*;

public class GamePanel extends JPanel{
	private ArrayList<Ball> balls = new ArrayList<Ball>();
	private final int BRADIUS = 10;
	private Image bgImage;
	
	public GamePanel() {
		ImageIcon icon = new ImageIcon(getClass().getResource("/images/gametable.jpg"));
    	bgImage = icon.getImage();
		
		balls.add(new CueBall(300,280,BRADIUS));
		
		double startX = 600;
	    double startY = 280;
	    
	    Color[] colors = {
	        Color.YELLOW, Color.BLUE, Color.RED, Color.ORANGE,
	        Color.GREEN, Color.MAGENTA, Color.CYAN, Color.PINK,
	        Color.LIGHT_GRAY, Color.BLACK
	    };
	    
	    ArrayList<Double[]> positions = setRackPos(startX, startY, BRADIUS);
		
	    for (int i = 0; i < positions.size(); i++) {
	    	balls.add(new Ball(positions.get(i)[0], positions.get(i)[1], BRADIUS, colors[i]));
	    }
		
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(bgImage, 0, 0, getWidth(), getHeight(),this);
        for (Ball ball: balls) {
        	ball.draw(g);
        }
	}
	
	public static ArrayList<Double[]> setRackPos(double startX, double startY, double radius) {
		ArrayList<Double[]> positions = new ArrayList<Double[]>();
		double gap = 4 + 2 * radius;
		
		for (int i = 0; i < 4; i++) {
			double posX = startX + i * gap;
			double posY = startY - i * gap / 2;
			for (int j = 0; j <= i; j++) {
				double nPosY = posY + gap * j;
				Double[] pos = {posX,nPosY};
				positions.add(pos);
			}
		}
		
		return positions;
	}
}
