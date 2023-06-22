package com.greedisland.advancements;

import com.google.gson.JsonObject;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.critereon.SerializationContext;
import net.minecraft.resources.ResourceLocation;

public class HunterTrigger extends GreedIslandTriggerBase {
   static final ResourceLocation ID = new ResourceLocation("huntertrigger");

   public ResourceLocation getId() {
      return ID;
   }

   public static class TriggerInstance implements CriterionTriggerInstance {
      public ResourceLocation getCriterion() {
         return HunterTrigger.ID;
      }

      public JsonObject serializeToJson(SerializationContext p_41577_) {
         return new JsonObject();
      }
   }
}