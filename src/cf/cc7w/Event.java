package cf.cc7w;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityExplodeEvent;

public class Event implements Listener {

	
	@SuppressWarnings("deprecation")
	@EventHandler()
	public void ondamage(EntityDamageEvent evt){
		
		DamageCause damtypea = evt.getCause();
		EntityType victypea = evt.getEntityType();
		String damtype = String.valueOf(damtypea);
		String victype = String.valueOf(victypea);
		
		
		ChaoJiZhaDanRen.gamestart = true;
		
		if(ChaoJiZhaDanRen.gamestart){
			
			if((damtype.equals("ENTITY_ATTACK") || damtype.equals("FALL"))  && victype.equals("PLAYER")){
				
				evt.setCancelled(true);
				
			}
			
			
			
			if(damtype.equals("ENTITY_EXPLOSION") && victype.equals("PLAYER")){
				Player vic = (Player) evt.getEntity();
				
				evt.setCancelled(true);
				vic.damage(2.0);
				double health = vic.getHealth();
				String h = "";
				switch ((int) health){
					case 0:
						h = "§8§l❤§8§l❤§8§l❤";
						break;
					case 2:
						h = "§c§l❤§8§l❤§8§l❤";
						break;
					case 4:
						h = "§c§l❤§c§l❤§8§l❤";
						break;
					case 6:
						h = "§c§l❤§c§l❤§c§l❤";
						break;
					default:
						vic.setMaxHealth(6.0);
						break;
						
						
				}
				vic.sendTitle(h, "§4§l你被炸到了");
				
			}
		}
		
	}
	
	
	@EventHandler()
	public void onexplode(EntityExplodeEvent evt){
		
		evt.setCancelled(true);

		Location eloc = evt.getLocation();
		World w = eloc.getWorld();
        
		
		int x = (int) eloc.getX();
		int y = (int) eloc.getY();
		int z = (int) eloc.getZ() -1 ;

		Block a = w.getBlockAt(x+1,y,z);
		Block b = w.getBlockAt(x-1,y,z);
		Block c = w.getBlockAt(x,y,z+1);
		Block d = w.getBlockAt(x,y,z-1);
		
		
		if(String.valueOf(a.getType()).equalsIgnoreCase("LOG")){
			w.getBlockAt(x+1,y,z).setType(Material.AIR);
			w.getBlockAt(x+1,y+1,z).setType(Material.AIR);
		}
		
		if(String.valueOf(b.getType()).equalsIgnoreCase("LOG")){
			w.getBlockAt(x-1,y,z).setType(Material.AIR);
			w.getBlockAt(x-1,y+1,z).setType(Material.AIR);
		}
		
		if(String.valueOf(c.getType()).equalsIgnoreCase("LOG")){
			w.getBlockAt(x,y,z+1).setType(Material.AIR);
			w.getBlockAt(x,y+1,z+1).setType(Material.AIR);
		}
		
		if(String.valueOf(d.getType()).equalsIgnoreCase("LOG")){
			w.getBlockAt(x,y,z-1).setType(Material.AIR);
			w.getBlockAt(x,y+1,z-1).setType(Material.AIR);
		}
		
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler()
	public void placetnt(BlockPlaceEvent evt){
		Material b = evt.getBlock().getType();
		Player p = evt.getPlayer();

		if(String.valueOf(b).equalsIgnoreCase("TNT")){
			
			p.sendTitle("", "§4§l TNT §2§l已成功安放");
			p.playEffect((evt.getBlock().getLocation()), Effect.ANVIL_USE, 2);
			evt.setCancelled(true);
			evt.getBlock().getWorld().spawnEntity(evt.getBlock().getLocation().add(0.5, 0, 0.5), EntityType.PRIMED_TNT);
			p.getInventory().clear();
			ChaoJiZhaDanRen.givetnt(p);
		
					
		}

		
				
			};


			
		
	}
	
