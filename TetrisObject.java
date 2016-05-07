package student.unal.begun.project2;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;

import student.unal.begun.project2.TetrisGame.Direction;
import drawing_framework.Animatable;
import drawing_framework.AnimationCanvas;

public abstract class TetrisObject implements Animatable {
	
	int frameCounter;
	private int moveFrameInterval=10;
	
	public Point[] units;
	private TetrisGame game; 
	
	private boolean continueToMove = true;
	
	private boolean isBlockLanded = false;
	
	public TetrisObject(Point[] units, TetrisGame game){
		this.units = units.clone();
		this.game = game;
		
	}
	
	/**this a filled by subclasses 
	 * @param units current object
	 * @return a point array that is 
	 * rotated version of current object
	 */
	public abstract Point [] rotated(Point[] units);
	
	public void rotation (Point [] rotObj){
		boolean rotatable = true;
		for(Point k : rotObj){
			if(k.x>=0 && k.x<10 && k.y>=0 && k.y<20){
				if(game.grid[k.x][k.y] && !Contains(k.x, k.y)){
			
					rotatable =false;
					
				}
			}
		}
			for(Point k : rotObj){
					if(k.x<0 || k.x>9 || k.y<0 || k.y>19){
					rotatable = false;
					}
			}
		
				if(rotatable){
				for (Point p : units){
					game.grid[p.x] [p.y] = false;
				}
				
				for(int i =0; i<4; i++){
					units[i].setLocation(rotObj[i].x, rotObj[i].y);
					game.Filling(units[i].x, units[i].y);
				}
			}
		}
	
		
	
	
	/** moves the object in expected way
	 * makes boundy checks and movability according to filled grids
	 * @param dir is an enum 
	 */
	public void moveInDirection(Direction dir){
		int dx = (dir == Direction.LEFT) ? -1 : 1; 
	

		if((dx == 1 && isEmptyRight()) || (dx == -1 && isEmptyLeft())){
		
		for (Point p : units){
			game.grid[p.x] [p.y] = false;
		}
		
		for (Point p : units){
			
			p.translate(dx, 0); 
			game.Filling(p.x, p.y);
			}
			}
		}
	
	/**controls above of the object
	 * @return a boolean
	 */
	public boolean isEmptyUp(){
		for(Point p : units){
			if(p.y==0 || (game.grid[p.x][p.y+1] && !this.Contains(p.x, p.y+1))){
				return false;
			}
		}
		return true;
	}
	
	/** changes grids with the above ones 
	 * changes the grids above the filled line
	 * @param row is y coordinate of filled line
	 */
	public void drop(int row)
	{
		for(int x = 0; x < 10; x++)
		{
			for(int y = row; y < 19; y++)
			{
				game.grid[x][y] = game.grid[x][y + 1];
			}
			game.grid[x][19] = false;
		}
	}
	
		/**finds the fully filled lines
		 * and increases the y coordinates above the filled line
		 */
		public void checkFullLines(){
			
			for(int y = 0; y < 20; y++)
			{
				boolean isRowFilled = true;
				for (int x= 0; x < 10; x++) {
					if(!game.grid[x][y])
					{
						isRowFilled = false;
						break;
					}
				}
				if(isRowFilled)
				{
					drop(y);
					y--;
				}
			}
			
		}

	
	
		/** controls movability of the object 
		 * to rightside
		 * @return a boolean
		 */
		public boolean isEmptyRight(){
			for(Point p : units){
				if(p.x==20 || (game.grid[p.x+1][p.y] && !this.Contains(p.x+1, p.y))){
					return false;
				}
			}
			return true;
		}

		/**controls movability of the object
		 * to leftside
		 * @return a boolean
		 */
		public boolean isEmptyLeft(){
			for(Point p : units){
				if(p.x==0 || (game.grid[p.x-1][p.y] && !this.Contains(p.x-1, p.y) )){
		
					return false;
				}
			}
			return true;
		}
		
		/**controls movability of the object
		 * to upward
		 * @return a boolean 
		 */
		public boolean isEmptyDown(){
			for(Point p : units){
				if (p.y>0){
				if((game.grid[p.x][p.y-1] && !this.Contains(p.x, p.y-1))){
					return false;
				}
				}else{
					return false;
				}
			}
			return true;
		}
		
	
		/** 
		 * @param x coordinate of grid that is being checked now
		 * @param y coordinate of grid that is being checked now
		 * @return true if the unit contains the coodinates
		 */
		public boolean Contains(int x, int y){
			for( Point p : units){
				if(p.x == x && p.y == y){
					return true;
				}
			}
			return false;
		}
//		public void WorksAI(){
//		if(game.getAi() != null)
//		{
//			game.getAi().enterInput();
//		}
//		}
		
		/**this method belongs the animatable interface 
		 * it makes the object drop 
		 * 
		 */
	@Override
	public void move(AnimationCanvas canvas) {
		
		if(!continueToMove){
			return;
		}
		

	
		frameCounter++;
		if (frameCounter < moveFrameInterval) {
			return;
		}else{
			frameCounter = 0;
		}
		
		if(isEmptyDown()){
			
			for (Point p : units){
				game.grid[p.x] [p.y] = false;
			}
			
			
			for(Point p : units){
				p.translate(0,-1);
				game.Filling(p.x,p.y);
			} 	
		
		} else {
			frameCounter++;
			if (frameCounter < moveFrameInterval) {
				continueToMove = false;
				isBlockLanded = true;

				checkFullLines();
				game.addNewShape();
				return;
			}else{
			frameCounter = 0;
			
			}
		}
		
	}

	/**this method belongs the animatable interface 
	 * it makes the object drop 
	 * 
	 */
	@Override
	public void draw(AnimationCanvas canvas) {
		if(!isBlockLanded)
		{
			for (Point p : units){
				canvas.fillGridSquare((int)p.getX(), (int)p.getY(), Color.BLUE);
			}
		}else{
			for (int x = 0; x < 10; x++) {
				for (int y = 0; y < 20; y++) {
					boolean b = game.grid[x][y];
					if(b)
					{
						canvas.fillGridSquare(x, y, Color.BLUE);
					}
				}
			}
		}
		
	}

}
