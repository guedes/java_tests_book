import net.sourceforge.jwebunit.util.TestingEngineRegistry;
import static net.sourceforge.jwebunit.junit.JWebUnit.*;

import org.junit.Before;
import org.junit.Test;


public class WebTest {

    @Before public void prepare() {
        setTestingEngineKey(TestingEngineRegistry.TESTING_ENGINE_SELENIUM);    // use Selenium
        setBaseUrl("http://www.google.com");
    }

    public void test1() {
        beginAt("/"); //Open the browser on http://localhost:8080/test/home.xhtml
        //clickLink("login");
        //assertTitleEquals("Login");
        setTextField("q", "teste");
        //setTextField("password", "test123");
        //submit();
	clickButtonWithText("Pesquisar");
        assertLinkPresentWithText("Testes - CAPRICHO");
    }
}
