package collidascope.collidahandla;

import collidascope.ICollider;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.function.BiConsumer;

import static collidascope.CollidaTestUtil.getListOfConsumers;
import static collidascope.CollidaTestUtil.getListOfKeys;

/**
 * Created by Robert Wilk
 * on 4/18/2016.
 */
public class HandlerTest {

    private Handler handler;
    private List<String> keys;
    private List<BiConsumer<ICollider, ICollider>> consumers;
    private final int MANY = 2;

    @Before
    public void setUp() throws Exception {
        handler = new Handler();
        keys =  getListOfKeys(MANY);
        consumers = getListOfConsumers(MANY);
    }

    @Test
    public void testAddHandlers() throws Exception {
        handler.addHandlers(keys, consumers);
        for (int i = 0; i < MANY; i++) {
            // this is where I'm stumped
            consumers.get(i).accept(null, null);
        }
    }

    public static void callBack(ICollider a, ICollider b) {
        System.out.println("I'm here now...");
    }


    @Test
    public void testAddHandlers1() throws Exception {

    }

    @Test
    public void testHandleCollision() throws Exception {

    }

    @Test
    public void testHandleCollisions() throws Exception {

    }
}