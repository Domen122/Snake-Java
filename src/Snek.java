import java.util.LinkedList;
import java.awt.Point;

public class Snek
{
	private Board board;
	private LinkedList<Point> Snekpoints; //points of the snake
	public Snek(Board board) 
	{
		 this.board = board;
		 this.Snekpoints = new LinkedList<Point>();
		 Direction currentDirection;
	}
	public static enum Direction {

		 UP,

		 DOWN,

		 LEFT,

		 RIGHT,

		 NONE

		}

}

