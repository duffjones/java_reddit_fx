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
	ArrayList<String> frontpageimglinks = new ArrayList<String>();
	ArrayList<String> frontpagetitles = new ArrayList<String>();
	
	
	
    UserAgent userAgent = new UserAgent("uob_bot", "com.example.usefulbot", "v0.1", "uobcs_testreddit");
    Credentials credentials = Credentials.script("uobcs_testreddit", "uobtest",
    "7AC5Ejz2s85-Bw", "bs00x4GBNrfYgTUkcXMcmnQS00s");
    NetworkAdapter adapter = new OkHttpNetworkAdapter(userAgent);
    RedditClient reddit = OAuthHelper.automatic(adapter, credentials);
    
	
	
      public static void main(String[] args) {
		RedditHandler reddit = new RedditHandler(); 
		System.out.print("testing hello world");
		reddit.getRedditData();
		//reddit.frontpagetwo();
	}
     	
    
    public void getRedditData() {

      	 DefaultPaginator<Submission> paginator = reddit.subreddits("EarthPorn", "spaceporn")
       		    //.sorting(SubredditSort.TOP)
       		    .timePeriod(TimePeriod.DAY)
       		    .limit(10)
       		    .build();

      		Listing<Submission> submissions = paginator.next();
      		for (Submission s : submissions) {
      		    System.out.println(s.getTitle());
      		    System.out.println(s.getAuthor());
      		    frontpagesummary.add(s.getSelfText());
      		    frontpagetitles.add(s.getTitle());
      		    frontpageimglinks.add(s.getUrl());

      		}
       }
    
    public ArrayList<String> getSubredditImgs(String subreddit) {
    	
    	ArrayList<String> sublinks = new ArrayList<String>(); 
     	 DefaultPaginator<Submission> paginator = reddit
     			 .subreddit(subreddit)
     			    .posts()
     			    .limit(20)
     			    .sorting(SubredditSort.TOP)
     			    .timePeriod(TimePeriod.WEEK)
        		    .build();

       		Listing<Submission> submissions = paginator.next();
       		for (Submission s : submissions) {
       			if (!s.isSelfPost() 
       					//&& s.getUrl().contains("i.imgur.com")
       					) {
       				sublinks.add(s.getUrl());
       		    }
       		    
       		}
       		
       		if(sublinks.size() > 3) {
       		return sublinks;
    }
       		else return null; 
       		}
       	
    public ArrayList<String> frontpage() { return frontpagetitles;}
       
    public ArrayList<String> frontpageself() {return frontpagesummary; }
    
    public ArrayList<String> frontpageimages() {return frontpageimglinks;}

    public Image madeimage() {
    	Image image = new Image("https://i.imgur.com/bfvBnwD.png", true) ; 
    	return image; 
    }


}