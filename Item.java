
/**
 * Write a description of class Item here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Item{
    
    private boolean isVisible;
    private int height;
    private int number;
    
    public void makeInvisible(){
        isVisible = false;
    }
    public void makeVisible(){
        isVisible = true;
    }
    public void setHeight(int height){
        this.height = height;
    }
    public int getHeight(){
        return height;
    }
    public void setNumber(int number){
        this.number = number;
    }
    public int getNumber(){
        return number;
    }
}