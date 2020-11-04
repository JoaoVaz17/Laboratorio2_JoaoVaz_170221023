//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;

//import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;
import pt.pa.adts.BookmarkManager;

import static org.junit.Assert.*;

public class BookmarkManagerTest {

    private BookmarkManager manager;
    @Before
    public void setUp() {
       manager= new BookmarkManager();
    }

    @Test
    public void getTotalEntries() {
        assertEquals(0,manager.getTotalEntries());
        manager.addBookmarkFolder("bookmarks", "Redes Sociais");
        manager.addBookmarkFolder("bookmarks", "Diversos");
        assertEquals(2,manager.getTotalEntries());
        manager.addBookmarkEntry("Diversos", "Gmail", "http://www.gmail.com");
        assertEquals(3,manager.getTotalEntries());
        manager.addBookmarkEntry("Diversos", "StackOverflow", "http://www.stackoverflow.com");
        //assertEquals(4,manager.getTotalEntries());
        assertEquals(2, manager.getTotalLinks());
    }

    @Test
    public void ShouldGet0TotalLinks(){
        assertEquals(0,manager.getTotalLinks());
    }

    @Test
    public void ShouldGet2TotalLinks(){
        assertEquals(0,manager.getTotalLinks());
        manager.addBookmarkFolder("bookmarks", "Diversos");
        manager.addBookmarkEntry("Diversos", "Gmail", "http://www.gmail.com");
        manager.addBookmarkEntry("Diversos", "StackOverflow", "http://www.stackoverflow.com");
        assertEquals(2,manager.getTotalLinks());
    }

    @Test
    public void ShouldGetRightLinkCheck(){
        manager.addBookmarkFolder("bookmarks", "Diversos");
        manager.addBookmarkEntry("Diversos", "Gmail", "http://www.gmail.com");
        manager.addBookmarkEntry("Diversos", "StackOverflow", "https://www.gmail.com");
        assertEquals(2,manager.getTotalLinks());
    }

    @Test
    public void ShouldMove(){
        manager.addBookmarkFolder("bookmarks", "Diversos");
        manager.addBookmarkFolder("bookmarks", "Redes Sociais");
        manager.addBookmarkEntry("Diversos", "facebook", "https://www.facebook.com");
        String p1 = manager.getParentFolder("facebook");
        manager.moveEntryToFolder("facebook","Redes Sociais");
        String p2 = manager.getParentFolder("facebook");
        assertFalse(p1.equals(p2));
    }

    @Test
    public void ShouldMoveAndGoBack(){
        manager.addBookmarkFolder("bookmarks", "Diversos");
        manager.addBookmarkFolder("bookmarks", "Redes Sociais");
        manager.addBookmarkEntry("Diversos", "facebook", "https://www.facebook.com");
        String p1 = manager.getParentFolder("facebook");
        manager.moveEntryToFolder("facebook","Redes Sociais");
        String p2 = manager.getParentFolder("facebook");
        manager.moveEntryToFolder("facebook","Diversos");
        String p3 = manager.getParentFolder("facebook");
        assertFalse(p1.equals(p2));
        assertFalse(p3.equals(p2));
        assertTrue(p1.equals(p3));
    }


}