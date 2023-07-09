<script lang="ts">
import TokenService from "@/services/TokenService";

export default {
  name: "LogoutView",
  setup() {
    try {
      TokenService.destroyToken();
      window.location.href = "/";
    } catch (e) {
      console.log(e);
    }
  },
};
</script>

<template>
  <div></div>
</template>

