import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Queue;


public class MyGraph implements DynamicGraph{

    private int numV;
    private int size;
    private boolean isDirected;
    private ArrayList<Vertex> vertices;
    private Vertex dummy = null;
    private int dummyCount;


    int[] parent1;
    int[] visited1;

    MyGraph(int numV, boolean directed){
        this.numV = numV;
        this.isDirected = directed;
        size = 0;
        dummyCount = 0;
        vertices = new ArrayList<>(numV);

    }
    
    /**
     * Generate a new vertex by given parameters. 
     */
    @Override
    public Vertex newVertex(String label, Double weight) {
        return new Vertex(numV,label,weight);
    }



    /**
     * Add the given vertex to the graph.
     */
    @Override
    public void addVertex(Vertex new_vertex) {
        //checks dummy reference first
        int i;
        if(dummyCount>0){
            for(i=0;i<vertices.size();i++){
                if(vertices.get(i)==dummy) break;
            }
            vertices.set(i, new_vertex);
            dummyCount--;
            size++;
        }
        else{
            vertices.add(new_vertex);
            vertices.get(vertices.size()-1).setID(vertices.size()-1);
        }
        
    }




    /**
     * Add an edge between the given two vertices in the graph.
     */
    @Override
    public void addEdge(int vertexID1, int vertexID2, double weight) {
        vertices.get(vertexID1).addEdge(vertexID2, weight);
        if(!isDirected())
            vertices.get(vertexID2).addEdge(vertexID1, weight);
    }




    /**
     * Remove the vertex from the graph with respect to the given vertex id.
     */
    @Override
    public void removeEdge(int vertexID1, int vertexID2){
        if(vertexID1 >= 0 && vertexID1 < vertices.size() && vertexID2 >= 0 && vertexID2 < vertices.size())
            vertices.get(vertexID1).removeEdge(vertexID2);

        if(isDirected() == false)
        vertices.get(vertexID2).removeEdge(vertexID1);
    }




    /**
     * Remove the vertex from the graph with respect to the given vertex id.
     */
    @Override
    public void removeVertex(int vertexID) {
        if(vertexID >= 0 && vertexID < numV){
            for(int i=0;i<numV;i++){
                vertices.get(i).removeEdge(vertexID);
            }
        }
        
        vertices.set(vertexID, dummy);
        dummyCount++;
    }




    /**
     * Remove the vertices that have the given label from the graph.
     */
    @Override
    public void removeVertex(String label) {
        int index;
        for(index=0;index<vertices.size();index++)
            if(vertices.get(index).getLabel().compareTo(label) == 0)
                break;
        
        removeVertex(index);
    }

    

    /**
	 * Return the number of vertices
	 * @return The number of vertices
	 */
    @Override
    public int getNumV() {
        return numV;
    }


    
    /**
	 * Determine whether this is a directed graph
	 * @return True if this is a directed graph
	 */
    @Override
    public boolean isDirected() {
        return isDirected;
    }



    /**
	 * Determine whether an edge exists
	 * @param source The source vertex
	 * @param dest The destination vertex
	 * @return true if there is an edge from source to dest
	 */
    @Override
    public boolean isEdge(int source, int dest) {
        if(source>=0 && source < vertices.size() && dest >= 0 && dest < vertices.size()){
            Vertex.Edge temp = vertices.get(source).getEdge(dest);
            if(temp == null) return false;
            return true;
        }
        return false;
    }


    

    /**
	 * Get edge between two vertices
	 * @param source The source vertex
	 * @param dest The destination vertex
	 * @return The Edge between these two vertices,
	 *         or an Edge with a weight of 
	 *         Double.POSITIVE_INFINITY if there is no edge
	 */
    @Override
    public Vertex.Edge getEdge(int source, int dest) {
        return vertices.get(source).getEdge(dest);
    }




    /**
     * Print the graph in adjacency list format (You should use the format that can be imported by the method in AbstarctGraph in the book).
     */
    @Override
    public void printGraph() {
        for(int i=0;i<vertices.size();i++){
            if(vertices.get(i) != dummy){
                System.out.printf("\n || ID:%d - Weight:%.1f || ",i, vertices.get(i).getWeight());
                vertices.get(i).printEdge();
            }
            
        }
    }

    /**
     * Returns vertex with given ID
     */
    public Vertex getVertex(int ID){
        return vertices.get(ID);
    }


    /**
     * Generate the adjacency matrix representation of the graph and returns the matrix.
     */
    @Override
    public double[][] exportMatrix() {
        double[][] array= new double[vertices.size()][vertices.size()];
        
        //passing values to matrix
        for(int i=0;i<vertices.size();i++){
            for(int j=0;j<vertices.get(i).getEdgeSize();j++){
                int tempID = vertices.get(i).edges.get(j).getID();
                double weight = vertices.get(i).edges.get(j).getWeight();
                array[i][tempID] = weight;
            }
        }


        if(!isDirected()){
            for(int i=0;i<vertices.size();i++){
                for(int j=0;j<vertices.size();j++){
                    array[j][i] = array[i][j];
                }
            }
        }
        return array;
    }
    
