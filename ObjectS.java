package student.unal.begun.project2;

import java.awt.Point;

public class ObjectS extends TetrisObject {
	private static int rotateCounter = 0;
	public ObjectS(TetrisGame game) {
		super(new Point[] { new Point(5, 19), new Point(6, 19),
				new Point(4, 18), new Point(5, 18) }, game);
	}
	/** it rotates counterclockwise for first two rotation
	 *  clockwise for third and fourth rotation
	 *  it has 2 kinds of version
	 * return points of rotated version of current object in a point array
	 */
	@Override
	public Point[] rotated(Point[]units) {
		rotateCounter++;
		Point [] rotObj = new Point[4];
		int origin = 0;
		if(rotateCounter % 2 ==1){
		for(int i=0; i<4; i++){
			rotObj[i] = new Point (units[origin].y - units[i].y + units[origin].x, units[origin].y - units[origin].x+units[i].x);
		}
		}else{
			for(int i=0; i<4; i++){
				rotObj[i] = new Point(units[origin].x - units[origin].y + units[i].y,units[origin].x - units[i].x + units[origin].y);
			}
		}

			return rotObj;
		}
	

	}
	

