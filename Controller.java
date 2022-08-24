import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;

class Controller implements ActionListener, MouseListener, KeyListener {
	Model model;
	View view;
	boolean keyLeft;
	boolean keyRight;
	boolean keyUp;
	boolean keyDown;
	boolean spacebar;
	
	Controller(Model m) {
		model = m;
	}
	
	void setModel(Model m) {
		model = m;
	}
	
	void setView(View v) {
		view = v;
	}
	
	public void actionPerformed(ActionEvent e) {
		
	}
	
	public void mousePressed(MouseEvent e) {
		model.addTube(e.getX() + model.mario.x - view.marioOffset, e.getY());
	}
	
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_RIGHT: keyRight = true; break;
		case KeyEvent.VK_LEFT: keyLeft = true; break;
		case KeyEvent.VK_UP: keyUp = true; break;
		case KeyEvent.VK_DOWN: keyDown = true; break;
		case KeyEvent.VK_SPACE: spacebar = true; break;
		}
	}
	
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_RIGHT:
				keyRight = false;
				break;
			case KeyEvent.VK_LEFT:
				keyLeft = false;
				break;
			case KeyEvent.VK_UP:
				keyUp = false;
				model.mario.isFalling = true;
				break;
			case KeyEvent.VK_DOWN:
				keyDown = false;
				break;
			case KeyEvent.VK_SPACE:
				spacebar = false;
				model.mario.isFalling = true;
				break;
		}
		char c = e.getKeyChar();
		if (c == 's') {
			model.marshal().save("map.json");
			System.out.println("You have saved map.json");
		}
		if (c == 'l') {
			Json j = Json.load("map.json");
			model.unmarshal(j);
			System.out.println("You have loaded map.json");
		}
	}
	
	public void keyTyped(KeyEvent e) {}
	
	void update() {
		model.mario.savePreviousCoordinates();
		getOutOfTube();
		if (keyRight) {
			model.mario.marioImageNum++;
			model.mario.x += 5;
		}
		if(keyLeft) {
			model.mario.marioImageNum++;
			model.mario.x -= 5;
		}
		if(keyUp || spacebar) {
			if (model.mario.y + model.mario.height == 400)
				model.mario.isFalling = false;
			for (int i = 0; i < model.tubes.size(); i++) {
				if (model.tubes.get(i).doesTubeExist(model.mario.x, model.mario.y + model.mario.height+ 1)) {
					model.mario.isFalling = false;
				}
			}
			model.mario.yeet();
		}
	}
	
	void getOutOfTube() {
		for (int i = 0; i < model.tubes.size(); i++) {
			if (model.mario.py + model.mario.height > model.tubes.get(i).y) {
				if (model.tubes.get(i).doesTubeExist(model.mario.x + model.mario.width, model.mario.y)
					|| model.tubes.get(i).doesTubeExist(model.mario.x + model.mario.width, model.mario.y + model.mario.height)) {
					model.mario.y = model.mario.py;
					model.mario.x = model.tubes.get(i).x - model.mario.width;
				} else if (model.tubes.get(i).doesTubeExist(model.mario.x, model.mario.y)
						|| model.tubes.get(i).doesTubeExist(model.mario.x, model.mario.y + model.mario.height)) {
						model.mario.y = model.mario.py;
						model.mario.x = model.tubes.get(i).x + model.tubes.get(i).w;
				}
			}
		}
	}



}
