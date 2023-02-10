package ru.Savenko.javaTheThirdTask.utils;

import ru.Savenko.javaTheThirdTask.office.Client;
import ru.Savenko.javaTheThirdTask.office.Offer;
import ru.Savenko.javaTheThirdTask.office.Stuff;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Search {

    public static ArrayList<Offer> off;
    public static HashMap<Client, Integer> cl;
    public static HashMap<Stuff, Integer> st;
    public static HashSet<String> sur;

    public static Map<String, Integer> getClient() {
        Map<String, Integer> temp = new HashMap<>();
        if (cl != null && !cl.isEmpty()) {
            for (Map.Entry entry : cl.entrySet()) {
                temp.put(entry.getKey().toString(), (Integer) entry.getValue());
            }
        }
        return temp;
    }

    public static Map<String, Integer> getStuff() {
        Map<String, Integer> temp = new HashMap<>();
        if (st != null && !st.isEmpty()) {
            for (Map.Entry entry : st.entrySet()) {
                temp.put(entry.getKey().toString(), (Integer) entry.getValue());
            }
        }
        return temp;
    }

    public static void addOff(Offer f) {
        if (off == null) {
            off = new ArrayList<>();
        }
        if (cl == null) {
            cl = new HashMap<>();
        }
        if (st == null) {
            st = new HashMap<>();
        }
        if (sur == null) {
            sur = new HashSet<>();
        }
        off.add(f);
        sur.add(f.getClient().getSurname());
        if (!cl.containsKey(f.getClient())) {
            cl.put(f.getClient(), 1);
        } else {
            var temp = cl.get(f.getClient());
            cl.put(f.getClient(), ++temp);
        }
        if (!st.containsKey(f.getStuff())) {
            st.put(f.getStuff(), 1);
        } else {
            var temp = st.get(f.getStuff());
            st.put(f.getStuff(), ++temp);
        }
    }
}