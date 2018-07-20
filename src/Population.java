import java.awt.Window.Type;

public class Population {
	// initial population size
    private static final int initSetsNum=50;
   
    private static final double evalA=0.65,evalB=0.35;
    
    private Chromosome individual;
    
    private Chromosome[] individuals=new Chromosome[initSetsNum];
    
    private Chromosome[] tempindividuals=new Chromosome[initSetsNum];
    
    private double[] eval=new double[initSetsNum];
    
    private byte[] separateX1,separateX2,separateX3,separateX4;
    
    private double x1,x2,x3,x4;
    
    private double totalFitness=0.0;
    
    private double avgF1=0.0,avgF2=0.0;
    
    private double[] p=new double[initSetsNum];
    
    private double[] q=new double[initSetsNum];
    
    public Population(){
    	for (int i=0;i<initSetsNum;i++){
    		individual = new Chromosome();
    		saveIndividual(i,individual);
    	}
    }

    // save individual
    public void saveIndividual(int index, Chromosome individual) {
        individuals[index] = individual;
    }
    
    public Chromosome getSpecificIndividual(int index){
    	return individuals[index];
    }
    
    public void showPopulation(){
    	for (int i=0;i<initSetsNum;i++){
    		System.out.print("v"+i+": ");
    		for (int j=0;j<size();j++)
    			System.out.print(individuals[i].getIndividual()[j]);
    		System.out.println();
    	}
    }
    
    public int size(){
    	return individual.getChromosomeSize();
    }
    
    public double getFitness(int index){
    	return eval[index];
    }
    
    public void generateEvalArray() {
	    for (int i=0;i<initSetsNum;i++){
	    	separateBinary(i);
	    	eval[i]=1/calculateFitness();
	    }
	    this.avgF1=this.avgF1/initSetsNum;
	    this.avgF2=this.avgF2/initSetsNum;
    }
    
    public double f1(){
    	double value=2*x2*x4+x3*(x1-2*x4);
    	this.avgF1=avgF1+value;
    	return value;
    }
    
    public double f2(){
    	double value=60000/(x3*(x1-2*x4)*(x1-2*x4)*(x1-2*x4)+2*x2*x4*(4*x4*x4+3*x1*(x1-2*x4)));
    	this.avgF2=this.avgF2+value;
    	return value;
    }
    
    public double calculateFitness(){
//    	System.out.println("hahahahah  "+x1+" "+x2+" "+x3+" "+x4);
    	return evalA*f1()+evalB*f2();
    }
    
    public int fittest(){
    	for (int i=0;i<initSetsNum;i++){
	    	separateBinary(i);
	    	eval[i]=1/calculateFitness();
	    }
    	double max=eval[0];
    	int index=0;
    	for (int i=1;i<eval.length;i++){
    		if (max<eval[i]){
    			max=eval[i];
    			index=i;
    		}
    	}
    	return index;
    }
    
//    public void separateBinary(Chromosome c){
//    	
//    	int initX1=0,initX2=0,initX3=0,initX4=0;
//    	
//    	int gene1DigitNum=c.getX1DigitNum();
//    	int gene2DigitNum=c.getX2DigitNum();
//    	int gene3DigitNum=c.getX3DigitNum();
//    	int gene4DigitNum=c.getX4DigitNum();
//    	separateX1=new byte[gene1DigitNum];
//    	separateX2=new byte[gene2DigitNum];
//    	separateX3=new byte[gene3DigitNum];
//    	separateX4=new byte[gene4DigitNum];
//    	
//    	
//    	
//    	for (int i=0;i<gene1DigitNum;i++)
//    		separateX1[i]=c.getIndividual()[i];
//    	for (int i=0;i<gene2DigitNum;i++)
//    		separateX2[i]=c.getIndividual()[gene1DigitNum+i];
//    	for (int i=0;i<gene3DigitNum;i++)
//    		separateX3[i]=c.getIndividual()[gene1DigitNum+gene2DigitNum+i];
//    	for (int i=0;i<gene4DigitNum;i++)
//    		separateX4[i]=c.getIndividual()[gene1DigitNum+gene2DigitNum+gene3DigitNum+i];
//    	
//    	initX1=binaryArrayToNum(separateX1);
//    	initX2=binaryArrayToNum(separateX2);
//    	initX3=binaryArrayToNum(separateX3);
//    	initX4=binaryArrayToNum(separateX4);
//    	
//    	this.x1=10+initX1*((80-10)/(Math.pow(2, 20)-1));
//    	this.x2=10+initX2*((50-10)/(Math.pow(2, 19)-1));
//    	this.x3=0.9+initX3*((5.0-0.9)/(Math.pow(2, 16)-1));
//    	this.x4=0.9+initX4*((5.0-0.9)/(Math.pow(2, 16)-1));
//    }
    
