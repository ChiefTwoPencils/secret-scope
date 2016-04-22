package collidascope;

import collidascope.collidahandla.HandlerTest;
import examples.Friend;
import examples.Enemy;

import java.util.*;
import java.util.function.BiConsumer;
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

    public static BiConsumer<ICollider, ICollider> getRandomConsumer(){
        return HandlerTest::callBack;
    }

    public static Collision getRandomCollision() {
        ICollider a = new Friend();
        ICollider b = new Enemy();
        //int priority = random.nextInt(6);
        return new Collision(a, b);
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

    public static List<BiConsumer<ICollider, ICollider>> getListOfConsumers(final int many){
        List<BiConsumer<ICollider, ICollider>> consumers = new ArrayList<>(many);
        for (int i = 0; i < many; i++){
            consumers.add(getRandomConsumer());
        }
        return consumers;
    }

    public static Map<String, BiConsumer<ICollider, ICollider>>
    getMapOfKeysAndConsumers(
            final List<String> keys,
            final List<BiConsumer<ICollider, ICollider>> consumers,
            final int many) {
        Map<String, BiConsumer<ICollider, ICollider>> map = new HashMap<>(many);
        for (int i = 0; i < many; i++){
            map.put(keys.get(i), consumers.get(i));
        }
        return map;
    }

    public static List<Collision> getListOfCollisions(final int many) {
        List<Collision> collisions = new ArrayList<>(many);
        for(int i = 0; i < many; i++) {
            collisions.add(getRandomCollision());
        }
        return collisions;
    }
}

