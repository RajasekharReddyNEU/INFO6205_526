package BinPacking;

public class Genetic {

    public static void main(String[] args) {
        int elements[] = {5, 3, 2, 6, 3, 1, 8, 7, 1, 2, 4, 1, 6, 2, 3, 4, 5, 2, 3, 2, 6, 1, 1, 8, 7, 1, 2, 4, 1, 2, 2, 3, 4, 5, 2, 3, 2, 6, 1, 1, 8,3,6,5,8,2,3,5,9,5,4,3,2,5,1,3,2,4,2,3,2 };
        System.out.println(elements.length);
        Generation gen = Generation.FirstGeneration(Gene.intArrToList(elements), 100);
        for(int i = 0; i < 100; i++){
            gen.selection();
            gen.NextGeneration();
            gen.mutation();
            gen.countFitness();
            System.out.println("Best fitness in generation - "+gen.getGenerationNumber()+": "+gen.getBestFitValue() + " with " + gen.bestBinsCount + " used. "+ gen.allGens()+"and their corresponding fitness values are " + gen.allFits());
        }

    }

}
