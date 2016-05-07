package student.unal.begun.project2;

import java.awt.Point;
import java.awt.event.KeyEvent;



public class AI extends TetrisGame{
	
	
	KeyEvent e;
	private TetrisGame game= new TetrisGame(this);
	
	private Point [] rot1 = new Point[] {new Point(0,0), new Point (0,0), new Point (0,0), new Point (0,0),};
	private Point [] rot2 = new Point[] {new Point(0,0), new Point (0,0), new Point (0,0), new Point (0,0),};
	private Point [] rot3 = new Point[] {new Point(0,0), new Point (0,0), new Point (0,0), new Point (0,0),};
	private Point [] rot4 = new Point[] {new Point(0,0), new Point (0,0), new Point (0,0), new Point (0,0),};

	public AI(){
		
		game.setAi(this);
		game.getGui().setTitle("Bunu AI oynuyo");
		game.getGui().setLocation(game.getGui().getLocation().x+game.getGui().getWidth(),game.getGui().getLocation().y);
		game.gameCanvas.setFocusable(false);	

		
			rot1 = game.getPiece().rotated(game.getPiece().units);
		
			rot2 = game.getPiece().rotated(rot1);
		
			rot3 = game.getPiece().rotated(rot2);
		
			rot4 = game.getPiece().rotated(rot3);
	
	}

	/**find index of the left most point of the piece
	 * @param rtd is a point array 
	 * @return left most point 
	 */
	public Point LeftMost(Point[] rtd){
	
		int min =rtd[0].x;
		int index=0;
		for(int i=0; i<3; i++){
			if(rtd[i+1].x<min){
			 min =rtd[i+1].x;
			 index = i+1;
			}
		}
		
		return rtd[index];
	}
	/**find index of the right most point of the piece
	 * @param rtd is a point array 
	 * @return right most point 
	 */
	public Point RightMost(Point[] rtd){
		int max =rtd[0].x;
		int index=0;
		for(int i=0; i<3; i++){
			if(rtd[i+1].x>max){
			 max =rtd[i+1].x;
			 index = i+1;
			}
		}
		return rtd[index];
	}
	/**find index of the most down point of the piece
	 * @param rtd is a point array 
	 * @return most down point 
	 */
	public Point DownMost(Point[] rtd){
		int min =rtd[0].y;
		int index=0;
		for(int i=0; i<2; i++){
			if(rtd[i+1].y<min){
			 min =rtd[i+1].y;
			 index = i+1;
			}
		}
		return rtd[index];
	}
	/**find distance between horizontal edges 
	 * @param rtd is a leftmost point of the object 
	 * @return horizontal length
	 */
	public int distanceHor(Point [] rtd){
		
		return RightMost(rtd).x -LeftMost(rtd).x;
	
	}
	/**translate the point array according to x coordinate
	 * @param rtd is a point array 
	 * @param a x coordinate of left most point of the object
	 */
	public void translate (Point [] rtd,int a ){
		int transDist = a-LeftMost(rtd).x;
		for(Point p : rtd){
			p.translate(transDist, 0);
		}
		
	}
	/** checks the ability to move of the piece to downward
	 * @param rtd a point array
	 * @return a boolean 
	 */
	public boolean isEmptyDown(Point [] rtd){
		for(Point p : rtd){
			if(p.y>0 && p.x<10){
			if((game.grid[p.x][p.y-1] && !this.Contains(p.x, p.y-1,rtd))){
				return false;
			}
			}else{
				return false;
			}
		}
		return true;
	}
	/** checks whether rtd array contains (x,y) point 
	 * @param x
	 * @param y
	 * @param rtd
	 * @return a boolean 
	 */
	public boolean Contains(int x, int y, Point [] rtd){
		for( Point p : rtd){
			if((int)p.getX() == x && (int)p.getY() == y){
				return true;
			}
		}
		return false;
	}
	

	/**
	 * @param rtd can be one of 4 rotated version of current piece
	 * 			each version scans the canvas horizontally
	 * 			checks very side of the version when it stops 
	 * for each side that next to any filled grid increases the counter
	 * 			
	 * @return max of counter and x coordinate of the left most point of the object  in an array
	 * when the counter becomes max 
	 */
	public int[] checkPossiblePlaces(Point [] rtd)
	{
		int [] maxAndPlaceX =new  int [2];
		int counter = 0;
		int maxCounterPlaceX = 0;
		int maxCounter = 0;
		int dist = distanceHor(rtd);
		
		for(int i=0; i< 9-distanceHor(rtd)-2; i++){
			translate(rtd,i);
			int[] oldYs = new int[rtd.length];
			for (int j = 0; j < rtd.length; j++) {
				oldYs[j] = rtd[j].y;
			}
			while(isEmptyDown(rtd)){
				for (Point p : rtd) {
					p.y--;	
				}
			}
			for (Point p : rtd) {
				if(p.x>0 && p.y<20){
				if(game.grid[p.x-1][p.y]){
					counter++;
				}
				}else{
					counter++;
				}
				if(p.x<9 && p.y<20){
				if(game.grid[p.x+1][p.y]){
					counter++;
				}else{
					counter++;
				}
				}
				if(p.y>1){
				if(game.grid[p.x][p.y-1]){
					counter++;
				}else{
					counter++;
				}
				}if(p.y<19){
				if(game.grid[p.x][p.y+1]){
					counter++;
				}
				}else{
					counter++;
				}
			}
			if(counter > maxCounter)
			{
				maxCounter = counter;
				maxCounterPlaceX = i;
				maxAndPlaceX[0] = maxCounter;
				maxAndPlaceX[1] = maxCounterPlaceX;
			}
			counter = 0;
			for (int j = 0; j < rtd.length; j++) {
				rtd[j].y = oldYs[j];
			}
		}
		return maxAndPlaceX;
	}
	
	
	

	
	/** compares the counters of the rotated versions by sending them to checkPossiblePlaces method
	 * @return an index of an integer array for representing rotated versions 
	 */
	public int RotationNumber (){
		int Rots [] = new int []{1,2,3,4};
		int bestRot = 0;
		int max = checkPossiblePlaces(rot1)[0];
		
		if(checkPossiblePlaces(rot2)[0]>max){
			max = checkPossiblePlaces(rot2)[0];
			bestRot=1;
		}else if(checkPossiblePlaces(rot3)[0]>max){
			max = checkPossiblePlaces(rot3)[0];
			bestRot=2;
		}else if(checkPossiblePlaces(rot4)[0]>max){
			max = checkPossiblePlaces(rot4)[0];
			bestRot=3;
		}
		game.DownPressed();
	
		return Rots[bestRot]; 
		
		}
	
	
	/**
	 * calls the method for moving for expected way
	 */
	public void enterInput()
	{
		int a = checkPossiblePlaces(game.getPiece().units)[1]-LeftMost(game.getPiece().units).x;
		if(a<0){
		for(int i=0; i<a; i++){
			game.LeftPressed();
		}
		}else if(a>0){
			for(int i=a; i>0; i++){
				game.RightPressed();
			}
		}
		for(int i=0; i<RotationNumber(); i++){
			game.UpPressed();
		}
	}
	
	public TetrisGame getGame() {
		return game;
	}
	
	
	
}
