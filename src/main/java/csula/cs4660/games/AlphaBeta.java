package csula.cs4660.games;

import csula.cs4660.games.models.MiniMaxState;
import csula.cs4660.graphs.Graph;
import csula.cs4660.graphs.Node;
import java.util.List;

/*

 function alphabeta(node, depth, α, β, maximizingPlayer)
      if depth = 0 or node is a terminal node
          return the heuristic value of node
      if maximizingPlayer
          v := -∞
          for each child of node
              v := max(v, alphabeta(child, depth – 1, α, β, FALSE))
              α := max(α, v)
              if β ≤ α
                  break (* β cut-off *)
          return v
      else
          v := ∞
          for each child of node
              v := min(v, alphabeta(child, depth – 1, α, β, TRUE))
              β := min(β, v)
              if β ≤ α
                 break (* α cut-off *)
          return v

 */


public class AlphaBeta {
    public static Node getBestMove(Graph graph, Node source, Integer depth, Integer alpha, Integer beta, Boolean max) {
        // TODO: implement your alpha beta pruning algorithm here

        List<Node> children = graph.neighbors(source);
        MiniMaxState mmState;
        Node<MiniMaxState> node;

        if ((depth == 0) || (children.size() == 0)) {
            mmState =  new MiniMaxState(0,(Integer)source.getData());
            node = new Node<>(mmState);
            return node;
        }


        if(max) {

            int bestValue = Integer.MIN_VALUE;

            for (Node n : children) {
                int value = ((MiniMaxState) getBestMove(graph, source, depth - 1, alpha, beta, false).getData()).getValue();
                bestValue = Math.max(bestValue, value);

                if (bestValue > alpha) {
                    alpha = bestValue;
                } else if (beta <= alpha) {
                    break;
                }

            }
            
        }
          else{
            int bestValue = Integer.MAX_VALUE;

            for (Node n : children) {
                int value = ((MiniMaxState) getBestMove(graph, source, depth - 1, alpha, beta, false).getData()).getValue();
                bestValue = Math.max(bestValue, value);


                if (bestValue < alpha) {
                    beta = bestValue;

                } else if (beta >= alpha) {
                    break;
                }

            }


            }

        }




        //Testing
        return null;
    }
}
