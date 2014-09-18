package com.mawersoft.projrain;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.mawersoft.projrain.entity.mob.Player;
import com.mawersoft.projrain.graphics.Screen;
import com.mawersoft.projrain.input.Keyboard;
import com.mawersoft.projrain.input.Mouse;
import com.mawersoft.projrain.level.Level;
import com.mawersoft.projrain.level.RandomLevel;
import com.mawersoft.projrain.level.SpawnLevel;
import com.mawersoft.projrain.level.TileCoordinate;

public class Game extends Canvas implements Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//
	private static int width = 300;
	private static int height = width / 16 * 9;
	private static int scale = 3;
	public static String ver = "0.0.6";
	//public static String title = "ProjRain";
	public static String title = "Project Rain "+ver;
	public int currentFPS;
	public int currentTPS;
	
	private Thread thread;
	private JFrame frame;
	private Keyboard key;
	private Level level;
	private Player player;
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
		//level = new RandomLevel(64, 64);
		level = Level.spawn;
		TileCoordinate playerSpawn = new TileCoordinate(20, 66);
		player = new Player(playerSpawn.x(), playerSpawn.y(), key);
		player.init(level);
		addKeyListener(key);
		
		Mouse mouse = new Mouse();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
	}
	
	public static int getWindowWidth() {
		return width * scale;
	}
	
	public static int getWindowHeight() {
		return height * scale;
	}
	
	//
	
	public synchronized void start() {
		System.out.println("Hello!");
		System.out.println("This is Project Rain Version" + ver + " by jack mawer games");
		running = true;
		thread = new Thread(this, "Display");
		thread.start();//Start the main thread
	}
	
	public synchronized void stop() {
		//This code is unused, and will remain so unless we embed the game into an applet.
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
				currentFPS = frames;
				currentTPS = ticks;
				ticks = 0;
				frames = 0;
			}
		}
		stop();
	}
	
	
	public void tick() {
		//In the tutorial, tick = update
		key.update();
		player.update();
		level.update();
		
	}
	
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		screen.clear();
		int xScroll = player.x - screen.width / 2;
		int yScroll = player.y - screen.height / 2;
		level.render(xScroll, yScroll, screen);
		player.render(screen);
		
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}
		
		Graphics g = bs.getDrawGraphics();
		//Graphics!
		//g.setColor(Color.BLACK);//Redundant code
		//g.fillRect(0, 0, getWidth(), getHeight() );//Redundant code
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		//g.fillRect(Mouse.getX() - 32, Mouse.getY() - 32, 64, 64); // Don't need it
		g.drawString("Project Rain by Jack Mawer Games, Version " + ver, 5, 10);
		g.drawString("CurrentMouseButton: " + Mouse.getB(), 5, 20);
		g.drawString("MouseX: " + Mouse.getX() + " MouseY:" +Mouse.getY(), 5, 30);
		//g.drawString("PlayerX: " +  + " PlayerY: " + , 5, 40); // Broken...
		g.drawString("TPS: " + currentTPS + " FPS: " + currentFPS, 5, 40);
		if (currentFPS >= 1000) g.drawString("Your FPS is FLAMING!", 5, 50);
		if (currentFPS >= 60 && currentFPS < 1000) g.drawString("Your FPS is great!", 5, 50);
		if (currentFPS >= 30 && currentFPS < 60) g.drawString("Your FPS is good", 5, 50);
		if (currentFPS >= 10 && currentFPS < 30) g.drawString("Your FPS is bad...", 5, 50);
		if (currentFPS >= 1 && currentFPS < 10) g.drawString("Your FPS is terrible!", 5, 50);
		
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