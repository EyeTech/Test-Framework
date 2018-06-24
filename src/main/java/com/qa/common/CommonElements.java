package com.qa.common;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;

public class CommonElements {

    public ArrayList<String> getDeDupedList(ArrayList<String> originalList1, ArrayList<String> originalList2){
        originalList1.retainAll(originalList2);

        return new ArrayList<>(originalList1.stream().collect(Collectors.toList()));
    }

    public ArrayList<String> getDeDupedListDistinctAfter(ArrayList<String> originalList1, ArrayList<String> originalList2){
        originalList1.retainAll(originalList2);

        return new ArrayList<>(originalList1.stream().distinct().collect(Collectors.toList()));
    }

    public ArrayList<String> getDeDupedListDistinctBefore(ArrayList<String> originalList1, ArrayList<String> originalList2){
        ArrayList<String> list1 = new ArrayList<>(originalList1.stream().distinct().collect(Collectors.toList()));
        ArrayList<String> list2 = new ArrayList<>(originalList2.stream().distinct().collect(Collectors.toList()));

        list1.retainAll(list2);

        return list1;
    }

    public ArrayList<String> getDeDupedHashSet(ArrayList<String> originalList1, ArrayList<String> originalList2) {
        HashSet<String> set1 = new HashSet<>(originalList1);
        HashSet<String> set2 = new HashSet<>(originalList2);

        set1.retainAll(set2);
        return new ArrayList<>(set1);
    }
}
