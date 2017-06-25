import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Wizard extends GameObject{
	
	Handler handler;
	
	public Wizard(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		System.out.println("tick wizarda");
		// TODO Auto-generated method stub
		x += velX;
		y += velY;
		
		collision();
		
		if(handler.isUp()) velY = -5;
		else if(!handler.isDown()) velY = 0;
		
		if(handler.isDown()) velY = 5;
		else if(!handler.isUp()) velY= 0;
		
			
		if(handler.isRight()) velX = 5;
		else if(!handler.isLeft()) velX = 0;
		
		if(handler.isLeft()) velX = -5;
		else if(!handler.isRight()) velX = 0;
	}
	
	private void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			
			if(tempObject.getId() == ID.Block) {
				
				if(getBounds().intersects(tempObject.getBounds())){
					x += velX * -1;
					y += velY * -1;
				}
			}
		}
	}
	
	public void render(Graphics g) {
		
		g.setColor(Color.blue);
		g.fillRect(x, y, 32, 48);
	}

	@Override
	public Rectangle getBounds() {
	
		
		return new Rectangle(x, y, 32, 48);
	}

}
