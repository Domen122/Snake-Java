import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Graphics;

public class Board extends JPanel {
	private static final long serialVersionUID = 7526472295622776147L;
	public static final int ColumnSize = 25;
	public static final int RowSize = 25;
	public static final int TileSize = 20;
	private Snek snek;
	private TileType[] tiles;

	public Board(Snek snek) {
		this.snek = snek;
		this.tiles = new TileType[RowSize * TileSize];

		setPreferredSize(new Dimension(ColumnSize * TileSize, RowSize * TileSize));
		setBackground(Color.BLACK);
	}

	public void resetBoard() {
		for (int i = 0; i < tiles.length; i++) {
			tiles[i] = null;
		}
	}

	public void setTile(Point Pt, TileType type) {
		setTile(Pt.x, Pt.y, type);
	}

	public void setTile(int x, int y, TileType type) {
		tiles[y * RowSize + x] = type;
	}

	public TileType getTile(int x, int y) {
		return tiles[y * RowSize + x];
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int x = 0; x < ColumnSize; x++) {
			for (int y = 0; y < RowSize; y++) {
				TileType type = getTile(x, y);
				if (type != null) {
					drawTile(x * TileSize, y * TileSize, type, g);
				}
			}
		}
		g.setColor(Color.DARK_GRAY);
		g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
		for (int x = 0; x < ColumnSize; x++) {
			for (int y = 0; y < RowSize; y++) {
				g.drawLine(x * TileSize, 0, x * TileSize, getHeight());
				g.drawLine(0, y * TileSize, getWidth(), y * TileSize);
			}
		}
	}

	private void drawTile(int x, int y, TileType type, Graphics g) {
		switch (type) {
		case Fruit:
			g.setColor(Color.RED);
			g.fillOval(x + 2, y + 2, TileSize - 4, TileSize - 4);
			break;
		case SnakeBody:
			g.setColor(Color.GREEN);
			g.fillRect(x, y, TileSize, TileSize);
			break;
		case SnakeHead:
			g.setColor(Color.GREEN);
			g.fillRect(x, y, TileSize, TileSize);
		}
	}
}
