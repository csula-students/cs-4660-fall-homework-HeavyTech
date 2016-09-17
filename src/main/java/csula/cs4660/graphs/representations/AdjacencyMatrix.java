package csula.cs4660.graphs.representations;

import com.google.common.collect.ArrayListMultimap;
import csula.cs4660.graphs.Edge;
import csula.cs4660.graphs.Node;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Adjacency matrix in a sense store the nodes in two dimensional array
 *
 * TODO: please fill the method body of this class
 */
public class AdjacencyMatrix implements Representation {
    //private Node[] nodes;
    private ArrayList<Node> nodes = new ArrayList<>();
    private int[][] adjacencyMatrix;

    public AdjacencyMatrix(File file) {

        try{
            Scanner input = new Scanner(file);
            int nod = Integer.parseInt(input.nextLine());
            System.out.println(nod);

            String [] tokens;
            adjacencyMatrix = new int[nod][nod];

            while(input.hasNextLine()){
                String line = input.nextLine();
                tokens = line.split(":");
                int weight = Integer.parseInt(tokens[2]);

                int fromNode = Integer.parseInt(tokens[0]);
                int toNode = Integer.parseInt(tokens[1]);

                adjacencyMatrix[fromNode][toNode] =1;

            }

        }catch(FileNotFoundException e){
            System.out.print("Error");
        }


    }

    public AdjacencyMatrix() {

    }

    @Override
    public boolean adjacent(Node x, Node y) {
        boolean status  = false;
         int row = (int)x.getData();
         int col = (int)y.getData();

        if(adjacencyMatrix[row][col] == 1){
            status = true;
        }

        return status;
    }

    @Override
    public List<Node> neighbors(Node x) {
        return null;
    }

    @Override
    public boolean addNode(Node x){
        boolean status = false;
        if(nodes.contains(x)){
            status = false;
        }
        nodes.add(x);
        int[][] matrx = adjacencyMatrix;

        //Creating a new matrix with adjusted Nodes.
        adjacencyMatrix = new int[nodes.size()][nodes.size()];

        for(int i = 0; i < nodes.size(); i ++){
            for(int j = 0; j < nodes.size(); j++){
                if(i == nodes.size() - 1 || j == nodes.size() -1){
                    adjacencyMatrix[i][j] = 0;
                }else{
                    adjacencyMatrix[i][j] = matrx[i][j];
                }
            }
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
        return false;
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
