package my.test.scala;

import java.util.*;

/**
 * Author: Igor Levoniuk
 * Mail: igor.levoniuk@gmail.com
 * Date: 7/13/13
 */
public class ListImplTest {

    static int LIMIT = 100000;

    public static void main(String[] args) {
        test(new ArrayList<String>());
        test(new LinkedList<String>());

        test1(new ArrayList<String>());
        test1(new LinkedList<String>());

    }

    private static void test(List<String> list) {
        System.out.println("Using " + list.getClass());

        for (int i = 0; i < LIMIT; i++) {
            list.add("");
        }

        int pos = LIMIT / 2;

        for (int j = 0; j < 1; j++ ) {
            long start = System.currentTimeMillis();

            System.out.println(start);
            for (int i = 0; i < LIMIT; i++) {
                list.add(pos, "");
            }
            System.out.println(System.currentTimeMillis() - start);
        }
    }

    private static void test1(List<String> list) {
        System.out.println("Using " + list.getClass());

        for (int i = 0; i < LIMIT; i++) {
            list.add("");
        }

        int pos = LIMIT / 2;

        for (int j = 0; j < 1; j++ ) {
            long start = System.currentTimeMillis();

            ListIterator<String> it = list.listIterator();
            for (int i = 0; i < pos; i++) {
                it.next();
            }

            for (int i = 0; i < LIMIT; i++) {
                it.add("");
            }
            System.out.println(System.currentTimeMillis() - start);
        }
    }
}