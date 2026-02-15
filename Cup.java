import java.util.*;
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
    
    public Cup(int i, int index){
        if(!usedNumbers.containsKey(i)){
            this.setNumber(i);
            usedNumbers.put(i,index);
            this.setHeight(2*i - 1);
            this.makeInvisible();
            isCovered = false;
        }
    }
    
    public void remove(){
        usedNumbers.remove((Integer) this.getNumber());
        this.makeInvisible();
        if(isCovered){
            lid.remove();
            lid = null;
        }
    }
    
    public static int getIndex(int i){
        return usedNumbers.get(i);
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