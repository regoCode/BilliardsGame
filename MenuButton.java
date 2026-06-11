import java.awt.*;
import javax.swing.*;

public class MenuButton extends JButton{
	private static final Color TURQUOISE = new Color(34, 120, 82);
	private static final Color CREAM = new Color(245, 240, 220);
	private static final Color HOVER = new Color(46, 150, 104);
	
	public MenuButton(String text, Runnable onClick) {
        super(text);
        styleButton();
        addActionListener(e -> onClick.run());
        addHover();
    }
	
	private void styleButton() {
        setBackground(TURQUOISE);
        setForeground(CREAM);
        setFont(new Font("Serif", Font.BOLD, 20));
        setFocusPainted(false);
        setOpaque(true);
        setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
    private void addHover() {
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                setBackground(HOVER);
            }
            public void mouseExited(java.awt.event.MouseEvent e) {
                setBackground(TURQUOISE);
            }
        });
    }
}
