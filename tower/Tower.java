package tower;
import shapes.*;
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
    private boolean applyingSpecialMoves = false;
    
    
    
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
            pushCup("normal",i);
        }
    }
    
    /**
     * Agregar una Cup i a la torre.
     * @param  i numero.
     */
    public void pushCup(String type,int i){
        if(!cupIndex.containsKey((Integer)i)){
            Cup cup = getTypeCup(type,i);
            cupIndex.put(i,items.size());
            items.add(cup);
            indexLastCups.add(items.size()-1);
            if(isVisible && (height+(2*i-1))*10 < maxHeight){
                cup.makeVisible();
            }
            height += cup.getHeight();
            redraw();
            lastOperation = true;
        }else{
            lastOperation = false;
            if(isVisible) JOptionPane.showMessageDialog(null,"No se pudo crear Cup: "+i);
        }
    }
    
    private Cup getTypeCup(String type, int i){
        Cup cup = null;
        if(type.equals("opener")){
            cup = new Opener(i,this);
        }else if(type.equals("hierarchical")){
            cup = new Hierarchical(i,this);
        }
        else{
            cup = new Cup(i,this);
        }
        return cup;
    }
    
    /**
     * Eliminar ultima Cup.
     */
    public void popCup(){
        if(!indexLastCups.isEmpty()){
            Cup cup = (Cup)items.get(indexLastCups.get(indexLastCups.size()-1));
            height -= cup.getHeight();
            if(cup.isCovered()){
                cup.makeIncovered();
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
                    cup.makeIncovered();
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
    public void pushLid(String type,int i){
        if(!lidIndex.containsKey(i)){
            Lid lid = getTypeLid(type,i);
            if(isVisible  && 10+(height*10) < maxHeight && lid.getWidth()*10 < width){
                    lid.makeVisible();
            }
            lidIndex.put(i,items.size());
            items.add(lid);
            indexLastLids.add(items.size()-1);
            height += lid.getHeight();
            redraw();
            lastOperation = true;
        }else{
            lastOperation = false;
            if(isVisible) JOptionPane.showMessageDialog(null,"No se pudo crear Lid: "+i);
        }
    }
    
    private Lid getTypeLid(String type, int i){
        Lid lid = null;
        if(type.equals("fearful")){
            lid = new Fearful(i,this);
        }else if(type.equals("crazy")){
            lid = new Crazy(i,this);
        }else if(type.equals("rainbow")){
            lid = new Rainbow(i,this);
        }
        else{
            lid = new Lid(i,this);
        }
        return lid;
    }
    
    /**
     * Eliminar ultima Lid.
     */
    public void popLid(){
        if(!indexLastLids.isEmpty() && ((Lid)items.get(indexLastLids.get(indexLastLids.size()-1))).okRemove()){
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
        if(lidIndex.containsKey(i) && ((Lid) items.get(lidIndex.get(i))).okRemove() ){
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
        if (a.getType().equals("cup") || a.getType().equals("opener") || a.getType().equals("hierarchical")) return -1;
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
    private void reorden(){
        if(isVisible){
            canvasOperation = true;
            makeInvisible();
            isVisible = true;
        }
        recalculado();
        if(isVisible){
            makeVisible();
        }
    }
    
    private void recalculado(){
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
            }else if(item.isCovered()){
                item.makeIncovered();
            }
            height += item.getHeight();
            lastItem = item;
        }
    }
    
    private void redraw(){
        if(!applyingSpecialMoves){
            reorden();
            applicationSpecialMoves();
        }
        reorden();
    }
    
    private void applicationSpecialMoves(){
        applyingSpecialMoves = true;
        for(int i = 0; i < items.size(); i++){
            Item item = items.get(i);
            item.specialMove(new ArrayList<>(items.subList(0,i)));
        }
        applyingSpecialMoves = false;
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
        if(type.equals("cup") || type.equals("opener") || type.equals("hierarchical")){
            if(!cupIndex.containsKey(Integer.valueOf(number))){
                return null;
            }
            return  cupIndex.get(Integer.valueOf(number));
        }else{
            if(!lidIndex.containsKey(Integer.valueOf(number))){
                return null;
            }
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
            if(item instanceof Cup){
                numberCups.add(item.getNumber());
            }else {
                numberLids.add(item.getNumber());
            } 
        }
        if(isVisible){
            canvasOperation = true;
            makeInvisible();
            isVisible = true;
        }
        clearTower();
        for(Item item : oldItems){
            if(item instanceof Cup){
                pushCup(item.getType(),item.getNumber());
                if(numberLids.contains(item.getNumber())){
                    pushLid(searchLidType(item.getNumber(),oldItems),item.getNumber());
                    numberLids.remove((Integer)item.getNumber());
                }
            }else{
                if(numberLids.contains(item.getNumber()) && !numberCups.contains(item.getNumber())){
                    pushLid(item.getType(),item.getNumber());
                }
            }
        }
        redraw();
        lastOperation = true;
    }
    
    private String searchLidType(int i,List<Item> it){
        for(Item item : it){
            if(item instanceof Lid && item.getNumber()==i){
                return item.getType();
            }
        }
        return "normal";
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
    
    /**
     * Registrar el indice en la torre de una cup.
     * @param number a registrar
     * @param index indice en la torre
     */
    public void registerCup(int number, int index){
        cupIndex.put(number, index);
        indexLastCups.add(index);
    }
    
    /**
     * Registrar el indice en la torre de una lid.
     * @param number a registrar
     * @param index indice en la torre
     */
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