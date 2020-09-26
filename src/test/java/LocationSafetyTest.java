import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import com.lupus.utils.LocationsUtil;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LocationSafetyTest {
	ServerMock mock = MockBukkit.isMocked() ? MockBukkit.getMock() : MockBukkit.mock();
	Player player = mock.addPlayer();
	World world = mock.addSimpleWorld("TEST");
	@Test
	void TestIsLocationSafe_LegsDangerous_Success(){

		Location loc = world.getSpawnLocation();
		player.teleport(loc);
		loc.getBlock().setType(Material.LAVA);
		boolean result = LocationsUtil.isSafeLocation(loc);
		assertFalse(result);
	}
	@Test
	void TestIsLocationSafe_StoneLegs_Success(){
		Location loc = world.getSpawnLocation();
		player.teleport(loc);
		loc.getBlock().setType(Material.STONE);
		boolean result = LocationsUtil.isSafeLocation(loc);
		assertTrue(result);
	}
	@Test
	void TestIsLocationSafe_StoneAbove_Success(){
		Location loc = world.getSpawnLocation().add(0,1,0);
		player.teleport(loc);
		loc.getBlock().setType(Material.STONE);
		boolean result = LocationsUtil.isSafeLocation(loc);
		assertFalse(result);
	}
	@Test
	void TestIsLocationSafe_LavaBelow_Success(){
		Location loc = world.getSpawnLocation().add(0,1,0);
		player.teleport(loc);
		loc.getBlock().setType(Material.STONE);
		boolean result = LocationsUtil.isSafeLocation(loc);
		assertFalse(result);
	}

}
