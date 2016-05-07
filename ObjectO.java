package student.unal.begun.project2;

import java.awt.Point;

public class ObjectO extends TetrisObject{

	public ObjectO(TetrisGame game) {
		super(new Point[] { new Point(4, 19), new Point(5, 19),
				new Point(4, 18), new Point(5, 18) }, game);
	}
	/**
	 * it does not rotates
	 * returns the parameter
	 */
	@Override
	public Point[] rotated(Point[]units) {
		// TODO Auto-generated method stub
		return units;
	}
	
}
