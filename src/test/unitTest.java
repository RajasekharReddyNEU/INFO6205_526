import BinPacking.Bin;
import BinPacking.Gene;
import BinPacking.Generation;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
//import org.junit.jupiter.api.Test;

//import static org.junit.jupiter.api.Assertions.assertTrue;


public class unitTest {
    @Test
    public void testBin0() {
        Generation gen= new Generation();
        double bf= gen.getBestFitValue();

        for(int i=0;i<gen.population.size();i++)
        {
            assertTrue(gen.population.get(i).getFitness()<=bf);


        }
    }

    @Test
    public void testBin1() {
        Bin bin= new Bin();

        assertTrue(bin.addElement(new Gene(5,6)));
    }

    @Test
    public void testBin2() {
        Generation gen= new Generation();
        int bf= 1;
        int generation=gen.getGenerationNumber();
            assertEquals(bf,generation);



    }

    @Test
    public void testBin3() {
        Generation gen= new Generation();
        int bf= 0;

        for(int i=0;i<gen.population.size();i++)
        {
            assertTrue(gen.population.get(i).getFitness()<=bf);


        }
    }

    @Test
    public void testBin4() {
        Generation gen= new Generation();
        double bf= gen.getBestFitValue();

        for(int i=0;i<gen.population.size();i++)
        {
            assertTrue(gen.population.get(i).getFitness()<=bf);


        }
    }

}
