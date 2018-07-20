import java.util.ArrayList;
import java.util.Random;

public class Chromosome {
    private static final double minNumX1=10, maxNumX1=80,minNumX2=10, maxNumX2=50, minNumX3X4=0.9, maxNumX3X4=5;
    
    private static final int genesNum=4;
    
    private int chromosomeNum;
    
    private byte[] individual;
    
    private int x1DigitNum,x2DigitNum,x3DigitNum,x4DigitNum;
  
    public Chromosome() {
    	x1DigitNum=geneDigitNum(minNumX1,maxNumX1);
    	x2DigitNum=geneDigitNum(minNumX2,maxNumX2);
    	x3DigitNum=geneDigitNum(minNumX3X4,maxNumX3X4);
    	x4DigitNum=geneDigitNum(minNumX3X4,maxNumX3X4);
    	
    	chromosomeNum=x1DigitNum+x2DigitNum+x3DigitNum+x4DigitNum;
    	individual=new byte[chromosomeNum];
    	generateChromosome();
    }
    
//    public Chromosome(byte[] individual) {
//    	this.individual=individual;
//    	x1DigitNum=geneDigitNum(minNumX1,maxNumX1);
//    	x2DigitNum=geneDigitNum(minNumX2,maxNumX2);
//    	x3DigitNum=geneDigitNum(minNumX3X4,maxNumX3X4);
//    	x4DigitNum=geneDigitNum(minNumX3X4,maxNumX3X4);
//    	
//    	chromosomeNum=x1DigitNum+x2DigitNum+x3DigitNum+x4DigitNum;
//    }
    
    public int getChromosomeSize(){
    	return this.chromosomeNum;
    }
    
  //****************************************************** 
    //*** Purpose: Generate digit number	
    //*** Input: 
    //*** Output: 
    //****************************************************** 
    public int geneDigitNum(double min, double max){
    	double d=0.0;
    	int m=0;
    	d=(max-min)*10000;
    	m=(int) Math.ceil(Math.log(d)/Math.log(2));
    	return m;
    }
    
    /** 
     * Create a random gene
     */
    public void generateChromosome() {
        for (int i = 0; i < chromosomeNum; i++) {
            byte gene = (byte) Math.round(Math.random());
            individual[i] = gene;
        }
    }
    
    public byte[] getIndividual(){
    	return this.individual;
    }
    
    public int getX1DigitNum(){
    	return this.x1DigitNum;
    }
    
    public int getX2DigitNum(){
    	return this.x2DigitNum;
    }
    
    public int getX3DigitNum(){
    	return this.x3DigitNum;
    }
    
    public int getX4DigitNum(){
    	return this.x4DigitNum;
    }
    
    public void showChromosome(){
    	for (int i=0;i<chromosomeNum;i++)
    		System.out.print(individual[i]);
    }
    
    @Override
    public String toString(){
    	String s="";
    	for (int i=0;i<chromosomeNum;i++)
    		s=s+individual[i];
    	return s;
    }
}
