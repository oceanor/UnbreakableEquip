package me.oceanor.UnbreakableEquip;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;

public class Listeners implements Listener 
{
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent ev)
    {
        if(ev.getEntity() instanceof Player)
        {
            Player p = (Player)ev.getEntity();

            ItemStack[] armor = p.getInventory().getArmorContents();
                
            if(armor.length > 0)
            {
                for(int i = 0; i < armor.length; i++)
                {
                    if(p.hasPermission("unbreakable.all") || p.hasPermission("unbreakable.armor") || p.hasPermission("unbreakable." + armor[i].getType().getId()))
                    {
                        armor[i].setDurability((short)(-armor[i].getType().getMaxDurability()));
                    }
                }
            }
        }
        if(ev.getDamager() instanceof Player)
        {
            Player p = (Player)ev.getDamager();

            if(p.hasPermission("unbreakable.all") || p.hasPermission("unbreakable.tools") || p.hasPermission("unbreakable." + p.getItemInHand().getType().getId()))
            {
                ItemStack handitem = p.getItemInHand();
                if(handitem.getDurability() != handitem.getType().getMaxDurability())
                    handitem.setDurability(Short.MIN_VALUE);
            }
        }
    }
    
    @EventHandler
    public void onBlockBreak(BlockBreakEvent ev)
    {
        Player p = ev.getPlayer();
        if(p.hasPermission("unbreakable.all") || p.hasPermission("unbreakable.tools") || p.hasPermission("unbreakable." + p.getItemInHand().getType().getId()))
        {
            ItemStack handitem = p.getItemInHand();
            if(handitem.getDurability() != handitem.getType().getMaxDurability())
                handitem.setDurability(Short.MIN_VALUE);
        }
    }

    @EventHandler
    public void onBowUse(EntityShootBowEvent ev)
    {
        if(ev.getEntity() instanceof Player)
        {
            Player p = (Player)ev.getEntity();
            
            if(p.hasPermission("unbreakable.all") || p.hasPermission("unbreakable.tools") || p.hasPermission("unbreakable." + p.getItemInHand().getType().getId()))
            {
                ItemStack handitem = p.getItemInHand();
                if(handitem.getDurability() != handitem.getType().getMaxDurability())
                    handitem.setDurability(Short.MIN_VALUE);
            }
        }
    }
/*
    @EventHandler
    public void onItemUse(PlayerInteractEvent ev)
    {
        Player p = ev.getPlayer();

        if(p.hasPermission("unbreakable.all") || p.hasPermission("unbreakable.tools") || p.hasPermission("unbreakable." + ev.getItem().getType().getId()))
        {
            ItemStack handitem = ev.getItem();
            if(handitem.getDurability() != handitem.getType().getMaxDurability())
                handitem.setDurability(Short.MIN_VALUE);
        }
    }
*/
}
