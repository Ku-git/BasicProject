package com.openhome.generics;

import java.util.Arrays;
import java.util.Comparator;

public class Group<T> {
    public T[] things;

    public Group(T... things) {
        this.things = things;
    }

    //?為T的父類別; T為?的子類別
    public void sort(Comparator<? super T> comparator) {
        Arrays.sort(things, comparator);
    }
}