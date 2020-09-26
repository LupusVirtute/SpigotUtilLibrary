import com.lupus.utils.DateUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DateUtilTest {
	@Test
	void TestGetTimeLeftFromSeconds_Days_Success(){
		String result = DateUtil.getTimeLeftFromSeconds(86400);
		assertTrue(result.contains(DateUtil.DAYS_WORD));
	}
	@Test
	void TestGetTimeLeftFromSeconds_Hour_Success(){
		String result = DateUtil.getTimeLeftFromSeconds(3600);
		assertTrue(result.contains(DateUtil.HOURS_WORD));
	}
	@Test
	void TestGetTimeLeftFromSeconds_Minute_Success(){
		String result = DateUtil.getTimeLeftFromSeconds(60);
		assertTrue(result.contains(DateUtil.MINUTES_WORD));
	}
	@Test
	void TestGetTimeLeftFromSeconds_Second_Success(){
		String result = DateUtil.getTimeLeftFromSeconds(1);
		assertTrue(result.contains(DateUtil.SECONDS_WORD));
	}
	@Test
	void TestGetTimeLeftFromSeconds_Everything_Success(){
		String result = DateUtil.getTimeLeftFromSeconds(90061);
		assertTrue(result.contains(DateUtil.DAYS_WORD));
		assertTrue(result.contains(DateUtil.HOURS_WORD));
		assertTrue(result.contains(DateUtil.MINUTES_WORD));
		assertTrue(result.contains(DateUtil.SECONDS_WORD));
	}
}
