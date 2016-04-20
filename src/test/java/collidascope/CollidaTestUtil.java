package collidascope;

import java.util.*;
import java.util.function.BiFunction;

/**
 * Created by Robert Wilk
 * on 4/19/2016.
 */
public class CollidaTestUtil {

    private static final Random random = new Random(System.currentTimeMillis());

    public static List<String> getListOfKeys(final int many) {
        List<String> keys = new ArrayList<>(many);
        for (int i = 0; i < many; i++) {
            keys.add(getRandomKey());
        }
        return keys;
    }

    public static String getRandomKey() {
        return String.valueOf(random.nextInt());
    }

    public static BiFunction<ICollider, ICollider, Boolean> getRandomFunction() {
        boolean result = random.nextBoolean();
        return (a, b) -> result;
    }

    public static List<BiFunction<ICollider, ICollider, Boolean>> getListOfFunctions(final int many) {
        List<BiFunction<ICollider, ICollider, Boolean>> functions = new ArrayList<>(many);
        for (int i = 0; i < many; i++) {
            functions.add(getRandomFunction());
        }
        return functions;
    }

    public static Map<String, BiFunction<ICollider, ICollider, Boolean>>
    getMapOfKeysAndFunctions(
            final List<String> keys,
            final List<BiFunction<ICollider, ICollider, Boolean>> functions,
            final int many) {
        Map<String, BiFunction<ICollider, ICollider, Boolean>> map = new HashMap<>(many);
        for (int i = 0; i < many; i++) {
            map.put(keys.get(i), functions.get(i));
        }
        return map;
    }
}

