import java.util.*;
import java.awt.Color;
import javax.swing.JOptionPane;
/**
 * Clase de representacion de Tower.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tower{
    
    public static int maxHeight;
    public static int width;
    private int height;
    private List<Integer> indexLastCups;
    private List<Integer> indexLastLids;
    private List<Item> items;
    private List<Rectangle> marks;
    private Canvas canvas;
    private boolean isVisible;
    private boolean canvasOperation;
    private boolean lastOperation;
    private static Tower towerSingleton;

    /**
     * Obtener Tower singleton.
     * @param  width ancho del canvas
     * @param  height alto del canvas
     */
    public static Tower getTower(int width, int height){
        if(towerSingleton == null) {
            towerSingleton = new Tower(width, height);
        }
        return towerSingleton;
    }
    
    /**
     * Constructor de Tower.
     * @param  width ancho del canvas
     * @param  height alto del canvas
     */
    private Tower(int width, int maxHeight){
        this.width = width;
        this.maxHeight = maxHeight;
        canvas = Canvas.getCanvas(width,maxHeight);
        canvas.setVisible(false);
        items = new ArrayList<>();
        indexLastCups = new ArrayList<>();
        indexLastLids = new ArrayList<>();
        isVisible = false;
        canvasOperation = false;
        lastOperation = true;
        marks = new ArrayList<>();
        for(int i=0;i<maxHeight;i+=10){
            Rectangle rectangle = new Rectangle();
            rectangle.changeColor(Color.black);
            rectangle.setPosition(0,i);
            rectangle.changeSize(1,10);
            marks.add(rectangle);
        }
    }
    
    /**
     * Agregar una Cup i a la torre.
     * @param  i numero.
     */
    public void pushCup(int i){
        if(!Cup.containCup(i)){
            Cup cup = new Cup(i,items.size(),this);
            items.add(cup);
            indexLastCups.add(items.size()-1);
            height += cup.getHeight();
            if(isVisible && (height+(2*i-1))*10 < maxHeight){
                cup.makeVisibleShape();
            }
            lastOperation = true;
        }else{
            lastOperation = false;
            if(isVisible) JOptionPane.showMessageDialog(null,"No se pudo crear Cup: "+i);
        }
    }
    
    /**
     * Eliminar ultima Cup.
     */
    public void popCup(){
        if(items.size() != 0 && !indexLastCups.isEmpty()){
            Cup cup = (Cup)items.get(indexLastCups.get(indexLastCups.size()-1));
            height -= cup.getHeight();
            if(cup.isConvered()){
                removeLid(cup.getNumber());
            }
            items.remove(cup);
            indexLastCups.remove((Integer) cup.getNumber());
            cup.remove();
            if(isVisible){
                canvasOperation = true;
                makeInvisible();
                isVisible = true;
            }
            indexLastCups.clear();
            indexLastLids.clear();
            height = 0;
            int i = 0;
            for (Item item : items) {
                if (item instanceof Cup) {
                    cup = (Cup) item;
                    Cup.setIndex(cup.getNumber(),i);
                    indexLastCups.add(i);
                    cup.createShapeCup(height,width,maxHeight);
                } else if (item instanceof Lid) {
                    Lid lid = (Lid) item;
                    Lid.setIndex(lid.getNumber(),i);
                    indexLastLids.add(i);
                    lid.createShapeLid(height,width,maxHeight);
                }
                height += item.getHeight();
                i++;
            }
            if(isVisible){
                makeVisible();
            }
            lastOperation = true;
        }else{
            lastOperation = false;
            if(isVisible) JOptionPane.showMessageDialog(null,"No se pudo eliminar Cup");
        }
    }
    
    /**
     * Eliminar Cup i.
     * @param  i numero.
     */
    public void removeCup(int i){
            if(Cup.containCup(i)){
                Cup cup = (Cup) items.get(Cup.getIndex(i));
                height -= cup.getHeight();
                if(cup.isConvered()){
                    removeLid(cup.getNumber());
                }
                items.remove(cup);
                indexLastCups.remove((Integer)cup.getNumber());
                cup.remove();
                if(isVisible){
                    canvasOperation = true;
                    makeInvisible();
                    isVisible = true;
                }
                indexLastCups.clear();
                indexLastLids.clear();
                height = 0;
                int in = 0;
                for (Item item : items) {
                    if (item instanceof Cup) {
                        cup = (Cup) item;
                        Cup.setIndex(cup.getNumber(),in);
                        indexLastCups.add(in);
                        cup.createShapeCup(height,width,maxHeight);
                    } else if (item instanceof Lid) {
                        Lid lid = (Lid) item;
                        Lid.setIndex(lid.getNumber(),in);
                        indexLastLids.add(in);
                        lid.createShapeLid(height,width,maxHeight);
                    }
                    height += item.getHeight();
                    in++;
                }
                if(isVisible){
                    makeVisible();
                }
                lastOperation = true;
            }else{
                lastOperation = false;
                if(isVisible) JOptionPane.showMessageDialog(null,"No se pudo eliminar Cup: "+i);
            }
    }
    
    /**
     * Agregar Lid i a la torre.
     * @param  i numero.
     */
    public void pushLid(int i){
        Lid lid = null;
        if(!Lid.containLid(i)){
            if(!items.isEmpty()){
                Item item = items.get(items.size()-1);
                if(item.getNumber() == i){
                    lid = new Lid((Cup)item,items.size(),this);
                }else{
                    lid = new Lid(i,items.size(),this);
                }
            }else{
                lid = new Lid(i,items.size(),this);
            }
            if(isVisible  && 10+(height*10) < maxHeight){
                    lid.makeVisibleShape();
            }
            items.add(lid);
            indexLastLids.add(items.size()-1);
            height += lid.getHeight();
            lastOperation = true;
        }else{
            lastOperation = false;
            if(isVisible) JOptionPane.showMessageDialog(null,"No se pudo crear Lid: "+i);
        }
    }
    
    /**
     * Eliminar ultima Lid.
     */
    public void popLid(){
        if(items.size() != 0 && !indexLastLids.isEmpty()){
            Lid lid = (Lid)items.get(indexLastLids.get(indexLastLids.size()-1));
            height -= lid.getHeight();
            items.remove(lid);
            indexLastLids.remove((Integer) lid.getNumber());
            lid.remove();
            if(isVisible){
                canvasOperation = true;
                makeInvisible();
                isVisible = true;
            }
            indexLastCups.clear();
            indexLastLids.clear();
            height = 0;
            int i = 0;
            for (Item item : items) {
                if (item instanceof Cup) {
                    Cup cup = (Cup) item;
                    Cup.setIndex(cup.getNumber(),i);
                    indexLastCups.add(i);
                    cup.createShapeCup(height,width,maxHeight);
                } else if (item instanceof Lid) {
                    lid = (Lid) item;
                    Lid.setIndex(lid.getNumber(),i);
                    indexLastLids.add(i);
                    lid.createShapeLid(height,width,maxHeight);
                }
                height += item.getHeight();
                i++;
            }
            if(isVisible){
                makeVisible();
            }
            lastOperation = true;
        }else{
            lastOperation = false;
            if(isVisible) JOptionPane.showMessageDialog(null,"No se pudo eliminar Lid");
        }
    }
    
    /**
     * Eliminar Lid i.
     * @param  i numero
     */
    public void removeLid(int i){
        if(Lid.containLid(i)){
            Lid lid = (Lid) items.get(Lid.getIndex(i));
            height -= lid.getHeight();
            items.remove(lid);
            indexLastLids.remove((Integer) lid.getNumber());
            lid.remove();
            if(isVisible){
                canvasOperation = true;
                makeInvisible();
                isVisible = true;
            }
            indexLastCups.clear();
            indexLastLids.clear();
            height = 0;
            int in = 0;
            for (Item item : items) {
                if (item instanceof Cup) {
                    Cup cup = (Cup) item;
                    Cup.setIndex(cup.getNumber(),in);
                    indexLastCups.add(in);
                    cup.createShapeCup(height,width,maxHeight);
                } else if (item instanceof Lid) {
                    lid = (Lid) item;
                    Lid.setIndex(lid.getNumber(),in);
                    indexLastLids.add(in);
                    lid.createShapeLid(height,width,maxHeight);
                }
                height += item.getHeight();
                in++;
            }
            if(isVisible){
                makeVisible();
            }
            lastOperation = true;
        }else{
            lastOperation = false;
            if(isVisible) JOptionPane.showMessageDialog(null,"No se pudo eliminar Lid: "+i);
        }
    }
    
    /**
     * Obtener altura actual de la torre.
     */
    public int height(){
        return height;
    }
    
    /**
     * Hacer visible la torre.
     */
    public void makeVisible(){
        int tempHeight = 0;
        for (Item item : items) {
            if (item instanceof Cup && (tempHeight+item.getHeight())*10 < maxHeight) {
                Cup cup = (Cup) item;
                cup.makeVisibleShape();
            } else if (item instanceof Lid && (tempHeight+item.getHeight())*10 < maxHeight) {
                Lid lid = (Lid) item;
                lid.makeVisibleShape();
            }
            tempHeight += item.getHeight();
        } 
        for(Rectangle rectangle : marks){
            rectangle.makeVisible();
        }
        isVisible = true;
        canvas.setVisible(true);
        canvasOperation = false;
        lastOperation = true;
    }
    
    /**
     * Hacer invisible la torre.
     */
    public void makeInvisible(){
        for (Item item : items) {
            if (item instanceof Cup) {
                Cup cup = (Cup) item;
                cup.makeInvisibleShape();
            } else if (item instanceof Lid) {
                Lid lid = (Lid) item;
                lid.makeInvisibleShape();
            }
        }
        for(Rectangle rectangle : marks){
            rectangle.makeInvisible();
        }
        isVisible = false;
        if(!canvasOperation){
            canvas.setVisible(false);
        }
        canvasOperation = false;
        lastOperation = true;
    }
    
    /**
     * Ordena la torre de Mayor a menor.
     */
    public void orderTower(){
        Collections.sort(items, (a, b) -> {
        if (a.getNumber() != b.getNumber()) {
            return b.getNumber() - a.getNumber();
        }
        if (a instanceof Cup) return -1;
        return 1;
        });
        if(isVisible){
            canvasOperation = true;
            makeInvisible();
            isVisible = true;
        }
        indexLastCups.clear();
        indexLastLids.clear();
        height = 0;
        int i = 0;
        int last = -1;
        Item lastItem = null;
        for (Item item : items) {
            if (item instanceof Cup) {
                Cup cup = (Cup) item;
                Cup.setIndex(cup.getNumber(),i);
                indexLastCups.add(i);
                last = cup.getNumber();
                cup.createShapeCup(height,width,maxHeight);
            } else if (item instanceof Lid) {
                Lid lid = (Lid) item;
                Lid.setIndex(lid.getNumber(),i);
                indexLastLids.add(i);
                lid.createShapeLid(height,width,maxHeight);
                if(last == lid.getNumber()){
                    Cup cup = (Cup) lastItem;
                    cup.makeConvered(lid);
                }
                last = -1;
            }
            i++;
            height += item.getHeight();
            lastItem = item;
        }
        if(isVisible){
            makeVisible();
        }
        lastOperation = true;
    }
    
    /**
     * Invierte la torre.
     */
    public void reverseTower(){
        Collections.reverse(items);
        if(isVisible){
            canvasOperation = true;
            makeInvisible();
            isVisible = true;
        }
        indexLastCups.clear();
        indexLastLids.clear();
        height = 0;
        int i = 0;
        int last = -1;
        Item lastItem = null;
        for (Item item : items) {
            if (item instanceof Cup) {
                Cup cup = (Cup) item;
                Cup.setIndex(cup.getNumber(),i);
                indexLastCups.add(i);
                last = cup.getNumber();
                cup.createShapeCup(height,width,maxHeight);
            } else if (item instanceof Lid) {
                Lid lid = (Lid) item;
                Lid.setIndex(lid.getNumber(),i);
                indexLastLids.add(i);
                lid.createShapeLid(height,width,maxHeight);
                if(last == lid.getNumber()){
                    Cup cup = (Cup) lastItem;
                    cup.makeConvered(lid);
                }
                last = -1;
            }
            i++;
            height += item.getHeight();
            lastItem = item;
        }
        if(isVisible){
            makeVisible();
        }
        lastOperation = true;
    }
    
    /**
     * Numeros de las Cups tapadas ordenadas de menor a mayor.
     */
    public int[] lidedCups(){
        int countLidedCups = 0;
        List<Integer> listLidedCups = new ArrayList<>();
        for(Item item : items){
            if(item instanceof Cup){
                Cup cup = (Cup) item;
                if(cup.isConvered()){
                   countLidedCups++;
                    listLidedCups.add(item.getNumber()); 
                }
            }
        }
        int[] resLidedCups = new int[countLidedCups];
        Collections.sort(listLidedCups);
        for(int i=0;i<countLidedCups;i++){
            resLidedCups[i] = listLidedCups.get(i);
        }
        lastOperation = true;
        return resLidedCups;
    }
    
    /**
     * Matriz de Items con su tipo y numero, desde la base a la punta.
     */
    public String[][] stackingItems(){
        String[][] resStackingItems = new String[items.size()][2];
        for(int i=0;i<items.size();i++){
            Item item = items.get(i);
            if(item instanceof Cup){
                resStackingItems[i][0] = "cup";
                resStackingItems[i][1] = String.valueOf(item.getNumber());
            }else{
                resStackingItems[i][0] = "lid";
                resStackingItems[i][1] = String.valueOf(item.getNumber());
            }
        }
        lastOperation = true;
        return resStackingItems;
    }
    
    /**
     * Obtener estado de la ultima operacion.
     */
    public boolean ok(){
        return lastOperation;
    }
    
    /**
     * Salir del simulador.
     */
    public void exit(){
        makeInvisible();
        items.clear();
        indexLastCups.clear();
        indexLastLids.clear();
    }
}