package collidascope;

import examples.Enemy;
import examples.Friend;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

import static collidascope.CollidaTestUtil.*;

/**
 * Created by Robert Wilk
 * on 4/18/2016.
 */
public class ColliderTest {

    private ICollider a, b;
    private Collider collider;
    private Collision collision;
    private List<String> keys;
    private List<BiConsumer<ICollider, ICollider>> consumers;
    private List<BiFunction<ICollider, ICollider, Boolean>> functions;
    private Map<String, BiConsumer<ICollider, ICollider>> consumerMap;
    private Map<String, BiFunction<ICollider, ICollider, Boolean>> functionMap;
    private static int expected = 0;
    private static int actual = 0;

    @Before
    public void setUp() throws Exception {
        keys = new ArrayList<>();
        consumers = new ArrayList<>();
        functions = new ArrayList<>();
        consumerMap = new HashMap<>();
        functionMap = new HashMap<>();
        a = new Friend();
        b = new Enemy();
        collision = new Collision(a, b);
        keys.add(a.getCollisionKey() + b.getCollisionKey());
        consumers.add(getColliderConsumer());
        functions.add(getRandomFunction());
        for (int i = 0; i < keys.size(); i++) {
            consumerMap.put(keys.get(i), consumers.get(i));
            functionMap.put(keys.get(i), functions.get(i));
        }
    }

    @Test
    public void testDetectedCollision() throws Exception {
        collider = new Collider();
        collider.addDetectors(keys, functions);
        collider.addHandlers(keys, consumers);
        Assert.assertSame(functions.get(0).apply(a, b), collider.detectedCollision(a, b));
    }

    @Test
    public void testTrackCollision() throws Exception {
        collider = new Collider();
        collider.addDetectors(keys, functions);
        collider.addHandlers(keys, consumers);
        collider.trackCollision(collision);
        expected = 1;
        actual = 0;
        collider.handleCollisions();
        Assert.assertSame(expected, actual);
    }

    public static void callBack(ICollider a, ICollider b) {
        ++actual;
    }

    @Test
    public void testHandleCollision() throws Exception {
        collider = new Collider();
        collider.addDetectors(keys, functions);
        collider.addHandlers(keys, consumers);
        expected = 1;
        actual = 0;
        collider.handleCollision(a, b);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testHandleCollisions() throws Exception {
        collider = new Collider();
        collider.addDetectors(keys, functions);
        collider.addHandlers(keys, consumers);
        expected = 1;
        actual = 0;
        collider.handleCollisions();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testAddHandlers() throws Exception {
        collider = new Collider();
        collider.addDetectors(keys, functions);
        collider.addHandlers(keys, consumers);
        expected = 1;
        actual = 0;
        collider.handleCollisions();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testAddHandlers1() throws Exception {
        collider = new Collider();
        collider.addDetectors(keys, functions);
        collider.addHandlers(keys, consumers);
        expected = 1;
        actual = 0;
        collider.handleCollisions();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testAddDetectors() throws Exception {

    }

    @Test
    public void testAddDetectors1() throws Exception {

    }

    @Test
    public void testGetCollisionString() throws Exception {

    }
}