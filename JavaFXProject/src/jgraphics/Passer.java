package jgraphics;

public class Passer {
    private final static Passer instance = new Passer();

    public static Passer getInstance() {
        return instance;
    }

    private String subreddit = new String();

    public void setSubreddit(String subname) {
    	subreddit = subname; 
    }
    
    public String currentSubreddit() {
        return subreddit;
    }
}