package domain.tower;
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
    /**
     * Calcular altura de una serie de tazas.
     * @param nums valores de las tazas
     * @return numero con la altura
     */
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
    
    /**
     * Agreagar taza en ambos contenedores.
     * @param contenedor pila de valor de espacio de contenedores
     * @param icontent pila con el valor de las tazas
     * @param cup taza a almacenar
     */
    private static void pushBoth(Deque<Long> contenedor, Deque<Long> icontent, long cup) {
        contenedor.addLast(cup - 1);
        icontent.addLast(cup);
    }
    
    /**
     * Encontrar indice del cambio del numero minimo.
     * @param nums secuencia de numeros de tazas
     * @return indice del swap encontrado si no -1
     */
    private static int minimo(List<Long> nums) {
        for (int i = 0; i < nums.size() - 1; i++) {
            if (nums.get(i) < nums.get(i + 1)){
                return i;
            }
        }
        return -1;
    }
    
    /**
     * Encontrar indice del cambio del numero maximo.
     * @param nums secuencia de numeros de tazas
     * @return indice del swap encontrado si no -1
     */
    private static int maximo(List<Long> nums) {
        for (int i = 0; i < nums.size() - 1; i++) {
            if (nums.get(i) > nums.get(i + 1)){
                return i;
            }
        }
        return -1;
    }
    
    /**
     * Validar permutaciones de altura.
     * @param nums secuencia de numeros de tazas
     * @param h altura deseada
     * @param tipo debe ser true para minimo y false para maximo
     * @return verdadero si se valido caso contrario false
     */
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
    
    /**
     * Obtener permutacion de solucion.
     * @param n numero de tazas
     * @param h altura deseada
     * @return permutacion de solucion, vacia si no hay
     */
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
    
    /**
     * Obtener cadena con la respuesta de solucion.
     * @param n numero de tazas
     * @param h altura deseada
     * @return cadena con los nuemros de las tazas, en caso de no tener sera imposible
     */
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
    
    /**
     * Simular solucion con tower, no se podra simular si no hay solucion posible.
     * @param n numero de tazas
     * @param h altura deseada
     */
    public static void simulate(long n, long h){
        List<Long> respond = respond(n,h);
        if(respond.isEmpty()){
            JOptionPane.showMessageDialog(null,"No se puede simular, debido a que es imposible");
        }else{
            Tower tower = new Tower(500,500);
            for(long cup : respond){
                tower.pushCup("normal",(int)((cup+1)/2));
            }
            tower.makeVisible();
        }
    }
    
}