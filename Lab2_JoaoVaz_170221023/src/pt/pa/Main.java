/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.pa;

import pt.pa.adts.BookmarkManager;
import pt.pa.model.BookmarkInvalidOperation;

/**
 *
 * @author brunomnsilva
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //     take comments when BookmarkManager is implemented
        try {
            BookmarkManager manager = new BookmarkManager();
            
            manager.addBookmarkFolder("bookmarks", "Jornais");
            manager.addBookmarkFolder("Jornais", "Finanças");
            manager.addBookmarkFolder("bookmarks", "Redes Sociais");
            manager.addBookmarkFolder("bookmarks", "Diversos");
            manager.addBookmarkFolder("Jornais","Desportivo");
            
            manager.addBookmarkEntry("Jornais", "Publico", "http://www.publico.pt");
            manager.addBookmarkEntry("Jornais", "Expresso", "http://www.expresso.pt");
            manager.addBookmarkEntry("Finanças", "Diário Económico", "http://economico.sapo.pt/");
            manager.addBookmarkEntry("Desportivo", "A Bola", "https://www.abola.pt");
            manager.addBookmarkEntry("Desportivo", "Record", "https://www.record.xl.pt");

            manager.addBookmarkEntry("Redes Sociais", "Facebook", "http://www.facebook.com");
            manager.addBookmarkEntry("Redes Sociais", "Instagram", "http://www.instagram.com");
            
            manager.addBookmarkEntry("Diversos", "Gmail", "http://www.gmail.com");
            manager.addBookmarkEntry("Diversos", "StackOverflow", "http://www.stackoverflow.com");
            
            manager.addBookmarkEntry("bookmarks", "IPS", "http://www.ips.pt");

            System.out.println(manager);

            System.exit(0);

        } catch (BookmarkInvalidOperation exception) {
            System.err.println(exception.getMessage());
            exception.printStackTrace();
        }
    }
    
}
