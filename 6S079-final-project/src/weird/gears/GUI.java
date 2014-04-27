package weird.gears;

import java.awt.BorderLayout;
import javax.swing.JFrame;

// @SuppressWarnings("serial")
public class GUI extends JFrame {
    // necessary to get rid of an error
    private static final long serialVersionUID = 1L;
    private final GUIWindow window;
    
    public GUI() {
        setTitle("Weird Gears");
        setLayout(new BorderLayout());
        window = new GUIWindow();
        add(window, BorderLayout.CENTER);
        pack();
    }
    
    public static void main(String[] args) {
        GUI win = new GUI();
        win.setVisible(true);
    }
}