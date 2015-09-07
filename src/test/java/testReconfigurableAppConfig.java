/**
 * Created by glenn on 5/09/15.
 */
import com.bluemongo.springmvcjsontest.model.ReconfigurableAppConfig;
import com.bluemongo.springmvcjsontest.service.ConfigHelper;
import org.junit.*;
import sun.security.krb5.Config;

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
        reconfigurableAppConfig = ConfigHelper.get(configID);
        assertEquals(null, reconfigurableAppConfig);
    }

/*    @Test
    public void testGetConfigNotNull(){
        int configID = 4;
        reconfigurableAppConfig = ConfigHelper.get(configID);
        assertNotEquals(null, reconfigurableAppConfig);
    }*/

    @Test
    public void testRevisionIncrementing(){

    }


    @Test
    public void testConfigInsertRetrieve(){
        reconfigurableAppConfig = new ReconfigurableAppConfig();
        ConfigHelper configHelper = new ConfigHelper(reconfigurableAppConfig);
        configHelper.setCustomerId(123);
        configHelper.setConfigName("testing the config name");
        final String title = "testing config title - insert";
        reconfigurableAppConfig.setTitle(title);
        reconfigurableAppConfig.setButtonList(null);

        //We differentiate between updating and saving new by the existence of an id
        int savedId = configHelper.save();

        ReconfigurableAppConfig reconfigurableAppConfig2 = ConfigHelper.get(savedId);
        assertEquals(title, reconfigurableAppConfig2.getTitle());
    }

    @Test
    public void testConfigUpdateRetrieve(){
        reconfigurableAppConfig = ConfigHelper.get(2);
        ConfigHelper configHelper = new ConfigHelper(reconfigurableAppConfig);
        configHelper.setCustomerId(123);
        configHelper.setConfigName("testing the config name");
        final String title = "testing config title - update";
        reconfigurableAppConfig.setTitle(title);
        reconfigurableAppConfig.setButtonList(null);

        //We differentiate between updating and saving new by the existence of an id
        int savedId = configHelper.save();

        ReconfigurableAppConfig reconfigurableAppConfig2 = ConfigHelper.get(savedId);
        assertEquals(title, reconfigurableAppConfig2.getTitle());
    }


}
