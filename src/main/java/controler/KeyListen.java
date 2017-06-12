package controler;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

import model.Direction;
import model.Model;

public class KeyListen implements KeyListener
{
	Model model;

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
				// timer.setDelay(model.getFrameTime());
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
}
