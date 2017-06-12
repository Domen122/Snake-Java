package modelTest;

import static org.junit.Assert.*;

import org.junit.Test;
import model.*;

public class ModelTest
{

	@Test
	public void testModel()
	{
		Model m = new Model();
		assertTrue(m.getScore() == 0);
	}

	@Test
	public void testUpdateGame()
	{
		Model m = new Model();
		m.resetGame();
		m.getBoard().setTile(Board.ColumnSize / 2, (Board.RowSize / 2) - 1, TileType.SnakeBody);
		m.updateGame();
		assertTrue(m.isGameOver() == true);
	}

	@Test
	public void testResetGame()
	{
		Model m = new Model();
		m.resetGame();
		assertTrue(m.isNewGame() == false);
		assertTrue(m.isGameOver() == false);
		assertTrue(m.getBoard().getTile(Board.ColumnSize / 2, Board.RowSize / 2) == TileType.SnakeHead);
	}

	@Test
	public void testUpdateSnake()
	{
		Model fruitTest = new Model();
		Model snakeBodyTest = new Model();
		fruitTest.resetGame();
		fruitTest.getBoard().setTile(Board.ColumnSize / 2, (Board.RowSize / 2) - 1, TileType.Fruit);
		fruitTest.updateGame();
		snakeBodyTest.resetGame();
		snakeBodyTest.getBoard().setTile(Board.ColumnSize / 2, (Board.RowSize / 2) - 1, TileType.SnakeBody);
		snakeBodyTest.updateGame();
		assertTrue(fruitTest.getBoard().getTile(Board.ColumnSize / 2, (Board.RowSize / 2) - 1) == TileType.SnakeHead);
		assertTrue(fruitTest.getScore() == 100);
		assertTrue(fruitTest.getScoreMul() == 20);
		assertTrue(snakeBodyTest.isGameOver() == true);
	}

	@Test
	public void testSetDirection()
	{
		Model m = new Model();
		m.resetGame();
		m.setDirection(Direction.DOWN);
		m.updateGame();
		assertTrue(m.getBoard().getTile(Board.ColumnSize / 2, (Board.RowSize / 2) - 1) == TileType.SnakeHead);
		// Snake cannot move down, because it's starting Direction is up
	}
}
