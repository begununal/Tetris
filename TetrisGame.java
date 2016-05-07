package student.unal.begun.project2;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import drawing_framework.AnimationCanvas;
import drawing_framework.GUI;



public class TetrisGame implements KeyListener{

	private static final int FRAME_RATE = 50;
	private static final int TETRIS_SPEED = 50;
	public enum Direction { LEFT, RIGHT, UP}; 
	
	private TetrisObject piece; 
	boolean[][] grid = new boolean [10][20];
	AnimationCanvas gameCanvas = new AnimationCanvas(); 
	private GUI gui;
	
	private AI ai;
	
	/**is a constructor to create a new tetrisgame for ai
	 * @param ai is an artificial Intelligent class(which is does not work :(()
	 */
	public TetrisGame(AI ai){
		this.ai=ai;
		gui = new GUI();
		//AnimationCanvas gameCanvas = new AnimationCanvas(); 
		gui.addCanvas(gameCanvas);
		
		//gameCanvas.addKeyListener(this);
		gameCanvas.setFocusable(true);
		//gameCanvas.drawGrid();
		piece = nextObject();
		
		gameCanvas.addObject(piece);
		gameCanvas.start();
	}
	
	/**a constructor for user
	 * 
	 */
	public TetrisGame(){
		gui = new GUI();
		//AnimationCanvas gameCanvas = new AnimationCanvas(); 
		gui.addCanvas(gameCanvas); 
	
		
		gameCanvas.addKeyListener(this);
		gameCanvas.setFocusable(true);
		//gameCanvas.drawGrid();
		piece = nextObject();
		
		gameCanvas.addObject(piece);
		gameCanvas.start();
	}
	

	
	/**
	 * @return current piece 
	 * that is usable
	 */
	public TetrisObject getPiece() {
		return piece;
	}



	/**
	 * @return current gui
	 */
	public GUI getGui() {
		return gui;
	}


	/**add a new random tetrisobject named piece
	 * to the gameCanvas
	 * 
	 */
	public void addNewShape(){
		
		piece = nextObject();
		gameCanvas.addObject(piece);
	}
	
	
	/**fills the boolean array grid with true if 
	 *  (x,y) coordinate is full 
	 * @param x is x coordinate of the grid in canvas
	 * @param y is y coordinate of the grid in canvas
	 */
	public void Filling( int x,int y){
		if(x>=0 && x<10 && y>=0 && y<20)
		grid[x][y] = true;
	}
	
	/**
	 * @return a random object 
	 * according to a random number 
	 * create any kind of object type
	 */
	public TetrisObject nextObject(){
		Random rand = new Random();
		int x = rand.nextInt(7);
		if(x == 0){
			return (new ObjectT(this));
		}else if(x == 1){
			return new ObjectI(this);
		}else if(x == 2){
			return new ObjectS(this);
		}else if(x == 3){
			return new ObjectZ(this);
		}else if(x == 4){
			return new ObjectL(this);
		}else if(x == 5){
			return new ObjectJ(this);
		}else{
			return new ObjectO(this);
		}
	}
	
	
	/* calls the methods from tetrisObject class 
	 *  according to keyboard press
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			for(int i=0; i<8; i++)
				piece.move(gameCanvas);
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			LeftPressed();
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			RightPressed();
		} else if(e.getKeyCode() == KeyEvent.VK_UP){
			UpPressed(); 
		} 
	}
	
	/**these methods for accessibility of these 
	 * commands from AI
	 */
	public void DownPressed(){
		for(Point p : piece.units)
		{
			p.translate(0, -1);
		}
	}
	public void RightPressed(){
		piece.moveInDirection(Direction.RIGHT);
	}
	
	public void LeftPressed(){
		piece.moveInDirection(Direction.LEFT);
	}
	
	public void UpPressed()
	{
		piece.rotation(piece.rotated(piece.units));
	}
//	public void WorksAI(){
//		if(ai != null)
//		{
//			ai.enterInput();
//		}
//		
//	}
	
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	public AI getAi() {
		return ai;
	}


	public void setAi(AI ai) {
		this.ai = ai;
	}




}
