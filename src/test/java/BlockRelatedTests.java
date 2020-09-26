import com.lupus.utils.BlockRelated;
import org.apache.commons.lang.RandomStringUtils;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Wool;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class BlockRelatedTests {
	List<Material> materials = Arrays.asList(Material.values());
	@Test
	public void TestParseArgumentAsItemStack_TestName_Success(){
		Material material = materials.get(new Random().nextInt(materials.size()-1));
		ItemStack result = BlockRelated.parseArgumentAsItemStack(material.name());
		assertNotNull(result);
		assertEquals(material,result.getType());
	}
	@Test
	public void TestParseArgumentAsItemStack_TestName_Failure(){
		ItemStack result = BlockRelated.
				parseArgumentAsItemStack(
						RandomStringUtils.random(new Random().nextInt(30))
				);
		assertNull(result);
	}
	@Test
	public void TestParseArgumentAsItemStack_TestId_Success(){
		Material material = materials.get(new Random().nextInt(materials.size()));
		ItemStack result = BlockRelated.parseArgumentAsItemStack(String.valueOf(material.getId()));
		assertNotNull(result);
		assertEquals(material,result.getType());
	}
	@Test
	public void TestParseArgumentAsItemStack_TestId_Failure(){
		int fakeMaterial = new Random().nextInt(1000) + materials.size();
		ItemStack result = BlockRelated.parseArgumentAsItemStack(
				String.valueOf(fakeMaterial)
		);
		assertNull(result);
	}
	@Test
	public void TestParseArgumentAsItemStack_TestIdWithSubValue_Success(){
		Material material = Material.WOOL;
		String materialString = "35:2";
		ItemStack result = BlockRelated.parseArgumentAsItemStack(materialString);
		assertNotNull(result);
		assertEquals(material,result.getType());
		Wool wool = (Wool)result.getData();
		assertNotNull(wool);
		assertEquals(DyeColor.MAGENTA,wool.getColor());
	}
	@Test
	public void TestParseArgumentAsItemStack_TestIdWithSubValueNotValue_Failure(){
		String materialString = "35:A";
		ItemStack result = BlockRelated.parseArgumentAsItemStack(materialString);
		assertNull(result);
	}
	@Test
	public void TestParseArgumentAsItemStack_TestIdWithNegativeSubValue_Failure(){
		String materialString = "35:-1";
		ItemStack result = BlockRelated.parseArgumentAsItemStack(materialString);
		assertNull(result);
	}
	@Test
	public void TestParseArgumentAsItemStack_TestIdWithSubValue_Failure(){
		String materialString = "35:50";
		ItemStack result = BlockRelated.parseArgumentAsItemStack(materialString);
		assertNull(result);
	}
}
