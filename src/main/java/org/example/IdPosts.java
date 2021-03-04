package org.example;

import java.util.Arrays;

public class IdPosts {

    private int[] idInt;

    public IdPosts(){
        idInt = new int[99];
        for (int i = 1; i < 100; i++) {
            idInt[i-1] = i;
        }
    }

    public int[] getIdInt() {
        return idInt;
    }

    @Override
    public String toString() {
        return Arrays.toString(idInt);
    }
}
