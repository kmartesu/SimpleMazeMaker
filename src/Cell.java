import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class Cell {
	private Wall[] wall = new Wall[4]; // 0 is up, 1 is right, 2 is down, 3 is left (count clockwise)
    boolean isInMaze = false;
    private Set<Cell> cellSet = new HashSet<Cell>(); //use Set to distinguish unique cells
    int x;
    int y;
    
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public Wall getWall(int i){
    	return wall[i];
    }
    
    public void createWall(int j, Wall newWall){
    	wall[j] =  newWall;
    	newWall.addCellToWall(this);
    }
    
    public Set<Cell> getCellSet(){
    	return cellSet;
    }
    
    public void setCellSet(Set<Cell> newSet){
    	cellSet = newSet;
    }
    
    public void addCell(Cell c){
    	cellSet.add(c);
    }
}
