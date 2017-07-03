package cf.cc7w;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class Items implements Listener {

    @SuppressWarnings("deprecation")
	@EventHandler
    public void addoneheart(PlayerPickupItemEvent evt){
    	Player p = evt.getPlayer();
    	Material i = evt.getItem().getItemStack().getType();
    	if(i.equals(Material.APPLE)){
    		evt.setCancelled(true);
    		evt.getItem().remove();
        	double h = p.getHealth();
        	double hm = p.getMaxHealth();
        	double canh = hm - h;
        	if (canh < 2){
        		h = hm;
        	}else{
        		h = h + 2;
        	}
        	p.setHealth(h);
        	p.sendTitle("", "§c§l生命值回复");
 
    	}


    	

    	
    }

    }
