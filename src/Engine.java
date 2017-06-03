import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;


import javax.swing.JFrame;
public class Engine 
{
	private static final int UPDATES_PER_SECOND = 5; //Game's tickrate
	private Canvas canvas;
	private Board board;
	private Snek snake;

	private Engine(Canvas canvas) 
	{
		this.board = new Board();
		this.canvas = canvas;
		snake = new Snek(board);
	}
	public void startGame()
	{
		canvas.createBufferStrategy(2);
		Graphics2D g =(Graphics2D)canvas.getBufferStrategy().getDrawGraphics();
		long start = 0L;
		long sleepDuration = 0L;
		 while(true) 
		 {
			  start = System.currentTimeMillis();
			  update();
			  render(g);
			  canvas.getBufferStrategy().show();
			  g.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
			  sleepDuration = (1000L / UPDATES_PER_SECOND) - (System.currentTimeMillis() - start);
			  if(sleepDuration > 0) 
			  {
				  try 
				  {
					  Thread.sleep(sleepDuration);
				  } 
				  catch(Exception e) 
				  {
					  e.printStackTrace();
				  }
			  }
		}
	}
			private void update() 
			{
				//TODO
			}
			private void render(Graphics2D g) 
			{
				board.draw(g);
			}
	public static void main(String[] args) {
		
		 JFrame frame = new JFrame("Snake Game");
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 frame.setResizable(false);
		
		 Canvas canvas = new Canvas();
		 canvas.setBackground(Color.BLACK);
		 canvas.setPreferredSize(new Dimension(Board.MAP_SIZE*Board.TILE_SIZE, Board.MAP_SIZE*Board.TILE_SIZE));
	
		 frame.add(canvas);
		 
		 frame.pack();
		 frame.setLocationRelativeTo(null);
		 frame.setVisible(true);	
		 
		 Engine engine = new Engine(canvas);
		 engine.board.setTile(1, 1, TileType.SNAKE);
		 engine.board.setTile(1, 2, TileType.FRUIT);
		 engine.board.setTile(1, 0, TileType.SNAKE);

		 engine.startGame();
	}
}