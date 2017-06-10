import javax.swing.BoxLayout;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

public class SnakeView extends JFrame
{
	Model m;
	SnakeBoardV boardV;

	public SnakeView(SnekMindControl c)
	{
		boardV = new SnakeBoardV(c.model);
		getContentPane().setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		boardV = new SnakeBoardV(c.model);
		add(boardV, BorderLayout.CENTER);
		setResizable(false);
		setSize(406, 450);
		setVisible(true);
		addKeyListener(c);
		getContentPane().addKeyListener(c);
		pack();
		setLocationRelativeTo(null);
	}
}