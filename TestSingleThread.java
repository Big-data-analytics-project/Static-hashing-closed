import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestSingleThread {

	public static void writeAccessPerformance(String filename) throws IOException {
    	HashMap<String, String> emergencyCalls = new HashMap<>(500);
    	ArrayList<Long> times = new ArrayList<>();
    	ArrayList<String> keys = new ArrayList<>();
    	
    	Scanner reader = new Scanner(new File("911.csv"));
    	reader.nextLine();
    	String sep = ",";
    	
    	int i = 0;
    	
    	
    	while(reader.hasNextLine()) {
            String [] line = reader.nextLine().split(sep);
            keys.add(line[2]);
            emergencyCalls.add(line[2], line[4]);
            i++;
            if((i-1) % 5000 == 0) {  // enregistrer toutes les 5000 itérations
            	int countTime = 0;
            	for(int j=0;j<1000;j++) {
            		int keyIndex = (int)(Math.random() * (i-1)); //
            		long start = System.nanoTime();
            		emergencyCalls.get(keys.get(keyIndex));
            		long finish = System.nanoTime();
            		countTime += finish - start;
            	}
            	times.add((long)(countTime / 1000));
            }  
        }
    	
    	PrintWriter pw=new PrintWriter(new FileWriter(filename));
	    pw.println(times.toString());
		pw.close();
    }
  
  public static void writeInsertPerformance(String filename) throws IOException {
    	HashMap<String, String> emergencyCalls = new HashMap<>(3000);
    	ArrayList<Long> times = new ArrayList<>();
    	
    	Scanner reader = new Scanner(new File("911.csv"));
    	reader.nextLine();
    	String sep = ",";
    	
    	int i = 0;
    	
    	
    	while(reader.hasNextLine()) {
            String [] line = reader.nextLine().split(sep);
            emergencyCalls.add(line[2], line[4]);
            i++;
            if((i-1) % 5000 == 0) {
            	int countTime = 0;
            	for(int j=0;j<1000;j++) {
            		long start = System.nanoTime();
            		emergencyCalls.add("Key", "Value");
            		long finish = System.nanoTime();
            		emergencyCalls.remove("Key");
            		countTime += finish - start;
            	}
            	times.add((long)(countTime / 1000));
            }  
        }
    	
    	PrintWriter pw=new PrintWriter(new FileWriter(filename));
	    pw.println(times.toString());
		pw.close();
    }
  
  
  
  public static void main(String[] args) throws IOException, InterruptedException {
	  
	  
	  
	  //writeInsertPerformance("insert_time.txt");
	  writeAccessPerformance("insert_time.txt");
  	
	   
	 
  }
  

  
}
