package jgraphics;
import java.util.ArrayList;
import java.util.List;

import net.dean.jraw.RedditClient;
import net.dean.jraw.http.NetworkAdapter;
import net.dean.jraw.http.OkHttpNetworkAdapter;
import net.dean.jraw.http.UserAgent;
import net.dean.jraw.models.Listing;
import net.dean.jraw.models.OAuthData;
import net.dean.jraw.models.Submission;
import net.dean.jraw.models.Subreddit;
import net.dean.jraw.models.SubredditSort;
import net.dean.jraw.models.TimePeriod;
import net.dean.jraw.oauth.Credentials;
import net.dean.jraw.oauth.OAuthHelper;
import net.dean.jraw.pagination.BarebonesPaginator;
import net.dean.jraw.pagination.DefaultPaginator;
import net.dean.jraw.pagination.Paginator;

public class RedditHandler {
	
	
	ArrayList<String> frontpagesummary = new ArrayList<String>();
    UserAgent userAgent = new UserAgent("uob_bot", "com.example.usefulbot", "v0.1", "uobcs_testreddit");
    Credentials credentials = Credentials.script("uobcs_testreddit", "uobtest",
    "7AC5Ejz2s85-Bw", "bs00x4GBNrfYgTUkcXMcmnQS00s");
    NetworkAdapter adapter = new OkHttpNetworkAdapter(userAgent);
    RedditClient reddit = OAuthHelper.automatic(adapter, credentials);
    
	
	
      public static void main(String[] args) {
		RedditHandler reddit = new RedditHandler(); 
		System.out.print("testing hello world");
		reddit.frontpage();
		//reddit.frontpagetwo();
	}
    
    public List<String> subimages() {

    	DefaultPaginator<Submission> earthPorn = reddit.subreddits("EarthPorn", "spaceporn").build();

    	List<String> images = new ArrayList<String>();
    	for (Submission s : earthPorn.next()) {
    	    if (!s.isSelfPost() && s.getUrl().contains("i.imgur.com")) {
    	        images.add(s.getUrl());
    	        System.out.println(s.getUrl());
    	    }
    	}
    	return images; 
    }
    	
    public ArrayList<String> frontpage() {

   	 ArrayList<String> frontpage = new ArrayList<String>();
   	 DefaultPaginator<Submission> paginator = reddit.frontPage()
   			    .limit(50) // 50 posts per page
   			   // .sorting(SubredditSort.TOP) // top posts
   			    .timePeriod(TimePeriod.DAY) // of all time
   			    .build();


   		Listing<Submission> submissions = paginator.next();
   		for (Submission s : submissions) {
   		    System.out.println(s.getTitle());
   		    System.out.println(s.getAuthor());
   		    frontpage.add(s.getTitle());
   		    frontpagesummary.add(s.getSelfText());
   		    
   		}
   		return frontpage; 
    }
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
//    	BarebonesPaginator<Subreddit> paginator = reddit.me().subreddits("subscriber")
//    		    // Request as many items as possible
//    		    .limit(Paginator.RECOMMENDED_MAX_LIMIT)
//    		    .build();
//
//    		List<Subreddit> subscribed = new ArrayList<Subreddit>();
//
//    		// Paginator implements Iterable, so we can use the enhanced for loop to iterate the Paginator until reddit
//    		// can't give us anything else. Don't do this for posts on a subreddit or the front page!
//    		for (Listing<Subreddit> page : paginator) {
//    			System.out.print(page);
//    		    subscribed.addAll(page);
//    		}
//
//    		// Do something with `subscribed`
//    		 System.out.print("PRINTING FIRST ELEMENT");
//    	 System.out.print(subscribed.get(1).getTitle());


    

    public ArrayList<String> frontpageself() {
        
   	 ArrayList<String> frontpage = new ArrayList<String>();
     DefaultPaginator<Submission> frontPage = reddit.frontPage()
   		    .sorting(SubredditSort.TOP)
   		    .timePeriod(TimePeriod.DAY)
   		    .limit(20)
   		    .build();

   		Listing<Submission> submissions = frontPage.next();
   		for (Submission s : submissions) {
   		    frontpage.add(s.getAuthor());
   		
   		}
   		return frontpage; 
   }


}