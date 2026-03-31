package tower;

import java.util.List;
import java.util.Collections;
import java.awt.Color;
import shapes.Rectangle;
import shapes.Triangle;


/**
 * Write a description of class Hierarchical here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Hierarchical extends Cup{
    private Tower tower;
    private Triangle r1;
    private Triangle r2;
    public Hierarchical(int i, Tower tower){
        super(i,tower);
        this.tower = tower;
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
        empty.changeColor(Color.blue);
        r1 = new Triangle();
        r1.changeColor(Color.white);
        r1.changeSize(10,10);
        r2 = new Triangle();
        r2.changeColor(Color.white);
        r2.changeSize(10,10);
        empty.changeSize((this.getHeight()*10)-10,(this.getHeight()*10)-20);
        int floor = maxHeight - height*10;
        int vertical = width/2;
        int xPosition = vertical-(this.getHeight()*5);
        int yPosition = floor - this.getHeight()*10;
        cup.setPosition(xPosition,yPosition);
        empty.setPosition(xPosition+10,yPosition);
        r1.setPosition(xPosition+5,yPosition);
        r2.setPosition(xPosition+(this.getHeight()*10)-5,yPosition);
    }
    
    /**
     * Obtener tipo de item.
     * @return tipo de item
     */
    @Override
    public String getType(){
        return "hierarchical";
    }
    
    /**
     * Hacer visible la figura de taza.
     */
    @Override
    public void makeVisible(){
        cup.makeVisible();
        empty.makeVisible();
        r1.makeVisible();
        r2.makeVisible();
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
            r1.makeInvisible();
            r2.makeInvisible();
        }
    }
    
    @Override
    public void specialMove(List<Item> items){
        //items.remove(items.size()-1);
        Collections.reverse(items);
        for(Item i : items){
            if(i.getNumber() < this.getNumber()){
                String[] i1 = new String[] {"cup",String.valueOf(this.getNumber())};
                String[] i2 = new String[] {i.getType(),String.valueOf(i.getNumber())};
                tower.swap(i1,i2);
            }else{
                break;
            }
        }
    }
}