package wikipedia.testsuites;

import org.testng.annotations.Test;
import utils.Utils;

public class TestSMT {
    static Utils utils1;
    @Test
    public void readExcel(){
        utils1 = new Utils();
        Utils.getTestData1("tam anh.xlsx","Sheet1");
    }
}
