import java.awt.*;
/**
 * Clase item para representar entidad.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Item{
    
    private boolean isVisible;
    private int height;
    private int number;
    private Color color;
    
    /**
     * Generador de color mediante proporcion aurea.
     * @param  number numero 
     */
    public Color makeColor(int number){
        float hue = (number*0.618033f)%1.0f;
        this.color = Color.getHSBColor(hue,0.7f,0.9f);
        return color;
    }
    
    /**
     * Hacer invisible.
     */
    public void makeInvisible(){
        isVisible = false;
    }
    
    /**
     * Hacer visible.
     */
    public void makeVisible(){
        isVisible = true;
    }
    
    /**
     * Asignar algtura.
     * @param  height altura a asignar
     */
    public void setHeight(int height){
        this.height = height;
    }
    
    /**
     * Obtener altura.
     */
    public int getHeight(){
        return height;
    }
    
    /**
     * Asignar numero.
     * @param  number numero a asignar
     */
    public void setNumber(int number){
        this.number = number;
    }
    
    /**
     * Obtener numero.
     */
    public int getNumber(){
        return number;
    }
}