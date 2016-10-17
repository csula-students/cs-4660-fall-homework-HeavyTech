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


        search(initialState,endingState);


    }


    private static void search(State initialState, State endingState) {

        Queue<State> queue = new LinkedList<>();
        Set<State> visited = new HashSet<>();
        Map<State, State> parent = new HashMap<>();


        queue.add(initialState);
        visited.add(initialState);


        //e577aa79473673f6158cc73e0e5dc122

        while (!queue.isEmpty()) {
            State currState = queue.poll();
            visited.add(currState);

            for(State s : Client.getState(currState.getId()).get().getNeighbors()){

                if (s.getId().equals(endingState)) {
                    System.out.println("Path with a depth of " + findPath(parent,currState,initialState));
                }
                else if(!visited.contains(s)){
                    parent.put(s,currState);
                    queue.add(s);
                }
            }


        }
    }

    public static int findPath(Map<State, State> parents, State current, State start) {

        State state = current;
        int path = 0;

        while (!state.equals(start)) {
            path++;
            state = parents.get(state);
        }
        return path;


    }
}