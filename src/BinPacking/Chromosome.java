package BinPacking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Chromosome implements Comparable<Chromosome> {
    private List<Bin> bins = new ArrayList<Bin>();
    private List<Gene> freeItems = new ArrayList<Gene>();
    private double fitness = 0;

    public double fitness(){
        double sum = 0;
        double F,f,c,k;

        k=2;

        for(int i = 0; i < bins.size(); i++){
            f = bins.get(i).getFilled();
            c = bins.get(i).getCapacity();
            sum = sum + Math.pow(f/c, k);
        }

        F = sum / bins.size();

        return F;
    }

    public void setFitness(){
        this.fitness = fitness();
    }

    public Double getFitness(){
        setFitness();

        return fitness;
    }

    public int countBins(){
        return this.bins.size();
    }

    public void insertRandom(List<Gene> items){
        firstFitItems(shuffleList(items));
    }

    public void addFreeItems(){
        Collections.sort(this.freeItems, Collections.reverseOrder());
        this.firstFitItems(this.freeItems);

        this.freeItems.clear();
    }

    private void firstFitItems(List<Gene> items) {
        for(int i = 0; i < items.size(); i++){
            Gene elem = items.get(i);

            for(int j = 0; j < bins.size(); j++){
                if(bins.get(j).addElement(elem)){
                    return;
                }
            }

            Bin bin = new Bin();
            bin.addElement(elem);
            bins.add(bin);
        }
    }

    public String toString(){
        return bins.toString();
    }

    public static List<Gene> shuffleList(List<Gene> items) {
        long seed = System.nanoTime();
        Collections.shuffle(items, new Random(seed));

        return items;
    }

    @Override
    public int compareTo(Chromosome o) {

        int w = this.getFitness().compareTo(o.getFitness());

        return w;
    }

    public Pair getCrossoverPoints(int delta){
        Pair pair = new Pair();
        int len = this.bins.size();

        if(len == 0)
            return null;

        pair.x = randRange(0, len - 1);

        if(pair.x == (len - 1 )){
            pair.y = len;
        } else {
            pair.y = randRange(pair.x, pair.x + delta);
        }

        return pair;
    }

    public void mutate(int probability, int mutationSize) {
        Random randGen = new Random();
        int test = randGen.nextInt(100);

        if(test > probability)
            return;

        //mutation
        if(bins.size() < mutationSize){

            for(int i = 0; i < bins.size(); i++){
                List<Gene> items = bins.get(i).getAll();

                for(int k = 0; k < items.size(); k++){
                    this.freeItems.add(items.get(k));
                }

            }
            this.bins.clear();
            addFreeItems();
            return;
        }

        for(int i = 0; i < mutationSize; i++){
            int pos = randGen.nextInt(bins.size());
            List<Gene> items = bins.get(pos).getAll();

            for(int k = 0; k < items.size(); k++){
                this.freeItems.add(items.get(k));
            }
            this.bins.remove(pos);
        }

        addFreeItems();
    }

    public static int randRange(int min, int max){

        Random rn = new Random();
        int range = max - min + 1;
        int randomNum = rn.nextInt(range) + min;

        return randomNum;
    }

    public class Pair{
        public int x;
        public int y;
    }

    public List<Bin> getGenesByDivision(Pair div) {
        List<Bin> genes = new ArrayList<Bin>();

        for(int i = div.x; i < div.y; i++){
            genes.add(this.bins.get(i));
        }

        return genes;
    }

    public void insertGenesOnPos(List<Bin> genes, int y) {
        if(y >= this.bins.size()){
            y = this.bins.size();
        }

        this.bins.addAll(y, genes);
    }

    public void deleteDuplicatesByGenes(List<Bin> newGenes) {
        for(int i = 0; i < this.bins.size(); i++){
            for(int j = 0; j < newGenes.size(); j++){
                for(int n = 0; n < newGenes.get(j).getAll().size(); n++){

                    for(int m = 0; m < this.bins.get(i).getAll().size(); m++){

                        if(this.bins.get(i).getElement(m).getId() == newGenes.get(j).getElement(n).getId()){
                            this.bins.get(i).remove(m);
                            this.bins.get(i).markForDelete();
                            break;
                        }

                    }

                }
            }
        }

        List<Bin> newBins = new ArrayList<Bin>();

        for(int i = 0; i < this.bins.size(); i++){
            if(this.bins.get(i).toDelete()){

                List<Gene> genes = this.bins.get(i).getAll();

                for(int k = 0; k < genes.size(); k++){
                    this.freeItems.add(genes.get(k));
                }

            } else {
                newBins.add(this.bins.get(i));
            }
        }

        this.bins = newBins;
    }

    public int genotypeSize(){
        return this.bins.size();
    }
}
