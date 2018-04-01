/**
 * 
 * @author Abdel K. Bokharouss
 */

public class QLearner {  
    /**
     * This method performs Q-learning.
     * @param rewards the rewards matrix
     * @param paths an array of paths to go along
     * @param gamma the learning rate gamma
     * @param noIterations the number of times the paths have to be traversed
     * @return a string representation of the policy where an integer x at position s in the string (ignoring the spaces)
     * means that for state s, a step to state x is the preferred action. If s a final state, x should equal "n"
     */
    public String execute(Integer[][] rewards, Integer[][] paths, Double gamma, Integer noIterations)
    {   
        // InitializeQ
        final int noStates = rewards.length; // the number of states
        final Double[][] Q = new Double[noStates][noStates];
        for (int i = 0; i < noStates; i++) {
            for (int j = 0; j < noStates; j++) {
                if (rewards[i][j] != null) { // check if it is a legal move
                    Q[i][j] = 0d;  // legal move
                } else {
                    Q[i][j] = null; // illegal move
                }
            }
        }
        // Do Q-learning
        for (int iter = 0; iter < noIterations; iter++) { // allow {@code: noIterations} over n paths, where n = paths.length
            // should handle the given paths in order and do so for the given number of iterations ({@code: noIterations})
            for (int path = 0; path < paths.length; path++) {
                execute(Q, rewards, paths[path], gamma);
            }
        }
        // Q should now contain the right values
        return policy(Q); // return a string representation of the policy
    }
    
    /**
     * do Q-learning for one path.
     * @param Q evaluation matrix
     * @param rewards the rewards matrix
     * @param path an array of a path to go along
     * @param gamma the learning rate gamma
     */
    private void execute(Double[][] Q, Integer[][] rewards, Integer[] path, Double gamma) {
        int currentState = path[0]; // starting state
        int nextState = 0; 
        if (path.length <= 1) { // no actions, (only starting state)
            return; 
        }
        for (int action = 1; action < path.length; action++) { // for each action in the given path
            nextState = path[action]; 
            if (rewards[currentState][nextState] != null) { // check for illegal move
                // iterate over all the actions of the next state and set maxQvalue to the highest found value
                double maxQvalue = -1d;
                for (int nextAction = 0; nextAction < Q[nextState].length; nextAction++) { // iterate over the actions
                    if (Q[nextState][nextAction] != null) { // check if it is a legal move to this state
                        if (Q[nextState][nextAction] > maxQvalue) { // check if it is better than the best found value up until now
                            maxQvalue = Q[nextState][nextAction];
                        }
                    }
                }
                // iteration over the next state actions (and values) has been completed --> 
                // calculate the update of Q[s][a] where s is the current state and a the next state (according to the specified path)
                Q[currentState][nextState] = (double) rewards[currentState][nextState] + gamma * maxQvalue; 
            }
            currentState = nextState; // update current state
        }
    }

    /**
     * Return a string representation of the policy
     * @param Q evaluation matrix
     * @return a string representation of the policy where an integer x at position s in the string (ignoring the spaces)
     * means that for state s, a step to state x is the preferred action. If s a final state, x should equal "n"
     */
    private String policy(Double[][] Q) {
        String policyRepresentation =  "";
        for (Double[] stateActions : Q) {
            double bestMoveValue = -1d;
            String bestMoveState = " n"; // to indicate final staes
            for (int action = 0; action < stateActions.length; action++) { // find highest value
                if (stateActions[action] != null) { // check if it is a legal move
                    if (stateActions[action] >= bestMoveValue) {
                        bestMoveValue = stateActions[action];
                        bestMoveState = " " + // seperate integers with an empty space
                                Integer.toString(action); // highest action (i.e. state) will be set if multiple actions  have the same value
                    } 
                }
            }
            policyRepresentation += bestMoveState; // append result for this state
        }
        return policyRepresentation.substring(1, policyRepresentation.length()); // remove empty space character at the beginning
    }
}