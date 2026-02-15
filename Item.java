import java.awt.*;
/**
 * Write a description of class Item here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Item{
    
    private boolean isVisible;
    private int height;
    private int number;
    private Color color;
    
    public Color makeColor(int number){
        float hue = (number*0.618033f)%1.0f;
        this.color = Color.getHSBColor(hue,0.7f,0.9f);
        return color;
    }
    
    public void makeInvisible(){
        isVisible = false;
    }
    public void makeVisible(){
        isVisible = true;
    }
    public void setHeight(int height){
        this.height = height;
    }
    public int getHeight(){
        return height;
    }
    public void setNumber(int number){
        this.number = number;
    }
    public int getNumber(){
        return number;
    }
    public Color getColor(){
        return color;
    }
}