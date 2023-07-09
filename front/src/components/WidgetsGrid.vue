<script lang="ts">
import { defineComponent, ref } from "vue";
import Widget from "@/components/Widget.vue";
export default defineComponent({
  name: "WidgetsGrid",
  components: { Widget },
  props: {
    widgets: {
      type: Array,
      required: true,
    },
  },
});
</script>

<template>
  <div class="grid overflow-hidden grid-cols-3 grid-rows-3 gap-5">
    <div v-for="widget in widgets">
      <Widget :widgetData="widget" class="dark:bg-gray-800 dark:border-gray-700"/>
    </div>
  </div>
</template>