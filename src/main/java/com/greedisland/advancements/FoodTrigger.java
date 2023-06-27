package com.greedisland.advancements;

import com.google.gson.JsonObject;
import com.greedisland.GreedIsland;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

public class FoodTrigger extends SimpleCriterionTrigger<FoodTrigger.Instance> {

   public static final ResourceLocation ID = new ResourceLocation(GreedIsland.MODID, "foodtrigger");

   @Override
   public ResourceLocation getId() {
      return ID;
   }

   public void trigger(ServerPlayer player) {
      this.trigger(player, (instance) -> true);
   }

   @Override
   protected Instance createInstance(JsonObject p_66248_, EntityPredicate.Composite p_66249_, DeserializationContext p_66250_) {
      return new FoodTrigger.Instance();
   }

   public static class Instance extends AbstractCriterionTriggerInstance {

      public Instance() {
         super(FoodTrigger.ID, EntityPredicate.Composite.ANY);
      }

      public static Instance trigger() {
         return new Instance();
      }
   }
}