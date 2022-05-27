import javax.swing.JFrame;
import java.awt.EventQueue;

public class Client extends JFrame{
	public Client() {
		add(new Snake());
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		Client snake = new Client();
		snake.setVisible(true);
	}
}
