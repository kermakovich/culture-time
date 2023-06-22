package helper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeAll;
import solvd.ermakovich.ct.domain.node.Dancer;
import solvd.ermakovich.ct.domain.node.Performance;
import solvd.ermakovich.ct.domain.relation.ActsIn;
import solvd.ermakovich.ct.repository.projection.PerformanceProjection;

/**
 * @author Ermakovich Kseniya
 */
@SuppressWarnings("JTCOP.RuleAllTestsHaveProductionClass")
public abstract class BaseTest {

    protected static Dancer dancer;
    protected static Performance performance;
    protected static Performance performanceWithDancer;
    protected static List<PerformanceProjection> performanceProjections;
    protected static ActsIn actsInRelation;

    @BeforeAll
    static void dancerSetUp() {
        dancer = new Dancer();
        dancer.setName("alex");
        dancer.setId("74e5ed85-c727-4441-8862-166bb4d5f07e");
        dancer.setSurname("polonov");
        dancer.setDescription("His body seems to flow effortlessly from one pose to the next");
        dancer.setExperience(7.8F);
    }

    @BeforeAll
    static void performanceSetUp() {
        performance = new Performance();
        performance.setTitle("Cinderella");
        performance.setId("d001a193-e232-4c1b-0086-1b86d3d6ae9e");
        performance.setDescription("""
                Cinderella is a 1960 Soviet musical film directed
                by Rostislav Zakharov and Aleksandr Rou.
                """);
    }

    @BeforeAll
    static void performanceProjectionsSetUp() {
        performanceProjections = new ArrayList<>();
        performanceProjections.add(new PerformanceProjection() {
            @Override
            public String getId() {
                return UUID.randomUUID().toString();
            }

            @Override
            public String getTitle() {
                return "Cinderella";
            }

            @Override
            public String getDescription() {
                return "Cinderella is a 1960 Soviet musical film directed" +
                "by Rostislav Zakharov and Aleksandr Rou.";
            }
        });
    }

    @BeforeAll
    static void actsInRelationSetUp() {
        actsInRelation = new ActsIn();
        actsInRelation.setDancer(dancer);
        actsInRelation.setId(1L);
        actsInRelation.setFirstPerformanceDate(LocalDate.of(2023, 1, 1));
    }

    @BeforeAll
    static void performanceWithDancerSetUp() {
        performanceWithDancer = new Performance();
        performanceWithDancer.setTitle("Cinderella");
        performanceWithDancer.setId("d001a193-e232-4c1b-0086-1b86d3d6ae9e");
        performanceWithDancer.setDescription("""
                Cinderella is a 1960 Soviet musical film directed
                by Rostislav Zakharov and Aleksandr Rou.
                """);
        performanceWithDancer.setDancers(List.of(actsInRelation));
    }

}
