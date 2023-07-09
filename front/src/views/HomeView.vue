<script lang="ts">
import WidgetsGrid from "@/components/WidgetsGrid.vue";
import Widget from "@/components/Widget.vue";
import {defineComponent, ref} from "vue";
import isLogged from "@/helpers/IsLogged";
import ApiService from "@/services/ApiService";

export default defineComponent({
  methods: {
    isLogged() {
      return isLogged
    }
  },
  components: {Widget, WidgetsGrid},
  data() {
    return {
      widgets: new Array<Object>(),
      temp: [],
    }
  },
  async created() {
    const widgetsRef = await ApiService.getAll("/widget").then(response => {
      return response;
    });
    const widgetsUser = await ApiService.getAll("/user/whoami").then(({widgets}) => {
      return widgets;
    });

    for (let i = 0; i < widgetsUser.length; i++)
      this.widgets.push({
        id: widgetsUser[i].widget.id,
        parameter: "Paris",
        state: '1',
        widget: widgetsRef.find((item: { id: any; }) => item.id === widgetsUser[i].widget.id).name
      })
    for (let i = this.widgets.length; i < 9; i++)
      this.widgets.push({});
    this.widgets = [...this.widgets];
    console.log(this.widgets);
  },
  watch: {
    "widgets": {
      deep: true,
      handler() {
        console.log("CHANGEMENT");
      }
    }
  }
});
</script>

<template>
  <section class="bg-gray-50 dark:bg-gray-900">
    <RouterLink to="/logout" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">Logout</RouterLink>
    <WidgetsGrid class="h-screen w-screen p-6" :widgets="widgets" />
  </section>
</template>
