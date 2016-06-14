import java.util.Calendar;

/**
 * Created by Andrew on 2016/5/9.
 */
public class Test {

    public static void main(String[] args) {
        Calendar c= Calendar.getInstance();
        int weeks = c.get(Calendar.WEEK_OF_YEAR);
        System.out.println(weeks);
    }
}
