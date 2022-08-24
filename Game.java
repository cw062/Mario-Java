/*
 *  Calder West, 10/9/2020
 *  Creates and runs a side scroller game where mario runs and jumps over tubes.
 *  
 */
	import javax.swing.JFrame;
	import java.awt.Toolkit;

	public class Game extends JFrame
	{
		Controller controller;
		View view;
		Model model;
		
		public Game()
		{
			model = new Model();
			controller = new Controller(model);
			view = new View(controller, model);
			this.setTitle("Fun With Mario");
			this.setSize(500, 500);
			this.setFocusable(true);
			this.getContentPane().add(view);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setVisible(true);
			view.addMouseListener(controller);
			this.addKeyListener(controller);
		}
		
		public void run() {
			while (true) {
				controller.update();
				model.update();
				view.repaint();
				Toolkit.getDefaultToolkit().sync();
				
				try {
					Thread.sleep(40);
					
				} catch(Exception e) {
					e.printStackTrace();
					System.exit(1);
				}
			}
		}
		
		public static void main(String [] args) {
			Game g = new Game();
			g.run();
		}
		
		
}
