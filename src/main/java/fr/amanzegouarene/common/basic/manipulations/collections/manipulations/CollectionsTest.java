package fr.amanzegouarene.common.basic.manipulations.collections.manipulations;

import com.sun.org.apache.xml.internal.utils.StringBufferPool;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by amanzego on 18/09/2017.
 */
public class CollectionsTest {

    public static void main(String[] args) {
        Map<Integer, String> mapDeNombre = new LinkedHashMap<>();
        mapDeNombre.put(1, "un");
        mapDeNombre.put(1, "one");
        mapDeNombre.put(4, "quatre");
        mapDeNombre.put(2, "deux");
        mapDeNombre.put(12, "deux");
        mapDeNombre.put(5, "deux");
        mapDeNombre.put(22, "deux");
        mapDeNombre.put(13, "deux");

        // mapDeNombre.entrySet().stream().forEach(System.out::println);

        String tot = "tot";
        tot.intern();
    }
}
