import java.util.*;
import java.awt.*;
/**
 * Write a description of class Cup here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Cup extends Item{
    private static Map<Integer,Integer> usedNumbers = new HashMap<>();
    private Lid lid;
    private boolean isCovered;
    private Rectangle cup;
    private Rectangle empty; 
    
    public Cup(int i, int index, Tower tower){
        if(!usedNumbers.containsKey(i)){
            this.setNumber(i);
            usedNumbers.put(i,index);
            this.setHeight(2*i - 1);
            this.makeInvisible();
            isCovered = false;
            createShapeCup(tower.height(),Tower.width,Tower.maxHeight);
        }
    }
    
    public void createShapeCup(int height,int width,int maxHeight){
        cup = new Rectangle();
        cup.changeColor(this.makeColor(this.getNumber()));
        cup.changeSize(this.getHeight()*10,this.getHeight()*10);
        empty = new Rectangle();
        empty.changeColor(Color.white);
        empty.changeSize((this.getHeight()*10)-10,(this.getHeight()*10)-20);
        int floor = maxHeight - height*10;
        int vertical = width/2;
        int xPosition = vertical-(this.getHeight()*5);
        int yPosition = floor - this.getHeight()*10;
        cup.setPosition(xPosition,yPosition);
        empty.setPosition(xPosition+10,yPosition);
    }
    
    public void makeVisibleShape(){
        cup.makeVisible();
        empty.makeVisible();
        this.makeVisible();
    }
    
    public void makeInvisibleShape(){
        cup.makeInvisible();
        empty.makeInvisible();
        this.makeInvisible();
    }
    
    public void remove(){
        usedNumbers.remove((Integer) this.getNumber());
        this.makeInvisibleShape();
        if(isCovered){
            lid.remove();
            lid = null;
        }
    }
    
    public static int getIndex(int i){
        return usedNumbers.get(i);
    }
    
    public static void setIndex(int i,int index){
        usedNumbers.put(i,index);
    }
    
    public static boolean containCup(int i){
        return usedNumbers.containsKey((Integer)i);
    }
    
    public void makeConvered(Lid lid){
        this.lid = lid;
        isCovered = true;
    }
    
    public void makeInconvered(){
        this.lid = null;
        isCovered = false;
    }
    
    public Lid getLid(){
        return lid;
    }
    
    public boolean isConvered(){
        return this.isCovered;
    }
}