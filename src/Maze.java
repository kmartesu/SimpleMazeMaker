import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Maze {
	
	private Cell[][] maze;
	private ArrayList<Wall> wallList = new ArrayList<Wall>();
	private int width;	//Maze width
	private int height; //Maze height
	
	public Maze(){
		getMazeSize();
		maze = new Cell[height][width];
		createWalls();
		makeMaze();
		printMaze(maze);
	}
	
	public static void main(String[] args){
		//Run forever
		while(true) {
			new Maze();
		}
	}

	public void union(Cell c1, Cell c2){
		//This will make union for two cells
		for(Cell c:c1.getCellSet()){
			c2.getCellSet().add(c);
			c.setCellSet(c2.getCellSet());
		} 
	}
	
	public boolean checkMaze(){
		boolean a = false;
		if(maze[0][0].getCellSet() == maze[height-1][width-1].getCellSet()){
			a = true;	
		}
		return a;
	}
	
	//This method does the main work
	//Randomnly choose walls and remove them, until all cells are in the same set
	public void makeMaze(){
		boolean done = false;
		Random rand = new Random();
		while(done != true){
			int n = rand.nextInt(wallList.size()) + 0;
			if(!wallList.get(n).getCell(0).getCellSet().contains(wallList.get(n).getCell(1)) && (wallList.get(n).isVisited == false)){
				wallList.get(n).setIsVisited();
				union(wallList.get(n).getCell(0), wallList.get(n).getCell(1));
				wallList.get(n).setIsOpen();
			}
			done = checkMaze();
		}
	}
	
	//This is just for printing the maze
	public static void printMaze(final Cell[][] maze) {
	    for (int r = 0; r < maze.length; r++) {
	        final Cell[] row = maze[r];
	        printTop(row);
	        printMiddle(row);
	        if (r == maze.length - 1) {
	            printBottom(row);
	        }
	    }
	}

	//This is just for printing the maze
	private static void printBottom(final Cell[] row) {
	    for (final Cell cell : row) {
	    	if(!cell.getWall(2).isOpen){
	    		System.out.print("+--");
	    	}
	    	else {
	        System.out.print("+  ");
	    	}
	    }
	    System.out.println("+");
	}

	//This is just for printing the maze
	private static void printMiddle(final Cell[] row) {
	    for (int c = 0; c < row.length; c++) {
	        final Cell cell = row[c];
	        System.out.print(!cell.getWall(3).isOpen ? "|  " : "   ");
	        if (c == row.length - 1) {
	            System.out.println(!cell.getWall(1).isOpen ? "|" : " ");
	        }
	    }
	}

	//This is just for printing the maze
	private static void printTop(final Cell[] row) {
	    for (final Cell cell : row) {
	        System.out.print(!cell.getWall(0).isOpen ? "+--" : "+  ");
	    }
	    System.out.println("+");
	}
	
	//Getting the maze size
	public void getMazeSize(){
		Scanner in = new Scanner(System.in);
		System.out.print("Widht of maze: ");
		width = in.nextInt();
		System.out.print("Height of maze: ");
		height = in.nextInt();
	}
	
	//Big method for creating the walls
	public void createWalls(){
		for(int i=0; i<height; i++){
			for(int j=0; j<width; j++){
				
				Cell cell = new Cell(width,height);
				maze[i][j] = cell;
				
				
				//Create uppermost wall
				if(i == 0){
					Wall top = new Wall();
					cell.createWall(0, top);
					top.setIsEdge();
				}
				else {
					cell.createWall(0, maze[i-1][j].getWall(2));
					wallList.add(maze[i-1][j].getWall(2));
				}
				//Create right wall
				if(j == width-1){
					Wall right = new Wall();
					cell.createWall(1, right);
					right.setIsEdge();
					if(i == height-1){
						right.setIsOpen();
					}
				}
				else {
					Wall right = new Wall();
					cell.createWall(1, right);
				}
				
				//Create lower wall
				if(i == height-1){
					Wall down = new Wall();
					cell.createWall(2, down);
					down.setIsEdge();
				}
				else {
					Wall down = new Wall();
					cell.createWall(2, down);
				}
				
				//Create left wall
				if(j==0){
					Wall left = new Wall();
					cell.createWall(3, left);
					left.setIsEdge();
					if(i==0){
						left.setIsOpen();
					}
				}
				else {
					cell.createWall(3, maze[i][j-1].getWall(1));
					wallList.add(maze[i][j-1].getWall(1));
				}
				cell.getCellSet().add(cell);
			}
		}
	}
}
