package wikipedia.testsuites;

import org.testng.annotations.Test;
import utils.Utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TestSMT {
    static Utils utils1;
    @Test
    public void readExcel() {
        utils1 = new Utils();
        Utils.getTestData1("tam anh.xlsx", "Sheet1");
    }
}
