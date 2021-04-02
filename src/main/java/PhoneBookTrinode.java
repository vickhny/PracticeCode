import java.util.HashMap;

public class PhoneBookTrinode {

    public static void main(String[] args) {
        Trie trie = new Trie();

        HashMap<String,String> contacts = new HashMap<>();
        contacts.put("Ashutosh","967896545");
        contacts.put("Asish","967896541");
        contacts.put("Richa","967896675");
        contacts.put("Binay","967678545");
        contacts.put("Bijay","967834545");
        contacts.put("Ankit","909896545");

        trie.addContact(contacts);

        trie.search("As");


    }

    private static class Trie {

        TrieNode root;
        HashMap<String,String> contacts;

        Trie(){
            root = new TrieNode();
            contacts = new HashMap<>();
        }

        public void addContact(HashMap<String,String> contactList) {
            contactList.entrySet().stream().forEach(e -> {
                addContact(e.getKey(),e.getValue());
            });

        }

        public void addContact(String Name, String Number) {
            insert(Name.toLowerCase());
            contacts.put(Name.toLowerCase(),Number.toLowerCase());
        }

        public void insert(String str) {
            TrieNode itr = root;
            for (int i = 0; i < str.length(); i++) {
                TrieNode nextNode = itr.child.get(str.charAt(i));
                if (nextNode == null) {
                    nextNode = new TrieNode();
                    itr.child.put(str.charAt(i), nextNode);
                }
                itr = nextNode;

                if (i == str.length() - 1) {
                    itr.isLast = true;
                }
            }
        }

        public void search(String query) {
            query = query.toLowerCase();
            TrieNode current = root;
            String prefix = "";
            int i;
            for (i = 0; i < query.length(); i++) {

                prefix += query.charAt(i);

                TrieNode searchNode = current.child.get(query.charAt(i));
                if (searchNode == null) {
                    System.out.println("No Contacts found with prefix - " + prefix);
                    break;
                }

                System.out.println("Available contacts are with prefix - "+prefix+" are\n");
                displayContact(prefix, searchNode);
                current = searchNode;
            }
            for (; i < query.length(); i++) {
                prefix += query.charAt(i);
                System.out.println("No Contacts found with prefix - " + prefix);
            }
        }

        private void displayContact(String query, TrieNode searchNode) {

            if (searchNode.isLast) {
                System.out.println(query.toUpperCase()+" - "+contacts.get(query));
            }

            for (char i = 'a'; i <= 'z'; i++) {
                TrieNode nextNode = searchNode.child.get(i);
                if (nextNode != null) {
                    displayContact(query + i, nextNode);
                }
            }

        }

        private class TrieNode {

            HashMap<Character, TrieNode> child;
            boolean isLast;

            TrieNode() {
                child = new HashMap<>();

                for (char i = 'a'; i <= 'z'; i++) {
                    child.put(i, null);
                }
                isLast = false;
            }
        }
    }
}
