import com.lupus.utils.ChatColorToWool;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ChatColorWoolTest {
	public ChatColor[] chatColors ={
			ChatColor.RED,
			ChatColor.DARK_RED,
			ChatColor.GRAY,
			ChatColor.YELLOW,
			ChatColor.AQUA,
			ChatColor.BLACK,
			ChatColor.DARK_AQUA,
			ChatColor.DARK_BLUE,
			ChatColor.DARK_GRAY,
			ChatColor.DARK_GREEN,
			ChatColor.GOLD,
			ChatColor.WHITE,
			ChatColor.LIGHT_PURPLE,
			ChatColor.GREEN

	};
	@Test
	public void TestGetItemFromChatColor(){
		for (ChatColor chatColor : chatColors) {
			ItemStack result = ChatColorToWool.cctw.getItemFromChatColor(chatColor);
			assertNotNull(result);
		}
	}
}
