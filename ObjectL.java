package student.unal.begun.project2;

import java.awt.Point;

public class ObjectL extends TetrisObject {

	public ObjectL (TetrisGame game) {
		super(new Point[] { new Point(4, 19), new Point(5, 19),
				new Point(6, 19), new Point(4, 18) }, game);
	}

	@Override
	public Point[] rotated(Point [] units) {
		
		Point [] rotObj = new Point[4];
		int origin = 1;
		for(int i=0; i<4; i++){
			rotObj[i] = new Point (units[origin].y - units[i].y + units[origin].x,units[origin].y - units[origin].x+units[i].x);
		}

			return rotObj;
		
	}
	
	
}
