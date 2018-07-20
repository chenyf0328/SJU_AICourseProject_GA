import jahuwaldt.plot.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.text.AttributeSet.CharacterAttribute;

public class GA {
	
	private static final int numGenerations=100;
	
	private static double[] xArr = new double[numGenerations];
	private static double[] yArr = new double[numGenerations];
	private static double[] f1YArr=new double[numGenerations];
	private static double[] f2YArr=new double[numGenerations];
	private static double[] f1AvgArr=new double[numGenerations];
	private static double[] f2AvgArr=new double[numGenerations];
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int fitestIndividual=0;
		
		double x1,x2,x3,x4;
		
		double fitness;
		
		Population myPopulation=new Population();
		GeneticAlgorithm myGeneticAlgorithm;
		
		myPopulation.generateEvalArray();
		myPopulation.calculateTotalFitness();
		myPopulation.calculateP();
		myPopulation.calculateQ();
		myPopulation.RouletteWheel();
		
		for (int i=0;i<numGenerations;i++){
			myGeneticAlgorithm=new GeneticAlgorithm(myPopulation);
			
			myPopulation.generateEvalArray();
	    	f1AvgArr[i]=myPopulation.getAvgF1();
	    	f2AvgArr[i]=myPopulation.getAvgF2();
	    	myPopulation.setAvgF2(0.0);
	    	myPopulation.setAvgF2(0.0);
	    	
			myPopulation.calculateTotalFitness();
			myPopulation.calculateP();
			myPopulation.calculateQ();
			myPopulation.RouletteWheel();
			
			myGeneticAlgorithm.crossover();
			myGeneticAlgorithm.mutate();
			
			fitestIndividual=myPopulation.fittest();
			myPopulation.separateBinary(fitestIndividual);
			x1=myPopulation.getX1();
			x2=myPopulation.getX2();
			x3=myPopulation.getX3();
			x4=myPopulation.getX4();
			double f1=myPopulation.f1();
			double f2=myPopulation.f2();
			
			// keep it!!!!!
			System.out.println("Generation="+i+"'s Best:");
			System.out.println("---------------------------------------");
			System.out.println("X1="+x1+"   "+"X2="+x2+"   "+"x3="+x3+"   "+"x4="+x4);
			System.out.println("Fitness: "+myPopulation.getFitness(fitestIndividual));
			System.out.println();
			
			// test
			System.out.println("Generation="+i+"   f1="+f1+"   f2="+f2);
			
			f1YArr[i]=f1;
			f2YArr[i]=f2;
		}
		
		fitestIndividual=myPopulation.fittest();
		myPopulation.separateBinary(fitestIndividual);
		x1=myPopulation.getX1();
		x2=myPopulation.getX2();
		x3=myPopulation.getX3();
		x4=myPopulation.getX4();
		fitness=myPopulation.calculateFitness();
		
		// test
//		double f1=myPopulation.f1();
//		double f2=myPopulation.f2();
//		System.out.println(x1+" "+x2+" "+x3+" "+x4+" "+f1+" "+f2+" "+fitness);
		
		//*** Create a Simple 2D XY plot window.
		createPlot(f1YArr,"Cross Section Area","Generation No.", "Best Particle Fitness",150,150);
		createPlot(f1AvgArr,"Cross Section Area", "Generation No.", "Avg. Population Fitness",680,150);
		createPlot(f2YArr,"Static Deflection", "Generation No.", "Best Particle Fitness",150,480);
		createPlot(f2AvgArr,"Cross Section Area", "Generation No.", "Avg. Population Fitness",680,480);
	}
	
	public static void createPlot(double[] yArr, String title, String xAxisTitle, String yAxisTitle, int locX, int locY){
		for (int i=0; i<numGenerations; i++){
			xArr[i] = i;
			yArr[i] = yArr[i]; // your GASolver should fill up this array
		}
		Plot2D aPlot = new SimplePlotXY(xArr, yArr, title, xAxisTitle, yAxisTitle, null, null, null);
		// Make the horizontal axis a log axis.
		PlotAxis xAxis = aPlot.getHorizontalAxis();
		xAxis.setScale(new LinearAxisScale());
		PlotPanel panel = new PlotPanel(aPlot);
		panel.setBackground( Color.white );
		PlotWindow window = new PlotWindow("SimplePlotXY Plot Window", panel);
		window.setSize(500, 300);
		window.setLocation(locX,locY); // location on screen
		window.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		window.show();
	}
}
