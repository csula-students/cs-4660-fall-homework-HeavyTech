package csula.cs4660.graphs.searches;

import csula.cs4660.graphs.Edge;
import csula.cs4660.graphs.Graph;
import csula.cs4660.graphs.Node;
import java.util.*;
import java.util.List;
import java.util.Queue;


/**
 * Breadth first search
 */
public class BFS implements SearchStrategy {

    Node lastNode;

    @Override
    public List<Edge> search(Graph graph, Node source, Node dist) {


        System.out.println("-------------");
        System.out.println("     BFS     ");
        System.out.println("-------------");

        //Creating the empty queue
        Queue queue = new LinkedList<Node>();

        Map<Node,Node> parent = new HashMap<>();

        //This will hold the nodes that have been visited
        List<Node> passedNode = new ArrayList<>();
        List<Node> result = new ArrayList<>();


        queue.add(source);
        passedNode.add(source);

        while(!queue.isEmpty()) {

            Node currNode = (Node) queue.poll();

            for (Node n : graph.neighbors(currNode)) {
                if (!passedNode.contains(n)) {
                    parent.put(n, currNode);
                    if (n.equals(dist)) {
                        lastNode = new Node(n.getData());
                    }
                    else if(!result.contains(n)){  //Make sure every Node has been visited

                        queue.add(n);
                        result.add(n);
                        parent.put(n,currNode);
                    }
                    queue.add(n);
                    passedNode.add(n);
                }
            }
        }


    }
}
