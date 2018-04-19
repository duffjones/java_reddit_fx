package jgraphics;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
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
	
    UserAgent userAgent = new UserAgent("uob_bot", "com.example.usefulbot", "v0.1", "uobcs_testreddit");
    
    Credentials credentials = Credentials.script("uobcs_testreddit", "uobtest",
    "7AC5Ejz2s85-Bw", "bs00x4GBNrfYgTUkcXMcmnQS00s");
  	
    NetworkAdapter adapter = new OkHttpNetworkAdapter(userAgent);
    
    RedditClient reddit = OAuthHelper.automatic(adapter, credentials);
	
	
      
    
    public ArrayList<String> frontpage() {
    
    	 ArrayList<String> frontpage = new ArrayList<String>();
    	
    	
      DefaultPaginator<Submission> frontPage = reddit.frontPage()
    		    .sorting(SubredditSort.TOP)
    		    .timePeriod(TimePeriod.DAY)
    		    .limit(15)
    		    .build();

    		Listing<Submission> submissions = frontPage.next();
    		for (Submission s : submissions) {
    		    System.out.println(s.getTitle());
    		    frontpage.add(s.getTitle());
    		}
    		
    		return frontpage; 
    }

        

}