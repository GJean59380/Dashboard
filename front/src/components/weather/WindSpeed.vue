<script lang="ts">
import {defineComponent, PropType} from "vue";
import type { WeatherType } from "@/types/WeatherType";
import Pressure from "@/components/weather/icons/Pressure.vue";
import Humidity from "@/components/weather/icons/Humidity.vue";
import Wind from "@/components/weather/icons/Wind.vue";
import TemperatureMinMax
  from "@/components/weather/icons/TemperatureMinMax.vue";

export default defineComponent({
  name: "Weather Widget",
  components: {TemperatureMinMax, Humidity, Wind, Pressure},
  props: {
    type: {
      type: String as PropType<WeatherType>,
      required: true,
    },
    city: {
      type: String,
      required: true,
    },
    value: {
      type: String,
      required: true,
    },
  },
});
</script>

<template>
  <div
    class="max-w-md p-8 mx-auto rounded-lg dark:text-gray-100"
  >
    <div class="flex justify-between space-x-8">
      <div class="flex flex-col items-center">
        <!--- Icon for wind -->
        <Wind v-if="type === 'Wind'" />
        <Pressure v-else-if="type === 'Pressure'"/>
        <Humidity v-else-if="type === 'Humidity'" />
        <TemperatureMinMax v-else-if="type === 'TemperatureMinMax' || type === 'FeelsLikeTemperature'" />

        <!--- City -->
        <h1 class="text-xl font-semibold">{{ city }}</h1>
      </div>
      <!--- Value -->
      <span class="font-bold text-6xl">{{ value }}</span>
    </div>
  </div>
</template>
