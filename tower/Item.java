package tower;
import java.awt.Color;
import java.util.List;
/**
 * Clase item para representar entidad.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Item{
    
    protected boolean isVisible;
    protected boolean isCovered;
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
     * Hacer movimiento especial del item.
     * @param items copia de la lista de items de la torre.
     */
    public abstract void specialMove(List<Item> items);
    
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
    
    /**
     * Crear figura del item.
     * @param height altura de la base
     * @param width ancho de la torre
     * @param maxHeight altura maxima de la torre
     */
    public abstract void createShape(int height,int width,int maxHeight);
    
    /**
     * Obtener si esta cubierta
     * @return si esta cubierta
     */
    public abstract boolean isCovered();
    
    /**
     * Tapar el item.
     * @param lid tapa para cubrir
     */
    public abstract void makeCovered(Lid lid);
    
    /**
     * Destapar el item.
     */
    public abstract void makeIncovered();
    
    /**
     * Eliminar la figura del item
     */
    public abstract void remove();
    
    /**
     * Cubrir el item inmediatamente anterior.
     * @param below item debajo del actual
     */
    public abstract void onStackedAbove(Item below);
    
    /**
     * Obtener tipo de item.
     * @return tipo de item
     */
    public abstract String getType();
    
    /**
     * Registrar el indice en la torre.
     * @param torre a registrar
     * @param index indice en la torre
     */
    public abstract void register(Tower tower, int index);
}