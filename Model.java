import java.util.ArrayList;
import java.util.Iterator;
class Model {
	ArrayList<Tube> tubes;
	Mario mario;
	
	Model() {
		tubes = new ArrayList<Tube>();
		mario = new Mario(200, 50);
	}
	
	public void update() {
		mario.update();
	}
	
	void unmarshal(Json ob) {
		tubes = new ArrayList<Tube>();
		Json tmpList = ob.get("tubes");
		for (int i = 0; i < tmpList.size(); i++)
			tubes.add(new Tube(tmpList.get(i)));
	}
	
	Json marshal() {
		Json ob = Json.newObject();
		Json tmpList = Json.newList();
		ob.add("tubes", tmpList);
		for (int i = 0; i < tubes.size(); i++)
			tmpList.add(tubes.get(i).tubeMarshal());
		return ob;
	}
	
	public void addTube(int x, int y) {
		Tube t = new Tube(x, y);
		boolean doesTubeExist = false;
		Iterator<Tube> tubeIterator = tubes.iterator();
		
		while(tubeIterator.hasNext()) {
			Tube temp = tubeIterator.next();
			if(temp.doesTubeExist(x, y)) {
				tubeIterator.remove();
				doesTubeExist = true;
				break;
			}
		}
		if (!doesTubeExist)
			tubes.add(t);
	}
}
