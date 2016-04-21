package collidascope.collidatracka;

import collidascope.Collision;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;

import static org.junit.Assert.fail;

/**
 * Created by Robert Wilk
 * on 4/18/2016.
 */
public class TrackerTest {

    private Tracker tracker;
    private Collision collision;

    @Before
    public void setUp() throws Exception {
        tracker = new Tracker();
        collision = new Collision(null, null, 0);
        tracker.track(collision);
    }

    @Test
    public void testTrack() throws Exception {
        Iterator<Collision> collisionIterator = tracker.iterator();
        Assert.assertSame(collision, collisionIterator.next());
    }

    @Test
    public void testClear() throws Exception {
        tracker.clear();
        if (tracker.iterator().hasNext())
            fail("TRACKER: clear() didn't clear the trackers");
    }

    @Test
    public void testForEach() throws Exception {
        List<Object> collisions = new ArrayList<>();
        tracker.forEach(collisions::add);
        Assert.assertTrue(checkTrackerCollisions(collisions));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testSpliterator() throws Exception {
        List<Object> collisions = new ArrayList<>();
        Spliterator collisionSpliterator = tracker.spliterator();
        collisionSpliterator.forEachRemaining(c -> collisions.add((Collision) c));
        Assert.assertTrue(checkTrackerCollisions(collisions));
    }

    private boolean checkTrackerCollisions(List collisions) {
        return collisions.size() == 1 && collisions.get(0) == collision;
    }
}