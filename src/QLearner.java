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
     * @return 
     */
    public String execute(Integer[][] rewards, Integer[][] paths, Double gamma, Integer noIterations)
    {   
        // InitializeQ
        final int noStates = rewards.length; // the number of states
        final Double[][] Q = new Double[noStates][noStates];
        for (int i = 0; i < noStates; i++) {
            for (int j = 0; j < noStates; j++) {
                if (rewards[i][j] != null) {
                    Q[i][j] = 0d;
                } else {
                    Q[i][j] = null;
                }
            }
        }
        // Do Q-learning
        for (int iter = 0; iter < noIterations; iter++) { // allow {@code: noIterations} over n paths, where n = paths.length
            // should handle the given paths in order and do so for the given number of iterations {@code: noIterations}
            for (int path = 0; path < paths.length; path++) {
                execute(Q, rewards, paths[path], gamma);
            }
        }
        return policy(Q);
    }
    
    /**
     * do Q-learning for one path.
     * @param Q evaluation matrix
     * @param rewards the rewards matrix
     * @param path an array of a path to go along
     * @param gamma the learning rate gamma
     */
    private void execute(Double[][] Q, Integer[][] rewards, Integer[] path, Double gamma) {
        int currentState = path[0];
        int nextState = 0;
        if (path.length <= 1) { // no actions, (only starting state)
            return; 
        }
        for (int action = 1; action < path.length; action++) { // for each action in the given path
            nextState = path[action]; 
            if (rewards[currentState][nextState] != null) {
                double maxQvalue = 0d;
                for (int nextAction = 0; nextAction < Q[nextState].length; nextAction++) {
                    if (Q[nextState][nextAction] != null) { // legal move
                        if (Q[nextState][nextAction] > maxQvalue) {
                            maxQvalue = Q[nextState][nextAction];
                        }
                    }
                }
                Q[currentState][nextState] = (double) rewards[currentState][nextState] + gamma * maxQvalue;
            }
            currentState = nextState;
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
            String bestMoveState = " n";
            for (int action = 0; action < stateActions.length; action++) {
                if (stateActions[action] != null) {
                    if (stateActions[action] >= bestMoveValue) {
                        bestMoveValue = stateActions[action];
                        bestMoveState = " " + Integer.toString(action);
                    } 
                }
            }
            policyRepresentation += bestMoveState;
        }
        return policyRepresentation.substring(1, policyRepresentation.length()); // remove empty space character at the beginning
    }
}