import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map.Entry;


public class UtilityTest {

    Hashtable<String, Double> documentSimilarities;
    List<Entry<String, Double>> rankedDocuments;

    public class MyEntry<K, V> implements Entry<K, V> {
        private final K key;
        private V value;
        public MyEntry(final K key) {
            this.key = key;
        }
        public MyEntry(final K key, final V value) {
            this.key = key;
            this.value = value;
        }
        public K getKey() {
            return key;
        }
        public V getValue() {
            return value;
        }
        public V setValue(final V value) {
            final V oldValue = this.value;
            this.value = value;
            return oldValue;
        }
    }

    @Before
    public void loadData() {
        documentSimilarities = new Hashtable<>();
        documentSimilarities.put("a.java",1.0);
        documentSimilarities.put("b.java",2.0);
        documentSimilarities.put("c.java",1.5);

        rankedDocuments = new ArrayList<>();
        rankedDocuments.add(new MyEntry<String,Double>("b.java", 2.0));
        rankedDocuments.add(new MyEntry<String,Double>("c.java", 1.5));
        rankedDocuments.add(new MyEntry<String,Double>("a.java", 1.0));
    }

    @Test
    public void testRankSimilarDocuments() {
        List<Entry<String, Double>> listOfEntries = Utility.rankSimilarDocuments(documentSimilarities);
        Assert.assertEquals(rankedDocuments, listOfEntries);
    }

    @Test
    public void testGetLemma() {
        Assert.assertEquals("shape", Utility.getLemma("shaped"));
    }

}
