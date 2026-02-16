import java.util.*;
/**
 * Clase de representacion de tapa.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Lid extends Item{
    
    private static Map<Integer,Integer> usedNumbers = new HashMap<>();
    private Cup cup;
    private int width;
    private Rectangle lid;
    
    /**
     * Constructor de Lid.
     * @param  cup cup de la tapa 
     * @param  index indice en la torre
     * @param  tower torre
     */
    public Lid(Cup cup,int index,Tower tower){
        if(!usedNumbers.containsKey((Integer) cup.getNumber())){
            this.makeInvisible();
            this.setHeight(1);
            this.width = cup.getHeight();
            this.setNumber(cup.getNumber());
            usedNumbers.put(cup.getNumber(),index);
            this.cup = cup;
            this.cup.makeConvered(this);
            createShapeLid(tower.height(),Tower.width,Tower.maxHeight);
        }
    }
    
    /**
     * Constructor de Lid.
     * @param  i numero 
     * @param  index indice en la torre
     * @param  tower torre
     */
    public Lid(int i,int index, Tower tower){
        if(!usedNumbers.containsKey((Integer)i)){
            this.makeInvisible();
            this.setHeight(1);
            this.width = 2*i - 1;
            this.setNumber(i);
            this.cup = null;
            usedNumbers.put(i,index);
            createShapeLid(tower.height(),Tower.width,Tower.maxHeight);
        }
    }
    
    /**
     * Creador forma de tapa.
     * @param  height altura de la torre 
     * @param  width ancho de la torre
     * @param  maxHeight altura maxima de la torre
     */
    public void createShapeLid(int height,int width,int maxHeight){
        lid = new Rectangle();
        lid.changeColor(this.makeColor(this.getNumber()));
        lid.changeSize(this.getHeight()*10,this.width*10);
        int floor = maxHeight - (height*10);
        int vertical = width/2;
        int xPosition = vertical-(this.width*5);
        int yPosition = floor - (this.getHeight()*10);
        lid.setPosition(xPosition,yPosition);
    }
    
    /**
     * Hacer visible la figura de tapa.
     */
    public void makeVisibleShape(){
        lid.makeVisible();
        this.makeVisible();
    }
    
    /**
     * Hacer invisible la figura de tapa.
     */
    public void makeInvisibleShape(){
        lid.makeInvisible();
        this.makeInvisible();
    }
    
    /**
     * Removedor de Lid.
     */
    public void remove(){
        this.makeInvisibleShape();
        usedNumbers.remove((Integer) this.getNumber());
        if(!(this.cup == null)){
            this.cup.makeInconvered();
            this.cup = null;
        }
        
    }
    
    /**
     * Obtener indice de torre de la Lid i.
     * @param  i numero
     */
    public static int getIndex(int i){
        return usedNumbers.get(i);
    }
    
    /**
     * Asignar indice de la torre de la Lid i.
     * @param  i numero 
     * @param  index indice de la torre
     */
    public static void setIndex(int i,int index){
        usedNumbers.put(i,index);
    }
    
    /**
     * Mirar contenencia de la Lid i.
     * @param  i numero 
     */
    public static boolean containLid(int i){
        return usedNumbers.containsKey((Integer)i);
    }
}