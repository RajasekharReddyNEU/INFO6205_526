package BinPacking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import BinPacking.Chromosome.Pair;

public class Generation {

    public List<Chromosome> population = new ArrayList<Chromosome>();
    private List<Double> fitnessArr;
    private int genNumber = 1;
    private double bestFitOnGeneration = 0;
    private int bestFitBinsCount = 0;

    public static Generation FirstGeneration(List<Gene> items, int sop) {
        Generation gen = new Generation();

        for (int i = 0; i < sop; i++) {
            Chromosome chromosome = new Chromosome();
            chromosome.insertRandom(items);
            gen.addChromosome(chromosome);
        }

        return gen;
    }

    private void addChromosome(Chromosome chromosome) {
        population.add(chromosome);
    }

    public Integer bestBinsCount;

    public void countFitness() {
        this.fitnessArr = new ArrayList<Double>();

        for (int i = 0; i < population.size(); i++) {
            this.fitnessArr.add(this.population.get(i).getFitness());

            if (this.bestFitOnGeneration < this.population.get(i).getFitness()) {

                this.bestFitOnGeneration = this.population.get(i).getFitness();
                this.bestBinsCount = this.population.get(i).countBins();

            }

        }
    }

    public String allFits(){
        Collections.sort(this.fitnessArr);
        return this.fitnessArr.toString();
    }

    public void selection() {

        Collections.sort(population, new ChromosomeComparator());

        int limit = 50;

        if (this.population.size() < limit) {
        } else {
            this.population = this.population.subList(0, limit);
        }
    }

    public void NextGeneration() {
        List<Chromosome> nextGeneration = new ArrayList<Chromosome>();

        nextGeneration = population;

        List<Chromosome> tournament = new ArrayList<Chromosome>();
        Random rand = new Random();

        for (int i = 0; i < 10; i++) {
            if (nextGeneration.size() == 0)
                continue;
            int index = rand.nextInt(nextGeneration.size());

                tournament.add(nextGeneration.get(index));
        }

        Collections.sort(tournament, new ChromosomeComparator());

        Chromosome chr1 = tournament.get(0);
        Chromosome chr2 = tournament.get(1);
        for (int i = 0; i < 25; i++) {
            Chromosome value1 = chr1;
            Chromosome value2 = chr2;

            this.crossover(value1, value2);
            if(value1.genotypeSize() > 0)
                nextGeneration.add(value1);

            if(value2.genotypeSize() > 0)
                nextGeneration.add(value2);
        }
        this.population = nextGeneration;
        this.genNumber++;
    }

    private void crossover(Chromosome chr1, Chromosome chr2) {
        Pair crPointsChr1 = chr1.getCrossoverPoints(2);
        Pair crPointsChr2 = chr2.getCrossoverPoints(2);

        if(crPointsChr1 != null && crPointsChr2 != null){
            List<Bin> genesFromChr1 = chr1.getGenesByDivision(crPointsChr1);
            List<Bin> genesFromChr2 = chr2.getGenesByDivision(crPointsChr2);

            chr1.deleteDuplicatesByGenes(genesFromChr2);
            chr2.deleteDuplicatesByGenes(genesFromChr1);

            chr1.insertGenesOnPos(genesFromChr2, crPointsChr1.y);
            chr2.insertGenesOnPos(genesFromChr1, crPointsChr2.x);
        }

        chr1.addFreeItems();
        chr2.addFreeItems();

    }

    public void mutation() {
        for (int i = 0; i < this.population.size(); i++) {
            this.population.get(i).mutate(66, 2);
        }
    }

    public int getGenerationNumber() {
        return this.genNumber;
    }

    public double getBestFitValue() {
        return this.bestFitOnGeneration;
    }

    public void zerosFit(){
        this.bestBinsCount =  100;
        this.bestFitBinsCount = 100;
    }

    class ChromosomeComparator implements Comparator<Chromosome>{

        @Override
        public int compare(Chromosome o1, Chromosome o2) {

            if(o1.getFitness() > o2.getFitness())
                return -1;

            if(o1.getFitness() < o2.getFitness())
                return 1;

            return 0;
        }

    }

    public String allGens() {
        Collections.sort(this.population);
        return this.population.toString();
    }

}