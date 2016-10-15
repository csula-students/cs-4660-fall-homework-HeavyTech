package csula.cs4660.graphs.searches;

import csula.cs4660.graphs.Edge;
import csula.cs4660.graphs.Graph;
import csula.cs4660.graphs.Node;
import java.util.Collections;

import java.util.*;

/**
 * Breadth first search
 */
public class BFS implements SearchStrategy {

    @Override
    public List<Edge> search(Graph graph, Node source, Node dist){

        System.out.println("-------------");
        System.out.println("     BFS     ");
        System.out.println("-------------");


        //Creating an empty queue
        Queue queue = new LinkedList<Node>();

        HashMap<Node,Node> parent = new HashMap<>();
        List<Node> passedNode = new ArrayList<Node>();  //List that keeps track of all node
        List<Node> resultList = new ArrayList<>();  //The List that contains the path of nodes
        List<Edge> edges = new ArrayList<>(); // List that contains the edges

        Node lastNode = dist; //Will Hold the last Node



        while(!queue.isEmpty()){

            Node currNode = (Node)queue.poll();

            for(Node n : graph.neighbors(currNode)){
                if(!passedNode.contains(n)){
                    parent.put(n,currNode);
                    if(n.equals(dist)){
                        lastNode = new Node(n.getData());
                    }
                    else if(!resultList.contains(n)){
                        queue.add(n);
                        resultList.add(n);
                        parent.put(n,currNode);
                    }
                    queue.add(n);
                    passedNode.add(n);
                }
            }
        }

        while(!lastNode.equals(source)){

               Node node = parent.get(lastNode);//This will the root parent node
               Edge e = new Edge(node,lastNode,graph.distance(node,lastNode));
               edges.add(e);
               lastNode = node;

        }
        //Reverse List
        ArrayList<Edge> reversedList = new ArrayList<>();

        for(int i = edges.size() - 1; i >= 0; i--){
            reversedList.add(edges.get(i));
        }

        return edges;
    }
}
