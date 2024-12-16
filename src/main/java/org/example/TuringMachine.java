package org.example;

import java.util.HashMap;
import java.util.Map;

record Transition(String newState, Boolean action, int move) { }

class TuringMachine {
    private final char[] data;
    private String state;
    private final Map<String, Transition> transitions;
    private int position;

    public TuringMachine(String data, String firstState, int position, Map<String, Transition> transitions) {
        this.data = data.toCharArray();
        this.state = firstState;
        this.position = position;
        this.transitions = transitions;
    }

    public void run() {
        while (!state.equals("HALT")) {
            try {
                Transition tr = transitions.get(state + data[position]);
                if (tr == null) {
                    state = "HALT";
                    continue;
                }

                if (tr.action() != null) {
                    if (tr.action()) {
                        data[position] = '1';
                    } else {
                        data[position] = '0';
                    }
                }

                position += tr.move();
                state = tr.newState();
            } catch (ArrayIndexOutOfBoundsException e) {
                state = "HALT";
            }
        }
    }

    public String getResult() {
        return new String(data);
    }

    /*

    11101000
    11110000
    01100100

     */
    public static void main(String[] args) {

        Map<String, Transition> transitions = new HashMap<>();
        transitions.put("q00", new Transition("q00", null, 8));
        transitions.put("q01", new Transition("q01", null, 8));

        transitions.put("q10", new Transition("q10", null, 8));
        transitions.put("q11", new Transition("q11", null, 8));



        transitions.put("q000", new Transition("q000", null, 8));
        transitions.put("q001", new Transition("q001", null, 8));

        transitions.put("q100", new Transition("q100", null, 8));
        transitions.put("q101", new Transition("q101", null, 8));

        transitions.put("q010", new Transition("q010", null, 8));
        transitions.put("q011", new Transition("q011", null, 8));

        transitions.put("q110", new Transition("q110", null, 8));
        transitions.put("q111", new Transition("q111", null, 8));



        transitions.put("q0000", new Transition("q0", false, -15));
        transitions.put("q0001", new Transition("q0", false, -15));

        transitions.put("q0010", new Transition("q0", true, -15));
        transitions.put("q0011", new Transition("q0", true, -15));

        transitions.put("q1000", new Transition("q0", true, -15));
        transitions.put("q1001", new Transition("q0", true, -15));

        transitions.put("q0100", new Transition("q0", true, -15));
        transitions.put("q0101", new Transition("q0", true, -15));

        transitions.put("q1010", new Transition("q1", false, -15));
        transitions.put("q1011", new Transition("q1", false, -15));

        transitions.put("q0110", new Transition("q1", false, -15));
        transitions.put("q0111", new Transition("q1", false, -15));

        transitions.put("q1100", new Transition("q1", false, -15));
        transitions.put("q1101", new Transition("q1", false, -15));

        transitions.put("q1110", new Transition("q1", true, -15));
        transitions.put("q1111", new Transition("q1", true, -15));

        TuringMachine tm = new TuringMachine("111010001111000010000011", "q0", 0, transitions);
        tm.run();
        System.out.println(tm.getResult());
    }
}
