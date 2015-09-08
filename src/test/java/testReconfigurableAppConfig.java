/**
 * Created by glenn on 5/09/15.
 */
import com.bluemongo.springmvcjsontest.model.ReconfigurableAppConfig;
import com.bluemongo.springmvcjsontest.persistence.ConfigStore;
import com.bluemongo.springmvcjsontest.service.ConfigHelper;
import org.junit.*;

import java.util.List;

import static org.junit.Assert.*;

public class testReconfigurableAppConfig {

    private ReconfigurableAppConfig reconfigurableAppConfig = null;


    @Before
    public void setUp(){
        reconfigurableAppConfig = null;
    }


    @Test
    public void testRevisionIncrementing(){

    }


    @Test
    public void testConfigInsertRetrieve(){
        reconfigurableAppConfig = new ReconfigurableAppConfig();
        ConfigHelper configHelper = new ConfigHelper(reconfigurableAppConfig);
        configHelper.setCustomerId(123);
        final String configName = "testing the config name - update " + configHelper.getCurrentAppConfig().getRevisionNumber();
        configHelper.setConfigName(configName);
        final String pageTitle = "testing config title - insert " + configHelper.getCurrentAppConfig().getRevisionNumber();
        reconfigurableAppConfig.setPageTitle(pageTitle);
        reconfigurableAppConfig.setButtonList(null);

        //We differentiate between updating and saving new by the existence of an id
        int savedId = configHelper.save();

        ConfigHelper configHelperRetrieve =  ConfigHelper.get(savedId);
        ReconfigurableAppConfig reconfigurableAppConfigRetrieve = configHelperRetrieve.getCurrentAppConfig();

        assertEquals(configName, configHelperRetrieve.getConfigName());
        assertEquals(pageTitle, reconfigurableAppConfigRetrieve.getPageTitle());
    }

    @Test
    public void testConfigUpdateRetrieve(){
        ConfigHelper configHelper = ConfigHelper.get(2);
        final String configName = "testing the config name - update " + configHelper.getCurrentAppConfig().getRevisionNumber();
        configHelper.setConfigName(configName);
        final String pageTitle = "testing config page title - update " + configHelper.getCurrentAppConfig().getRevisionNumber();
        reconfigurableAppConfig = configHelper.getCurrentAppConfig();
        reconfigurableAppConfig.setPageTitle(pageTitle);
        reconfigurableAppConfig.setButtonList(null);

        //We differentiate between updating and saving new by the existence of an id
        int savedId = configHelper.save();

        ConfigHelper configHelperRetrieve =  ConfigHelper.get(savedId);
        ReconfigurableAppConfig reconfigurableAppConfigRetrieve = configHelperRetrieve.getCurrentAppConfig();

        assertEquals(configName, configHelperRetrieve.getConfigName());
        assertEquals(pageTitle, reconfigurableAppConfigRetrieve.getPageTitle());
    }

    @Test
    public void testGetAllConfigsForCustomer(){
        ConfigStore configStore = new ConfigStore();
        List<ConfigHelper> configHelpers = configStore.getAll(123);
        assertTrue("configHelpers.size(): " + configHelpers.size(), configHelpers.size() > 0);
    }
}
