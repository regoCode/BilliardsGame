import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class GamePanel extends JPanel {
	private final ArrayList<Ball> balls = new ArrayList<>();
	private final int BRADIUS = 10;
	
	private final int[][] WALL_BOUNDS = {
			{125, 40, 335, 50}, 	//top let wall
		    {525, 40, 335, 50},		//top right wall
		    {125, 465, 335, 50},	//bottom left wall
		    {525, 465, 335, 50},	//bottom right wall
		    {50, 110, 50, 335},		//left wall
		    {885, 110, 50, 335},	//right wall

		};
	private final int[][] POCKETS = {
			{50, 40, 60, 60},		//top left
			{460, 35, 65, 65},		//top middle
			{875, 40, 60, 60},		//top right
			{50, 455, 60, 60},		//bottom left
			{460, 455, 65, 65},		//bottom middle
			{875, 455, 60, 60}		//bottom right
	};
	
	
	
	private CueBall cueBall;
	private Image bgImage;
	
	private int totalShots = 0;
	
	public GamePanel() {
		ImageIcon icon = new ImageIcon(getClass().getResource("/images/gametable.jpg"));
		bgImage = icon.getImage();

		cueBall = new CueBall(300, 280, BRADIUS);
		balls.add(cueBall);

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

		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (!cueBall.isMoving() && cueBall.containsPoint(e.getX(), e.getY())) {
					cueBall.startAim(e.getX(), e.getY());
				}
			}
			
			public void mouseReleased(MouseEvent e) {
				if (cueBall.isAiming()) {
					cueBall.updateAim(e.getX(), e.getY());
					cueBall.shoot();
					totalShots++;
				}
			}
		});
		
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				cueBall.updateAim(e.getX(),e.getY());
			}
		});
		
		Timer timer = new Timer(16, e -> {
			updateGame();
			repaint();
			for (int i = 0; i < balls.size(); i++) {
				for (int j = i + 1; j < balls.size(); j++) {
					Ball b1 = balls.get(i);
					Ball b2 = balls.get(j);
					b1.collision(b2);
				}
			}
		});
		timer.start();
	}

	private void updateGame() {
		for (Ball ball : balls) {
			if (!ball.isScored()) {
				ball.update();
				for (int[] wall : WALL_BOUNDS) {
					ball.bounceOffWalls(wall[0], wall[1], wall[2], wall[3]);
				}
				for (int[] pocket : POCKETS) {
					ball.scoreRules(pocket[0], pocket[1], pocket[2], pocket[3]);
				}
			}	
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
		for (Ball ball : balls) {
			if (!ball.isScored()) ball.draw(g);
			
		}
		for (int[] vals : WALL_BOUNDS) {
			g.setColor(Color.RED);
			g.drawRect(vals[0],vals[1],vals[2],vals[3]);
		}
		for (int[] vals : POCKETS) {
			g.setColor(Color.RED);
			g.drawRect(vals[0],vals[1],vals[2],vals[3]);
		}

		cueBall.drawAimLine(g);
	}

	public static ArrayList<Double[]> setRackPos(double startX, double startY, double radius) {
		ArrayList<Double[]> positions = new ArrayList<>();
		double gap = 4 + 2 * radius;

		for (int i = 0; i < 4; i++) {
			double posX = startX + i * gap;
			double posY = startY - i * gap / 2;
			for (int j = 0; j <= i; j++) {
				double nPosY = posY + gap * j;
				Double[] pos = { posX, nPosY };
				positions.add(pos);
			}
		}
		return positions;
	}
}
