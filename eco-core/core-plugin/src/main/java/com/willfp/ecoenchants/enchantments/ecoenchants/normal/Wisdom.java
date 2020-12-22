package com.willfp.ecoenchants.enchantments.ecoenchants.normal;

import com.willfp.eco.util.events.naturalexpgainevent.NaturalExpGainEvent;
import com.willfp.ecoenchants.enchantments.EcoEnchant;
import com.willfp.ecoenchants.enchantments.EcoEnchants;
import com.willfp.ecoenchants.enchantments.util.EnchantChecks;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
public class Wisdom extends EcoEnchant {
    public Wisdom() {
        super(
                "wisdom", EnchantmentType.NORMAL
        );
    }

    // START OF LISTENERS

    @EventHandler
    public void onExpChange(NaturalExpGainEvent event) {
        Player player = event.getExpChangeEvent().getPlayer();

        if(event.getExpChangeEvent().getAmount() < 0) return;

        if (!EnchantChecks.mainhand(player, this)) return;

        if(this.getDisabledWorlds().contains(player.getWorld())) return;

        int level = EnchantChecks.getMainhandLevel(player, this);

        event.getExpChangeEvent().setAmount((int) Math.ceil(event.getExpChangeEvent().getAmount() * (1 + (level * this.getConfig().getDouble(EcoEnchants.CONFIG_LOCATION + "bonus-per-point")))));
    }
}
