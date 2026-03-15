import java.util.*;
import javax.swing.JOptionPane;
/**
 * Write a description of class TowerContest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TowerContest
{
    private static long altura(List<Long> nums) {
        Deque<Long> contenedor = new ArrayDeque<>();
        Deque<Long> icontent = new ArrayDeque<>();
        long suma = 0;
        for (long cup : nums) {
            if (!contenedor.isEmpty() && contenedor.peekLast() - cup >= 0) {
                contenedor.addLast(contenedor.pollLast() - cup);
                pushBoth(contenedor, icontent, cup);
                continue;
            } else if (!contenedor.isEmpty()) {
                boolean con = false;
                while (!contenedor.isEmpty()) {
                    if (icontent.peekLast() < cup) {
                        icontent.pollLast();
                        contenedor.pollLast();
                    } else if (contenedor.peekLast() - cup >= 0) {
                        contenedor.addLast(contenedor.pollLast() - cup);
                        pushBoth(contenedor, icontent, cup);
                        con = true;
                        break;
                    } else {
                        suma += Math.abs(contenedor.peekLast() - cup);
                        pushBoth(contenedor, icontent, cup);
                        con = true;
                        break;
                    }
                }
                if (con){
                    continue;
                }
            }
            suma += cup;
            contenedor.clear();
            icontent.clear();
            pushBoth(contenedor, icontent, cup);
        }
        return suma;
    }
    
    private static void pushBoth(Deque<Long> contenedor, Deque<Long> icontent, long cup) {
        contenedor.addLast(cup - 1);
        icontent.addLast(cup);
    }
 
    private static int minimo(List<Long> nums) {
        for (int i = 0; i < nums.size() - 1; i++) {
            if (nums.get(i) < nums.get(i + 1)){
                return i;
            }
        }
        return -1;
    }
 
    private static int maximo(List<Long> nums) {
        for (int i = 0; i < nums.size() - 1; i++) {
            if (nums.get(i) > nums.get(i + 1)){
                return i;
            }
        }
        return -1;
    }
 
    private static boolean validar(List<Long> nums, long h, boolean tipo) {
        while (true) {
            if (altura(nums) == h){
                return true;
            }
            int idx = tipo ? minimo(nums) : maximo(nums);
            if (idx != -1) {
                long eliminado = nums.remove(idx);
                nums.add(eliminado);
            } else {
                break;
            }
        }
        return false;
    }
 
    private static List<Long> respond(long n, long h) {
        List<Long> nums = new ArrayList<>();
        for (long i = 1; i <= n; i++){
            nums.add(2L * i - 1);
        }
        boolean found = validar(nums, h, true);
        if (!found){
            found = validar(nums, h, false);
        }
        if (found){
            return nums;
        }else{
            return new ArrayList<>();
        }
    }
    
    public static String solve(long n, long h){
        List<Long> respond = respond(n,h);
        if(respond.isEmpty()){
            return "Imposible";
        }else{
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < respond.size(); i++) {
                if (i > 0) sb.append(" ");
                sb.append(respond.get(i));
            }
            return sb.toString();
        }
    }
    
    public static void simulate(long n, long h){
        List<Long> respond = respond(n,h);
        if(respond.isEmpty()){
            JOptionPane.showMessageDialog(null,"No se puede simular, debido a que es imposible");
        }else{
            Tower tower = new Tower(500,500);
            for(long cup : respond){
                tower.pushCup((int)((cup+1)/2));
            }
            tower.makeVisible();
        }
    }
    
}