import java.awt.*;
/**
 * Clase item para representar entidad.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Item{
    
    protected boolean isVisible;
    private int height;
    private int number;
    private Color color;
    
    /**
     * Generador de color mediante proporcion aurea.
     * @param  number numero 
     * @return color
     */
    public Color makeColor(int number){
        float hue = (number*0.618033f)%1.0f;
        this.color = Color.getHSBColor(hue,0.7f,0.9f);
        return color;
    }
    
    /**
     * Hacer invisible.
     */
    public abstract void makeInvisible();
    
    /**
     * Hacer visible.
     */
    public abstract void makeVisible();
    
    /**
     * Asignar algtura.
     * @param  height altura a asignar
     */
    public void setHeight(int height){
        this.height = height;
    }
    
    /**
     * Obtener altura.
     * @return numero con la altura del item
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
     * @return numero i
     */
    public int getNumber(){
        return number;
    }
    
    public abstract void createShape(int height,int width,int maxHeight);
    
    public abstract boolean isCovered();
    
    public abstract void remove();
    
    public abstract void onStackedAbove(Item below);
    
    public abstract String getType();
    
    public abstract void register(Tower tower, int index);
}