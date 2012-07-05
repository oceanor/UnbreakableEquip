package me.oceanor.UnbreakableEquip;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

public class UnbreakableEquip extends JavaPlugin
{
    public final static Logger logger = Logger.getLogger("Minecraft");

    public void onEnable() 
    {
        getServer().getPluginManager().registerEvents(new Listeners(), this);
        logger.info("[OceUtility] enabled");
	}

    public void onDisable() 
    {
        this.getServer().getScheduler().cancelTasks(this);
    	logger.info("[OceUtility] disabled");
    }

}
