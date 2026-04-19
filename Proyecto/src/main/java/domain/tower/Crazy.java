package domain.tower;
import domain.shapes.*;
import java.awt.Color;
import java.util.List;


/**
 * Write a description of class Crazy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Crazy extends Lid{
    
    private Tower tower;
    private Triangle r1;
    private Triangle r2;
    
    /**
     * Constructor de Crazy.
     * @param  i numero 
     * @param  tower torre
     */
    public Crazy(int i, Tower tower){
        super(i,tower);
        this.tower = tower;
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
        r1 = new Triangle();
        r1.changeColor(Color.white);
        r1.changeSize(10,10);
        r2 = new Triangle();
        r2.changeColor(Color.white);
        r2.changeSize(10,10);
        int floor = maxHeight - (height*10);
        int vertical = width/2;
        int xPosition = vertical-(this.width*5);
        int yPosition = floor - (this.getHeight()*10);
        lid.setPosition(xPosition,yPosition);
        r1.setPosition(xPosition+5,yPosition);
        r2.setPosition(xPosition+(this.getWidth()*10)-5,yPosition);
    }
    
    /**
     * Obtener tipo de item.
     * @return tipo de item
     */
    @Override
    public String getType(){
        return "crazy";
    }
    
    /**
     * Hacer visible la figura de tapa.
     */
    @Override
    public void makeVisible(){
        lid.makeVisible();
        r1.makeVisible();
        r2.makeVisible();
    }
    
    /**
     * Hacer invisible la figura de tapa.
     */
    @Override
    public void makeInvisible(){
        isVisible = false;
        if(lid != null){
            lid.makeInvisible();
            r1.makeInvisible();
            r2.makeInvisible();
        }
    }
    
    /**
     * Hacer movimiento especial de la tapa (Al tapar su taza se ubica debajo de esta).
     * @param items copia de la lista de items de la torre.
     */
    @Override
    public void specialMove(List<Item> items){
        if(this.isCovered()){
            String[] i1 = new String[] {"cup",String.valueOf(this.getNumber())};
            String[] i2 = new String[] {"lid",String.valueOf(this.getNumber())};
            tower.swap(i1,i2);
        }
    }
}