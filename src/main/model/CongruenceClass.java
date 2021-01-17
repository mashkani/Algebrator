package model;

// Represents a congruence class [a] modeled by the integer a

/*
Let a and n be integers with n > 0. The congruence class of a modulo n, denoted
[a]n, is the set of all integers that are congruent to a modulo n; i.e.,
[a]n = {z ∈ Z | a − z = kn for some k ∈ Z}.

Example: In congruence modulo 2 we have
[0]2 = {0, ±2, ±4, ±6,... }
[1]2 = {±1, ±3, ±5, ±7,... }.

We have the following operations for congruence classes:
[a]n + [b]n = [a + b]n,
[a]n * [b]n = [a * b]n.

The set of all congruence classes modulo n is denoted Zn (which is read “Z mod n”). Thus,
Zn = {[0], [1], [2], . . . , [n − 1]}.

Even though the elements of Zn are infinite sets of integers,
we can represent the elements as integers with the following operations:
(a mod n) + (b mod n) = (a + b mod n)
(a mod n) * (b mod n) = (a * b mod n).

Thus, in our representation, the set of all congruence classes modulo n is
Zn = {0, 1, 2, . . . , n − 1}.
 */

import org.json.JSONObject;
import persistence.Writable;

public class CongruenceClass implements Writable {
    public int congruenceClass;

    // EFFECTS: sets the integer modelling the congruence class [a]
    public CongruenceClass(int a) {
        this.congruenceClass = a;
    }

    public int getCongruenceClass() {
        return congruenceClass;
    }

    public void setCongruenceClass(int n) {
        congruenceClass = n;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("congruenceClass", congruenceClass);
        return json;
    }
}