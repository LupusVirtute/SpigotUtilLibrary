import com.lupus.utils.ColorUtil;
import org.bukkit.ChatColor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ColorUtilTest {
	@Test
	public void TestText2Color_StringColorChanged_Success(){
		String test = "&2Test";
		test = ColorUtil.text2Color(test);
		assertNotNull(test);
		assertNotEquals(test.indexOf(ChatColor.COLOR_CHAR), -1);
	}
	@Test
	public void TestText2Color_StringArrayColorChanged_Success(){
		String[] test = {"&2Test","&3Test"};
		ColorUtil.text2Color(test);
		assertNotNull(test);
		for (String s : test) {
			assertNotEquals(s.indexOf(ChatColor.COLOR_CHAR), -1);
		}
	}
}
