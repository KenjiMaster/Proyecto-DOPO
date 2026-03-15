import java.util.*;
/**
 * Clase de representacion de tapa.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Lid extends Item{
    
    private Cup cup;
    private int width;
    private Rectangle lid;
    
    /**
     * Constructor de Lid.
     * @param  cup cup de la tapa 
     * @param  index indice en la torre
     * @param  tower torre
     */
    public Lid(Cup cup,Tower tower){
        this.makeInvisible();
        this.setHeight(1);
        this.width = cup.getHeight();
        this.setNumber(cup.getNumber());
        //usedNumbers.put(cup.getNumber(),index);
        this.cup = cup;
        this.cup.makeCovered(this);
        createShape(tower.height(),Tower.width,Tower.maxHeight);
    }
    
    /**
     * Constructor de Lid.
     * @param  i numero 
     * @param  index indice en la torre
     * @param  tower torre
     */
    public Lid(int i, Tower tower){
        this.makeInvisible();
        this.setHeight(1);
        this.width = 2*i - 1;
        this.setNumber(i);
        this.cup = null;
        //usedNumbers.put(i,index);
        createShape(tower.height(),Tower.width,Tower.maxHeight);
    }
    
    /**
     * Creador forma de tapa.
     * @param  height altura de la torre 
     * @param  width ancho de la torre
     * @param  maxHeight altura maxima de la torre
     */
    @Override
    public void createShape(int height,int width,int maxHeight){
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
     * Obtener el ancho de la tapa.
     * @return numero del ancho
     */
    public int getWidth(){
        return width;
    }
    
    /**
     * Hacer visible la figura de tapa.
     */
    @Override
    public void makeVisible(){
        lid.makeVisible();
    }
    
    /**
     * Hacer invisible la figura de tapa.
     */
    @Override
    public void makeInvisible(){
        isVisible = false;
        if(lid != null){
            lid.makeInvisible();
        }
    }
    
    /**
     * Removedor de Lid.
     */
    @Override
    public void remove(){
        this.makeInvisible();
        //usedNumbers.remove((Integer) this.getNumber());
        if(!(this.cup == null)){
            this.cup.makeIncovered();
            this.cup = null;
        }
        
    }
    
    @Override
    public boolean isCovered(){
        return false;
    }
    
    @Override
    public void onStackedAbove(Item below){
        if (below instanceof Cup && below.getNumber() == this.getNumber()){
            ((Cup) below).makeCovered(this);
        }
        
    }
    
    @Override
    public String getType(){
        return "lid";
    }
    
    @Override
    public void register(Tower tower, int index){
        tower.registerLid(this.getNumber(), index);
    }
}