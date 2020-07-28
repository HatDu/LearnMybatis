package com.dnm.test;

public class tmp {
    public static void main(String[] args) {
        String a1 = new String("a");
        String a2 = new String("a");
        String a3 = "a";
        System.out.println(a1 == a2);
        System.out.println(a1 == a3);

        String b = "b";
        String ab1 = "ab";
        String ab2 = "a" + "b";
        String ab3 = a1 + "b";

        ab3 = ab3.intern();

        System.out.println(ab1 == ab2);
        System.out.println(ab1 == ab3);
        ab3 = ab3.intern();
        System.out.println(ab1 == ab3);
    }
}
