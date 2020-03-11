
import java.util.*;

public class Dictionary {

    private Hashtable<String, Double> idfs = new Hashtable<>();

    public Hashtable<String, Set<String>> getInvertedIndex() {
        return invertedIndex;
    }

    private Hashtable<String, Set<String>> invertedIndex = new Hashtable<>();

    public Dictionary(List<JavaClass> javaClasses) {
        this.invertedIndex = buildInvertedIndex(javaClasses);
        this.idfs = calculateIdfs(javaClasses.size());
    }

    public Hashtable<String, Double> getIdfs() {
        return idfs;
    }

    /**
     * Build the inverted index for the given Java files.
     * An inverted index is an index data structure storing a mapping from content, such as word,
     * to a set of documents that containing this content. In simple words,
     * it is a hashmap like data structure that directs you from a word to (a) document(s) that contain this word.
     * For example, we have three Java files:
     * a.java has tokens [“for”, “i”, “i”, “hello”]
     * b.java has tokens [“for”, “hello”]
     * c.java has tokens ["i"]
     * the invered index would be hashtable, key is the token, value are the documents containing this token, like
     * {"for":{"a.java", "b.java"},
     * "i":{"a.java", "b.java},
     * "hello":{"a.java", "c.java"}}
     * @param javaClasses
     * @return invertedIndex
     * TO DO
     */
    public Hashtable<String, Set<String>> buildInvertedIndex(List<JavaClass> javaClasses){
        Hashtable<String, Set<String>> invertedIndex = new Hashtable<>();
        for (JavaClass javaClass : javaClasses) {
            String fileName = javaClass.getFileName();
            for(String term : javaClass.getTerms()){
                if(!invertedIndex.containsKey(term)) {
                    Set<String> set = new HashSet<>();
                    set.add(fileName);
                    invertedIndex.putIfAbsent(term,set);
                }else {
                    invertedIndex.get(term).add(fileName);
                }
            }
        }
        return invertedIndex;
    }

    /**
     * calculate idfs for each term - log(N/m) - N - documents count, m - number of documents containing given term
     * the return is a hashtable, value is the token, value is the token's idf.
     * @param numberOfDocs
     * @return idfs
     * TO DO
     */
    public Hashtable<String, Double> calculateIdfs(int numberOfDocs) {
        Hashtable<String, Double> idfs = new Hashtable<>();
        for(Map.Entry entry : this.getInvertedIndex().entrySet()){
            Set<String> set = (Set<String>) entry.getValue();
            int m = set.size();
            idfs.put(String.valueOf(entry.getKey()), Math.log((double)numberOfDocs/(double)m));
        }
        return idfs;
    }

}
