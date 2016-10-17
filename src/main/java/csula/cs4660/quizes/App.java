package csula.cs4660.quizes;

import csula.cs4660.quizes.models.DTO;
import csula.cs4660.quizes.models.State;

import java.util.*;

/**
 * Here is your quiz entry point and your app
 */
public class App {
    public static void main(String[] args) {
        // to get a state, you can simply call `Client.getState with the id`
        State initialState = Client.getState("10a5461773e8fd60940a56d2e9ef7bf4").get();
        State endingState = Client.getState("e577aa79473673f6158cc73e0e5dc122").get();
        System.out.println(initialState);
        System.out.println(endingState);
        // to get an edge between state to its neighbor, you can call stateTransition
        System.out.println(Client.stateTransition(initialState.getId(), initialState.getNeighbors()[0].getId()));


    }


    private static void BFS(State initialState, State endingState) {

        Queue<State> queue = new LinkedList<>();
        Set<State> visited = new HashSet<>();
        Map<State, State> parent = new HashMap<>();


        queue.add(initialState);
        visited.add(initialState);


        while (!queue.isEmpty()) {
            State currState = queue.poll();
            visited.add(currState);

            if (currState.getId().equals("e577aa79473673f6158cc73e0e5dc122")) {

                
            }


        }
    }

    public static int findPath(Map<State, State> parents, State current, State start) {

        State state = current;
        int depth = 0;

        while (!state.equals(start)) {
            depth++;
            state = parents.get(state);
        }
        return depth;


    }
}