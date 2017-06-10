import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Font;
import javax.swing.JPanel;

public class SnakeBoardV extends JPanel
{
	private static final Font FONT = new Font("Tahoma", Font.BOLD, 25);
	Model model;

	public SnakeBoardV(Model m)
	{
		model = m;
		setPreferredSize(new Dimension(Board.ColumnSize * Board.TileSize, Board.RowSize * Board.TileSize));
		setBackground(Color.BLACK);
	}

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		for (int x = 0; x < Board.ColumnSize; x++)
		{
			for (int y = 0; y < Board.RowSize; y++)
			{
				TileType type = model.getTile(x, y);
				if (type != null)
					drawTile(x * Board.TileSize, y * Board.TileSize, type, g);
			}
		}
		g.setColor(Color.DARK_GRAY);
		g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
		for (int x = 0; x < Board.ColumnSize; x++)
		{
			for (int y = 0; y < Board.RowSize; y++)
			{
				g.drawLine(x * Board.TileSize, 0, x * Board.TileSize, getHeight());
				g.drawLine(0, y * Board.TileSize, getWidth(), y * Board.TileSize);
			}
		}
		if (model.isNewGame() || model.isGameOver() || model.isPaused())
		{
			g.setColor(Color.WHITE);

			int centerX = getWidth() / 2;
			int centerY = getHeight() / 2;

			String largeMessage = null;
			String smallMessage = null;
			if (model.isNewGame())
			{
				largeMessage = "Snake!";
				smallMessage = "Press Enter to Start";
			} else if (model.isGameOver())
			{
				largeMessage = "Game Over!";
				smallMessage = "Press Enter to Restart";
			} else if (model.isPaused())
			{
				largeMessage = "Paused";
				smallMessage = "Press P to Resume";
			}

			/*
			 * Set the message font and draw the messages in the center of the
			 * board.
			 */
			g.setFont(FONT);
			g.drawString(largeMessage, centerX - g.getFontMetrics().stringWidth(largeMessage) / 2, centerY - 50);
			g.drawString(smallMessage, centerX - g.getFontMetrics().stringWidth(smallMessage) / 2, centerY + 50);
		}
	}

	public void drawTile(int x, int y, TileType tile, Graphics g)
	{
		switch (tile)
		{
		case Fruit:
			g.setColor(Color.RED);
			g.fillOval(x + 2, y + 2, Board.TileSize - 4, Board.TileSize - 4);
			break;
		case SnakeBody:
			g.setColor(Color.GREEN);
			g.fillRect(x, y, Board.TileSize, Board.TileSize);
			break;
		case SnakeHead:
			g.setColor(Color.GREEN);
			g.fillRect(x, y, Board.TileSize, Board.TileSize);
			break;
		}
	}
}
