import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;


import javax.swing.JFrame;
public class Engine 
{
	
	public static void main(String[] args) {
		
		 JFrame frame = new JFrame("Snake Game");
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 frame.setResizable(false);
		
		 Canvas canvas = new Canvas();
		 canvas.setBackground(Color.BLACK);
		 canvas.setPreferredSize(new Dimension(500, 500));
	
		 frame.add(canvas);
		 
		 frame.pack();
		 frame.setLocationRelativeTo(null);
		 frame.setVisible(true);
		}
}