    public void separateBinary(int index){
    	
    	int initX1=0,initX2=0,initX3=0,initX4=0;
    	
    	int gene1DigitNum=individuals[index].getX1DigitNum();
    	int gene2DigitNum=individuals[index].getX2DigitNum();
    	int gene3DigitNum=individuals[index].getX3DigitNum();
    	int gene4DigitNum=individuals[index].getX4DigitNum();
    	separateX1=new byte[gene1DigitNum];
    	separateX2=new byte[gene2DigitNum];
    	separateX3=new byte[gene3DigitNum];
    	separateX4=new byte[gene4DigitNum];
    	
    	for (int i=0;i<gene1DigitNum;i++)
    		separateX1[i]=individuals[index].getIndividual()[i];
    	for (int i=0;i<gene2DigitNum;i++)
    		separateX2[i]=individuals[index].getIndividual()[gene1DigitNum+i];
    	for (int i=0;i<gene3DigitNum;i++)
    		separateX3[i]=individuals[index].getIndividual()[gene1DigitNum+gene2DigitNum+i];
    	for (int i=0;i<gene4DigitNum;i++)
    		separateX4[i]=individuals[index].getIndividual()[gene1DigitNum+gene2DigitNum+gene3DigitNum+i];
    	
    	initX1=binaryArrayToNum(separateX1);
    	initX2=binaryArrayToNum(separateX2);
    	initX3=binaryArrayToNum(separateX3);
    	initX4=binaryArrayToNum(separateX4);
    	
    	this.x1=10+initX1*((80-10)/(Math.pow(2, 20)-1));
    	this.x2=10+initX2*((50-10)/(Math.pow(2, 19)-1));
    	this.x3=0.9+initX3*((5.0-0.9)/(Math.pow(2, 16)-1));
    	this.x4=0.9+initX4*((5.0-0.9)/(Math.pow(2, 16)-1));
    	
//    	System.out.println("hahahahah  "+x1+" "+x2+" "+x3+" "+x4);
    	
    	// test
//    	for (int i=0;i<individuals[0].getChromosomeSize();i++)
//    		System.out.print(individuals[0].getIndividual()[i]);
//    	System.out.println();
//    	for (int i=0;i<gene1DigitNum;i++)
//    		System.out.print(separateX1[i]);
//    	for (int i=0;i<gene2DigitNum;i++)
//    		System.out.print(separateX2[i]);
//    	for (int i=0;i<gene3DigitNum;i++)
//    		System.out.print(separateX3[i]);
//    	for (int i=0;i<gene4DigitNum;i++)
//    		System.out.print(separateX4[i]);
//    	
//    	System.out.println();
    }
    
    /** 
     * 二进制数组转化为数字 
     *  
     * @param binaryArray 
     *            待转化二进制数组 
     */
    private int binaryArrayToNum(byte[] binaryArray) {  
    	int result = 0;
        for (int i = binaryArray.length-1, k=0; i >=0 ; i--, k++)
            if (binaryArray[i] == 1)
                result += Math.pow(2, k);
        return result;  
    }
    
    public void calculateTotalFitness(){
    	this.totalFitness=0;
    	for (int i=0;i<initSetsNum;i++)
    		this.totalFitness=this.totalFitness+eval[i];
    }
    
    public double getAvgF1(){
    	return this.avgF1;
    }
    
    public void setAvgF1(double avgF1){
    	this.avgF1=avgF1;
    }
    
    public double getAvgF2(){
    	return this.avgF2;
    }
    
    public void setAvgF2(double avgF2){
    	this.avgF2=avgF2;
    }
    
    public void calculateP(){
    	for (int i=0;i<initSetsNum;i++)
    		p[i]=eval[i]/totalFitness;
    }
    
    public void calculateQ(){
    	q[0]=p[0];
    	for (int i=1;i<initSetsNum;i++)
    		q[i]=q[i-1]+p[i];
    }
    
    public void RouletteWheel(){
    	Chromosome[] temp=new Chromosome[initSetsNum];
    	
    	for (int i=0;i<initSetsNum;i++)
    		temp[i]=individuals[i];
    	
    	for (int i=0;i<initSetsNum;i++){
	    	double r=Math.random();
	    	if (r<=q[0])
	    		temp[i]=individuals[0];
	    	else
	    		for (int k=1;k<initSetsNum;k++)
	    			if (r>q[k-1] && r<=q[k])
	    				temp[i]=individuals[k];
    	}
    	
    	for (int i=0;i<initSetsNum;i++)
    		individuals[i]=temp[i];
    }
    
    public Chromosome[] getPopulation(){
    	return this.individuals;
    }
    
    public Chromosome[] getTempPopulation(){
    	return this.tempindividuals;
    }
    
    public int getInitSetsNum(){
    	return this.initSetsNum;
    }
    
    public double getX1(){
    	return this.x1;
    }
    
    public double getX2(){
    	return this.x2;
    }
    
    public double getX3(){
    	return this.x3;
    }
    
    public double getX4(){
    	return this.x4;
    }
}
