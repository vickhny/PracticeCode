import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FuzzySearchGoogle {
    private static final String ABC = "abcdefghijklmnopqrstuvwxyz";

    private static Map<String, Integer> dictionary = new HashMap<>();

    private static String DICTIONARY_VALUES = "Tesla,Apple,Microsoft,CocaCola";

    public static void main(String[] args) {

        Stream.of(DICTIONARY_VALUES.toLowerCase().split(",")).forEach((word) -> {

            dictionary.compute(word, (k, v) -> v == null ? 1 : v + 1);

        });

        System.out.println("Correction for Tela: " + correct("Tela"));

        System.out.println("Correction for Apptle: " + correct("Apptle"));

        System.out.println("Correction for Cocakola: " + correct("Cocakola"));

        System.out.println("Correction for Mikrosoft: " + correct("Mikrosoft"));

    }


    private static Stream<String> getStringStream(String word) {

        Stream<String> deletes = IntStream.range(0, word.length())

                .mapToObj((i) -> word.substring(0, i) + word.substring(i + 1));

        Stream<String> replaces = IntStream.range(0, word.length()).boxed().flatMap((i) -> ABC.chars()

                .mapToObj((c) -> word.substring(0, i) + (char) c + word.substring(i + 1)));

        Stream<String> inserts = IntStream.range(0, word.length() + 1).boxed().flatMap((i) -> ABC.chars()

                .mapToObj((c) -> word.substring(0, i) + (char) c + word.substring(i)));

        Stream<String> transposes = IntStream.range(0, word.length() - 1)

                .mapToObj((i) -> word.substring(0, i) + word.substring(i + 1, i + 2) + word.charAt(i) + word.substring(i + 2));

        return Stream.of(deletes, replaces, inserts, transposes).flatMap((x) -> x);

    }

    private static Stream<String> edits1(final String word) {
        return getStringStream(word);
    }

    private static String correct(String word) {

        Optional<String> e1 = known(edits1(word)).max(Comparator.comparingInt(a -> dictionary.get(a)));

        if (e1.isPresent()) return dictionary.containsKey(word) ? word : e1.get();

        Optional<String> e2 = known(edits1(word).flatMap(FuzzySearchGoogle::edits1))

                .max(Comparator.comparingInt(a -> dictionary.get(a)));

        return (e2.orElse(word));

    }

    private static Stream<String> known(Stream<String> words) {
        return words.filter((word) -> dictionary.containsKey(word));
    }
}
