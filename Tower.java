import java.util.*;
/**
 * Write a description of class Tower here.
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
    private Canvas canvas;
    
    public Tower(int width, int maxHeight){
        this.width = width;
        this.maxHeight = maxHeight;
        canvas = Canvas.getCanvas(width,maxHeight);
        items = new ArrayList<>();
        indexLastCups = new ArrayList<>();
        indexLastLids = new ArrayList<>();
    }
    
    public void pushCup(int i){
        if(!Cup.containCup(i)){
            Cup cup = new Cup(i,items.size());
            items.add(cup);
            indexLastCups.add(items.size()-1);
            height += cup.getHeight();
        }
    }
    
    public void popCup(){
        if(items.size() != 0 && !indexLastCups.isEmpty()){
            Cup cup = (Cup)items.get(indexLastCups.get(indexLastCups.size()-1));
            height -= cup.getHeight();
            if(cup.isConvered()){
                removeLid(cup.getNumber());
            }
            cup.remove();
            items.remove(indexLastCups.get(indexLastCups.size()-1));
            indexLastCups.remove(indexLastCups.size()-1);
        }
    }
    
    public void removeCup(int i){
            if(Cup.containCup(i)){
                Cup cup = (Cup) items.get(Cup.getIndex(i));
                height -= cup.getHeight();
                if(cup.isConvered()){
                    removeLid(cup.getNumber());
                }
                cup.remove();
                items.remove(indexLastCups.get(indexLastCups.size()-1));
                indexLastCups.remove(indexLastCups.size()-1);
            }
    }
    
    
    public void pushLid(int i){
        Lid lid = null;
        if(!items.isEmpty() && !Lid.containLid(i)){
            Item item = items.get(items.size()-1);
            if(item.getNumber() == i){
                lid = new Lid((Cup)item,items.size());
            }
        }else{
            lid = new Lid(i,items.size());
        }
        items.add(lid);
        indexLastLids.add(items.size()-1);
        height += lid.getHeight();
    }
    
    public void popLid(){
        if(items.size() != 0 && !indexLastLids.isEmpty()){
            Lid lid = (Lid)items.get(indexLastLids.get(indexLastLids.size()-1));
            height -= lid.getHeight();
            lid.remove();
            items.remove(indexLastLids.get(indexLastLids.size()-1));
            indexLastLids.remove(indexLastLids.size()-1);
        }
    }
    
    public void removeLid(int i){
        if(Lid.containLid(i)){
            Lid lid = (Lid) items.get(Lid.getIndex(i));
            height -= lid.getHeight();
            lid.remove();
            items.remove(indexLastLids.get(indexLastLids.size()-1));
            indexLastLids.remove(indexLastLids.size()-1);
        }
    }
    
    public int height(){
        return height;
    }
}