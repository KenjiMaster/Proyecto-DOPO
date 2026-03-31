package tower;

import java.util.List;
import java.util.ArrayList;
import java.awt.*;
import shapes.Rectangle;
/**
 * Write a description of class Opener here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Opener extends Cup{
    private Tower tower;
    public Opener(int i, Tower tower){
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
        empty.changeColor(Color.yellow);
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
        //System.out.println(items);
        while(!items.isEmpty() && "lid".equals(items.get(items.size()-1).getType())){
            tower.removeLid(items.get(items.size()-1).getNumber());
            items.remove(items.size()-1);
        }
    }
}