package collidascope;

import examples.Enemy;
import examples.Friend;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Robert Wilk
 * on 4/18/2016.
 */
public class CollisionTest {

    private ICollider a;
    private ICollider b;
    private Collision collision;
    private final int PRIORITY = 0;

    @Before
    public void setUp() throws Exception {
        a = new Friend();
        b = new Enemy();
        collision = new Collision(a, b, PRIORITY);
    }

    @Test
    public void testGetPriority() throws Exception {
        Assert.assertEquals(PRIORITY, collision.getPriority());
    }

    @Test
    public void testGetOne() throws Exception {
        Assert.assertSame(a, collision.getOne());
    }

    @Test
    public void testGetTheOther() throws Exception {
        Assert.assertSame(b, collision.getTheOther(a));
        Assert.assertSame(a, collision.getTheOther(b));
    }

    @Test
    public void testGetCollisionKey() throws Exception {
        Assert.assertEquals(a.getCollisionKey() + b.getCollisionKey(), collision.getCollisionKey());
    }
}