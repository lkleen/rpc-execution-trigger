package de.steinberg.engine.core.messaging;

import de.steinberg.engine.core.configuration.CoreConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.util.concurrent.TimeUnit;

/**
 * Created by lkleen on 11/28/2016.
 */
@Test
@ContextConfiguration(classes = CoreConfiguration.class)
public class BlockingMessageQueueTest extends AbstractTestNGSpringContextTests {

    @Inject
    BlockingMessageQueue queue;

    @Test
    public void testSendReceiveShutdown() {
        String foo = "foo";
        String bar = "bar";

        int x = 1;
        int y = 2;

        queue.send(foo);
        queue.send(x);
        queue.send(bar);
        queue.send(y);

        Assert.assertEquals(foo, queue.receive(String.class, 1, TimeUnit.MILLISECONDS));
        Assert.assertEquals(bar, queue.receive(String.class, 1, TimeUnit.MILLISECONDS));
        Assert.assertEquals(x, (int) queue.receive(Integer.class, 1, TimeUnit.MILLISECONDS));
        Assert.assertEquals(y, (int) queue.receive(Integer.class, 1, TimeUnit.MILLISECONDS));

        queue.shutdown ();
    }


}
