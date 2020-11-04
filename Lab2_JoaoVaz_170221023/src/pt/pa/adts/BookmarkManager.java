package pt.pa.adts;

import pt.pa.model.BookmarkEntry;
import pt.pa.model.BookmarkInvalidOperation;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;

public class BookmarkManager {
    TreeLinked<BookmarkEntry> bookmarks;

    public BookmarkManager(){
        bookmarks = new TreeLinked<>(new BookmarkEntry("bookmarks"));
    }


    private Position<BookmarkEntry> find(String key){
        Position<BookmarkEntry> aux = bookmarks.root(); // cria uma root para começar a percorrer a arvore
        return findGilberto(key, aux);
    }

    private Position<BookmarkEntry> findGilberto(String key,Position<BookmarkEntry> root){ // função auxiliar para o find

        if(root.element().getKey().equals(key)){return root;} //verifica se é a solução
        Iterable<Position<BookmarkEntry>> list = bookmarks.children(root); // vai buscar a lista de filhos de um nó
        for(Position<BookmarkEntry> pos : list) //percorrer a lista de filhos
        {
            Position<BookmarkEntry> sol = findGilberto(key,pos); //so da return se for a solução
            if(sol != null){ //se nao for null é pq é solução
                return sol;
            }
        }
        return null;
    }

    private boolean exists(String key){
        return find(key) != null;
    }

    public void addBookmarkFolder(String keyParent, String keyFolder) throws BookmarkInvalidOperation {
        if(find(keyFolder) != null){
            throw new BookmarkInvalidOperation("Este separador já existe");
        }
        else{
            Position<BookmarkEntry> parent = find(keyParent);
            if(parent != null){
                BookmarkEntry filho = new BookmarkEntry(keyFolder);
                bookmarks.insert(parent, filho);
            }else{
            throw new BookmarkInvalidOperation("Não é possivel criar um separador nesta localização");}
        }

    }

    public void addBookmarkEntry(String keyParent, String keyEntry,String url) throws BookmarkInvalidOperation{
        if(find(keyEntry) != null){
            throw new BookmarkInvalidOperation("Esta chave ja existe ja existe");
        }
        else{
            Position<BookmarkEntry> parent = find(keyParent);
            if(parent != null){
                if(url.contains("http://www.")||url.contains("https://www.")){
                BookmarkEntry filho = new BookmarkEntry(keyEntry, url);
                bookmarks.insert(parent, filho);}
            }else{
            throw new BookmarkInvalidOperation("Não é possivel criar uma entrada nesta localização");}
        }
    }
    public int getTotalEntries(){
        //Position<BookmarkEntry> aux = bookmarks.root();
        //return getTotalEntriesAux(aux, 0);
        int count = -1;
        Iterable<Position<BookmarkEntry>> list = bookmarks.positions();
        for(Position<BookmarkEntry> pos : list){
                count++;
        }
        return count;

    }
    /*
    private int getTotalEntriesAux(Position<BookmarkEntry> root, int prevCount){

        Iterable<Position<BookmarkEntry>> list = bookmarks.children(root);
        for(Position<BookmarkEntry> pos : list){
            if(bookmarks.isInternal(pos)) {
                return getTotalEntriesAux(pos, prevCount);
            }
            return prevCount+1;
        }
        return prevCount;
    }
     */

    public String getParentFolder(String keyEntry) throws BookmarkInvalidOperation{

        if(!exists(keyEntry)){
            throw new BookmarkInvalidOperation("Esta chave não existe");
        }
        Position<BookmarkEntry> chave = find(keyEntry);
        String parent = bookmarks.parent(chave).toString();
        return parent;
    }

    void move(Position<BookmarkEntry> existingPosition, Position<BookmarkEntry> newParent){
        BookmarkEntry elem = existingPosition.element();
        bookmarks.insert(newParent, elem);
        bookmarks.remove(existingPosition);
    }

    public int getTotalLinks(){
        int count = 0;
        Iterable<Position<BookmarkEntry>> list = bookmarks.positions();
        for(Position<BookmarkEntry> pos : list){
            if(!pos.element().isFolder()) {
                count++;
            }
        }
        return count;
    }

    public void moveEntryToFolder(String keyEntry, String folder) throws BookmarkInvalidOperation{
        if(!exists(keyEntry) || !exists(folder)){throw new BookmarkInvalidOperation("Operação invalida, nao existe key Entry");}
        else {
            Position<BookmarkEntry> chave = find(keyEntry);
            Position<BookmarkEntry> parent = find(folder);
            if(!parent.element().isFolder()){throw new BookmarkInvalidOperation("This folder is not a folder");}
            else{
            BookmarkEntry elemento = chave.element();
            bookmarks.insert(parent, elemento);
            bookmarks.remove(chave);
            }
        }
    }
}
