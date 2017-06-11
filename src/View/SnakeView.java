package View;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import Model.Model;
import Controler.SnekMindControl;

public class SnakeView extends JFrame
{
	private static final long serialVersionUID = 40557434900946408L;
	Model m;
	SnakeBoardV boardV;
	SnakeSideBoardV sideV;

	public SnakeView(SnekMindControl c)
	{
		boardV = new SnakeBoardV(c.model);
		sideV = new SnakeSideBoardV(c.model);
		getContentPane().setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		add(boardV, BorderLayout.CENTER);
		add(sideV, BorderLayout.EAST);

		setResizable(false);
		setSize(406, 450);
		setVisible(true);
		addKeyListener(c);
		getContentPane().addKeyListener(c);
		pack();
		setLocationRelativeTo(null);
	}

	public void refresh()
	{
		boardV.repaint();
		sideV.repaint();
	}
}