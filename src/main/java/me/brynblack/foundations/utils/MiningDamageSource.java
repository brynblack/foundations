package me.brynblack.foundations.utils;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class MiningDamageSource {
    public static final RegistryKey<DamageType> BROKEN_HAND_DAMAGE =
        RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of("foundations", "broken_hand_damage"));

    public static final RegistryKey<DamageType> SPLINTER_DAMAGE =
        RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of("foundations", "splinter_damage"));

    public static DamageSource brokenHand(World world) {
        var registry = world.getRegistryManager().getOrThrow(RegistryKeys.DAMAGE_TYPE);
        return new DamageSource(registry.getOrThrow(BROKEN_HAND_DAMAGE));
    }

    public static DamageSource splinter(World world) {
        var registry = world.getRegistryManager().getOrThrow(RegistryKeys.DAMAGE_TYPE);
        return new DamageSource(registry.getOrThrow(SPLINTER_DAMAGE));
    }
}
