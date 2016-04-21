package collidascope.collidadetecta;

import collidascope.ICollider;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

import static collidascope.CollidaTestUtil.*;

/**
 * Created by Robert Wilk
 * on 4/18/2016.
 */
public class DetectorTest {

    private Detector detector;
    private List<String> keys;
    private List<BiFunction<ICollider, ICollider, Boolean>> functions;
    private final int MANY = 10;

    @Before
    public void setUp() throws Exception {
        detector = new Detector();
        keys = getListOfKeys(MANY);
        functions = getListOfFunctions(MANY);
    }

    @Test
    public void testAddDetectors() throws Exception {
        detector.addDetectors(keys, functions);
        for (int i = 0; i < MANY; i++) {
            boolean expected = functions.get(i).apply(null, null);
            boolean actual = detector.detectCollision(null, null, keys.get(i));
            Assert.assertSame(expected, actual);
        }
    }

    @Test
    public void testAddDetectors1() throws Exception {
        Map<String, BiFunction<ICollider, ICollider, Boolean>> map =
                getMapOfKeysAndFunctions(keys, functions, MANY);
        detector.addDetectors(map);
        for (String key : map.keySet()) {
            boolean expected = map.get(key).apply(null, null);
            boolean actual = detector.detectCollision(null, null, key);
            Assert.assertSame(expected, actual);
        }
    }

    @Test
    public void testDetectCollision() {
        detector.addDetectors(getMapOfKeysAndFunctions(keys, functions, MANY));
        Assert.assertSame(
                functions.get(0).apply(null, null),
                detector.detectCollision(null, null, keys.get(0))
        );
    }

    @Test
    public void testDetectCollisionWithBadKey() {
        detector.addDetectors(getMapOfKeysAndFunctions(keys, functions, MANY));
        Assert.assertFalse(detector.detectCollision(null, null, "BadKey"));
    }
}