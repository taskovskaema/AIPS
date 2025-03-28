import java.util.*;

class SLLNode<E> {
    protected E element;
    protected SLLNode<E> succ;

    public SLLNode(E elem, SLLNode<E> succ) {
        this.element = elem;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}

class MapEntry<K, E> {
    // Each MapEntry object is a pair consisting of a key
    // and a value (an arbitrary object).
    K key;
    E value;

    public MapEntry(K key, E val) {
        this.key = key;
        this.value = val;
    }

    public String toString() {
        return "<" + key + "," + value + ">";
    }
}

class CBHT<K, E> {
    // An object of class CBHT is a closed-bucket hash table, containing
    // entries of class MapEntry.
    private SLLNode<MapEntry<K, E>>[] buckets;

    @SuppressWarnings("unchecked")
    public CBHT(int m) {
        // Construct an empty CBHT with m buckets.
        buckets = (SLLNode<MapEntry<K, E>>[]) new SLLNode[m];
    }

    private int hash(K key) {
        // Translate key to an index of the array buckets.
        return Math.abs(key.hashCode()) % buckets.length;
    }

    public SLLNode<MapEntry<K, E>> search(K targetKey) {
        // Find which if any node of this CBHT contains an entry whose key is equal to targetKey.
        // Return a link to that node (or null if there is none).
        int b = hash(targetKey);
        SLLNode<MapEntry<K, E>> currNode = buckets[b];
        while (currNode != null) {
            MapEntry<K, E> currEntry = currNode.element;
            if (currEntry.key.equals(targetKey)) return currNode;
            else currNode = currNode.succ;
        }
        return null;
    }

    public void insert(K key, E val) {
        // Insert the entry <key, val> into this CBHT.
        // If entry with same key exists, overwrite it.
        MapEntry<K, E> newEntry = new MapEntry<>(key, val);
        int b = hash(key);
        SLLNode<MapEntry<K, E>> currNode = buckets[b];
        while (currNode != null) {
            MapEntry<K, E> currEntry = currNode.element;
            if (currEntry.key.equals(key)) {
                // Make newEntry replace the existing entry ...
                currNode.element = newEntry;
                return;
            } else currNode = currNode.succ;
        }
        // Insert newEntry at the front of the SLL in bucket b ...
        buckets[b] = new SLLNode<>(newEntry, buckets[b]);
    }

    public void delete(K key) {
        // Delete the entry (if any) whose key is equal to key from this CBHT.
        int b = hash(key);
        SLLNode<MapEntry<K, E>> predNode = null, currNode = buckets[b];
        while (currNode != null) {
            MapEntry<K, E> currEntry = currNode.element;
            if (currEntry.key.equals(key)) {
                if (predNode == null) buckets[b] = currNode.succ;
                else predNode.succ = currNode.succ;
                return;
            } else {
                predNode = currNode;
                currNode = currNode.succ;
            }
        }
    }

    public String toString() {
        String temp = "";
        for (int i = 0; i < buckets.length; i++) {
            temp += i + ":";
            SLLNode<MapEntry<K, E>> curr = buckets[i];
            while (curr != null) {
                temp += curr.element.toString() + " ";
                curr = curr.succ;
            }
            temp += "\n";
        }
        return temp;
    }
}

class Ponudi {
    public String data;
    public String vreme;
    public String grad;
    public int zarabotka;

    public Ponudi(String data, String vreme, String grad, int zarabotka) {
        this.data = data;
        this.vreme = vreme;
        this.grad = grad;
        this.zarabotka = zarabotka;
    }
}


public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int n = input.nextInt();

        CBHT<String, Ponudi> najdobraPonudi = new CBHT<>(2 * n);

        for (int i = 0; i < n; i++) {
            String data = input.next();
            String vreme = input.next();
            String grad = input.next();
            int zarabotka = input.nextInt();

            if (najdobraPonudi.search(data) != null) {
                if (zarabotka > najdobraPonudi.search(data).element.value.zarabotka) {
                    Ponudi ponudi = new Ponudi(data, vreme, grad, zarabotka);
                    najdobraPonudi.insert(data, ponudi);
                }
            } else {
                Ponudi ponudi = new Ponudi(data, vreme, grad, zarabotka);
                najdobraPonudi.insert(data, ponudi);
            }
        }

        String baranaData = input.next();

//      SLLNode<MapEntry<String, Ponudi>> entry= najdobraPonudi.search(baranaData); -> if( entry==null) .....

        if(najdobraPonudi.search(baranaData)==null){
            System.out.println("No offers");
        }else{
            System.out.println(najdobraPonudi.search(baranaData).element.value.vreme + " " + najdobraPonudi.search(baranaData).element.value.grad + " " + najdobraPonudi.search(baranaData).element.value.zarabotka);
        }
    }
}


