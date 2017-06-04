import javax.swing.JFrame;
import java.awt.Point;
import java.util.LinkedList;
import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Snek extends JFrame {
	private static final long serialVersionUID = 5985215749307426314L;
	private static final long FrameTime = 1000L / 50L;
	private static final int SnakeMinLength = 3;
	private static final int MaxDirections = 3;
	private Board board;
	private LinkedList<Point> snek;
	private LinkedList<Direction> directions;
	private int score;
	private int score_mul;
	private boolean GameOver;
	private Random random;

	public Snek() {
		super("Snake");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		this.board = new Board(this);
		add(board, BorderLayout.CENTER);
		addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) { // TODO
				}
			}

		});
	}

	private void startGame() { // TODOfinish after engine rework
		this.random = new Random();
		this.snek = new LinkedList<>();
		this.directions = new LinkedList<>();
		while (true) {
			long start = System.nanoTime();
			long delta = (System.nanoTime() - start) / 1000000L;
			if (delta < FrameTime) {
				try {
					Thread.sleep(FrameTime - delta);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void updateGame() {
		TileType collision = updateSnake(); // It will return SnakeBody for both
											// hitting SnakeBody and wall

		if (collision == TileType.Fruit) {
			score += 200;
			spawnFruit();
		} else if (collision == TileType.SnakeBody) {
			GameOver = true;
		}
	}

	private TileType updateSnake() {

		Direction direction = directions.peekFirst();
		Point head = new Point(snek.peekFirst());
		switch (direction) {
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
		if (head.x < 0 || head.x >= Board.ColumnSize || head.y < 0 || head.y >= Board.RowSize) {
			return TileType.SnakeBody; // return SnakeBody, 2 birds with one
										// stone
		}
		TileType old = board.getTile(head.x, head.y);
		if (old != TileType.Fruit && snek.size() > SnakeMinLength) {
			Point tail = snek.removeLast();
			board.setTile(tail, null);
			old = board.getTile(head.x, head.y);
		}

		// Update the snake's position on the board if we didn't collide wit our
		// tail
		if (old != TileType.SnakeBody) {
			board.setTile(snek.peekFirst(), TileType.SnakeBody);
			snek.push(head);
			board.setTile(head, TileType.SnakeHead);
			if (directions.size() > 1) {
				directions.poll();
			}
		}
		return old;
	}

	private void resetGame() {
		this.score = 0;
		Point head = new Point(Board.ColumnSize / 2, Board.RowSize / 2);
		snek.clear();
		snek.add(head);
		board.resetBoard();
		board.setTile(head, TileType.SnakeHead);
		directions.clear();
		directions.add(Direction.UP);
		spawnFruit();
	}

	private void spawnFruit() {

		int index = random.nextInt(Board.ColumnSize * Board.RowSize - snek.size());
		/*
		 * this method searches for a index'th free tile, rather than randoming
		 * index and checking if the tile on the randomed index is free
		 */
		int freeFound = -1;
		for (int x = 0; x < Board.ColumnSize; x++) {
			for (int y = 0; y < Board.RowSize; y++) {
				TileType type = board.getTile(x, y);
				if (type == null || type == TileType.Fruit) {
					if (++freeFound == index) {
						board.setTile(x, y, TileType.Fruit);
						break;
					}
				}
			}
		}
	}

	public int getScore() {
		return score;
	}

	public Direction getDirection() {
		return directions.peek();
	}

	public static void main(String[] args) {
		Snek snake = new Snek();
		snake.startGame();
	}
}
