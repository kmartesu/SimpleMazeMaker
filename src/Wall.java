import java.util.ArrayList;


public class Wall {
	boolean isOpen = false; 
    boolean isEdge = false;
    ArrayList<Cell> cells = new ArrayList<Cell>();
    boolean isVisited = false;
    
   
    
    public void setIsEdge(){ //Set wall to be an edge
    	isEdge = true;
    }
    
    public void setIsOpen(){ //set wall to be open
    	isOpen = true;
    }
    
    public boolean isEdge(){ //check if wall is an edge
    	return isEdge;
    }
    
    public void addCellToWall(Cell cell){ //Add cell to wall
    	cells.add(cell);
    }
    
    public ArrayList<Cell> getCells(){
    	return cells;
    }
    
    public Cell getCell(int i){
    	Cell c;
    	if(cells.size()==1){ //check if first cell
    		c = cells.get(0);
    	}
    	else {
    		c = cells.get(i);//else return cell
    	}
    	return c;
    }
    
    public void setIsVisited(){
    	isVisited = true;
    }
}
