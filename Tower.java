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
     * @return objeto torre
     */
    public static Tower getTower(int width, int height){
        if(towerSingleton == null) {
            towerSingleton = new Tower(width, height);
        }
        return towerSingleton;
    }
    
    /**
     * Obtener Tower singleton.
     * @param  width ancho del canvas
     * @param  height alto del canvas
     * @param  cups cantidad de copas iniciales
     * @return objeto torre
     */
    public static Tower getTower(int width, int height, int cups){
        if(towerSingleton == null) {
            towerSingleton = new Tower(width, height, cups);
        }
        return towerSingleton;
    }
    
    /**
     * Constructor de Tower.
     * @param  width ancho del canvas
     * @param  height alto del canvas
     * @param  cups cantidad de copas iniciales
     */
    private Tower(int width, int maxHeight, int cups){
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
        for(int i=1; i<=cups; i++) pushCup(i);
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
            if(isVisible && (height+(2*i-1))*10 < maxHeight){
                cup.makeVisibleShape();
            }
            height += cup.getHeight();
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
            redraw();
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
                redraw();
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
            if(isVisible  && 10+(height*10) < maxHeight && lid.getWidth()*10 < width){
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
            redraw();
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
            redraw();
            lastOperation = true;
        }else{
            lastOperation = false;
            if(isVisible) JOptionPane.showMessageDialog(null,"No se pudo eliminar Lid: "+i);
        }
    }
    
    /**
     * Obtener altura actual de la torre.
     * @return numero de altura de la torre
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
        redraw();
        lastOperation = true;
    }
    
    /**
     * Invierte la torre.
     */
    public void reverseTower(){
        Collections.reverse(items);
        redraw();
        lastOperation = true;
    }
    
    /**
     * Redibujado de la torre.
     */
    private void redraw(){
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
        
    }
    
    /**
     * Numeros de las Cups tapadas ordenadas de menor a mayor.
     * @return arreglo de los numeros de las tazas tapadas.
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
     * @param matriz con los Items
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
     * Intercambio de items de la torre.
     * @param o1 item 1
     * @param o2 item 2
     */
    public void swap(String[] o1, String[] o2){
        int indexItem1 = findIndexItem(o1[0],o1[1]);
        int indexItem2 = findIndexItem(o2[0],o2[1]);
        Item item1 = items.get(indexItem1);
        Item item2 = items.get(indexItem2);
        items.set(indexItem1,item2);
        items.set(indexItem2,item1);
        redraw();
    }
    
    /**
     * Encontrar el indice de un item en la torre.
     * @param type tipo de item cup/lid
     * @param number numero del item
     */
    private int findIndexItem(String type, String number){
        if(type.equals("cup")){
            return Cup.getIndex(Integer.valueOf(number));
        }else{
            return Lid.getIndex(Integer.valueOf(number));
        }
    }
    
    /**
     * Obtener estado de la ultima operacion.
     * @return valor booleano de la ultima operacion
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