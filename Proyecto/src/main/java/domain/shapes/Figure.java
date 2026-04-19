package domain.shapes;
import java.awt.Color;
import domain.tower.*;


/**
 * Write a description of class FIgure here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Figure{
    protected int xPosition;
    protected int yPosition;
    protected Color color;
    protected boolean isVisible;
    
    /**
     * Create a new figure.
     */
    public Figure(){
        xPosition = 10;
        yPosition = 10;
        color = Color.blue;
        isVisible = false;
    }
    
    /**
     * Make this rectangle visible. If it was already visible, do nothing.
     */
    public void makeVisible(){
        isVisible = true;
        draw();
    }
    
    /**
     * Make this rectangle invisible. If it was already invisible, do nothing.
     */
    public void makeInvisible(){
        erase();
        isVisible = false;
    }
    
    /**
     * Move the rectangle a few pixels to the right.
     */
    public void moveRight(){
        moveHorizontal(20);
    }

    /**
     * Move the rectangle a few pixels to the left.
     */
    public void moveLeft(){
        moveHorizontal(-20);
    }

    /**
     * Move the rectangle a few pixels up.
     */
    public void moveUp(){
        moveVertical(-20);
    }

    /**
     * Move the rectangle a few pixels down.
     */
    public void moveDown(){
        moveVertical(20);
    }

    /**
     * Move the rectangle horizontally.
     * @param distance the desired distance in pixels
     */
    public void moveHorizontal(int distance){
        erase();
        xPosition += distance;
        draw();
    }

    /**
     * Move the rectangle vertically.
     * @param distance the desired distance in pixels
     */
    public void moveVertical(int distance){
        erase();
        yPosition += distance;
        draw();
    }

    /**
     * Slowly move the rectangle horizontally.
     * @param distance the desired distance in pixels
     */
    public void slowMoveHorizontal(int distance){
        int delta;

        if(distance < 0) {
            delta = -1;
            distance = -distance;
        } else {
            delta = 1;
        }

        for(int i = 0; i < distance; i++){
            xPosition += delta;
            draw();
        }
    }

    /**
     * Slowly move the rectangle vertically.
     * @param distance the desired distance in pixels
     */
    public void slowMoveVertical(int distance){
        int delta;

        if(distance < 0) {
            delta = -1;
            distance = -distance;
        } else {
            delta = 1;
        }

        for(int i = 0; i < distance; i++){
            yPosition += delta;
            draw();
        }
    }
    
    /**
     * Change the color. 
     * @param color the new color. Valid colors are "red", "yellow", "blue", "green",
     * "magenta" and "black".
     */
    public void changeColor(Color newColor){
        color = newColor;
        draw();
    }

    /*
     * Draw the rectangle with current specifications on screen.
     */
    protected abstract void draw();

    /*
     * Erase the figure on screen.
     */
    protected void erase(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas(Tower.width,Tower.maxHeight);
            canvas.erase(this);
        }
    }
    
    /**
     * Asignar posicion xy.
     * @param  xPosition posicion en x
     * @param  yPosition posicion en y
     */
    public void setPosition(int xPosition,int yPosition){
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }
}