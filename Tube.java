public class Tube {
	int x, y;
	static int numTubes;
	final int w = 55;
	final int h = 272;
	
	public Tube(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	Tube (Json ob) {
		x = (int)ob.getLong("tube_x");
		y = (int)ob.getLong("tube_y");
	}
	
	Json tubeMarshal() {
		Json ob = Json.newObject();
		ob.add("tube_x", x);
		ob.add("tube_y", y);
		return ob;
	}
	
	boolean doesTubeExist(int user_x, int user_y) {
		if (user_x < x)
			return false;
		if (user_x > x + w)
			return false;
		if (user_y < y)
			return false;
		if (user_y > y + h)
			return false;
		return true;
	}
	
}
