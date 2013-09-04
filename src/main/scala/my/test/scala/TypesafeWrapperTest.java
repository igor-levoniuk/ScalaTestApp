package my.test.scala;

import java.util.ArrayList;
import java.util.List;

/**
 * Project: ScalaTestApp
 * Author:  Igor Levoniuk
 * Mail:    igor.levoniuk@gmail.com
 * Date:    8/12/13
 */
public class TypesafeWrapperTest {

    interface Handler<T> {
        public T handle(T param);
    }

    static class StringHandler implements Handler<String> {

        @Override
        public String handle(String param) {
            return param + "1";
        }
    }

    static class ListHandler implements Handler<List> {

        @Override
        public List handle(List param) {
            List newList = new ArrayList(param);
            newList.add(1);
            return newList;
        }
    }

    static class HandlerFactory {

        public Handler createHandler(Object paramToHadndle) {
            if (paramToHadndle instanceof String) {
                return new StringHandler();
            } else if (paramToHadndle instanceof List) {
                return new ListHandler();
            } else {
                throw new RuntimeException("No handler for " + paramToHadndle.getClass());
            }
        }
    }

    public static void main(String[] args) {
        HandlerFactory hf = new HandlerFactory();
        Object[] arr =  new Object[] {new ArrayList(), "2", "3"};

        for (Object o : arr) {
            System.out.println(hf.createHandler(o).handle(o));
        }
    }

}
