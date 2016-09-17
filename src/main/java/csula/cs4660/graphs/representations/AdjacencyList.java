package csula.cs4660.graphs.representations;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import csula.cs4660.graphs.Edge;
import csula.cs4660.graphs.Node;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Adjacency list is probably the most common implementation to store the unknown
 * loose graph
 *
 * TODO: please implement the method body
 */

public class AdjacencyList implements Representation {

    private Map<Node, Collection<Edge>> adjacencyList;
    private Multimap<Node, Edge> map;
    private List<Node> nodes;
    private ArrayList<Edge> edges;

    public AdjacencyList(File file) {

        try{
            Scanner input = new Scanner(file);
            int nod = Integer.parseInt(input.nextLine());
            System.out.println(nod);

            map = ArrayListMultimap.create();
            nodes = new ArrayList<>();
            String [] tokens;

            while(input.hasNextLine()){
                    String line = input.nextLine();
                    tokens = line.split(":");
                    int weight = Integer.parseInt(tokens[2]);

                    Node fromNode = new Node(tokens[0]);
                    Node toNode = new Node(tokens[1]);
                    nodes.add(fromNode);
                    nodes.add(toNode);
                    Edge edge = new Edge(fromNode,toNode,weight);
                    map.put(fromNode,edge);
            }

            //Testing
            for(Node n : map.keySet()) {
               for (Edge e : map.get(n)) {
                   System.out.println(e);
                }
            }

        }catch(FileNotFoundException e){
            System.out.print("Error");
        }
    }

    public AdjacencyList() {

    }
    @Override
    public boolean adjacent(Node x, Node y) {

        boolean status = false;
        List<Edge> edges = new ArrayList<>();
        edges.addAll(map.get(x));

        for(Edge e : edges){
            if(y.equals(e.getTo())){
                status = true;
            }
        }
        return status;
    }

    @Override
    public List<Node> neighbors(Node x) {

        List<Node> neighbors = new ArrayList<>();

        for(Edge edge : map.get(x)){
            neighbors.add(edge.getTo());
        }

        return neighbors;
    }
    @Override
    public boolean addNode(Node x) {

        boolean status;
        if(nodes.contains(x)){
            status = false;
        }else{
            nodes.add(x);
            status = true;
        }
           return status;
    }

    @Override
    public boolean removeNode(Node x) {
        return false;
    }

    @Override
    public boolean addEdge(Edge x) {




        return false;
    }

    @Override
    public boolean removeEdge(Edge x) {
        Node fromNode = x.getFrom();
        map.get(fromNode).remove(x);

        return !map.get(fromNode).contains(x);
    }
    @Override
    public int distance(Node from, Node to) {



        return 0;
    }

    @Override
    public Optional<Node> getNode(int index) {
        return null;
    }
}
