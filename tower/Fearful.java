package tower;
import shapes.Circle;
import shapes.Rectangle;
import java.awt.Color;
import java.util.List;

/**
 * Write a description of class Fearful here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Fearful extends Lid{
    private Tower tower;
    private Circle r1;
    private Circle r2;
    private boolean foundedCup;
    public Fearful(int i, Tower tower){
        super(i,tower);
        this.tower = tower;
        this.foundedCup = false;
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
        r1 = new Circle();
        r1.changeColor(Color.white);
        r1.changeSize(10);
        r2 = new Circle();
        r2.changeSize(10);
        r2.changeColor(Color.white);
        int floor = maxHeight - (height*10);
        int vertical = width/2;
        int xPosition = vertical-(this.width*5);
        int yPosition = floor - (this.getHeight()*10);
        lid.setPosition(xPosition,yPosition);
        r1.setPosition(xPosition,yPosition);
        r2.setPosition(xPosition+(this.width*10)-10,yPosition);
    }
    
    /**
     * Obtener tipo de item.
     * @return tipo de item
     */
    @Override
    public String getType(){
        return "fearful";
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
    
    @Override
    public boolean okRemove(){
        if(isCovered){
            return false;
        }
        return true;
    }
    
    @Override
    public void specialMove(List<Item> items){
        for(Item item : items){
            if(item.getNumber() == this.getNumber()){
                foundedCup = true;
            }
        }
        if(!foundedCup){
            tower.removeLid(this.getNumber());
        }
    }
}