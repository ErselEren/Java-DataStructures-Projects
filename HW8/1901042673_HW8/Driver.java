import java.util.HashMap;

public class Driver {
    public static void main(String [] aStrings) throws Exception{
        
        
        //Instantiating undirected graph
        System.out.printf("\n--->Creating undirected graph and adding vertex and edges<---\n");
        MyGraph graph1 = new MyGraph(6, false);
        

        graph1.addVertex(new Vertex("label0", 0.0));
        graph1.addVertex(new Vertex("label1", 1.0));
        graph1.addVertex(new Vertex("label2", 2.0));
        graph1.addVertex(new Vertex("label3", 3.0));
        graph1.addVertex(new Vertex("label4", 4.0));
        graph1.addVertex(new Vertex("label5", 5.0));
        graph1.addVertex(new Vertex("label6", 6.0));
        graph1.addVertex(new Vertex("label7", 7.0));
        graph1.addVertex(new Vertex("label8", 8.0));
        graph1.addVertex(new Vertex("label9", 9.0));

        graph1.addEdge(0 , 3 , 3);
        graph1.addEdge( 0, 1, 4 );
        graph1.addEdge( 3, 2, 7 );
        graph1.addEdge( 1, 2, 2);
        graph1.addEdge( 2 , 9, 1);
        graph1.addEdge( 2, 8, 5 );
        graph1.addEdge( 1 , 4, 3);
        graph1.addEdge( 1 , 6 , 6);
        graph1.addEdge( 1 , 7 , 5);
        graph1.addEdge( 4 , 7 , 9);
        graph1.addEdge( 4 , 6 , 5);
        graph1.addEdge( 4 , 5 , 6 );
        graph1.addEdge( 6 , 7 , 8);
    
        graph1.printGraph();
        System.out.printf("\n-----------------------------------------------------------------------------------------------------------------------\n");
        
        System.out.printf("\n--->Creating same graph as directed <---\n");
        MyGraph graph2 = new MyGraph(6, true);

        graph2.addVertex(new Vertex("label0", 0.0));
        graph2.addVertex(new Vertex("label1", 1.0));
        graph2.addVertex(new Vertex("label2", 2.0));
        graph2.addVertex(new Vertex("label3", 3.0));
        graph2.addVertex(new Vertex("label4", 4.0));
        graph2.addVertex(new Vertex("label5", 5.0));
        graph2.addVertex(new Vertex("label6", 6.0));
        graph2.addVertex(new Vertex("label7", 7.0));
        graph2.addVertex(new Vertex("label8", 8.0));
        graph2.addVertex(new Vertex("label9", 9.0));

        graph2.addEdge(0 , 3 , 3);
        graph2.addEdge( 0, 1, 4 );
        graph2.addEdge( 3, 2, 7 );
        graph2.addEdge( 1, 2, 2);
        graph2.addEdge( 2 , 9, 1);
        graph2.addEdge( 2, 8, 5 );
        graph2.addEdge( 1 , 4, 3);
        graph2.addEdge( 1 , 6 , 6);
        graph2.addEdge( 1 , 7 , 5);
        graph2.addEdge( 4 , 7 , 9);
        graph2.addEdge( 4 , 6 , 2);
        graph2.addEdge( 4 , 5 , 6 );
        graph2.addEdge( 6 , 7 , 8);

        graph2.printGraph();
        System.out.printf("\n-----------------------------------------------------------------------------------------------------------------------\n");
        
        System.out.printf("\n---> Removing edges from graph2 with source and dest <---\n");
        
        graph2.removeEdge(2, 9);
        graph2.removeEdge(0, 3);
        graph2.printGraph();
        
        System.out.printf("\n-----------------------------------------------------------------------------------------------------------------------\n");
        System.out.printf("\n---> Removing vertices from graph2 with ID and label <---\n");

        graph2.removeVertex(9);
        graph2.removeVertex("label3");

        graph2.printGraph();

        System.out.printf("\n-----------------------------------------------------------------------------------------------------------------------\n");

        System.out.printf("\n---> Adding vertex and edge into graph2 after removing edges and vertices <---\n");
        graph2.addVertex(new Vertex("newLabel", 3.2));
        graph2.addEdge(3, 8, 3.8);
        graph2.printGraph();

        System.out.printf("\n-----------------------------------------------------------------------------------------------------------------------\n");
        if(graph2.isEdge(6, 7)) System.out.printf("\n6 to 7 IS EDGE");
        else System.out.printf("\n6 to 7 NOT EDGE");
        if(graph2.isEdge(8, 2)) System.out.printf("\n8 to 2 IS EDGE");
        else System.out.printf("\n8 to 2 NOT EDGE");
        if(graph2.isEdge(5, 1)) System.out.printf("\n5 to 1 IS EDGE");
        else System.out.printf("\n5 to 1 NOT EDGE");
        if(graph2.isEdge(8, 11)) System.out.printf("\n8 to 11 IS EDGE");
        else System.out.printf("\n8 to 11 NOT EDGE");
        if(graph2.isEdge(23, 0)) System.out.printf("\n23 to 0 IS EDGE");
        else System.out.printf("\n23 to 0 NOT EDGE");
        if(graph2.isEdge(1, 4)) System.out.printf("\n1 to 4 IS EDGE");
        else System.out.printf("\n1 to 4 NOT EDGE");

        System.out.printf("\n-----------------------------------------------------------------------------------------------------------------------\n");
        System.out.printf("\n ---> Exporting Weight Matrix of Graph1 <---\n");
        double[][] array = graph1.exportMatrix();
        System.out.printf("    ");
        for(int i=0;i<array[0].length;i++) System.out.printf(" %d ",i);
        System.out.printf("\n");
        
        for(int i=0;i<array[0].length;i++){
            System.out.printf(" %d |",i);
            for(int j=0;j<array[0].length;j++){
                if(array[i][j]==0) System.out.printf(" - ");
                else System.out.printf(" %.0f ",array[i][j]);
            }
            System.out.printf("\n");
        }

        
        System.out.printf("\n-----------------------------------------------------------------------------------------------------------------------\n");
        System.out.printf("\n ---> BFS and DFS Calculations <---\n");
        graph1.differenceOfDistances();


        
        System.out.printf("\n\n\n");
    }
}
