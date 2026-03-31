package tower;

import java.util.List;
import java.util.ArrayList;
import java.awt.*;
import shapes.Rectangle;
import shapes.Circle;
/**
 * Write a description of class Opener here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Opener extends Cup{
    private Tower tower;
    private Circle r1;
    private Circle r2;
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
        r1 = new Circle();
        r1.changeColor(Color.white);
        r1.changeSize(10);
        r2 = new Circle();
        r2.changeSize(10);
        r2.changeColor(Color.white);
        empty.changeSize((this.getHeight()*10)-10,(this.getHeight()*10)-20);
        int floor = maxHeight - height*10;
        int vertical = width/2;
        int xPosition = vertical-(this.getHeight()*5);
        int yPosition = floor - this.getHeight()*10;
        cup.setPosition(xPosition,yPosition);
        empty.setPosition(xPosition+10,yPosition);
        r1.setPosition(xPosition,yPosition);
        r2.setPosition(xPosition+(this.getHeight()*10)-10,yPosition);
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
        //System.out.println(items);
        while(!items.isEmpty() && "lid".equals(items.get(items.size()-1).getType())){
            tower.removeLid(items.get(items.size()-1).getNumber());
            items.remove(items.size()-1);
        }
    }
}