package csula.cs4660.games;

import csula.cs4660.games.models.MiniMaxState;
import csula.cs4660.graphs.Graph;
import csula.cs4660.graphs.Node;

import java.util.List;
public class MiniMax {

/*
function minimax(graph, sourceNode, depth, maximizingPlayer) {
    // usually being optimized in a way to compute even before
    // the end of game by **evaluate** function

    if (depth = 0 || sourceNode is a leaf) {
        return evaluate(soureNode.gameState); // return a number
    }

    if (maximizingPlayer) {
        bestValue = Number.MAX_VALUE; // positive infinite
        for (node in graph.neighbors(sourceNode)) {
            value = minimax(node, graph, depth - 1, false);
            bestValue = Math.max(bestValue, value);
        }
        return bestValue;
    } else {
        bestValue = Number.MIN_VALUE; // negative infinite
        for (node in graph.neighbors(sourceNode)) {
            value = minimax(node, graph, depth - 1, true);
            bestValue = Math.min(bestValue, value);
        }
        return bestValue;
    }
}
 */

    public static Node getBestMove(Graph graph, Node root, Integer depth, Boolean max) {
        // TODO: implement minimax to retrieve best move
        // NOTE: you should mutate graph and node as you traverse and update value


        minMax(graph,root,depth,max);

        return null;
    }


    public static Integer minMax(Graph graph, Node root, Integer depth, Boolean player) {

        MiniMaxState currentState = (MiniMaxState) root.getData();
        int rootValue = ((MiniMaxState) root.getData()).getValue();

        List<Node> child = graph.neighbors(root);


        //Returning Source Node if its Zero
        if ((depth == 0) || (child.size() == 0)) {
            int i = ((MiniMaxState) root.getData()).getValue();
            return i;
        }

        int bestValue = 0;
        //Players Turn
        if (player) {

            bestValue = Integer.MIN_VALUE; //Negative

            for (Node node : child) {
                Integer nodeValue = minMax(graph, root, depth - 1, !player);
                bestValue = Integer.max(bestValue, nodeValue);
            }
            return bestValue;


        } else  {
            bestValue = Integer.MAX_VALUE;
            for (Node node : child) {
                Integer nodeValue = minMax(graph, root, depth - 1, player);
                bestValue = Integer.max(bestValue, nodeValue);
            }
            return bestValue;
        }
    }
}

