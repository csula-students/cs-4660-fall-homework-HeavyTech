package csula.cs4660.graphs.searches;

import csula.cs4660.graphs.Edge;
import csula.cs4660.graphs.Graph;
import csula.cs4660.graphs.Node;

import java.lang.reflect.Array;
import java.util.*;

/**
 * As name, dijkstra search using graph structure
 */
public class DijkstraSearch implements SearchStrategy {
    /* Pseudocode

    function Dijkstra(Graph, source):
2:  	for each vertex v in Graph:	// Initialization
3:	        dist[v] := infinity	// initial distance from source to vertex v is set to infinite
4:	        previous[v] := undefined	// Previous node in optimal path from source
5:	    dist[source] := 0	// Distance from source to source
6:	    Q := the set of all nodes in Graph	// all nodes in the graph are unoptimized - thus are in Q
7:
        while Q is not empty:	// main loop
8:	            u := node in Q with smallest dist[ ]
9:	            remove u from Q
10:	            for each neighbor v of u:	// where v has not yet been removed from Q.
11:	                alt := dist[u] + dist_between(u, v)
12:	                if alt < dist[v]	// Relax (u,v)
13:	                    dist[v] := alt
14:	                    previous[v] := u
15:	       return previous[ ]
     */
    @Override
    public List<Edge> search(Graph graph, Node source, Node dist) {

        int distance = 0;
        Node endNode = null;

        HashMap<Node,Node> nodeMap = new HashMap<>();
        List<Node> acum = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        ArrayList<Edge> edges = new ArrayList<>();

        queue.add(source);

        for(Node n : graph.neighbors(source)){
            if(!n.equals(source )){
                distance = graph.distance(source,n);
            }
            queue.add(n);
        }

        while(!queue.isEmpty()){
            Node curr = queue.poll();

            for(Node n : graph.neighbors(curr)){
                if(!acum.contains(n)){
                    nodeMap.put(n,curr);
                }
                if(distance < graph.distance(n,curr)){
                    distance = graph.distance(n,curr);
                    Edge edge = new Edge(n,curr,graph.distance(n,curr));
                    queue.poll();
                    edges.add(edge);
                }

            }
        }

        ArrayList<Edge> reversedPath = new ArrayList<>();

        //Reversing the Path.
        for(int i = edges.size()-1; i >= 0; i --){
            reversedPath.add(edges.get(i));
        }

        //Printing the result
        for(Edge e : reversedPath) {
            System.out.print(e);
        }
        return reversedPath;
    }
}
