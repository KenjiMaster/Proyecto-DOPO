package tower;

import java.util.List;
import java.util.Collections;
import java.awt.Color;
import shapes.Rectangle;


/**
 * Write a description of class Hierarchical here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Hierarchical extends Cup{
    private Tower tower;
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
        empty.changeSize((this.getHeight()*10)-10,(this.getHeight()*10)-20);
        int floor = maxHeight - height*10;
        int vertical = width/2;
        int xPosition = vertical-(this.getHeight()*5);
        int yPosition = floor - this.getHeight()*10;
        cup.setPosition(xPosition,yPosition);
        empty.setPosition(xPosition+10,yPosition);
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