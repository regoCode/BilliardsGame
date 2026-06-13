import java.awt.*;
import javax.swing.*;

public class GameScreen extends JFrame {
	private MainMenu mainMenu;

	public GameScreen(MainMenu mainMenu) {
		super("Billiards Game");
		this.mainMenu = mainMenu;
		setSize(1000, 660);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());

		JPanel topBar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		topBar.setOpaque(false);
		topBar.add(new MenuButton("Return To Menu", () -> {
			dispose();
			mainMenu.setVisible(true);
		}));

		add(new GamePanel(), BorderLayout.CENTER);
		add(topBar, BorderLayout.NORTH);
	}
}
