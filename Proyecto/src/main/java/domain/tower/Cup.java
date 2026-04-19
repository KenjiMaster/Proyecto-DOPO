package domain.tower;
import java.util.List;
import java.awt.*;
import domain.shapes.Rectangle;
/**
 * Clase de representacion de taza.
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Cup extends Item{
    private Lid lid;
    
    protected Rectangle cup;
    protected Rectangle empty; 
    
    /**
     * Constructor de cup.
     * @param  i numero 
     * @param  tower torre
     */
    public Cup(int i, Tower tower){
        this.setNumber(i);
        this.setHeight(2*i - 1);
        this.makeInvisible();
        isCovered = false;
        createShape(tower.height(),Tower.width,Tower.maxHeight);
    }
    
    /**
     * Creador forma de taza.
     * @param  height altura de la torre 
     * @param  width ancho de la torre
     * @param  maxHeight altura maxima de la torre
     */
    @Override
    public void createShape(int height,int width,int maxHeight){
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
    @Override
    public void makeVisible(){
        cup.makeVisible();
        empty.makeVisible();
    }
    
    /**
     * Hacer invisible la figura de taza.
     */
    @Override
    public void makeInvisible(){
        isVisible = false;
        if(cup != null){
            cup.makeInvisible();
            empty.makeInvisible();
        }
    }
    
    /**
     * Removedor de Cup.
     */
    @Override
    public void remove(){
        this.makeInvisible();
        if(isCovered){
            lid.remove();
            lid = null;
        }
    }
    
    /**
     * Tapar la cup.
     * @param  lid tapa de la cup
     */
    @Override
    public void makeCovered(Lid lid){
        this.lid = lid;
        this.lid.makeCovered(lid);
        empty.changeColor(Color.LIGHT_GRAY);
        isCovered = true;
    }
    
    /**
     * Destapar la cup.
     */
    @Override
    public void makeIncovered(){
        this.lid.makeIncovered();
        this.lid = null;
        empty.changeColor(Color.WHITE);
        isCovered = false;
    }
    
    /**
     * Mirar si la Cup esta tapada.
     * @return valor booleano de covertura
     */
    @Override
    public boolean isCovered(){
        return this.isCovered;
    }
    
    /**
     * Cubrir el item inmediatamente anterior.
     * @param below item debajo del actual
     */
    @Override
    public void onStackedAbove(Item below){
        if(isCovered){
            this.makeIncovered();
        }
    }
    
    /**
     * Hacer movimiento especial de la taza.
     * @param items copia de la lista de items de la torre.
     */
    @Override
    public void specialMove(List<Item> items){
        
    }
    
    /**
     * Obtener tipo de item.
     * @return tipo de item
     */
    @Override
    public String getType(){
        return "cup";
    }
    
    /**
     * Registrar el indice en la torre.
     * @param torre a registrar
     * @param index indice en la torre
     */
    @Override
    public void register(Tower tower, int index){
        tower.registerCup(this.getNumber(), index);
    }
}