<script lang="ts">
import { defineComponent } from "vue";
import { useRoute } from "vue-router";
import TokenService from "@/services/TokenService";

export default defineComponent({
  name: "OAuthView",
  async setup() {
    try {
      const route = useRoute();
      console.log(route.query.token);
      TokenService.saveToken(route.query.token as string);
      window.location.href = "/";
    } catch (e) {
      console.log(e);
    }
  },
});
</script>

<template>
  <div></div>
</template>