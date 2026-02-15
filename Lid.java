import java.util.*;
/**
 * Write a description of class Lid here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Lid extends Item{
    private static Map<Integer,Integer> usedNumbers = new HashMap<>();
    private Cup cup;
    private int width;
        
    public Lid(Cup cup,int index){
        if(!usedNumbers.containsKey((Integer) cup.getNumber())){
            this.makeInvisible();
            this.setHeight(1);
            this.width = cup.getHeight();
            this.setNumber(cup.getNumber());
            usedNumbers.put(cup.getNumber(),index);
            this.cup = cup;
            this.cup.makeConvered(this);
        }
    }
    
    public Lid(int i,int index){
        if(!usedNumbers.containsKey((Integer)i)){
            this.makeInvisible();
            this.setHeight(1);
            this.width = 2*i - 1;
            this.setNumber(i);
            this.cup = null;
            usedNumbers.put(i,index); 
        }
    }
    
    public void remove(){
        this.makeInvisible();
        usedNumbers.remove((Integer) this.getNumber());
        if(!(this.cup == null)){
            this.cup.makeInconvered();
            this.cup = null;
        }
        
    }
    
    public static int getIndex(int i){
        return usedNumbers.get(i);
    }
    
    public static boolean containLid(int i){
        return usedNumbers.containsKey((Integer)i);
    }
}