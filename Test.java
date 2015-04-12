
																			//Cesar Garcia
public class Test {

	public static void main( String[] args)throws Exception
	   {
	      Graph g1 = new Graph("matrixval.txt");
	      g1.printGraph();
	      g1.dfsTraversal(0);
	      System.out.println("");
	      g1.dfsTraversal(4);
	      
	      g1.bfsTraversal(5);
	      g1.bfsTraversal(4);
	      g1.bfsTraversal(0);
	      
	      g1.printShortestPaths(4);
	      g1.printShortestPaths(5);
	      
	      int x = 2;
	      int y = 4;
	      if( g1.existsPath(x,y)) 
	         System.out.println("There exists a path from " + x + " to " + y);
	      else
	         System.out.println("There is no path from " + x + " to " + y); 
	           
	      x = 5;
	      y = 2;
	      if( g1.existsPath(x,y)) 
	         System.out.println("There exists a path from " + x + " to " + y);
	      else
	         System.out.println("There is no path from " + x + " to " + y); 
	   
	        
	      g1.existsTriangle();
	      
	   }

}
