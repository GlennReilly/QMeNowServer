/**
 * Created by glenn on 5/09/15.
 */
import com.bluemongo.springmvcjsontest.model.ReconfigurableAppConfig;
import org.junit.*;
import static org.junit.Assert.*;

public class testReconfigurableAppConfig {

    private ReconfigurableAppConfig reconfigurableAppConfig = null;


    @Before
    public void setUp(){

    }

    @Test
    public void testGetId(){
        int configID = 42;
        reconfigurableAppConfig = new ReconfigurableAppConfig();
        reconfigurableAppConfig.setId(configID);

        assertEquals(42, reconfigurableAppConfig.getId());
    }

    @Test
    public void testGetConfigNull(){
        int configID = -1;
        reconfigurableAppConfig = reconfigurableAppConfig.get(configID);
        assertEquals(null, reconfigurableAppConfig);
    }

    @Test
    public void testGetConfigNotNull(){
        int configID = 4;
        reconfigurableAppConfig = reconfigurableAppConfig.get(configID);
        assertNotEquals(null, reconfigurableAppConfig);
    }

    @Test
    public void testRevisionIncrementing(){

    }
}
