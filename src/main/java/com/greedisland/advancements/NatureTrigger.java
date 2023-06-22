package com.greedisland.advancements;

import com.google.gson.JsonObject;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.critereon.DeserializationContext;
import net.minecraft.advancements.critereon.SerializationContext;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.PlayerAdvancements;

public class NatureTrigger extends GreedIslandTriggerBase {
   static final ResourceLocation ID = new ResourceLocation("naturetrigger");

   public ResourceLocation getId() {
      return ID;
   }

   public static class TriggerInstance implements CriterionTriggerInstance {
      public ResourceLocation getCriterion() {
         return NatureTrigger.ID;
      }

      public JsonObject serializeToJson(SerializationContext p_41577_) {
         return new JsonObject();
      }
   }
}