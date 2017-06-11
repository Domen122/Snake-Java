package Model;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class SideBoard extends JPanel
{
	private static final long serialVersionUID = 40557434900946408L;

	public SideBoard()
	{
		setPreferredSize(new Dimension(300, Board.RowSize * Board.TileSize));
		setBackground(Color.BLACK);
	}
}
