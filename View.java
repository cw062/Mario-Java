import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

class View extends JPanel {
	static BufferedImage tube_image;
	static BufferedImage[] mario_images;
	int marioOffset;
	Model model;
	
	View(Controller c, Model m) {
		model = m;
		c.setView(this);
		
		marioOffset = 100;
		mario_images = new BufferedImage[5];
		mario_images[0] = loadImage("mario1.png");
		mario_images[1] = loadImage("mario2.png");
		mario_images[2] = loadImage("mario3.png");
		mario_images[3] = loadImage("mario4.png");
		mario_images[4] = loadImage("mario5.png");
	}
	
	static BufferedImage loadImage(String filename) {
		BufferedImage im = null;
		try {
			im = ImageIO.read(new File(filename));
		} catch (Exception e) {
			e.printStackTrace(System.err);
			System.exit(1);
		}
		return im;
	}
	
	public void paintComponent(Graphics g) {
		g.setColor(new Color(128, 255, 255));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(new Color(100, 255, 80));
		g.fillRect(0, 400, this.getWidth(), 100);
		for(int i = 0; i < model.tubes.size(); i++) {
			if(tube_image == null)
				this.tube_image = loadImage("tube.png");
			g.drawImage(this.tube_image, model.tubes.get(i).x - model.mario.x + marioOffset, model.tubes.get(i).y, null);	
		}
		// draw mario
		g.drawImage(mario_images[model.mario.marioImageNum], marioOffset, model.mario.y, null);
	}
}
