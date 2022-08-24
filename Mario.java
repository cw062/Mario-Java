class Mario {
	int x, y, px, py, width, height, marioImageNum, numFramesInAir;
	double vert_velocity;
	boolean isFalling;
	
	public Mario(int x, int y) {
		this.x = x;
		this.y = y;
		width = 60;
		height = 95;
		marioImageNum = 0;
		vert_velocity = 12.0;
		numFramesInAir = 0;
		isFalling = false;
	}
	
	void yeet() {
		
		if (!isFalling) {
			if (numFramesInAir < 20) {
				vert_velocity = -20;
				numFramesInAir++;
			}
	
			if (y + height == 400) {
				numFramesInAir = 0;
				
			}
		}
	}
	
	void savePreviousCoordinates() {
		px = x;
		py = y;
	}
	
	
	void update() {

		vert_velocity += 6.8;
		y += vert_velocity;
		
		if(y > 400 - height) {
			vert_velocity = 0;
			y = 400 - height;
		}
		if (y < 0)
			y = 0;
		if (marioImageNum > 4)
			marioImageNum = 0;
	}
	
}
