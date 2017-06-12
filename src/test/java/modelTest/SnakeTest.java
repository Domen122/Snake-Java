package modelTest;

import static org.junit.Assert.*;

import org.junit.Test;

import java.awt.Point;
import model.Snake;

public class SnakeTest
{

	@Test
	public void testSnake()
	{
		Snake snake = new Snake();
		assertNull(snake.getHead());
	}

	@Test
	public void testGetHead()
	{
		Snake snake = new Snake();
		Point point = new Point(1, 3);
		snake.resetSnake(point);
		assertTrue(snake.getHead().x == 1 && snake.getHead().y == 3);
	}

}
