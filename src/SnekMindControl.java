import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SnekMindControl implements KeyListener
{
	Model model;
	SnakeView view;

	void mainLoop()
	{
		while (true)
		{
			long start = System.nanoTime();
			model.eUpdate();
			if (model.hasElapsedCycle())
			{
				model.updateGame();
			}
			view.boardV.repaint();
			long delta = (System.nanoTime() - start) / 1000000L;
			if (delta < model.getFrameTime())
			{
				try
				{
					Thread.sleep(model.getFrameTime() - delta);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}
	}

	public void start()
	{
		model = new Model();
		view = new SnakeView(this);
		model.resetGame();
		model.setPaused(true);
		model.setNewGame(true);

		mainLoop();
	}

	@Override
	public void keyPressed(KeyEvent ke)
	{
		switch (ke.getKeyCode())
		{
		case KeyEvent.VK_UP:
		case KeyEvent.VK_W:
			if (!model.isPaused())
			{
				model.setDirection(Direction.UP);

			}
			break;
		case KeyEvent.VK_RIGHT:
		case KeyEvent.VK_D:
			model.setDirection(Direction.RIGHT);
			break;

		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_S:
			model.setDirection(Direction.DOWN);
			break;

		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_A:
			model.setDirection(Direction.LEFT);
			break;
		case KeyEvent.VK_P:
			if (!model.isGameOver())
			{
				model.setMPaused(!model.isPaused());
			}
			break;
		case KeyEvent.VK_ENTER:
			if (model.isNewGame() || model.isGameOver())
			{
				model.resetGame();
				model.setMPaused(false);
			}
			break;
		}
		model.setPaused(false);
	}

	@Override
	public void keyReleased(KeyEvent arg0)
	{
	}

	@Override
	public void keyTyped(KeyEvent arg0)
	{
	}

	public static void main(String[] args)
	{
		SnekMindControl c = new SnekMindControl();
		c.start();
	}
}