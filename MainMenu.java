import java.awt.*;

import javax.swing.*;



public class MainMenu extends JFrame {

	private static final Color CREAM = new Color(245, 240, 220);

	public MainMenu() {
		super("Billiards Game");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);

		ImageIcon icon = new ImageIcon(getClass().getResource("/images/pooltable.jpg"));
		Image bgImage = icon.getImage();

		JPanel bgPanel = new JPanel(new BorderLayout()) {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
			}
		};

		JLabel title = new JLabel("Billiards Game", SwingConstants.CENTER);
		title.setFont(new Font("Serif", Font.BOLD, 52));
		title.setForeground(CREAM);
		setLayout(new BorderLayout());
		bgPanel.add(title, BorderLayout.NORTH);
		
		JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 15, 15));

		String[] buttons = { "Play Game", "Leaderboard", "Instructions", "Settings", "Exit Game" };

		for (String label : buttons) {
			buttonPanel.add(new MenuButton(label, getButtonAction(label)));
		}

		buttonPanel.setOpaque(false);

		JPanel paddingPanel = new JPanel(new BorderLayout());
		paddingPanel.setOpaque(false);
		paddingPanel.setBorder(BorderFactory.createEmptyBorder(60, 250, 60, 250));
		paddingPanel.add(buttonPanel, BorderLayout.CENTER);
		bgPanel.add(paddingPanel, BorderLayout.CENTER);

		setContentPane(bgPanel);

	}

	public Runnable getButtonAction(String label) {
		return switch (label) {
			case "Play Game" -> () -> {
				setVisible(false);
				GameScreen game = new GameScreen(this);
				game.setVisible(true);
			};
			case "Leaderboard" -> () -> JOptionPane.showMessageDialog(this, "Opening Leaderboard...");
			case "Instructions" -> () -> JOptionPane.showMessageDialog(this, "Opening Instructions...");
			case "Settings" -> () -> JOptionPane.showMessageDialog(this, "Opening Settings...");
			case "Exit Game" -> () -> System.exit(0);
			default -> () -> { };
		};
	}
}


