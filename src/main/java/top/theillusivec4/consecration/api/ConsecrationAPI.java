package top.theillusivec4.consecration.api;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.IndirectEntityDamageSource;

public class ConsecrationAPI {

  public static final String HOLY_ID = "holy";

  private static Map<EntityType<?>, UndeadType> undeadMap = new HashMap<>();
  private static List<BiFunction<LivingEntity, DamageSource, Boolean>> holyAttacks = new ArrayList<>();
  private static List<BiFunction<LivingEntity, DamageSource, Boolean>> holyProtection = new ArrayList<>();
  private static Set<EntityType<?>> holyEntities = new HashSet<>();
  private static Set<Potion> holyPotions = new HashSet<>();
  private static Set<Item> holyItems = new HashSet<>();
  private static Set<Enchantment> holyEnchantments = new HashSet<>();
  private static Set<String> holyDamage = new HashSet<>();
  private static Set<String> holyMaterials = new HashSet<>();

  public static void addHolyProtection(BiFunction<LivingEntity, DamageSource, Boolean> func) {
    holyProtection.add(func);
  }

  public static List<BiFunction<LivingEntity, DamageSource, Boolean>> getHolyProtection() {
    return ImmutableList.copyOf(holyProtection);
  }

  public static void addHolyMaterial(String material) {
    holyMaterials.add(material);
  }

  public static Set<String> getHolyMaterials() {
    return ImmutableSet.copyOf(holyMaterials);
  }

  public static void addHolyDamage(String damageType) {
    holyDamage.add(damageType);
  }

  public static Set<String> getHolyDamage() {
    return ImmutableSet.copyOf(holyDamage);
  }

  public static void addHolyEnchantment(Enchantment enchantment) {
    holyEnchantments.add(enchantment);
  }

  public static Set<Enchantment> getHolyEnchantments() {
    return ImmutableSet.copyOf(holyEnchantments);
  }

  public static void addHolyItem(Item item) {
    holyItems.add(item);
  }

  public static Set<Item> getHolyItems() {
    return ImmutableSet.copyOf(holyItems);
  }

  public static void addHolyPotion(Potion potion) {
    holyPotions.add(potion);
  }

  public static Set<Potion> getHolyPotions() {
    return ImmutableSet.copyOf(holyPotions);
  }

  public static void addHolyEntity(EntityType<?> entityType) {
    holyEntities.add(entityType);
  }

  public static Set<EntityType<?>> getHolyEntities() {
    return ImmutableSet.copyOf(holyEntities);
  }

  public static void addHolyAttack(BiFunction<LivingEntity, DamageSource, Boolean> func) {
    holyAttacks.add(func);
  }

  public static List<BiFunction<LivingEntity, DamageSource, Boolean>> getHolyAttacks() {
    return ImmutableList.copyOf(holyAttacks);
  }

  public static void addUndead(EntityType<?> entityType) {
    addUndead(entityType, UndeadType.NORMAL);
  }

  public static void addUndead(EntityType<?> entityType, String undeadType) {
    if (undeadType.equals("unholy")) {
      addUndead(entityType, UndeadType.UNHOLY);
    } else if (undeadType.equals("absolute")) {
      addUndead(entityType, UndeadType.ABSOLUTE);
    } else {
      addUndead(entityType, UndeadType.NORMAL);
    }
  }

  public static void addUndead(EntityType<?> entityType, UndeadType undeadType) {
    undeadMap.putIfAbsent(entityType, undeadType);
  }

  public static Map<EntityType<?>, UndeadType> getUndead() {
    return ImmutableMap.copyOf(undeadMap);
  }

  public static DamageSource causeHolyDamage(@Nonnull Entity entity) {
    return new EntityDamageSource(HOLY_ID, entity).setMagicDamage();
  }

  public static DamageSource causeIndirectHolyDamage(@Nonnull Entity source,
      @Nullable Entity indirect) {
    return new IndirectEntityDamageSource(HOLY_ID, source, indirect).setMagicDamage();
  }

  public static DamageSource causeHolyDamage() {
    return new DamageSource(HOLY_ID).setMagicDamage();
  }

  public enum UndeadType {
    NORMAL, UNHOLY, ABSOLUTE
  }
}