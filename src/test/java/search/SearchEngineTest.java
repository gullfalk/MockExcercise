package search;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author nilstes
 */
public class SearchEngineTest {
    
    public SearchEngineTest() {
    }

    String[] facebook = {"facebook", "like", "reacted", "friend", "commented", "request", "like", "later"};
    String[] youtube = {"comment", "upvote", "subscribe", "unsubscribe", "like", "later"};
    String[] netflix = {"Bojack", "Netflix", "Original", "episodes", "rate", "later", "later"};

    PageReader readerMock;
    SearchEngine searchEngine;



    @Before
    public void setUp() {
        readerMock = mock(PageReader.class);
        searchEngine = new SearchEngine(readerMock);
        when(readerMock.readPage("www.facebook.com")).thenReturn(facebook);
        when(readerMock.readPage("www.youtube.com")).thenReturn(youtube);
        when(readerMock.readPage("www.netflix.com")).thenReturn(netflix);

        searchEngine.indexPage("www.youtube.com");
        searchEngine.indexPage("www.facebook.com");
        searchEngine.indexPage("www.netflix.com");
    }

    @Test
    public void testThatPageReaderWorks() {
        ArrayList<String> likeList = new ArrayList<String>();
        likeList.add("www.facebook.com");
        likeList.add("www.youtube.com");
        assertEquals(searchEngine.search("like"), likeList);

        ArrayList<String> bojackList = new ArrayList<String>();
        bojackList.add("www.netflix.com");
        assertEquals(searchEngine.search("Bojack"), bojackList);

        ArrayList<String> laterList = new ArrayList<String>();
        laterList.add("www.netflix.com");
        laterList.add("www.facebook.com");
        laterList.add("www.youtube.com");
        assertEquals(searchEngine.search("later"), laterList);
    }

    
}
