class Entry<K,V>{
    final K key;
    V value;
    Entry<K,V> next;
    public Entry(K key, V value, Entry<K,V> next){
        this.key = key;
        this.value = value;
        this.next = next;
    }

    public K getKey(){
        return key;
    }

    public V getValue(){
        return value;
    }

}


class SimpleMap<K, V>{

    private Entry<K, V> buckets[];
    private int INITIAL_CAPACITY = 16;

    private int size = 0;


    public SimpleMap(){
        this.buckets = new Entry[INITIAL_CAPACITY];
    }

    public SimpleMap(int capacity){
        this.INITIAL_CAPACITY = capacity;
        this.buckets = new Entry[capacity];
    }

    public void put(K key, V value) {

        Entry<K,V> entry = new Entry<>(key, value, null);

        int index = getHash(key) % INITIAL_CAPACITY;

        Entry<K,V> existing = buckets[index];
        if(existing == null){
            buckets[index] = entry;
            size++;
        }else{
            //compare key to check if key exists
            //if exists then override value
            while(existing.next != null){
                if(existing.key.equals(key)){
                    existing.value = value;
                    return;
                }
                existing = existing.next.next;
            }

            //if not exist then assign value to next node
            if(existing.key.equals(key)){
                existing.value = value;
            }else{
                existing.next = entry;
                size++;
            }
        }
    }

    private int getHash(K key){
        return key.hashCode();
    }

    public V get(K key) {

        Entry<K, V> bucket = buckets[getHash(key)%INITIAL_CAPACITY];

        while(bucket != null){
            if(bucket.key.equals(key)){
                return bucket.value;
            }
            bucket = bucket.next;
        }
        return null;
    }
    public int size(){
        return size;
    }
}

public class MapImplementationCheck{

    public static void main(String arg[]){

        SimpleMap<String,String> map = new SimpleMap<>();
        map.put("NL", "Netherlands");
        map.put("UK", "Ukraine");
        map.put("DE", "Germany");
        map.put("UK", "United Kingdom");

        System.out.println(map.get("NL"));

        System.out.println(map.get("FR"));

    }
}