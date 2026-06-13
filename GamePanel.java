import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class GamePanel extends JPanel {
	private final ArrayList<Ball> balls = new ArrayList<>();
	private final int BRADIUS = 10;
	private final int TABLE_LEFT = 100;
	private final int TABLE_TOP = 100;
	private final int TABLE_RIGHT = 890;
	private final int TABLE_BOTTOM = 470;

	private CueBall cueBall;
	private Image bgImage;

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
		});
		timer.start();
	}

	private void updateGame() {
		for (Ball ball : balls) {
			if (!ball.isScored()) {
				ball.update();
				ball.bounceOffWalls(TABLE_LEFT, TABLE_TOP, TABLE_RIGHT, TABLE_BOTTOM);
			}
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
		for (Ball ball : balls) {
			ball.draw(g);
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
