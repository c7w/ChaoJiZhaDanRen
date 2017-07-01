package cf.cc7w;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class ChaoJiZhaDanRen extends JavaPlugin implements Listener{
	
	public static Boolean gamestart;
	public static String worldname;

	
	public void onEnable(){
		getServer().getPluginManager().registerEvents(new Event(), this);
		getLogger().info("超级炸弹人插件成功启用");
	}
	
	public void onDisable(){
		getLogger().info("超级炸弹人插件成功关闭");		
	}
	
	public void loadcfg(){
		if(!getDataFolder().exists()) {getDataFolder().mkdir();}
		File file=new File(getDataFolder(),"config.yml");
		if (!(file.exists())) {saveDefaultConfig();}
		reloadConfig();
	}
	
	public void healthset(Player p) {

		p.setMaxHealth(6.0);

	}
	
	public static void givetnt(final Player p){
		Bukkit.getServer().getScheduler().runTaskLater
		(Bukkit.getServer().getPluginManager().getPlugin("ChaoJiZhaDanRen"), new Runnable() {
            
            @SuppressWarnings("deprecation")
			@Override
            public void run() {
            	ItemStack item = new ItemStack(Material.TNT);
            	ItemMeta meta = item.getItemMeta();
            	meta.setDisplayName("§4§l炸弹§8(§a右键地面安放§8)");
            	item.setItemMeta(meta);
            	
            	p.getInventory().setHeldItemSlot(4);
            	p.setItemInHand(item);
                    
            }
    }, 80);
	}
	
	

	
}
