package collidascope.collidahandla;

import collidascope.Collision;
import collidascope.ICollider;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import static collidascope.CollidaTestUtil.*;

/**
 * Created by Robert Wilk
 * on 4/18/2016.
 */
public class HandlerTest {

    private Handler handler;
    private List<String> keys;
    private List<BiConsumer<ICollider, ICollider>> consumers;
    private final int MANY = 2;
    private static int expected = 0;
    private static int actual = 0;

    @Before
    public void setUp() throws Exception {
        handler = new Handler();
        keys =  getListOfKeys(MANY);
        consumers = getListOfConsumers(MANY);
    }

    @Test
    public void testAddHandlers0() throws Exception {
        handler.addHandlers(keys, consumers);
        expected = keys.size();
        actual = 0;
        for (String key : keys) {
            handler.handleCollision(null, null, key);
        }
        Assert.assertEquals(expected, actual);
    }

    public static void callBack(ICollider a, ICollider b) {
        ++actual;
        System.out.println("I'm here now..." + actual);
    }


    @Test
    public void testAddHandlers1() throws Exception {
        Map<String, BiConsumer<ICollider, ICollider>> map =
                getMapOfKeysAndConsumers(keys, consumers, MANY);
        handler.addHandlers(map);
        expected = keys.size();
        actual = 0;
        for (String key : keys) {
            handler.handleCollision(null, null, key);
        }
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testHandleCollision() throws Exception {
        handler.addHandlers(getMapOfKeysAndConsumers(keys, consumers, MANY));
        expected = keys.size();
        actual = 0;
        for( int i = 0; i < MANY; i++) {
            handler.handleCollision(null, null, keys.get(i));
        }
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testHandleCollisions() throws Exception {
        List<Collision> list = getListOfCollisions(MANY);
        Iterator<Collision> iter = list.iterator();
        expected = keys.size();
        actual = 0;
        handler.handleCollisions(iter);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testHandleCollisionWithBadKey() {
        handler.addHandlers(getMapOfKeysAndConsumers(keys, consumers, MANY));
        expected = keys.size();
        actual = 0;
        handler.handleCollision(null, null, "BadKey");
        Assert.assertFalse(expected == actual);
    }
}