package View;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import Model.SideBoard;
import Model.Model;
import Model.Board;

public class SnakeSideBoardV extends JPanel
{
	SideBoard side;
	Model model;

	public SnakeSideBoardV(Model m)
	{
		model = m;
		setPreferredSize(new Dimension(300, Board.RowSize * Board.TileSize));
		setBackground(Color.BLACK);
	}

	private static final long serialVersionUID = -40557434900946408L;

	private static final Font LARGE_FONT = new Font("Tahoma", Font.BOLD, 20);

	private static final Font MEDIUM_FONT = new Font("Tahoma", Font.BOLD, 16);

	private static final Font SMALL_FONT = new Font("Tahoma", Font.BOLD, 12);

	private static final int STATISTICS_OFFSET = 150;

	private static final int CONTROLS_OFFSET = 320;

	private static final int MESSAGE_STRIDE = 30;

	private static final int SMALL_OFFSET = 30;

	private static final int LARGE_OFFSET = 50;

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		g.setFont(LARGE_FONT);
		g.drawString("Snake", getWidth() / 2 - g.getFontMetrics().stringWidth("Snake") / 2, 50);

		g.setFont(MEDIUM_FONT);
		g.drawString("Statistics", SMALL_OFFSET, STATISTICS_OFFSET);
		g.drawString("Controls", SMALL_OFFSET, CONTROLS_OFFSET);

		g.setFont(SMALL_FONT);

		int drawY = STATISTICS_OFFSET;
		g.drawString("Total Score: " + model.getScore(), LARGE_OFFSET, drawY += MESSAGE_STRIDE);
		g.drawString("Fruits collected: " + model.getFruits(), LARGE_OFFSET, drawY += MESSAGE_STRIDE);
		String s = new String("");
		s += model.getScoreMul();
		s = s.substring(0, 3);
		g.drawString("Score Multiplier: " + s, LARGE_OFFSET, drawY += MESSAGE_STRIDE);
		drawY = CONTROLS_OFFSET;
		g.drawString("Move Up: W / Up Arrowkey", LARGE_OFFSET, drawY += MESSAGE_STRIDE);
		g.drawString("Move Down: S / Down Arrowkey", LARGE_OFFSET, drawY += MESSAGE_STRIDE);
		g.drawString("Move Left: A / Left Arrowkey", LARGE_OFFSET, drawY += MESSAGE_STRIDE);
		g.drawString("Move Right: D / Right Arrowkey", LARGE_OFFSET, drawY += MESSAGE_STRIDE);
		g.drawString("Pause Game: P", LARGE_OFFSET, drawY += MESSAGE_STRIDE);

	}
}
