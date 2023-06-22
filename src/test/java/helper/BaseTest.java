package helper;

import org.junit.jupiter.api.BeforeAll;
import solvd.ermakovich.ct.domain.node.Dancer;

/**
 * @author Ermakovich Kseniya
 */
@SuppressWarnings("JTCOP.RuleAllTestsHaveProductionClass")
public abstract class BaseTest {

    protected static Dancer dancer;

    @BeforeAll
    static void dancerSetUp() {
        dancer = new Dancer();
        dancer.setName("alex");
        dancer.setId("74e5ed85-c727-4441-8862-166bb4d5f07e");
        dancer.setSurname("polonov");
        dancer.setDescription("His body seems to flow effortlessly from one pose to the next");
        dancer.setExperience(7.8F);
    }

}