    /**
     * Private helper method for calculating levels in BFS search
     */
    private void setLevels(double [][] array){
        for(int i=0;i<vertices.size();i++) array[1][i] = -1;

        array[1][0] = 0;
        
        boolean flag = true;
        while(flag){
            for(int i=1;i<vertices.size();i++){
                double parent = array[0][i];
                double parentLevel = array[1][(int) parent];
    
                if(parentLevel != -1)
                    array[1][i] = parentLevel + 1;  
            }

            flag = false;
            for(int i=0;i<vertices.size();i++){
                if(array[1][i] == -1){
                    flag = true;
                    break;
                }
            }
        }
    }

    public void differenceOfDistances(){
        double BFS = shortestPathBFS();
        double DFS = shortestPath_DFS();
        double difference = BFS - DFS;
        System.out.printf("\n Distance of BFS : %.1f \n Distance of DFS : %.1f \n Difference : %.1f ",BFS,DFS,difference);
    }

    /**
     * Initialize BFS search algorithm and returns distance of shortest path
     */
    private double shortestPathBFS(){
        //instead of using 3 different array, I used multidimensional array
        double[][] parent = new double[3][vertices.size()];
        double distance = 0;
        findLevels(parent);
        
        for(int i=0;i<parent[2].length;i++) parent[2][i] = -1;

        Queue<Integer> theQueue = new LinkedList<Integer>(); 
        
        boolean[] identified = new boolean[vertices.size()];
        identified[0] = true;
        theQueue.offer(0);

        while(!theQueue.isEmpty()){
            int current = theQueue.remove();
            Iterator<Vertex.Edge> itr = vertices.get(current).edgeIterator();
            while(itr.hasNext()){
                Vertex.Edge edge = itr.next();
                int neighbor = edge.getID();

                if(parent[1][current] +1 == parent[1][neighbor]){
                    if(parent[2][neighbor] > edge.getWeight() || parent[2][neighbor] == -1){
                        parent[2][neighbor] = edge.getWeight();
                        theQueue.offer(neighbor);
                    }
                }
            }

        }
        for(int i=1;i<parent[2].length;i++) 
            distance += parent[2][i];
        
        return distance;
    }


    /**
     * 
     * @param parent
     */
    private void findLevels(double [][] parent){
        Queue<Integer> theQueue = new LinkedList<Integer>(); 

        for (int i = 0; i < vertices.size(); i++) parent[0][i] = -1;

        boolean[] identified = new boolean[vertices.size()];

        identified[0] = true;
        theQueue.offer(0);
        while(!theQueue.isEmpty()){
            int current = theQueue.remove();
            Iterator<Vertex.Edge> itr = vertices.get(current).edgeIterator();
            while(itr.hasNext()){
                Vertex.Edge edge = itr.next();
                int neighbor = edge.getID();

                if(!identified[neighbor]){
                    identified[neighbor] = true;
                    theQueue.offer(neighbor);
                    parent[0][neighbor] = current;
                }
            }
            
        }

        setLevels(parent);

    }

    /**
     * Initialize DFS search algorithm and returns distance of shortest path
     */
    private double shortestPath_DFS(){
        
        int n = vertices.size();
        parent1 = new int[n];
        visited1 = new int[n];
        for(int i=0;i<n;i++){
            visited1[i] = -1;
        } 

        for (int i = 0; i < n; i++) {
            if (visited1[i]==-1)
                DFS(i);
        }

        double distance = 0;
        for(int i=1;i<parent1.length;i++){
            distance += getEdge(i, parent1[i]).getWeight();
        }
        return distance;
    }
    
    
    private void DFS(int current){
        int minID = -1;
        double minWeight = Double.POSITIVE_INFINITY;

        Iterator<Vertex.Edge> itr = vertices.get(current).edgeIterator();
        while(itr.hasNext()){
            Vertex.Edge edge = itr.next();
            int neighbor = edge.getID();
            
            if(edge.getWeight() <= minWeight && visited1[neighbor] == 0){
                minID = edge.getID();
                minWeight = edge.getWeight();
            }
            
            if(visited1[neighbor]==-1){
                visited1[current] = 0;
                DFS(neighbor);
            }

        }
        
        parent1[current] = minID;
        visited1[current] = 1;
        
    }


    //---------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------

    
    
    /**
     * Filter the vertices by the given user-defined property and returns a subgraph of the graph.
     * @throws Exception
     */
    @Override
    public MyGraph filterVertices(String key, String filter) throws Exception {
        MyGraph newGraph = new MyGraph(1, isDirected());
        ArrayList<Integer> filtered = new ArrayList<>();

        for(int i=0;i<numV;i++){
            if(vertices.get(i).compareVertices(key,filter) == true){
                filtered.add(i);
            }
        }

        for(int i=0;i<filtered.size();i++){
            Vertex temp = new Vertex( vertices.get(filtered.get(i)));
            newGraph.addVertex(temp);
        }

       
        return newGraph;
    }

}
