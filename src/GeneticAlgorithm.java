import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GeneticAlgorithm {
	private Population population;

	// private int[][] pair=new int[50][2];

	private int[] parent = new int[200];

	private int pos = 0;

	private static final double crossoverProb = 0.75;
	private static final double mutationProb = 0.001;

	public GeneticAlgorithm(Population population) {
		this.population = population;
		generateParent();
	}

	/**
	 * 交叉运算
	 * 
	 * @param selectedCodes
	 *            上步骤的选择后的编码
	 * @return
	 */
	public void crossover() {
		ArrayList<Integer> check = new ArrayList<Integer>();
		if (pos % 2 != 0)
			pos = pos - 2;
		else
			pos = pos - 1;

		int r = 0;
		check.add(0);
		r = (int) (pos * Math.random()+1);
		check.add(r);
		crossoverOperator(population.getPopulation()[parent[0]], population.getPopulation()[parent[r]]);
		for (int i = 1; i < pos; i++) {
			if (!check.contains(i)){
				check.add(i);
				r = -1;
				// 终止条件：找到check里面没有出现过的index
				while (!check.contains(r)) {
					r = (int) ((pos + 1) * Math.random());
					if (!check.contains(r)) {
						check.add(r);
						break;
					}
				}
				crossoverOperator(population.getPopulation()[parent[i]], population.getPopulation()[parent[r]]);
			}
		}

		// for (int i=0;i<pos;i++){
		// for (int j=0;j<pos;j++){
		// double r=Math.random();
		// if (r<crossoverProb){
		//// System.out.println("********************************************************");
		//// System.out.println("hahahah"+population.getPopulation()[parent[i]]);
		//// System.out.println("klklklk"+population.getPopulation()[parent[j]]);
		//// System.out.println("-----------------------------------------------------");
		// crossoverOperator(population.getPopulation()[parent[i]],
		// population.getPopulation()[parent[j]]);
		//// System.out.println("hahahah"+population.getPopulation()[parent[i]]);
		//// System.out.println("klklklk"+population.getPopulation()[parent[j]]);
		//
		// break;
		// }
		// }
		// }
		// System.out.println("=================================================");

		// for (int i=0;i<pos;i++){
		// int r1=(int)((pos+1)*Math.random());
		// int r2=(int)((pos+1)*Math.random());
		// double r=
		// }
	}

	public void crossoverOperator(Chromosome indiv1, Chromosome indiv2) {
		double r = indiv1.getChromosomeSize() * Math.random();
		int cutPoint = (int) r;
		byte temp;
		for (int i = cutPoint + 1; i < indiv1.getChromosomeSize(); i++) {
			temp = indiv1.getIndividual()[i];
			indiv1.getIndividual()[i] = indiv2.getIndividual()[i];
			indiv2.getIndividual()[i] = temp;
		}
	}
	
//	public void crossoverOperator(Chromosome indiv1, Chromosome indiv2) {
//		double r = indiv1.getChromosomeSize() * Math.random();
//		int cutPoint = (int) r;
//		for (int i = cutPoint + 1; i < indiv1.getChromosomeSize(); i++) {
//			double rand=Math.random();
//			if (rand<crossoverProb)
//				indiv1.getIndividual()[i] = indiv2.getIndividual()[i];
//			else
//				indiv2.getIndividual()[i] = indiv1.getIndividual()[i];
//		}
//	}

	public void generateParent() {
		pos = 0;
		for (int i = 0; i < population.getInitSetsNum(); i++) {
			double r = Math.random();
			if (r < crossoverProb) {
				parent[pos] = i;
				pos++;
			}
		}
	}
	
	public Chromosome crossover2() {
		Chromosome newSol = new Chromosome();
		Chromosome temp1 = selectChrome(null);
		Chromosome temp2 = selectChrome(temp1);
		double r = temp1.getChromosomeSize() * Math.random();
		int cutPoint = (int) r;
		for (int i = 0; i < temp1.getChromosomeSize(); i++) {
			if (i <= cutPoint) {
				newSol.getIndividual()[i] = temp1.getIndividual()[i];
			}
			else {
				newSol.getIndividual()[i] = temp2.getIndividual()[i];
			}
		}
		return newSol;
	}
	
	public Chromosome selectChrome(Chromosome chromosome) {
		// pop.rouletteWheel();
		if (chromosome == null) {
			chromosome = new Chromosome();
		}
		Chromosome tempChromo = population.getPopulation()[population.fittest()];
		double r=0.0;
		for (int i = 0; i < population.getInitSetsNum(); i++) {
			r=Math.random();
			if (r < crossoverProb && !chromosome.equals(population.getPopulation()[i])) {
				tempChromo = population.getPopulation()[i];
				break;
			}
		}
		return tempChromo;
	}
	
//	public static Chromosome selectChrome(Population pop, Chromosome chromosome) {
//		// pop.rouletteWheel();
//		if (chromosome == null) {
//			chromosome = new Chromosome();
//		}
//		Chromosome tempChromo = pop.getFittest();
//		Random rand = new Random();
//		double tempNum = 0;
//		for (int i = 0; i < pop.DefaultPopulationSize; i++) {
//			tempNum = rand.nextDouble();
//			if (tempNum < crossoverRate && !chromosome.equals(pop.afterRouletteWheelIndividuals[i])) {
//				tempChromo = pop.afterRouletteWheelIndividuals[i];
//				break;
//			}
//		}
//		return tempChromo;
//	}

	// test
//	public void crossover2() {
//		Chromosome tempChromo=new Chromosome();
//		for (int i = 0; i < population.getInitSetsNum(); i++) {
//			System.out.println(parent[0]+"       "+parent[1]);
//			Chromosome indiv1=population.getPopulation()[parent[0]];
//			Chromosome indiv2=population.getPopulation()[parent[1]];
////			double r = indiv1.getChromosomeSize() * Math.random();
////			int cutPoint = (int) r;
//			for (int j = 0; j < tempChromo.getChromosomeSize(); j++) {
//				double r=Math.random();
//				if (r<crossoverProb)
//					tempChromo.getIndividual()[j]=indiv1.getIndividual()[j];
//				else
//					tempChromo.getIndividual()[j]=indiv2.getIndividual()[j];
//			}
//			population.getTempPopulation()[i]=tempChromo;
//			selectChromosome();
//		}
//		for (int i = 0; i < population.getInitSetsNum(); i++) {
//			population.getPopulation()[i]=population.getTempPopulation()[i];
//		}
//	}
	
//	public void selectChromosome() {
//		double r = Math.random();
//		for (int i=0;i<2;i++){
//			r = Math.random();
//			while(r < crossoverProb){
//				r = Math.random();
//				int rand=(int)(Math.random()*population.getInitSetsNum());
//				parent[i]=rand;
//				break;
//			}
//		}
//	}
	// test
	
	// public void mutate(){
	// int count=0;
	// for (int i=0;i<population.getInitSetsNum();i++)
	// for (int j=0;j<71;j++){
	// if (count<=7){
	// if (count==7)
	// System.out.println("count="+count);
	// double r=Math.random();
	// if (r<mutationProb){
	// if (population.getPopulation()[i].getIndividual()[j]==1)
	// population.getPopulation()[i].getIndividual()[j]=0;
	// else
	// population.getPopulation()[i].getIndividual()[j]=1;
	// count++;
	// }
	// }
	// else
	// break;
	// }
	// }

	public void mutate() {
		int size = population.getInitSetsNum() * population.getPopulation()[0].getChromosomeSize();
		int mutationSize = (int) Math.ceil(size * mutationProb);

		for (int i = 0; i < mutationSize; i++) {
			int r = (int) Math.random() * size;
			int posChromosome = r / population.getPopulation()[0].getChromosomeSize();
			int indexChromosome = r % population.getPopulation()[0].getChromosomeSize();
			if (population.getPopulation()[posChromosome].getIndividual()[indexChromosome] == 1)
				population.getPopulation()[posChromosome].getIndividual()[indexChromosome] = 0;
			else
				population.getPopulation()[posChromosome].getIndividual()[indexChromosome] = 1;
		}
	}

	// public void generatePair(){
	// ArrayList<Integer> sequence=new ArrayList<Integer>();
	// for (int i=0;i<population.getInitSetsNum();i++)
	// sequence.add(i);
	//
	// int r;
	// for (int i=0;i<(population.getInitSetsNum()/2)-1;i++)
	// for (int j=0;j<2;j++){
	// while(!sequence.contains(r= (int) ((int)50*Math.random())))
	// System.out.println(r);
	// sequence.remove(r);
	// pair[i][j]=r;
	// }
	//
	// // test
	// for (int i=0;i<50;i++){
	// for (int j=0;j<2;j++)
	// System.out.print(pair[i][j]);
	// System.out.println();
	// }
	// }
}
