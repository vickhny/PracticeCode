public class HashMapImplementation {

    static class Entry<K, V> {
        final K key;
        V value;
        Entry<K, V> next;

        public Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        // getters, equals, hashCode and toString
    }

    public static class MyMap<K, V> {
        private Entry<K, V>[] buckets;
        private static final int INITIAL_CAPACITY = 1 << 4; // 16

        private int size = 0;

        public MyMap() {
            this(INITIAL_CAPACITY);
        }

        public MyMap(int capacity) {
            this.buckets = new Entry[capacity];
        }

        public void put(K key, V value) {
            Entry<K, V> entry = new Entry<>(key, value, null);

            int bucket = getHash(key) % getBucketSize();

            Entry<K, V> existing = buckets[bucket];
            if (existing == null) {
                buckets[bucket] = entry;
                size++;
            } else {
                // compare the keys see if key already exists
                while (existing.next != null) {
                    if (existing.key.equals(key)) {
                        existing.value = value;
                        return;
                    }
                    existing = existing.next;
                }

                if (existing.key.equals(key)) {
                    existing.value = value;
                } else {
                    existing.next = entry;
                    size++;
                }
            }
        }

        private int getHash(K key) {
            return key.hashCode() % INITIAL_CAPACITY;
        }

        public V get(K key) {
            Entry<K, V> bucket = buckets[getHash(key) % getBucketSize()];

            while (bucket != null) {
                if (bucket.key.equals(key)) {
                    return bucket.value;
                }
                bucket = bucket.next;
            }
            return null;
        }

        private int getBucketSize() {
            return buckets.length;
        }

        public int size() {
            return size;
        }
    }

    public static void main(String[] args) {
        MyMap<String, String> myMap = new MyMap<>();
        myMap.put("USA", "Washington DC");
        myMap.put("Nepal", "Kathmandu");
        myMap.put("India", "New Delhi");
        myMap.put("Australia", "Sydney");

        System.out.println(myMap);
        System.out.println(4 == myMap.size());
        System.out.println("Kathmandu".equalsIgnoreCase(myMap.get("Nepal")));
        System.out.println("Sydney".equalsIgnoreCase(myMap.get("Australia")));
    }
}
