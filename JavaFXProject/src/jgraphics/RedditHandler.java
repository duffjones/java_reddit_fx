package jgraphics;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    	
    	
   
    
    public Image madeimage() {
        
    	
    Image image = new Image("https://i.imgur.com/bfvBnwD.png", true) ; 
    

   		return image; 
   }
    
    public ArrayList<String> frontpageself() {
        
   	 ArrayList<String> frontpage = new ArrayList<String>();
     DefaultPaginator<Submission> frontPage = reddit.frontPage()
   		    //.sorting(SubredditSort.TOP)
   		    .timePeriod(TimePeriod.DAY)
   		    .limit(20)
   		    .build();

   		Listing<Submission> submissions = frontPage.next();
   		for (Submission s : submissions) {
   		    frontpage.add(s.getSelfText());
   		
   		}
   		return frontpage; 
   }


}