package com.mawersoft.projrain;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.mawersoft.projrain.graphics.Screen;
import com.mawersoft.projrain.input.Keyboard;

public class Game extends Canvas implements Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//
	public static int width = 300;
	public static int height = width / 16 * 9;
	public static int scale = 3;
	public static String ver = "0.0.2";
	//public static String title = "ProjRain";
	public static String title = "Project Rain "+ver;
	
	private Thread thread;
	private JFrame frame;
	private Keyboard key;
	private boolean running = false;
	private Screen screen;
	
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	
	public Game() {
		Dimension size = new Dimension(width * scale, height * scale);
		setPreferredSize(size);
		
		screen = new Screen(width, height);		
		frame = new JFrame();
		
		key = new Keyboard();
		addKeyListener(key);
	}
	
	//
	
	public synchronized void start() {
		System.out.println("Hello!");
		running = true;
		thread = new Thread(this, "Display");
		thread.start();//Start the main thread
	}
	
	public synchronized void stop() {
		System.out.println("Goodbye!");
		running = false;
		try {
			thread.join();//Stop the threads
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run () {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int ticks = 0;
		requestFocus();
		while (running) {
			//Game loop, runs while (running == true)
			//System.out.println("Tick!"); //Testing
			long now = System.nanoTime();
			delta += (now-lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick(); //In the tutorial, tick = update
				ticks++;
				delta--;
			}
			render();
			frames++;
			
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				//System.out.println(ticks + "Ticks " + frames + " FPS"); //Print info to console if needed.
				frame.setTitle(title + "  |  "  + ticks + "TPS " + frames + " FPS");
				ticks = 0;
				frames = 0;
			}
		}
		stop();
	}
	
	int x = 0, y = 0;
	
	public void tick() {
		//In the tutorial, tick = update
		key.update();
		if (key.up) y++;
		if (key.down) y--;
		if (key.left) x++;
		if (key.right) x--;
	}
	
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		screen.clear();
		screen.render(x, y);
		
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}
		
		Graphics g = bs.getDrawGraphics();
		//Graphics!
		//g.setColor(Color.BLACK);//Testing only
		//g.fillRect(0, 0, getWidth(), getHeight() );//Testing only
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.dispose();
		bs.show();//If using testing code, disable me
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		game.frame.setResizable(false);
		game.frame.setTitle(title+"  |  Reticulating splines...");
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		
		game.start();
	}
	
}