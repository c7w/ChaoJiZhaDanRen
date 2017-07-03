package cf.cc7w;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.GameMode;
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
import org.bukkit.event.entity.ExpBottleEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

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
				double health = vic.getHealth();
				String h = "";
				if(vic.getInventory().getChestplate().getType().toString().equalsIgnoreCase("GOLD_CHESTPLATE")){
					vic.sendTitle(h, "§e§l保护盾§a保护了你免受了此次爆炸伤害 ");		
					vic.getInventory().getChestplate().setType(Material.AIR);
				}else if(health == 2.0){
					h = "§8§l❤§8§l❤§8§l❤";
					vic.sendTitle(h, "§4§l你被炸死了");
					//vic.death
					vic.setGameMode(GameMode.SPECTATOR);
				}else if(health == 4.0){
					h = "§c§l❤§8§l❤§8§l❤";
					vic.sendTitle(h, "§4§l你被炸到了");
					vic.damage(2.0);
				}else if(health == 6.0){
					h = "§c§l❤§c§l❤§8§l❤";
					vic.sendTitle(h, "§4§l你被炸到了");
					vic.damage(2.0);
				}else if (health == 8.0){
					h = "§c§l❤§c§l❤§c§l❤";
					vic.sendTitle(h, "§4§l你被炸到了");
					vic.damage(2.0);
				}else{
					vic.setMaxHealth(6.0);
					int ha = (int) health;
					if (ha%2 == 1){
						ha--;
					}
					health = (double) ha;
					vic.setHealth(health);
				}
				
				
				
				
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
	public void place(BlockPlaceEvent evt){
		Material b = evt.getBlock().getType();
		Player p = evt.getPlayer();
		Location loc = evt.getBlock().getLocation();
		World w = loc.getWorld();

		if(String.valueOf(b).equalsIgnoreCase("TNT")){
			evt.getBlock().setType(Material.AIR);
			p.sendTitle("", "§4§l TNT §2§l已成功安放");
			p.playEffect((evt.getBlock().getLocation()), Effect.ANVIL_USE, 2);
			evt.getBlock().getWorld().spawnEntity(evt.getBlock().getLocation().add(0.5, 0, 0.5), EntityType.PRIMED_TNT);
			ChaoJiZhaDanRen.givetnt(p);
		}
	    if(String.valueOf(b).equalsIgnoreCase("WOOD_PLATE")){
	    	evt.getBlock().setType(Material.AIR);
			p.sendTitle("", "§e§l 地雷 §2§l已成功安放");
			p.playEffect((evt.getBlock().getLocation()), Effect.ANVIL_USE, 2);
			w.getBlockAt(loc.add(0, -1, 0)).setType(Material.REDSTONE_ORE);

					
		}


				
			
	};

		@SuppressWarnings("deprecation")
		@EventHandler()
		public void walk(PlayerMoveEvent evt){
			Player p = evt.getPlayer();
			final World w = p.getWorld();
			Location loc = p.getLocation();
            int x = loc.getBlockX();
            int y = loc.getBlockY() - 1;
            int z = loc.getBlockZ();
            
            final Location loca = new Location(w,(double)x+0.5,(double)y,(double)z+0.5);
            
            //System.out.println(x + "and" + y + "and" + z );
            //System.out.println(w.getBlockAt(x,y,z).getType());
            if(w.getBlockAt(x,y,z).getType().toString().equalsIgnoreCase("REDSTONE_ORE") || w.getBlockAt(x,y,z).getType().toString().equalsIgnoreCase("GLOWING_REDSTONE_ORE")){
            	w.getBlockAt(loca.add(0,1,0)).setType(Material.WEB);
            	p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,80,255,false,false));
            	p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP,80,128,false,false));
            	w.getBlockAt(x,y,z).setType(Material.OBSIDIAN);
            	p.playEffect(loc, Effect.EXPLOSION, 2);
            	w.spawnEntity(loca.add(0,-1,0), EntityType.PRIMED_TNT);
            	p.sendTitle("", "§4§l你踩到了 §e§l地雷 §4§l上！");
            	Bukkit.getServer().getScheduler().runTaskLater
        		(Bukkit.getServer().getPluginManager().getPlugin("ChaoJiZhaDanRen"), new Runnable() {
                    
                    @Override
                    public void run() {
                    	w.getBlockAt(loca.add(0,1,0)).setType(Material.AIR);
                            
                    }
            }, 80);
            	
            	
            }
            
			
		}
		
		@EventHandler()
		public void shouliudan(ExpBottleEvent evt){
			World w = evt.getEntity().getWorld();
			Location loc = evt.getEntity().getLocation();
			w.spawnEntity(loc, EntityType.PRIMED_TNT);
		}
			
		
	}
	
