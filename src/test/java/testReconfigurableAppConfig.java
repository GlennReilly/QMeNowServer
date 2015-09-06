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
        reconfigurableAppConfig = null;
    }

/*    @Test
    public void testGetId(){
        int configID = 42;
        reconfigurableAppConfig = new ReconfigurableAppConfig();
        //reconfigurableAppConfig.setId(configID);

        assertEquals(42, reconfigurableAppConfig.getId());
    }*/

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


    @Test
    public void testConfigInsertRetrieve(){
        reconfigurableAppConfig = new ReconfigurableAppConfig();
        reconfigurableAppConfig.setCustomerId(123);
        final String title = "testing of the insert";
        reconfigurableAppConfig.setTitle(title);
        reconfigurableAppConfig.setButtonList(null);

        //We differentiate between updating and saving new by the existence of an id
        int savedId = reconfigurableAppConfig.save();

        ReconfigurableAppConfig reconfigurableAppConfig2 = ReconfigurableAppConfig.get(savedId);
        assertEquals(title, reconfigurableAppConfig2.getTitle());
    }

    @Test
    public void testConfigUpdateRetrieve(){
        reconfigurableAppConfig = ReconfigurableAppConfig.get(2);
        reconfigurableAppConfig.setCustomerId(123);
        final String title = "testing of the update";
        reconfigurableAppConfig.setTitle(title);
        reconfigurableAppConfig.setButtonList(null);

        //We differentiate between updating and saving new by the existence of an id
        int savedId = reconfigurableAppConfig.save();

        ReconfigurableAppConfig reconfigurableAppConfig2 = ReconfigurableAppConfig.get(savedId);
        assertEquals(title, reconfigurableAppConfig2.getTitle());
    }


}
