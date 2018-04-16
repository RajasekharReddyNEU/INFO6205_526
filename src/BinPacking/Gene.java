package BinPacking;

import java.util.ArrayList;
import java.util.List;

public class Gene implements Comparable<Gene>{

    private int id;
    private int value;

    public Gene(int id, int value){
        this.id = id;
        this.value = value;
    }

    public int getValue(){
        return value;
    }

    public int getId(){
        return id;
    }

    public static List<Gene> intArrToList(int[] arr) {
        List<Gene> genes = new ArrayList<Gene>();

        for(int i = 0; i < arr.length; i++){
            Gene elem = new Gene(i, arr[i]);
            genes.add(elem);
        }

        return genes;
    }

    public String toString(){
        return Integer.toString(value) + "(" + Integer.toString(id) + ")";
    }

    @Override
    public int compareTo(Gene o) {
        Integer size = this.value;
        Integer osize = o.getValue();
        return size.compareTo(osize);
    }
}
