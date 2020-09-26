import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import com.lupus.utils.InventoryUtility;
import com.lupus.utils.Skulls;
import org.bukkit.inventory.Inventory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SkullNumberTest {
	static ServerMock mock = MockBukkit.isMocked() ? MockBukkit.getMock() : MockBukkit.mock();
	// ToDo Make this test somehow not get air in slots and proper skulls
	@Test
	void TestIntToSkullConverter_Set9SkullsTopRow_Success(){
		Inventory inventory = InventoryUtility.createCustomGUI(mock.addPlayer(),54,"TEST");
		assertNotNull(inventory);
		int number = 199999992;
		String digits = String.valueOf(number);
		Skulls.intToSkullConverter(inventory,number,0,8);
		for (int i=0;i<9;i++){
			int index = digits.charAt(i) - '0';
			boolean result = inventory.getItem(i).equals(Skulls.numberSkulls[index]);
			assertTrue(result);
		}

	}
}
