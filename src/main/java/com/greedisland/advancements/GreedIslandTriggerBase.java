package com.greedisland.advancements;

import com.google.gson.JsonObject;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.PlayerAdvancements;

public class GreedIslandTriggerBase extends SimpleCriterionTrigger<GreedIslandTriggerBase.TriggerInstance> {
   public static final ResourceLocation ID = new ResourceLocation("greedislandtriggerbase");

   public ResourceLocation getId() {
      return ID;
   }

   @Override
   protected TriggerInstance createInstance(JsonObject p_66248_, EntityPredicate.Composite p_66249_, DeserializationContext p_66250_) {
      return new TriggerInstance(ID, p_66249_);
   }

   public static class TriggerInstance extends AbstractCriterionTriggerInstance {
      public TriggerInstance(ResourceLocation p_16975_, EntityPredicate.Composite p_16976_) {
         super(p_16975_, p_16976_);
      }

      public ResourceLocation getCriterion() {
         return GreedIslandTriggerBase.ID;
      }

      public JsonObject serializeToJson(SerializationContext p_41577_) {
         return new JsonObject();
      }
   }
}