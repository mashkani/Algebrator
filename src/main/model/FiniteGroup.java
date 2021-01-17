package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.Set;


// Represents a finite set of congruence classes paired with addition or multiplication
public class FiniteGroup implements Writable {
    public Set<CongruenceClass> set;
    public int operation; // 1 represents modular addition, 2 represents modular multiplication

    // EFFECTS: Set gets the set s,
    //          if n is 1, binary operation gets the integer 1 representing addition
    //          if n is 2, binary operation gets the integer 2 representing multiplication
    //          for all other n, throws InvalidParameterException
    public FiniteGroup(Set<CongruenceClass> s, int n) throws InvalidParameterException {
        this.set = s;
        this.operation = n;
        if (!(n == 1 || n == 2)) {
            throw new InvalidParameterException("Ensure operation is either 1 or 2");
        }
    }

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: changes current operation to the given operation
    public void setOperation(int n) {
        this.operation = n;
    }

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: changes current set to the given set
    public void setSet(Set<CongruenceClass> s) {
        this.set = s;
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS: returns the operation that was defined
    public int getOperation() {
        return operation;
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS: returns the set that was defined
    public Set<CongruenceClass> getSet() {
        return set;
    }

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: adds CongruenceClass c to the set
    public void addElement(CongruenceClass c) {
        set.add(c);
    }

    // MODIFIES: this
    // EFFECTS: if CongruenceClass c is in the set, returns true and removes c from the set
    //          otherwise returns false
    public boolean removeElement(CongruenceClass c) {
        return set.remove(c);
    }

    // EFFECTS: counts number of CongruenceClasses in the set
    public int countSet() {
        return set.size();
    }

    // EFFECTS: returns true if CongruenceClass c is in the set, false otherwise
    public boolean contains(CongruenceClass c) {
        return set.contains(c);
    }


/*
    // REQUIRES:
    // MODIFIES:
    // EFFECTS: returns true if the elements of the given set are closed with respect to the given binary operation,
    //          returns false otherwise
    public Boolean isClosed() {
        switch (getOperation()) {
            case 1:
                return isClosedAddition();
            case 2:
                return isClosedMultiplication();
            default:
                return false;
        }
    }

    public boolean isClosedAddition() {
        Set<CongruenceClass> set = getSet();
        int n = countSet();

        for (CongruenceClass c1 : set) {
            int a = c1.getCongruenceClass();
            for (CongruenceClass c2 : set) {
                int b = c2.getCongruenceClass();
                CongruenceClass c = new CongruenceClass(a + b % n);
                if (!contains(c)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isClosedMultiplication() {
        Set<CongruenceClass> set = getSet();
        int n = countSet();

        for (CongruenceClass c1 : set) {
            int a = c1.getCongruenceClass();
            for (CongruenceClass c2 : set) {
                int b = c2.getCongruenceClass();
                CongruenceClass c = new CongruenceClass(a * b % n);
                if (!contains(c)) {
                    return false;
                }
            }
        }
        return true;
    }



    //IsAssociative
    // REQUIRES:
    // MODIFIES:
    // EFFECTS: returns true if the elements of the given set are associative with respect
    //          to the given binary operation
    //          returns false otherwise
    public Boolean isAssociative(Set<CongruenceClass> s, int o) { // stub
        return true;
    }

    //hasIdentity
    // REQUIRES:
    // MODIFIES:
    // EFFECTS: returns true if the given set contains an identity element with respect to the given binary operation
    //          returns false otherwise
    public Boolean hasIdentity(FiniteGroup fg) {
        switch (fg.getOperation()) {
            case 1:
                hasIdentityAddition(fg);
            case 2:
                hasIdentityMultiplication(fg);
            default:
                return false;
        }

    }

    public Boolean hasIdentityAddition(FiniteGroup fg) {
//        Set<CongruenceClass> set = fg.getSet();
//        int n = fg.countSet();
//
//        for (CongruenceClass c1 : set) {
//            int a = c1.getCongruenceClass();
//            for (CongruenceClass c2 : set) {
//                int b = c2.getCongruenceClass();
//                Boolean bool = (a + b % n == a);
//                Set<Boolean> bools = new HashSet<>();
//                bools.add(bool);
//            }
//        }
        return true;
    }

    public Boolean hasIdentityMultiplication(FiniteGroup fg) { // stub
        return false;
    }

    //getIdentity
    // REQUIRES:
    // MODIFIES:
    // EFFECTS: returns the identity element if the given set contains an identity element with respect
    //          to the given binary operation
    //          returns null otherwise
    public java.lang.Integer getIdentity(Set<CongruenceClass> s, int o) { // stub
        return null;
    }

    //hasInverse
    // REQUIRES:
    // MODIFIES:
    // EFFECTS: given a set s, binary operation o, and element e,
    //          returns true if the element e has an inverse within the set s with respect to the given operation
    //          returns false otherwise
    public Boolean hasInverse(Set<CongruenceClass> s, int o, java.lang.Integer e) { // stub
        return true;
    }

    //getInverse
    // REQUIRES:
    // MODIFIES:
    // EFFECTS: given a set s, binary operation o, and element e,
    //          returns the inverse if the element e has an inverse within the set s
    //          with respect to the given binary operation,
    //          returns null otherwise
    public int getInverse(Set<CongruenceClass> s, int o, int e) { // stub
        return 0;
    }

    //isGroup
    // REQUIRES:
    // MODIFIES:
    // EFFECTS: returns true if the given set satisfies all the group axioms with respect to the given binary operation
    //          returns false otherwise
    public Boolean isGroup(Set<CongruenceClass> s, int o) { // stub
        return true;
    }


    //isAbelian
    // REQUIRES: the set s must be a group with respect to the binary operation o
    // MODIFIES:
    // EFFECTS: returns true if the given set is commutative with respect to the given binary operation
    //          returns false otherwise
    public Boolean isAbelian(Set<CongruenceClass> s, int o) { // stub
        return true;
    }

 */

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("operation", operation);
        json.put("set", setToJson());
        return json;
    }

    // EFFECTS: returns congruenceClasses in this set as a JSON array
    private JSONArray setToJson() {
        JSONArray jsonArray = new JSONArray();

        for (CongruenceClass c : set) {
            jsonArray.put(c.toJson());
        }

        return jsonArray;
    }
}