public class QLearner {  
    /**
     * This method performs Q-learning. TODO complete documentation.
     * @param rewards
     * @param paths
     * @param gamma
     * @param noIterations
     * @return 
     */
    public String execute(Integer[][] rewards, Integer[][] paths, Double gamma, Integer noIterations)
    {
        final Double[][] Q = null;
        
        // InitializeQ
        
        // Do Q-learning
        
        return policy(Q);
    }
    
    /** do Q-learning for one path.  TODO complete documentation. */
    private void execute(Double[][] Q, Integer[][] rewards, Integer[] path, Double gamma) {
    
    }

    /**
     * computes the policy. TODO complete documentation.
     */
    private String policy(Double[][] Q) {
        return ""; // TODO compute policy
    }
}