package collidascope;

import collidascope.collidahandla.Handler;
import collidascope.collidadetecta.Detector;
import collidascope.collidatracka.Tracker;
import examples.Enemy;
import examples.Friend;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
    private Handler handler;
    private Detector detector;
    private Tracker tracker;
    private List<String> keys;
    private List<BiConsumer<ICollider, ICollider>> consumers;
    private List<BiFunction<ICollider, ICollider, Boolean>> functions;

    private final int MANY = 2;

    @Before
    public void setUp() throws Exception {
        detector = new Detector();
        handler = new Handler();
        tracker = new Tracker();
        keys = new ArrayList<>();
        consumers = new ArrayList<>();
        functions = new ArrayList<>();
        a = new Friend();
        b = new Enemy();
        collision = new Collision(a, b);
        keys.add(a.getCollisionKey() + b.getCollisionKey());
        consumers.add(getRandomConsumer());
        functions.add(getRandomFunction());
        tracker.track(collision);
        collider = new Collider(keys, functions,
                                keys, consumers);
    }

    @Test
    public void testDetectedCollision() throws Exception {
        Assert.assertSame(functions.get(0).apply(a, b), collider.detectedCollision(a, b));
    }

    @Test
    public void testTrackCollision() throws Exception {
        Iterator<Collision> iter = tracker.iterator();
        Assert.assertSame(collision, iter.next());
    }

    @Test
    public void testHandleCollision() throws Exception {

    }

    @Test
    public void testHandleCollisions() throws Exception {

    }

    @Test
    public void testAddHandlers() throws Exception {

    }

    @Test
    public void testAddHandlers1() throws Exception {

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