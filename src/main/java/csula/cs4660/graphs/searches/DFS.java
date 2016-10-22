package csula.cs4660.graphs.searches;

import csula.cs4660.graphs.Edge;
import csula.cs4660.graphs.Graph;
import csula.cs4660.graphs.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Depth first search
 */
public class DFS implements SearchStrategy {
    @Override
    public List<Edge> search(Graph graph, Node source, Node dist) {

        return DFS(graph, source, dist, source, new HashMap<>());
    }


    public List<Edge> DFS(Graph graph, Node source, Node dist, Node currentNode, HashMap<Node, Node> nodeMap) {

        System.out.println("------------");
        System.out.println("----DFS-----");
        System.out.println("------------");

        List<Edge> resultPath = new ArrayList<>();

        if (currentNode.equals(dist)) {
            Node c = dist;
            Node parent = nodeMap.get(c);

            while (!c.equals(source)) {
                int distance = graph.distance(parent, c);
                resultPath.add((new Edge(parent, c, distance)));
                c = parent;
                parent = nodeMap.get(c);
            }
            return resultPath;
        }


        //Recursively creating a path
        for (Node n : graph.neighbors(currentNode)) {
            if (!nodeMap.containsKey(n)) {
                nodeMap.put(n, currentNode);
                resultPath = DFS(graph, source, dist, n, nodeMap);
            }

        }

        return resultPath;

    }
}
