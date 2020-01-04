package com.cat.o.mat.day_six;

import java.util.ArrayList;
import java.util.Optional;

class Graph {

    private ArrayList<Node> graph;

    Graph() {
        this.graph = new ArrayList<>();
    }


    void addInputToGraph(String[] inputs) {
        for (String i : inputs) {
            String src = i.substring(0, i.indexOf(")"));
            String dest = i.substring(i.indexOf(")") + 1).strip();
            addToGraph(src, dest);
        }
    }


    private void addToGraph(String srcName, String destName) {
        Node src = new Node(srcName);
        Node dest = new Node(destName);

        int posSrc = graph.indexOf(src);
        int posDest = graph.indexOf(dest);
        if (posSrc >= 0) {
            graph.get(posSrc).addOrbiting(dest);
        } else {
            src.addOrbiting(dest);
            graph.add(src);
        }

        if (posDest < 0) {
            graph.add(dest);
        }

        graph.stream()
                .flatMap(n -> n.getNextOrbs().stream())
                .filter(node -> node.equals(src))
                .forEach(n -> n.addOrbiting(dest));

    }

    void countNumOfOrbits() {
        int num = 0;
        for (Node n : graph) {
            Node runner = n;
            Node finalRunner = runner;
            Optional<Node> curr = graph.stream()
                    .filter(node -> node.getNextOrbs().contains(finalRunner))
                    .findFirst();
            while (curr.isPresent() && !runner.getName().equals("COM")) {
                num += 1;
                runner = curr.get();
                Node finalRunner1 = runner;
                curr = graph.stream()
                        .filter(node -> node.getNextOrbs().contains(finalRunner1))
                        .findFirst();

            }
        }
        System.out.println("Sum: " + num);
    }

}
