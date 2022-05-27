import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Snake extends JPanel implements ActionListener {
	private boolean alive = true;
	private char dir = 's';
	private int length, ax, ay;
	private int xPos[] = new int[900];
	private int yPos[] = new int[900];
	private Image segment, apple;
	private Timer timer;
	
	public Snake() {		
		addKeyListener(new KA());
		setBackground(Color.gray);
		setFocusable(true);
		setPreferredSize(new Dimension(300, 300));
		
		ImageIcon segIcon = new ImageIcon("blackbox.png");
        segment = segIcon.getImage();
        ImageIcon appleIcon = new ImageIcon("reddot.png");
        apple = appleIcon.getImage();
        
        length = 2;
        
        for(int i = 0; i < length; i++) {
        	xPos[i] = 50 - i*10;
        	yPos[i] = 50;
        }
        
        makeApple();
        
        timer = new Timer(140, this);
        timer.start();
	}
	
	 public void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        draw(g);
	    }
	
	public void draw(Graphics g) {
		if(alive) {
			g.drawImage(apple, ax, ay, this);
			for(int i = 0; i < length; i++) {
				g.drawImage(segment, xPos[i], yPos[i], this);
			}
			Toolkit.getDefaultToolkit().sync();
		}
	}
	
	public void step() {
		if ((xPos[0] == ax) && (yPos[0] == ay)) {
            length++;
            makeApple();
        }
		for(int i = length; i > 0; i--)
			if(i > 4 && xPos[0] == xPos[i] && yPos[0] == yPos[i])
				alive = false;
		if(yPos[0] >= 500)
			alive = false;
		if(yPos[0] < 0)
			alive = false;
		if(xPos[0] >= 500)
			alive = false;
		if(xPos[0] < 0)
			alive = false;
		if(!alive)
			timer.stop();
	}
	
	public void makeApple() {
		int randPosx = (int) (Math.random() * 29);
        int randPosy = (int) (Math.random() * 29);
        ax = randPosx * 10;
        ay = randPosy * 10;
	}
	
	public void move() {
		for (int i = length; i > 0; i--) {
            xPos[i] = xPos[(i - 1)];
            yPos[i] = yPos[(i - 1)];
        }
		
		switch(dir) {
		case 'n':
			yPos[0] -= 10;
			break;
		case 's':
			yPos[0] += 10;
			break;
		case 'w':
			xPos[0] -= 10;
			break;
		case 'e':
			xPos[0] += 10;
			break;
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if(alive) {
	        step();
	        move();
		}
		repaint();
    }
	
	private class KA extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			System.out.println(e.getKeyChar());
			int k = e.getKeyCode();
			if(k == 87 && dir != 's') //w
				dir = 'n';
			else if(k == 65 && dir != 'e') //a
				dir = 'w';
			else if(k == 83 && dir != 'n') //s
				dir = 's';
			else if(k == 68 && dir != 'w') //d
				dir = 'e';
			else if(k == 27) //esc
				System.exit(0);
		}
	}
}