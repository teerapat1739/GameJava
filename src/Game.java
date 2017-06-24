import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {
	
		
	private static final long serialVersionUID = 1L;
	
	private boolean isRunning = false;
	private Thread thread;
	private Handler handler;
	
			public Game(){
				new Window(1000, 563,"Wizard Game", this);
				 start();
	
				 handler = new Handler();
				 this.addKeyListener(new KeyInput(handler));
				 handler.addObjectObject(new Wizard(100, 100, ID.Player, handler));
				 this.addKeyListener(new KeyInput(handler));
			}
			
			public void start(){
				isRunning = true;
				thread = new Thread(this);
				thread.start();
			}
			private void stop(){
				isRunning = false;
				try {
					thread.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			@Override
			public void run() {
				// TODO Auto-generated method stub
				this.requestFocus();
				long lastime = System.nanoTime();
				double amountofTicks = 60.0;
				double ms = 100000000 / amountofTicks;
				double delta = 0;
				long timer = System.currentTimeMillis();
				int frames = 0;
				while(isRunning){
					long now = System.nanoTime();
					delta = 0;
					lastime = now;
					while(delta >= 1){
						tick();
						//updates++;
						delta--;
					}
					render();
					frames++;
					
					if(System.currentTimeMillis() - timer > 1000){
						timer += 1000;
						frames = 0;
						//updates = 0;
					}
				}
				stop();
			}
			public void tick(){
				handler.tick();
			}
			public void render(){
				BufferStrategy bs = this.getBufferStrategy();
				if(bs == null){
					this.createBufferStrategy(3);
					return;
				}
				
				Graphics g = bs.getDrawGraphics();
				/////////////////////////////////
				
				g.setColor(Color.red);
				g.fillRect(0, 0, 1000, 563);
				
				handler.render(g);
				/////////////////////////////////
				
				g.dispose();
				bs.show();
			}
			public static void main(String args[]){
				new Game();
			 }
}
