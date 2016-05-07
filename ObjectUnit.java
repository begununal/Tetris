package student.unal.begun.project2;

import com.sun.javafx.scene.traversal.Direction;

public class ObjectUnit {
	
	private int x,y;
	
	public ObjectUnit(int x,int y){
		
		this.x = x;
	
		this.y = y;
		
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	
	public void moveInDirection(Direction dir){
		if(dir == Direction.LEFT){
		x--;
		
		} else if (dir == Direction.RIGHT){
			x++;
		} else if (dir == Direction.DOWN){
			y--;
		}
		
	}
}
