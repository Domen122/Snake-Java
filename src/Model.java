import java.awt.Point;
import java.util.LinkedList;
import java.util.Random;

public class Model
{
	private static final int FrameTime = 100;
	public static final int MaxDirections = 3;
	private static final int SnakeMinLength = 3;

	private Snake snek;
	private Board board;
	private SideBoard side;
	private boolean pause;
	private int score;
	private float score_Mul;
	private boolean isNewGame;
	private boolean isOver = false;
	private Random random;
	private LinkedList<Direction> directions;

	public Model()
	{
		snek = new Snake();
		score_Mul = 1.0f;
		score = 0;
		board = new Board();
		random = new Random();
		directions = new LinkedList<Direction>();
		side = new SideBoard();
		isNewGame = true;
		pause = true;
	}

	public void setNewGame(boolean p)
	{
		isNewGame = p;
	}

	public int getFrameTime()
	{
		return FrameTime;
	}

	public Board getBoard()
	{
		return board;
	}

	public TileType getTile(int x, int y)
	{
		return board.getTile(x, y);
	}

	public SideBoard getSideBoard()
	{
		return side;
	}

	public int getScore()
	{
		return score;
	}

	public float getScoreMul()
	{
		return score_Mul;
	}

	public void resetScore()
	{
		score = 0;
		score_Mul = 1.0f;
	}

	public boolean isGameOver()
	{
		return isOver;
	}

	public boolean isNewGame()
	{
		return isNewGame;
	}

	public boolean isPaused()
	{
		return pause;
	}

	public void setPaused(boolean p)
	{
		pause = p;
	}

	public void setOver(boolean p)
	{
		isOver = p;
	}

	public void updateGame()
	{
		TileType collision = updateSnake();
		if (collision == TileType.Fruit)
		{
			score += 100 * score_Mul;
			score_Mul += 0.1;
			spawnFruit();
		} else if (collision == TileType.SnakeBody)
		{
			isOver = true;
		}
	}

	public void resetGame()
	{
		resetScore();
		isNewGame = false;
		isOver = false;
		Point head = new Point(Board.ColumnSize / 2, Board.RowSize / 2);
		snek.resetSnake(head);
		board.resetBoard();
		board.setTile(head, TileType.SnakeHead);
		directions.clear();
		directions.add(Direction.UP);
		spawnFruit();
	}

	public int getDirSize()
	{
		return directions.size();
	}

	public TileType updateSnake()
	{

		Direction direction = directions.peekFirst();
		Point head = new Point(snek.getHead());
		switch (direction)
		{
		case UP:
			head.y--;
			break;

		case DOWN:
			head.y++;
			break;

		case LEFT:
			head.x--;
			break;

		case RIGHT:
			head.x++;
			break;
		}
		if (head.x < 0 || head.x >= Board.ColumnSize || head.y < 0 || head.y >= Board.RowSize)
		{
			return TileType.SnakeBody; // return SnakeBody, 2 birds with one
										// stone
		}
		TileType old = board.getTile(head.x, head.y);
		if (old != TileType.Fruit && snek.getSize() > SnakeMinLength)
		{
			Point tail = snek.removeLast();
			board.setTile(tail, null);
			old = board.getTile(head.x, head.y);
		}
		// Update the snake's position on the board if we didn't collide with
		// our
		// tail
		if (old != TileType.SnakeBody)
		{
			board.setTile(snek.getFirst(), TileType.SnakeBody);
			snek.pushP(head);
			board.setTile(head, TileType.SnakeHead);
			if (directions.size() > 1)
			{
				directions.poll();
			}
		}
		return old;
	}

	public void spawnFruit()
	{
		int index = random.nextInt(Board.ColumnSize * Board.RowSize - snek.getSize());
		/*
		 * this method searches for a index'th free tile, rather than randoming
		 * index and checking if the tile on the randomed index is free
		 */
		int freeFound = -1;
		for (int x = 0; x < Board.ColumnSize; x++)
		{
			for (int y = 0; y < Board.RowSize; y++)
			{
				TileType type = board.getTile(x, y);
				if (type == null || type == TileType.Fruit)
				{
					if (++freeFound == index)
					{
						board.setTile(x, y, TileType.Fruit);
						break;
					}
				}
			}
		}
	}

	public void setDirection(Direction direct)
	{
		switch (direct)
		{
		case UP:
			if (!pause && !isOver)
			{
				if (directions.size() < MaxDirections)
				{
					Direction last = directions.peekLast();
					if (last != Direction.DOWN && last != Direction.UP)
					{
						directions.addLast(Direction.UP);
					}
				}
			}
			break;
		case DOWN:
			if (!pause && !isOver)
			{
				if (directions.size() < MaxDirections)
				{
					Direction last = directions.peekLast();
					if (last != Direction.UP && last != Direction.DOWN)
					{
						directions.addLast(Direction.DOWN);
					}
				}
			}
			break;
		case LEFT:
			if (!pause && !isOver)
			{
				if (directions.size() < MaxDirections)
				{
					Direction last = directions.peekLast();
					if (last != Direction.RIGHT && last != Direction.LEFT)
					{
						directions.addLast(Direction.LEFT);
					}
				}
			}
			break;
		case RIGHT:
			if (!pause && !isOver)
			{
				if (directions.size() < MaxDirections)
				{
					Direction last = directions.peekLast();
					if (last != Direction.LEFT && last != Direction.RIGHT)
					{
						directions.addLast(Direction.RIGHT);
					}
				}
			}
			break;
		}
	}
}
