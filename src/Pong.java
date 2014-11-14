import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Pong extends JPanel implements KeyListener
{
	private static final long serialVersionUID = 1L;

	boolean isGameRunning = false;
	
	private final int WIDTH = 640;
	private final int HEIGHT = 480; 

	Dimension dimension = new Dimension(WIDTH, HEIGHT);	
	
	private int playerA_X = 20; // location of player A's paddle
	private int playerA_Y = 240;

	private int playerB_X = 600; // location of player B's paddle
	private int playerB_Y = 240;
	
	private int ball_X = 320;   // location of Ball
	private int ball_Y = 240;
	
	private int ball_velocity_x = 4;
	private int ball_velocity_y = 2;
	
	private int width_bat = 10;
	private int height_bat = 50;
	
	public Pong()
	{
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Pong MultiPlayer game");
		frame.setResizable(false);
		
		this.setPreferredSize(dimension);
		
		frame.add(this);		
		frame.pack();
		frame.setVisible(true);
		
		frame.addKeyListener(this);		
		draw();
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponents(g);
		
		g.setColor(Color.black);
		g.fillRect(0,0, WIDTH, HEIGHT);
		
		g.setColor(Color.blue);
		g.fillRect(playerA_X, playerA_Y, width_bat, height_bat);
		g.fillRect(playerB_X, playerB_Y, width_bat, height_bat);	
		
		g.setColor(Color.green);
		g.fillOval(ball_X, ball_Y, 10, 10);
	}	
	
	private void draw() 
	{
		this.repaint();
	}

	public void play()
	{
		isGameRunning = true;
		
		while(isGameRunning)
		{
			if(ball_X > WIDTH || ball_X < 0 || detectCollision())
			{
				ball_velocity_x = -ball_velocity_x;
			}
			
			if(ball_Y > HEIGHT || ball_Y < 0)
			{
				ball_velocity_y = -ball_velocity_y;
			}
			
			ball_X += ball_velocity_x;
			ball_Y += ball_velocity_y;
			
			draw();

			try
			{
				Thread.sleep(100);
			}
			catch(Exception e)
			{
				
			}			
		}
	}

	@Override
	public void keyPressed(KeyEvent key) 
	{
		if(key.getKeyCode() == KeyEvent.VK_UP)
		{
			if(playerA_Y - 10 > 0)
			{
				playerA_Y -= 10;				
			}			
		}
		else if(key.getKeyCode() == KeyEvent.VK_DOWN)
		{
			if((playerA_Y + 20) < HEIGHT)
			{
				playerA_Y += 10;				
			}
		}
		else if(key.getKeyChar() == 'w')
		{
			if(playerB_Y > 10)
			{
				playerB_Y -= 10;				
			}
		}
		else if(key.getKeyChar() == 's')
		{
			if((playerB_Y + 20) < (HEIGHT))
			{
				playerB_Y += 10;				
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) 
	{
		// TODO Auto-generated method stub
		
	}
	
	public boolean detectCollision()
	{
		// player A
		if( (ball_Y > playerA_Y) && ( (ball_Y + ball_velocity_y) < playerA_Y + height_bat))
		{
			if( (ball_X > playerA_X) && ((ball_X + ball_velocity_x) < (playerA_X + width_bat)))
			{
				return true;
			}
		}
		
		// player B
		if( (ball_Y > playerB_Y) && ( (ball_Y + ball_velocity_y) < (playerB_Y + height_bat) ))
		{
			if( (ball_X < playerB_X) && ( (ball_X + ball_velocity_x) > (playerB_X - width_bat)))
			{
				return true;
			}
		}		
		
		return false;
	}
	
}
