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
    private Map<Integer,Integer> cupIndex;
    private Map<Integer,Integer> lidIndex;
    private List<Integer> indexLastCups;
    private List<Integer> indexLastLids;
    private List<Item> items;
    private List<Rectangle> marks;
    private Canvas canvas;
    private boolean isVisible;
    private boolean canvasOperation;
    private boolean lastOperation;
    
    
    
    /**
     * Constructor de Tower.
     * @param  width ancho del canvas
     * @param  height alto del canvas
     */
    public Tower(int width, int maxHeight){
        this.width = width;
        this.maxHeight = maxHeight;
        canvas = Canvas.getCanvas(width,maxHeight);
        canvas.setVisible(false);
        items = new ArrayList<>();
        indexLastCups = new ArrayList<>();
        indexLastLids = new ArrayList<>();
        cupIndex = new HashMap<>();
        lidIndex = new HashMap<>();
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
     * Constructor de Tower.
     * @param  cups cantidad de copas iniciales
     */
    public Tower(int cups){
        this(300,300);
        for(int i=1; i<=cups; i++){
            pushCup(i);
        }
    }
    
    /**
     * Agregar una Cup i a la torre.
     * @param  i numero.
     */
    public void pushCup(int i){
        if(!cupIndex.containsKey((Integer)i)){
            Cup cup = new Cup(i,this);
            cupIndex.put(i,items.size());
            items.add(cup);
            indexLastCups.add(items.size()-1);
            if(isVisible && (height+(2*i-1))*10 < maxHeight){
                cup.makeVisible();
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
        if(!indexLastCups.isEmpty()){
            Cup cup = (Cup)items.get(indexLastCups.get(indexLastCups.size()-1));
            height -= cup.getHeight();
            if(cup.isCovered()){
                removeLid(cup.getNumber());
            }
            items.remove(cup);
            indexLastCups.remove((Integer) cup.getNumber());
            cupIndex.remove((Integer) cup.getNumber());
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
            if(cupIndex.containsKey(i)){
                Cup cup = (Cup) items.get(cupIndex.get(i));
                height -= cup.getHeight();
                if(cup.isCovered()){
                    removeLid(cup.getNumber());
                }
                items.remove(cup);
                indexLastCups.remove((Integer)cup.getNumber());
                cupIndex.remove((Integer) cup.getNumber());
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
        if(!lidIndex.containsKey(i)){
            if(!items.isEmpty()){
                Item item = items.get(items.size()-1);
                if(item.getNumber() == i){
                    lid = new Lid((Cup)item,this);
                }else{
                    lid = new Lid(i,this);
                }
            }else{
                lid = new Lid(i,this);
            }
            if(isVisible  && 10+(height*10) < maxHeight && lid.getWidth()*10 < width){
                    lid.makeVisible();
            }
            lidIndex.put(i,items.size());
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
        if(!indexLastLids.isEmpty()){
            Lid lid = (Lid)items.get(indexLastLids.get(indexLastLids.size()-1));
            height -= lid.getHeight();
            items.remove(lid);
            indexLastLids.remove((Integer) lid.getNumber());
            lidIndex.remove((Integer) lid.getNumber());
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
        if(lidIndex.containsKey(i)){
            Lid lid = (Lid) items.get(lidIndex.get(i));
            height -= lid.getHeight();
            items.remove(lid);
            indexLastLids.remove((Integer) lid.getNumber());
            lidIndex.remove((Integer) lid.getNumber());
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
            if ((tempHeight+item.getHeight())*10 < maxHeight) {
               item.makeVisible(); 
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
            item.makeInvisible();
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
        if (a.getType().equals("cup")) return -1;
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
        cupIndex.clear();
        lidIndex.clear();
        height = 0;
        Item lastItem = null;
        for(int i = 0; i < items.size(); i++){
            Item item = items.get(i);
            item.register(this, i);
            item.createShape(height, width, maxHeight);
            if(lastItem != null){
                item.onStackedAbove(lastItem);
            }
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
            if(item.isCovered()){
                countLidedCups++;
                listLidedCups.add(item.getNumber()); 
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
            resStackingItems[i][0] = item.getType();
            resStackingItems[i][1] = String.valueOf(item.getNumber());
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
        if(!items.isEmpty()){
            Integer indexItem1 = findIndexItem(o1[0],o1[1]);
            Integer indexItem2 = findIndexItem(o2[0],o2[1]);
            if(indexItem1 == null || indexItem2 == null){
                lastOperation = false ;
                if(isVisible){
                    JOptionPane.showMessageDialog(null,"No se encontraron los elementos");
                }
            }
            else{
                Item item1 = items.get(indexItem1);
                Item item2 = items.get(indexItem2);
                items.set(indexItem1,item2);
                items.set(indexItem2,item1);
                redraw();
                lastOperation = true; 
            }
        }else{
            lastOperation = false;
            if(isVisible){
                JOptionPane.showMessageDialog(null,"No hay elementos en la torre"); 
            }
        }
    }
    
    /**
     * Encontrar el indice de un item en la torre.
     * @param type tipo de item cup/lid
     * @param number numero del item
     */
    private Integer findIndexItem(String type, String number){
        if(type.equals("cup")){
            if(!cupIndex.containsKey(Integer.valueOf(number))) return null;
            return  cupIndex.get(Integer.valueOf(number));
        }else{
            if(!lidIndex.containsKey(Integer.valueOf(number))) return null;
            return lidIndex.get(Integer.valueOf(number));
        }
    }
    
    /**
     * Cubrir tazas si la torre tiene sus tapas.
     */
    public void cover(){
        List<Integer> numberLids = new ArrayList<>();
        List<Integer> numberCups = new ArrayList<>();
        List<Item> oldItems = new ArrayList<>(items);
        for(Item item : items){
            if(item instanceof Cup) numberCups.add(item.getNumber());
            else numberLids.add(item.getNumber()); 
        }
        if(isVisible){
            canvasOperation = true;
            makeInvisible();
            isVisible = true;
        }
        clearTower();
        for(Item item : oldItems){
            if(item instanceof Cup){
                pushCup(item.getNumber());
                if(numberLids.contains(item.getNumber())){
                    pushLid(item.getNumber());
                    numberLids.remove((Integer)item.getNumber());
                }
            }else{
                if(numberLids.contains(item.getNumber()) && !numberCups.contains(item.getNumber())) pushLid(item.getNumber());
            }
        }
        redraw();
        lastOperation = true;
    }
    
    /**
     * Limpiar todos los contenedores
     */
    private void clearTower(){
        items.clear();
        indexLastLids.clear();
        indexLastCups.clear();
        cupIndex.clear();
        lidIndex.clear();
    }
    
    /**
     * Aconsejar cambio para reducir el tamaño de la torre.
     * @return pareja a itercambiar o null si no hay cambio.
     */
    public String[][] swapToReduce() {
        for (int i = 0; i < items.size() - 1; i++) {
            Item first = items.get(i);
            Item second = items.get(i + 1);
            if (second instanceof Cup && first.getNumber() < second.getNumber()) {
                String firstType = first instanceof Cup ? "cup" : "lid";
                String[][] result = {
                    {firstType, String.valueOf(first.getNumber())},
                    {"cup", String.valueOf(second.getNumber())}
                };
                lastOperation = true;
                return result;
            }
        }
        lastOperation = false;
        return null;
    }
    
    public void registerCup(int number, int index){
        cupIndex.put(number, index);
        indexLastCups.add(index);
    }
    
    public void registerLid(int number, int index){
        lidIndex.put(number, index);
        indexLastLids.add(index);
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
        clearTower();
    }
}