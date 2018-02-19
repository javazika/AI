package geneticalgorithm;

import java.util.*;

public class GeneticAlgorithm {

    public static void main(String[] args) {
        int min = 0, max = 15;
        int generation = 4;
        int[] pop = {9, 1, 2, 14};
        GA ga = new GA(min, max, pop);
        ga.init();
        System.out.println(ga.toString());

    }
}

class GA {

    ArrayList<Chromosome> chros;
    private int chroSize, size, min, max;
    private static Random r = new Random();
    private double[] selectionRate;

    public GA(int min, int max, int size) {
        this.size = size;
        this.max = max;
        this.min = min;
        this.chros = new ArrayList<Chromosome>();
        chroSize = getChroSize();
        SetChroItems();
        selectionRate = new double[size];
    }

    public GA(int min, int max, int[] arrChros) {
        this.size = arrChros.length;
        this.min = min;
        this.max = max;
        this.chroSize = getChroSize();
        this.chros = new ArrayList<Chromosome>();
        SetChroItems(arrChros);
        selectionRate = new double[size];
    }

    public void init() {
        SelectParent();
        //Crossover();
        //Mutation();
        //SelectChro();
    }

    public int getChroSize() {
        return Integer.bitCount(this.max);
    }

    private void SetChroItems() {
        for (int i = 0; i < this.size; i++) {
            Chromosome chro = new Chromosome(this.chroSize);
            chro.setChromosome(r.nextInt(this.max) + 1);
            chros.add(chro);
        }
    }

    private void SetChroItems(int[] arrChros) {
        for (int i = 0; i < this.size; i++) {
            Chromosome chro = new Chromosome(this.chroSize);
            chro.setChromosome(arrChros[i]);
            this.chros.add(chro);
        }
    }

    private void SelectParent() {
        double sum = 0;
        for (int i = 0; i < this.size; i++) {
            double selectRate = r.nextDouble();
            if (selectRate > this.selectionRate[i]) {

            }
        }

    }

    private void Crossover() {

    }

    private void Mutation() {

    }

    private void SelectChro() {
        Collections.sort(chros,new SortList());
    }

    @Override
    public String toString() {
        String NString = "";
        for (int i = 0; i < this.size; i++) {
            NString += chros.get(i).toString() + "\n";
        }
        return NString;
    }

}

class Chromosome {

    int size, num;
    boolean[] gens;

    public Chromosome() {
    }

    public Chromosome(int size) {
        this.size = size;
        this.gens = new boolean[this.size];
    }

    public void setChromosome(int gen) {
        this.gens = transfer(gen);
        this.num = gen;
    }

    public int getChromosome() {
        return transfer(gens);
    }

    public boolean[] transfer(int num) {
        boolean[] NewChromosome = new boolean[this.size];
        String NString = Integer.toBinaryString(num);
        String Zero = "";
        for (int j = 0; j < this.size - NString.length(); j++) {
            Zero += "0";
        }
        NString = Zero + NString;
        for (int i = 0; i < NString.length(); i++) {
            if (NString.charAt(i) == '1') {
                NewChromosome[i] = true;
            } else {
                NewChromosome[i] = false;
            }
        }
        return NewChromosome;
    }

    public int transfer(boolean[] chromosome) {
        String ChroString = chromosome.toString();
        return Integer.parseInt(ChroString, 2);

    }

    public double calFitness() {
        double NDouble = 0L;
        NDouble = 15 * this.num - Math.pow(this.num, 2);
        return NDouble;
    }

    @Override
    public String toString() {
        String newString = "";
        for (int i = 0; i < this.size; i++) {
            newString += this.gens[i] ? "1" : "0";
        }
        return "Chro: " + newString + "\tFit: " + calFitness();
    }

}

class SortList implements Comparator<Chromosome> {

    public int compare(Chromosome a, Chromosome b) {
        return (int) (a.calFitness() - b.calFitness());
    }
}
