package solvd.ermakovich.ct.service;

import java.util.List;
import solvd.ermakovich.ct.domain.node.Visitor;

/**
 * @author Ermakovich Kseniya
 */
public interface VisitorService {

    Visitor create(Visitor visitor);

    Visitor makeFriend(String from, String to);

    List<Visitor> getWayToConnect(String visitorFrom, String visitorTo);

}
