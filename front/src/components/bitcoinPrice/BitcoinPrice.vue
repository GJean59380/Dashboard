<script lang="ts">
import {defineComponent} from "vue";

export default defineComponent({
  name: "BitcoinPrice",
  props: {
    value: {
      type: String,
      required: true,
    },
  },
});
</script>

<template>
  <!--- Icon -->
  <svg class="fill-current block w-24 h-24 p-2 text-yellow-400"
       xmlns="http://www.w3.org/2000/svg" viewBox="0 0 32 32">
    <g fill-rule="evenodd">
      <path
          d="M21.78 15.37c.51-.61.83-1.4.83-2.26 0-2.74-1.6-4.38-4.24-4.38V5.45c0-.12-.1-.22-.22-.22h-1.27c-.11 0-.2.1-.2.21v3.3h-1.7V5.44c0-.12-.1-.22-.22-.22H13.5c-.12 0-.2.1-.21.21v3.3H9.67c-.12 0-.21.09-.21.21v1.31c0 .12.1.22.21.22h.21c.94 0 1.7.79 1.7 1.75v7c0 .92-.68 1.67-1.55 1.75a.21.21 0 0 0-.18.16l-.33 1.32c-.01.06 0 .13.04.19.04.05.1.08.17.08h3.55v3.3c0 .1.1.2.2.2h1.28c.12 0 .21-.1.21-.22v-3.28h1.7v3.3c0 .1.1.2.21.2h1.27c.12 0 .22-.1.22-.22v-3.28h.85c2.65 0 4.24-1.64 4.24-4.37 0-1.28-.68-2.39-1.68-3zm-6.8-4.01h2.54c.94 0 1.7.78 1.7 1.75 0 .96-.76 1.75-1.7 1.75h-2.55v-3.5zm3.39 8.75h-3.4v-3.5h3.4c.93 0 1.7.78 1.7 1.75 0 .96-.77 1.75-1.7 1.75z"></path>
    </g>
  </svg>
  <div class="hidden lg:flex">
    <div class="w-1/3 text-center py-8">
      <div class="border-r">
        <div class="text-grey-darker mb-2">
          <span class="text-3xl">{{ value }}€</span>
        </div>
      </div>
    </div>
  </div>
</template>
