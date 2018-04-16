package BinPacking;

import java.util.ArrayList;
import java.util.List;

public class Bin {
    private int capacity = 10;
    private int filled = 0;
    private List<Gene> genes = new ArrayList<Gene>();
    private boolean markedForDelete = false;


    public Gene getElement(int key) {
        return this.genes.get(key);
    }

    public List<Gene> getAll() {
        return this.genes;
    }

    public boolean addElement(Gene elem) {
        if (filled + elem.getValue() <= capacity) {
            filled += elem.getValue();
            genes.add(elem);
            return true;
        }
        return false;
    }

    public void remove(int n) {
        this.filled-= genes.get(n).getValue();
        this.genes.remove(n);
    }

    public void markForDelete() {
        this.markedForDelete  = true;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getFilled() {
        return filled;
    }

    public boolean toDelete(){
        return this.markedForDelete;
    }

    public String toString() {

        String ret = new String();
        for (int i = 0; i < genes.size(); i++) {

            if (ret.length() > 0) {
                ret = ret + ',';
            }

            ret = ret + genes.get(i).toString();

        }

        return '[' + ret + ']';
    }
}
