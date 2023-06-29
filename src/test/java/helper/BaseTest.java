package helper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeAll;
import solvd.ermakovich.ct.domain.node.Dancer;
import solvd.ermakovich.ct.domain.node.Performance;
import solvd.ermakovich.ct.domain.node.Visitor;
import solvd.ermakovich.ct.domain.relation.ActsIn;
import solvd.ermakovich.ct.repository.projection.PerformanceProjection;
import solvd.ermakovich.ct.web.dto.DancerInPerformance;

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
    protected static Visitor visitor;
    protected static Visitor visitorWithFriend;
    protected static DancerInPerformance dancerInPerformance;
    protected static List<Visitor> visitorList;

    @BeforeAll
    static void dancerSetUp() {
        dancer = new Dancer();
        dancer.setName("alex");
        dancer.setSurname("polonov");
        dancer.setDescription("His body seems to flow "
                + "effortlessly from one pose to the next");
        dancer.setExperience(7.8F);
    }

    @BeforeAll
    static void performanceSetUp() {
        performance = new Performance();
        performance.setTitle("Cinderella");
        performance.setId("d001a193-e232-4c1b-0086-1b86d3d6ae9e");
        performance.setDescription("Cinderella is a 1960 Soviet"
                + " musical film directed by Rostislav Zakharov and "
                + "Aleksandr Rou.");
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
                return "Cinderella is a 1960 Soviet musical film directed"
                        + "by Rostislav Zakharov and Aleksandr Rou.";
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
        performanceWithDancer.setDescription("Cinderella is a 1960 Soviet "
                + "musical film directed by Rostislav Zakharov "
                + "and Aleksandr Rou.");
        performanceWithDancer.setDancers(List.of(actsInRelation));
    }

    @BeforeAll
    static void visitorSetUp() {
        visitor = new Visitor();
        visitor.setId("d001a193-e232-4c1b-0086-1b86d3d6ae9e");
        visitor.setName("Victor");
        visitor.setSurname("Pilipenko");
    }

    @BeforeAll
    static void visitorWithFriendSetUp() {
        visitorWithFriend = new Visitor();
        visitorWithFriend.setId("643be014-9f73-417a-91c7-34f0850cfc68");
        visitorWithFriend.setName("polina");
        visitorWithFriend.setSurname("lopenko");
        visitorWithFriend.setFriends(List.of(visitor));
    }

    @BeforeAll
    static void dancerInPerformanceSetUp() {
        dancerInPerformance = new DancerInPerformance();
        dancerInPerformance.setDancerId("2a404590-f9e7-463e-a11a-1fdac3a1d1c4");
        dancerInPerformance.setFirstPerformanceDate(LocalDate.of(2022, 1, 1));
    }

    @BeforeAll
    static void visitorListSetUp() {
        visitorList = new ArrayList<>();
        var firstVisitor = new Visitor();
        firstVisitor.setId("643be014-9f73-417a-91c7-34f0850cfc68");
        firstVisitor.setName("Victor");
        firstVisitor.setSurname("Pilipenko");
        var secondVisitor = new Visitor();
        secondVisitor.setId("7725dfb1-1ad8-4d2d-a271-bbcb8d99ac7d");
        secondVisitor.setName("Petr");
        secondVisitor.setSurname("Popov");
        visitorList.add(firstVisitor);
        visitorList.add(secondVisitor);
    }

}
