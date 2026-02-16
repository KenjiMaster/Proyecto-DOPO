import java.util.*;
import java.awt.*;
/**
 * Clase de representacion de taza.
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
    
    /**
     * Constructor de cup.
     * @param  i numero 
     * @param  index indice en la torre
     * @param  tower torre
     */
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
    
    /**
     * Creador forma de taza.
     * @param  height altura de la torre 
     * @param  width ancho de la torre
     * @param  maxHeight altura maxima de la torre
     */
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
    
    /**
     * Hacer visible la figura de taza.
     */
    public void makeVisibleShape(){
        cup.makeVisible();
        empty.makeVisible();
        this.makeVisible();
    }
    
    /**
     * Hacer invisible la figura de taza.
     */
    public void makeInvisibleShape(){
        cup.makeInvisible();
        empty.makeInvisible();
        this.makeInvisible();
    }
    
    /**
     * Removedor de Cup.
     */
    public void remove(){
        usedNumbers.remove((Integer) this.getNumber());
        this.makeInvisibleShape();
        if(isCovered){
            lid.remove();
            lid = null;
        }
    }
    
    /**
     * Obtener indice de torre de la Cup i.
     * @param  i numero
     */
    public static int getIndex(int i){
        return usedNumbers.get(i);
    }
    
    /**
     * Asignar indice de la torre de la Cup i.
     * @param  i numero 
     * @param  index indice de la torre
     */
    public static void setIndex(int i,int index){
        usedNumbers.put(i,index);
    }
    
    /**
     * Mirar contenencia de la Cup i.
     * @param  i numero 
     */
    public static boolean containCup(int i){
        return usedNumbers.containsKey((Integer)i);
    }
    
    /**
     * Tapar la cup.
     * @param  lid tapa de la cup
     */
    public void makeConvered(Lid lid){
        this.lid = lid;
        isCovered = true;
    }
    
    /**
     * Destapar la cup.
     */
    public void makeInconvered(){
        this.lid = null;
        isCovered = false;
    }
    
    /**
     * Mirar si la Cup esta tapada.
     */
    public boolean isConvered(){
        return this.isCovered;
    }
}