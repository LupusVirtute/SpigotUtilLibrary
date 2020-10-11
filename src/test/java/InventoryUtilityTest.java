import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import com.lupus.utils.InventoryUtility;
import org.apache.commons.lang.RandomStringUtils;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryUtilityTest {
	static List<Material> materials = Arrays.asList(Material.values());
	static ServerMock mock = !MockBukkit.isMocked() ? MockBukkit.mock() : MockBukkit.getMock();
	static Material[] illegalMaterials = {
			Material.BANNER,
			Material.STANDING_BANNER,
			Material.WALL_BANNER,
			Material.BOOK,
			Material.ENCHANTED_BOOK,
			Material.FIREWORK,
			Material.KNOWLEDGE_BOOK,
			Material.LEATHER_BOOTS,
			Material.LEATHER_CHESTPLATE,
			Material.LEATHER_HELMET,
			Material.LEATHER_LEGGINGS,
			Material.MAP,
			Material.POTION,
			Material.LINGERING_POTION,
			Material.SPLASH_POTION,
			Material.SKULL,
			Material.SKULL_ITEM,
			Material.EGG,
			Material.DRAGON_EGG,
			Material.KNOWLEDGE_BOOK,
			Material.MONSTER_EGG,
			Material.MONSTER_EGGS,
	};
	@BeforeAll
	public static void beforeAll(){
		mock = MockBukkit.isMocked() ? MockBukkit.getMock() : MockBukkit.mock();
		List<Material> materialList = new LinkedList<>(materials);
		List<Material> illegalMaterialsList = new LinkedList<>(Arrays.asList(illegalMaterials));
		materialList.removeIf(illegalMaterialsList::contains);
		materials = new ArrayList<>(materialList);

	}
	@Test
	void TestCreateCustomGUI_TestSlots_Success(){
		Random rnd = new Random();
		Inventory inv = InventoryUtility.createCustomGUI(mock.addPlayer(), rnd.nextInt(53)+1, RandomStringUtils.random(60));
		assertNotNull(inv);
	}
	@Test
	void TestCreateCustomGUI_TestInvalidSlots_Failure(){
		Random rnd = new Random();
		Inventory inv = InventoryUtility.createCustomGUI(mock.addPlayer(), rnd.nextInt(7000)+53, RandomStringUtils.random(60));
		assertNull(inv);
	}
	@Test
	void TestFillSlots_TestValidSlots_Success(){
		Random rnd = new Random();

		Inventory inv = InventoryUtility.createCustomGUI(mock.addPlayer(),53,"TEST");
		assertNotNull(inv);
		ItemStack itemStack = new ItemStack(Material.STICK,1);
		int[][] slots = {
				{0,rnd.nextInt(8)+1},
				{rnd.nextInt(8)+9,rnd.nextInt(8)+16},
		};
		InventoryUtility.fillSlots(itemStack,inv,slots[0][0],slots[0][1]);
		InventoryUtility.fillSlots(itemStack,inv,slots[1][0],slots[1][1]);
		for (int i=slots[0][0];i<=slots[0][1];i++){
			assertTrue(inv.getItem(i).isSimilar(itemStack));
		}
		for (int i=slots[1][0];i<=slots[1][1];i++){
			assertTrue(inv.getItem(i).isSimilar(itemStack));
		}

	}
	@Test
	void TestFillSlotsArray_TestValidSlots_Success(){
		Random rnd = new Random();

		Inventory inv = InventoryUtility.createCustomGUI(mock.addPlayer(),54,"TEST");
		assertNotNull(inv);
		ItemStack[] itemStack = getRandomItemAmount();
		int[] slots = {
				0,
				rnd.nextInt(8)+ itemStack.length
		};
		InventoryUtility.fillSlots(itemStack,inv,slots[0]);
		InventoryUtility.fillSlots(itemStack,inv,slots[1]);
		for (int i=slots[0];i-slots[0]<itemStack.length;i++){
			assertTrue(inv.getItem(i).isSimilar(itemStack[i-slots[0]]));
		}
		for (int i=slots[1];i-slots[1]<itemStack.length;i++){
			ItemStack item = itemStack[i-slots[1]];
			ItemStack item2 = inv.getItem(i);
			assertTrue(item2.isSimilar(item));
		}
	}
	int[] sideRows = {
		9,18,27,36, // LEFT
		17,26,35,44, // RIGHT
	};
	@Test
	void TestFillBorder_TestRandomSizes_Success(){
		Random rnd = new Random();
		for (int i=0;i<rnd.nextInt(30);i++){
			int size = rnd.nextInt(27)+26;
			ItemStack randomItem =
				new ItemStack(
					materials.get(
						rnd.nextInt(
								materials.size()
						)
					)
				);
			Inventory inv = InventoryUtility.createCustomGUI(mock.addPlayer(), size,"TEST");
			assertNotNull(inv);
			InventoryUtility.fillBorder(randomItem,inv);
			// Top ROW
			for (int j=0;j<9;j++){
				assertTrue(inv.getItem(j).isSimilar(randomItem));
			}
			// Down ROW
			for (int j=inv.getSize()-9;j<inv.getSize();j++){
				assertTrue(inv.getItem(j).isSimilar(randomItem));
			}
			// UH fuck...
			for (int j=0;j<sideRows.length;j++){
				if (inv.getSize() <= sideRows[j] && j < 4){
					j = 4;
					continue;
				}
				if (inv.getSize() <= sideRows[j] && j >=4){
					break;
				}
				assertTrue(inv.getItem(sideRows[j]).isSimilar(randomItem));
			}
		}
	}
	public ItemStack[] getRandomItemAmount(){
		Random rnd = new Random();
		return getRandomItemAmount(rnd.nextInt(23)+4);
	}
	public ItemStack[] getRandomItemAmount(int length){
		List<ItemStack> items = new ArrayList<>();
		Random rnd = new Random();
		for (int i=0,j=length;i<j;i++){
			items.add(
					new ItemStack(materials.get(
							rnd.nextInt(materials.size()
						)
					)
				)
			);
		}
		ItemStack[] itemsb = new ItemStack[1];
		itemsb = items.toArray(itemsb);
		return itemsb;
	}
	@Test
	void TestCenterItems_HasItems_Success(){
		Random rnd = new Random();
		ItemStack[] items = getRandomItemAmount();
		assertNotNull(items);
		int size = rnd.nextInt(27)+26;
		Inventory inv = InventoryUtility.createCustomGUI(mock.addPlayer(),size,"TEST");
		assertNotNull(inv);
		InventoryUtility.centerItemsInInventory(items,inv);
		// Uh.... fuck...
		int itemsCounted = 0;
		for (int i=0;i<inv.getSize();i++){
			for (ItemStack item : items) {
				if (inv.getItem(i).isSimilar(item)) {
					itemsCounted++;
				}
			}
		}
		// WE JUST NEED TO MAKE SURE IT'S IN THERE .... right?????
		assertEquals(items.length,itemsCounted);
		// I mean if it's not in the center then fuck so much possibilities ....
	}
	int[] slotsThatNeedToBeFilledInTheory = {
		1,2,3,4,5,6,7,
		10,11,12,13,14,15,16,
		19,20,21,22,23,24,25,
		28,29,30,31,32,33,34,
		37,38,39,40,41,42,43,
		46,47,48,49,50,51,52
	};
	@Test
	void TestCenterItems_CenterTest_Success(){
		Random rnd = new Random();
		ItemStack[] items = getRandomItemAmount(42);
		assertNotNull(items);
		int size = 54;
		Inventory inv = InventoryUtility.createCustomGUI(mock.addPlayer(),size,"TEST");
		assertNotNull(inv);
		InventoryUtility.centerItemsInInventory(items,inv);
		// If you're looking at this code let me tell you i am terrified too
		// I don't want to do this with math because this test is designed to see if things
		// Are in ANY OF the right spots to be filled

		for (int i=0;i<inv.getSize();i++)
			for (int j = 0; j< slotsThatNeedToBeFilledInTheory.length; j++)
				if (i == slotsThatNeedToBeFilledInTheory[j])
					for (int k=0;k<items.length;k++)
					{
						boolean beTrue = inv.getItem(i).isSimilar(items[k]);
						if (beTrue)
							break;
						else if (!(k + 1 < items.length))
							fail("None of the items exist in current inventory item pool");
					}
	}
	@Test
	void TestFillSquare_FillWholeInventory_Success(){
		Inventory inv = InventoryUtility.createCustomGUI(mock.addPlayer(),54,"TEST");
		assertNotNull(inv);
		ItemStack item = new ItemStack(
				materials.get(
						new Random().nextInt(
								materials.size()
						)
				)
		);
		InventoryUtility.fillSquare(item,inv,0,0,9,6);
		for(int i=0;i<inv.getSize();i++){
			assertTrue(inv.getItem(i).isSimilar(item));
		}

	}
}
