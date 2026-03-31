package tower;
import java.util.*;
import shapes.Rectangle;
/**
 * Clase de representacion de tapa.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Lid extends Item{
    
    private Cup cup;
    protected int width;
    protected Rectangle lid;
    
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
        this.isCovered = false;
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
    
    /**
     * Obtener si esta cubierta
     * @return si esta cubierta
     */
    @Override
    public boolean isCovered(){
        return isCovered;
    }
    
    /**
     * Tapar la cup.
     * @param  lid tapa de la cup
     */
    @Override
    public void makeCovered(Lid lid){
        isCovered = true;
    }
    
    /**
     * Destapar la cup.
     */
    @Override
    public void makeIncovered(){
        isCovered = false;
    }
    
    /**
     * Cubrir el item inmediatamente anterior.
     * @param below item debajo del actual
     */
    @Override
    public void onStackedAbove(Item below){
        if (below instanceof Cup && below.getNumber() == this.getNumber()){
            ((Cup) below).makeCovered(this);
        }
    }
    
    /**
     * Obtener tipo de item.
     * @return tipo de item
     */
    @Override
    public String getType(){
        return "lid";
    }
    
    public boolean okRemove(){
        return true;
    }
    
    @Override
    public void specialMove(List<Item> items){
        
    }
    
    /**
     * Registrar el indice en la torre.
     * @param torre a registrar
     * @param index indice en la torre
     */
    @Override
    public void register(Tower tower, int index){
        tower.registerLid(this.getNumber(), index);
    }
}