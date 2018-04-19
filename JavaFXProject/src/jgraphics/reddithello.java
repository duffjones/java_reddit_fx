package jgraphics;
import net.dean.jraw.RedditClient;
import net.dean.jraw.http.NetworkAdapter;
import net.dean.jraw.http.OkHttpNetworkAdapter;
import net.dean.jraw.http.UserAgent;
import net.dean.jraw.models.Listing;
import net.dean.jraw.models.OAuthData;
import net.dean.jraw.models.Submission;
import net.dean.jraw.models.SubredditSort;
import net.dean.jraw.models.TimePeriod;
import net.dean.jraw.oauth.Credentials;
import net.dean.jraw.oauth.OAuthHelper;
import net.dean.jraw.pagination.DefaultPaginator;

public class reddithello {

    public static void main(String[] args) {
    	
    	UserAgent userAgent = new UserAgent("uob_bot", "com.example.usefulbot", "v0.1", "uobcs_testreddit");

    	
      Credentials credentials = Credentials.script("uobcs_testreddit", "uobtest",
      "7AC5Ejz2s85-Bw", "bs00x4GBNrfYgTUkcXMcmnQS00s");
    	
      NetworkAdapter adapter = new OkHttpNetworkAdapter(userAgent);
      
      
      RedditClient reddit = OAuthHelper.automatic(adapter, credentials);
      
      
      DefaultPaginator<Submission> frontPage = reddit.frontPage()
    		    .sorting(SubredditSort.TOP)
    		    .timePeriod(TimePeriod.DAY)
    		    .limit(30)
    		    .build();

    		Listing<Submission> submissions = frontPage.next();
    		for (Submission s : submissions) {
    		    System.out.println(s.getTitle());
    		}
      
      
      
      
      
      
      
      
      
      //RedditClient reddit = OAuthHelper.automatic(adapter, credentials);
    	//RedditClient reddit = OAuthHelper.automatic(adapter, Credentials.script);
    }

    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	//        // Prints "Hello, World" to the terminal window.
//        System.out.println("Hello, World");
//        
//        
//        
//        RedditClient redditClient = new RedditClient(userAgent);
//        
//        
//        
//     // Create our credentials

//
//        
//        
//        OAuthData authData = redditClient.getOAuthHelper().easyAuth(credentials);
//        redditClient.authenticate(authData);
//        
//        
//        // This is what really sends HTTP requests
//       
//
//
//        
//        
//        
//        
        
        
        
        
        
        
        

}