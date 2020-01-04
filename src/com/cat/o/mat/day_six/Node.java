package com.cat.o.mat.day_six;

import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

public class Node {

    private String name;
    private ArrayList<Node> nextOrbs;

    Node(String name) {
        this.name = name;
        this.nextOrbs = new ArrayList<>();
    }

    String getName() {
        return name;
    }

    ArrayList<Node> getNextOrbs() {
        return nextOrbs;
    }

    void addOrbiting(Node orbiting) {
        nextOrbs.add(orbiting);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        return Objects.equals(name, node.name);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    void printOrbiting() {
        System.out.println(this.nextOrbs.stream().map(Node::getName).collect(Collectors.joining(",")));
    }
}
