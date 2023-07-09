<script lang="ts">
import { defineComponent } from "vue";

export default defineComponent({
  name: "Top10",
  props: {
    gameLists: {
      type: Array,
      required: true,
    },
  },
});
</script>

<template>
  <div>
    <table class="w-full text-sm text-left text-gray-500 dark:text-gray-400">
      <thead class="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
      <tr>
        <th scope="col" class="py-1 px-1">Title</th>
        <th scope="col" class="py-1 px-1">Normal</th>
        <th scope="col" class="py-1 px-1">Sale</th>
      </tr>
      </thead>
      <tbody>
        <tr v-for="game in gameLists" class="bg-white border-b dark:bg-gray-800 dark:border-gray-700">
          <td class="py-1 px-1">{{ game.title }}</td>
          <td class="py-1 px-1">{{ game.normalPrice }}€</td>
          <td class="py-1 px-1">{{ game.salePrice }}€</td>
        </tr>
        </tbody>
      </table>
    </div>

</template>
