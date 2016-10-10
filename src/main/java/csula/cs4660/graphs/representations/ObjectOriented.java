package csula.cs4660.graphs.representations;

import csula.cs4660.graphs.Edge;
import csula.cs4660.graphs.Node;
import java.io.FileNotFoundException;
import java.io.File;
import java.lang.reflect.Array;
import java.util.*;
import java.util.Collection;
import java.util.ArrayList;
/**
 * Object oriented representation of graph is using OOP approach to store nodes
 * and edges
 *   Author: Jose Virgen
 * TODO: Please fill the body of methods in this class
 */
public class ObjectOriented implements Representation  {

    //private Collection<Node> nodes = new ArrayList<>();

    private ArrayList<Edge> edges;
    private ArrayList<Node> nodes;
    private HashMap<Node,ArrayList<Edge>> hMap;

    public ObjectOriented(File file) {

        try {
            Scanner input = new Scanner(file);
            String[] tokens;
            String line = input.nextLine();

            int numberOfNodes = Integer.parseInt(line);
            System.out.println(numberOfNodes);

            hMap = new HashMap<>();
            nodes = new ArrayList<>();

            while (input.hasNextLine()) {
                line = input.nextLine();
                tokens = line.split(":");
                int weight = Integer.parseInt(tokens[2]);

                Node fromNode = new Node(tokens[0]);
                Node toNode = new Node(tokens[1]);

                nodes.add(fromNode);
                nodes.add(toNode);

                if(!hMap.containsKey(fromNode)){
                    edges = new ArrayList<>();
                    edges.add(new Edge(fromNode, toNode, weight));
                    hMap.put(fromNode, edges);
                }else{
                    edges = hMap.get(fromNode);
                    edges.add(new Edge(fromNode,toNode,weight));
                }
            }

//            for(Node n : hMap.keySet()) {
//                for (Edge e : hMap.get(n)) {
//                    System.out.println(e);
//                }
//            }

        } catch (FileNotFoundException e) {

            System.out.println("Error");
        }
    }
    public ObjectOriented() {

    }
    @Override
    public boolean adjacent(Node x, Node y) {
        boolean status = false;

        for(Edge e : edges){
            if((e.getTo().equals(x)) && e.getFrom().equals(y) || e.getTo().equals(y) && e.getFrom().equals(x)) {
                status = true;
            }
            else{
                status = false;
            }
        }

        return status;
    }
    @Override
    public List<Node> neighbors(Node x) {

        List<Node> neighbors  = new ArrayList<>();

        for(Edge e : edges){
            if(e.getFrom().equals(x)){
                neighbors.add(e.getTo());
            }
        }

        if(neighbors != null){
            return neighbors;
        }

        return neighbors;
    }

    @Override
    public boolean addNode(Node x) {
        boolean status;
        if(hMap.containsKey(x)){
            status = false;
        }
        else{
            edges = new ArrayList<>();
            hMap.put(x,edges);
            status = true;
        }
        return status;
    }

    @Override
    public boolean removeNode(Node x) {
        boolean status = true;

        if (!nodes.contains(x))
            return false;

        nodes.remove(x);    //Removing the node from the list.

        for (Edge e : edges) {
            if (e.getTo().equals(x) || e.getFrom().equals(x)) {
                nodes.remove(x);
                return status;

            }
        }
        return status;
    }

    @Override
    public boolean addEdge(Edge x) {

//        Node toNode = x.getFrom();
//        hMap.get(toNode).add(x);
//        return hMap.get(toNode).contains(x);

        if(!edges.contains(x)){
            edges.add(x);
            return true;
        }
        return false;



    }

    @Override
    public boolean removeEdge(Edge x) {

        Node fromNode = x.getFrom();
        hMap.get(fromNode).remove(x);

       return !hMap.get(fromNode).contains(x);
    }

    @Override
    public int distance(Node from, Node to) {
        int distance = 0;
        for (Edge x : edges) {
            if(from.equals(x.getFrom()) && to.equals(x.getTo())){
                distance = x.getValue();
            }
        }
        return distance;
    }

    @Override
    public Optional<Node> getNode(int index) {
        return null;
    }
}
