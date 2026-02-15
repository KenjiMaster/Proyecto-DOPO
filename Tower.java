import java.util.*;
import java.awt.Color;
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
    private List<Rectangle> marks;
    private Canvas canvas;
    private boolean isVisible;
    
    public Tower(int width, int maxHeight){
        this.width = width;
        this.maxHeight = maxHeight;
        canvas = Canvas.getCanvas(width,maxHeight);
        items = new ArrayList<>();
        indexLastCups = new ArrayList<>();
        indexLastLids = new ArrayList<>();
        isVisible = false;
        marks = new ArrayList<>();
        for(int i=0;i<maxHeight;i+=10){
            Rectangle rectangle = new Rectangle();
            rectangle.changeColor(Color.black);
            rectangle.setPosition(0,i);
            rectangle.changeSize(1,10);
            marks.add(rectangle);
        }
    }
    
    public void pushCup(int i){
        if(!Cup.containCup(i) && (height+(2*i-1))*10 < maxHeight){
            Cup cup = new Cup(i,items.size(),this);
            items.add(cup);
            indexLastCups.add(items.size()-1);
            height += cup.getHeight();
            if(isVisible){
                cup.makeVisibleShape();
            }
        }
    }
    
    public void popCup(){
        if(items.size() != 0 && !indexLastCups.isEmpty()){
            Cup cup = (Cup)items.get(indexLastCups.get(indexLastCups.size()-1));
            height -= cup.getHeight();
            if(cup.isConvered()){
                removeLid(cup.getNumber());
            }
            items.remove(cup);
            indexLastCups.remove(indexLastCups.size()-1);
            cup.remove();
            if(isVisible){
                makeInvisible();
                isVisible = true;
            }
            height = 0;
            for (Item item : items) {
                if (item instanceof Cup) {
                    cup = (Cup) item;
                    cup.createShapeCup(height,width,maxHeight);
                } else if (item instanceof Lid) {
                    Lid lid = (Lid) item;
                    lid.createShapeLid(height,width,maxHeight);
                }
                height += item.getHeight();
            }
            if(isVisible){
                makeVisible();
            }
        }
    }
    
    public void removeCup(int i){
            if(Cup.containCup(i)){
                Cup cup = (Cup) items.get(Cup.getIndex(i));
                height -= cup.getHeight();
                if(cup.isConvered()){
                    removeLid(cup.getNumber());
                }
                items.remove(cup);
                indexLastCups.remove(indexLastCups.size()-1);
                cup.remove();
                if(isVisible){
                    makeInvisible();
                    isVisible = true;
                }
                height = 0;
                for (Item item : items) {
                    if (item instanceof Cup) {
                        cup = (Cup) item;
                        cup.createShapeCup(height,width,maxHeight);
                    } else if (item instanceof Lid) {
                        Lid lid = (Lid) item;
                        lid.createShapeLid(height,width,maxHeight);
                    }
                    height += item.getHeight();
                }
                if(isVisible){
                    makeVisible();
                }
            }
    }
    
    
    public void pushLid(int i){
        Lid lid = null;
        if(!Lid.containLid(i) && 10 < maxHeight){
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
            if(isVisible){
                    lid.makeVisibleShape();
            }
            items.add(lid);
            indexLastLids.add(items.size()-1);
            height += lid.getHeight();
        }
    }
    
    public void popLid(){
        if(items.size() != 0 && !indexLastLids.isEmpty()){
            Lid lid = (Lid)items.get(indexLastLids.get(indexLastLids.size()-1));
            height -= lid.getHeight();
            items.remove(lid);
            indexLastLids.remove(indexLastLids.size()-1);
            lid.remove();
            if(isVisible){
                    makeInvisible();
                    isVisible = true;
                }
            height = 0;
            for (Item item : items) {
                if (item instanceof Cup) {
                    Cup cup = (Cup) item;
                    cup.createShapeCup(height,width,maxHeight);
                } else if (item instanceof Lid) {
                    lid = (Lid) item;
                    lid.createShapeLid(height,width,maxHeight);
                }
                height += item.getHeight();
            }
            if(isVisible){
                makeVisible();
            }
        }
    }
    
    public void removeLid(int i){
        if(Lid.containLid(i)){
            Lid lid = (Lid) items.get(Lid.getIndex(i));
            height -= lid.getHeight();
            items.remove(lid);
            indexLastLids.remove(indexLastLids.size()-1);
            lid.remove();
            if(isVisible){
                    makeInvisible();
                    isVisible = true;
                }
            height = 0;
            for (Item item : items) {
                if (item instanceof Cup) {
                    Cup cup = (Cup) item;
                    cup.createShapeCup(height,width,maxHeight);
                } else if (item instanceof Lid) {
                    lid = (Lid) item;
                    lid.createShapeLid(height,width,maxHeight);
                }
                height += item.getHeight();
            }
            if(isVisible){
                makeVisible();
            }
        }
    }
    
    public int height(){
        return height;
    }
    
    public void makeVisible(){
        for (Item item : items) {
            if (item instanceof Cup) {
                Cup cup = (Cup) item;
                cup.makeVisibleShape();
            } else if (item instanceof Lid) {
                Lid lid = (Lid) item;
                lid.makeVisibleShape();
            }
        } 
        for(Rectangle rectangle : marks){
            rectangle.makeVisible();
        }
        isVisible = true;
    }
    
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
    }
}