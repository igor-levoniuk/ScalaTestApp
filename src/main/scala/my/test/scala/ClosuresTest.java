package my.test.scala;

/**
 * Project: ScalaTestApp
 * Author:  Igor Levoniuk
 * Mail:    igor.levoniuk@gmail.com
 * Date:    10/4/13
 */
public class ClosuresTest {

    String field;
    TaskClosure taskClosure = null;

    public void setField(String field) {
        this.field = field;
    }

    public void setTaskInternally() {
        this.taskClosure = new TaskClosure() {
            @Override
            void execute() {
                System.out.println("Field value is " + field);
            }
        };
    }

    void runTask() {
        taskClosure.execute();
    }

    abstract  class TaskClosure {
        abstract void execute();
    }

    public static void main(String[] str) {
        ClosuresTest test = new ClosuresTest();
        test.setField("1");
        test.setTaskInternally();
        test.runTask();
        test.setField("2");
        test.runTask();
    }
}
