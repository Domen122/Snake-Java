import java.awt.Point;

public class Board
{
	public static final int ColumnSize = 25;
	public static final int RowSize = 25;
	public static final int TileSize = 20;
	private TileType[] tiles;

	public Board()
	{
		tiles = new TileType[RowSize * ColumnSize];
		resetBoard();
	}

	public void resetBoard()
	{
		for (int i = 0; i < tiles.length; i++)
		{
			tiles[i] = null;
		}
	}

	public TileType getTile(int x, int y)
	{
		return tiles[y * RowSize + x];
	}

	public void setTile(int x, int y, TileType type)
	{
		tiles[y * RowSize + x] = type;
	}

	public void setTile(Point Pt, TileType type)
	{
		setTile(Pt.x, Pt.y, type);
	}

}
