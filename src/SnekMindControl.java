import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SnekMindControl implements KeyListener, ActionListener
{
	Model model;
	SnakeView view;
	Timer timer;

	public void actionPerformed(ActionEvent e)
	{
		if (!model.isPaused() && !model.isGameOver())
		{
			model.updateGame();
		}
		view.refresh();
	}

	public void start()
	{
		model = new Model();
		view = new SnakeView(this);
		model.resetGame();
		model.setPaused(true);
		model.setNewGame(true);
		timer = new Timer(model.getFrameTime(), this);
		timer.start();
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
				model.setPaused(!model.isPaused());
			}
			break;
		case KeyEvent.VK_ENTER:
			if (model.isNewGame() || model.isGameOver())
			{
				model.resetGame();
				model.setPaused(false);
			}
			break;
		}
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