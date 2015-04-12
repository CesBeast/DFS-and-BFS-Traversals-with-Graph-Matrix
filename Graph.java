import java.io.*;
import java.util.*;


public class Graph {
private int[][]  adjMatrix  ;
private int[] visited;
 private int[] paths ;      
//other  private fields
 private ArrayList<Integer> data = new ArrayList<Integer>();
 ArrayList<Integer> dataQueue = new ArrayList<Integer>();
private Scanner inFile = null;
String filename=null;
int dfsFlag=0;
public Graph ( String inputFileName)  {  
	this.filename =inputFileName;
	readInputData(); //init. Matrix and loads data into an arrayList data
	loadMatrix(); //sets up graph matrix from arrayList
	visited=new int[data.get(0)];
}
public void printGraph()  {  
	int edges=0;
	System.out.println("Graph Matrix");
	System.out.print("        ");
	for(int i=0;i<data.get(0);i++){
		System.out.print(i+"     ");
	}
	System.out.println(" ");
	for(int i=0;i<data.get(0);i++){
		System.out.print(i+"|   ");
		for(int k=0;k<data.get(0);k++){
			System.out.print(adjMatrix[i][k]+"     ");
			if(adjMatrix[i][k]==1)
				edges++;
		}
		System.out.println("");
	}
	System.out.println("Number of Verticies: "+data.get(0));
	System.out.println("Number of Edges: "+edges);
	System.out.println("");
}
public void bfsTraversal ( int vertex)   {  
	System.out.println("BFS Traversal");
	visited=new int[data.get(0)];
	dataQueue.add(vertex);
	while(dataQueue.isEmpty()!=true){
		//work
		if(visited[vertex]!=1)//if not visited
			System.out.println(dataQueue.get(0));
		visited[vertex]=1;
		loadNeighbors(dataQueue.get(0),visited);
		//
		dataQueue.remove(0);
		if(dataQueue.isEmpty()==false)
			vertex=dataQueue.get(0);
	}
	visited=new int[data.get(0)];
	System.out.println("");
} 

public void dfsTraversal ( int vertex)  {  // use recursion
	dfsRecur(vertex);
	visited=new int[data.get(0)];
}  
public int[]  findShortestPaths (int vertex ){// return paths array
	visited=new int[data.get(0)];
	paths= new int[data.get(0)];
	initPath(paths);
	dataQueue.clear();
	dataQueue.add(vertex);
	paths[vertex]=-2;
	while(dataQueue.isEmpty()!=true){
		//work
		visited[vertex]=1;
		loadNeighborsPaths(dataQueue.get(0),visited);
		//
		dataQueue.remove(0);
		if(dataQueue.isEmpty()==false)
			vertex=dataQueue.get(0);
	}
	visited=new int[data.get(0)];
	return paths;
	}  

public void printShortestPaths( int vertex) {  
	int current;
	paths=findShortestPaths(vertex);
	System.out.println("");
	System.out.println("Shortest Paths");
	System.out.println(" The shortest Paths from "+vertex);
	for(int i=0;i<paths.length-1;i++){
		System.out.print("To "+i+": ");
		if(paths[i]==-2){
			System.out.println(vertex);
			}
		else if(paths[i]==-1)
			System.out.println("No path connected from "+vertex+" to "+i);
		else{
			current=paths[i];
			System.out.print(current+" ");
			while(current!=vertex&&current!=-2&&current!=-1){
				current=paths[current];
				System.out.print(current+" ");
			}
			System.out.println("");
		}
	}
	visited=new int[data.get(0)];
} 
public boolean existsPath(int x, int y) {
int[] allPaths=findShortestPaths(x);
int curr=allPaths[y];
if(curr==-1)
	return false;
while(curr!=-2||curr==x){
	if(curr==x)
		return true;
	curr=allPaths[curr];
}
	return false; } //is there a path from x to y?
public boolean existsTriangle() { // is there a triangle in graph? If so, print it.
	for(int i=0;i<data.get(0);i++)
		for(int k=0;k<data.get(0);k++){
			if(adjMatrix[i][k]==1){
				boolean result=checkSec(i,k);
				if(result==true)
					return true;
			}
		}
	System.out.println("Triangle not found");
	return false;  
	} 

//private methods

private void dfsRecur(int vertex){
	visited[vertex]=1;
	System.out.println(vertex);//work
	for(int i=0;i<data.get(0);i++){
		if(adjMatrix[vertex][i]==1&&visited[i]!=1){
			dfsRecur(i);
			}
	}
}
private boolean checkSec(int i, int k) {
	boolean result = false;
	for(int j=0;j<data.get(0);j++)
		if(adjMatrix[k][j]==1)
			result = checkThird(i,j,k);
	return result;
}
private boolean checkThird(int i, int j,int k) {
	if(adjMatrix[j][i]==1){
		System.out.println("Triangle found at verticies: "+i+k+j);
		return true;
		}
		else{
			return false;
		}
}
private void  readInputData()  { 
	int nextInt;
	try {
		inFile = new Scanner(new File(filename));
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	while(inFile.hasNext()){
		nextInt=inFile.nextInt();
		data.add(nextInt);
	}
	initMatrix(); //init. matrix values to 0
}
private void initMatrix(){
	int matrixSize;
	matrixSize=data.get(0); //First value is matrix size.
	adjMatrix = new int [matrixSize+1][matrixSize+1];
	for(int i=1;i<matrixSize;i++){//init. matrix to 0
		for(int k=2;k<matrixSize;k++){
			adjMatrix[i][k]=0;
		}
	}
}
private void loadMatrix(){
	int k=2;
	for(int i=1;i<data.size();i=i+2){ 
		adjMatrix[data.get(i)][data.get(k)]=1;
		k+=2;
	}
}

private void loadNeighbors(Integer vertex, int[] visited2) {
	for (int k=0;k<data.get(0);k++){
		if(adjMatrix[vertex][k]==1&&(visited2[k]!=1)){
			dataQueue.add(k);
		}
		}
	}

private void loadNeighborsPaths(Integer integer, int[] visited2) {
	for (int k=0;k<data.get(0);k++){
		if(adjMatrix[integer][k]==1&&(visited2[k]!=1)){
			dataQueue.add(k);
			if(paths[k]==-1)
				paths[k]=integer;//update path array
		}
	}	
}

public void initPath(int[] paths2){
	for(int i=0;i<paths2.length;i++)
		paths[i]=-1;
}
}
