import java.awt.Color;

public  enum TileType 
{
	
	 SNAKE(Color.GREEN),

	 FRUIT(Color.RED),

	 EMPTY(null);

	 private Color tileColor;

	 private TileType(Color color) 
	 {
		 this.tileColor = color;
	 }

	 public Color getColor() 
	 {
		 return tileColor;
	 }
}

