import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class Vertex {
    int ID;
    String label;
    double weight;
    LinkedList<Edge> edges = new LinkedList<>(); 
    HashMap<String,String> properties = new HashMap<String,String>();
    
    Vertex(int ID){
        this.ID = ID;
        label = new String("unknown");
        weight = -1;
        properties = null;
    }

    Vertex(String label, Double weight){
        this.label = label;
        this.weight = weight;
        this.ID = -1;
        properties = null;
    }

    Vertex(int ID, String label, Double weight){
        this.ID = ID;
        this.label = label;
        this.weight = weight;
        properties = null;
    }

    Vertex(int ID, String label, Double weight, HashMap<String,String> map){
        this.ID = ID;
        this.label = label;
        this.weight = weight;
        properties = map;
    }

    Vertex(Vertex other){
        this.ID = other.ID;
        this.label = other.label;
        this.weight = other.weight;
        properties = other.properties;
    }

    public void addProperties(String key, String value){
        properties.put(key, value);
    }

    public double getWeight(){
        return weight;
    }

    public String getLabel(){
        return label;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Adds new edge to list
     */
    public void addEdge(int ID, double weight){
        Edge newEdge = new Edge(ID, weight);
        edges.add(newEdge);
    }


    /**
     * Removes edge with given ID from linkedlist of edges
     * @param vertexID
     */
    public void removeEdge(int vertexID){
        ListIterator<Edge> iterator = edges.listIterator();
        int index = 0;
        boolean found = false;
        Edge tempEdge;
        while(iterator.hasNext()){
            tempEdge = iterator.next();
            if(tempEdge.getID() == vertexID){
                found = true;
                break;
            }
            index++;
        }
        if(found == true) edges.remove(index);
    }
    
    
    /**
     * Returns edge with given vertexID
     */
    public Edge getEdge(int vertexID){
        ListIterator<Edge> iterator = edges.listIterator();
        Edge tempEdge;

        while(iterator.hasNext()){
            tempEdge = (Edge) iterator.next();
            if(tempEdge.getID() == vertexID){
                return tempEdge;
            }
        }
        return null;
    }

    
    /**
     * Prints edge informations
     */
    public void printEdge(){
        if(edges==null) return;
        
        ListIterator iterator = edges.listIterator();
        Edge tempEdge;
        System.out.printf("=======<EDGES>    ");
        while(iterator.hasNext()){
            tempEdge = (Edge) iterator.next();
            System.out.printf(" <|| ID :> %d  - Weight:%.1f ||>  ",tempEdge.getID(),tempEdge.getWeight());
        }

    }


    /**
    * Search edges according to given key and filter
    */
    public boolean compareVertices(String key,String filter) throws Exception{
        
        if(properties == null) return false;
        String str = properties.get(key);
        if(str != null ){
            if(str.compareTo(filter)==0){
                return true;
            }
            else{
                return false;
            }
        }
        return false;
     
    }

    /**
     * Returns number of edges connected to this vertex
     */
    public int getEdgeSize(){
        return edges.size();
    }

    /**
     * Returns iterator
     * @return linkedlist iterator
     */
    public Iterator<Edge> edgeIterator() {
        return  edges.listIterator();
    }

    
    //-------------------------------EDGE CLASS ----------------------------------------//
    //-------------------------------EDGE CLASS ----------------------------------------//
    //-------------------------------EDGE CLASS ----------------------------------------//
    public static class Edge {
        private int ID;
        private double weight;
        public static double UNWEIGHTED_EDGE = 1.0;
        
        Edge(int ID, double weight){
            this.ID = ID;
            this.weight = weight;
        }

        Edge(int ID){
            this.ID = ID;
            this.weight = UNWEIGHTED_EDGE;
        }
        
        public int getID(){
            return ID;
        }
    
        public double getWeight(){
            return weight;
        }
        
    }

}
