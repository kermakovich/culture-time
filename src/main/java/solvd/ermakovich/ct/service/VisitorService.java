package solvd.ermakovich.ct.service;

import solvd.ermakovich.ct.domain.node.Visitor;

/**
 * @author Ermakovich Kseniya
 */
public interface VisitorService {

    Visitor create(Visitor visitor);

    Visitor makeFriend(String from, String to);

}
