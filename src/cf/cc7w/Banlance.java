package cf.cc7w;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

public class Banlance {

	public static void setblock(Location loc,Material data){
	
		World b = loc.getWorld();
		Block c = b.getBlockAt(loc);
		c.setType(data);
		
	}
	
	
}
