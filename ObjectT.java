package student.unal.begun.project2;

import java.awt.Point;

public class ObjectT extends TetrisObject {

	public ObjectT(TetrisGame game) {
		super(new Point[] { new Point(5, 18), new Point(4, 19),
				new Point(6, 19), new Point(5, 19) }, game);
	}
	/** each time it counterrotates clockwise
	 * return points of rotated version of current object in a point array
	 */
	@Override
	public Point[] rotated(Point[]units) {
		// TODO Auto-generated method stub
		Point [] rotObj = new Point[4];
		int origin = 3;
		for(int i=0; i<4; i++){
			rotObj[i] = new Point (units[origin].y - units[i].y + units[origin].x,units[origin].y - units[origin].x+units[i].x);
		}

			return rotObj;
		
		
	}

}
