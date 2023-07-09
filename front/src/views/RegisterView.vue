<script lang="ts">
import { defineComponent } from "vue";
import ApiService from "@/services/ApiService";
import TokenService from "@/services/TokenService";

export default defineComponent({
  name: "RegisterView",
  data() {
    return {
      email: "",
      password: "",
      name: "",
      error: {
        show: false,
        message: "",
      },
    };
  },
  methods: {
    async submit() {
      this.error.show = false;
      try {
        await ApiService.create("/auth/register", {
          name: this.name,
          email: this.email,
          password: this.password,
        });
        await ApiService.create("/auth/login", {
          email: this.email,
          password: this.password,
        }).then(({ token }) => {
          TokenService.saveToken(token);
        });
        window.location.href = "/";
      } catch (e) {
        this.error.show = true;
        console.log(e);
      }
    },
  },
});
</script>

<template>
  <section class="bg-gray-50 dark:bg-gray-900">
    <div
      class="flex flex-col items-center justify-center px-6 py-8 mx-auto md:h-screen lg:py-0"
    >
      <div
        class="w-full bg-white rounded-lg shadow dark:border md:mt-0 sm:max-w-md xl:p-0 dark:bg-gray-800 dark:border-gray-700"
      >
        <div class="p-6 space-y-4 md:space-y-6 sm:p-8">
          <h1
            class="text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl dark:text-white"
          >
            Welcome to our dashboard!
          </h1>
          <form class="space-y-4 md:space-y-6" @submit.prevent="submit">
            <div>
              <label
                class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
                >Name
                <input
                  type="text"
                  name="lastname"
                  class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                  placeholder="John Doe"
                  v-model="name"
                  required
                />
              </label>
            </div>
            <div>
              <label
                class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
                >E-mail
                <input
                  type="email"
                  name="email"
                  class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                  placeholder="name@company.com"
                  v-model="email"
                  required
                />
              </label>
            </div>
            <div>
              <label
                class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
                >Password
                <input
                  type="password"
                  name="password"
                  placeholder="••••••••"
                  class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                  v-model="password"
                  required
                />
              </label>
            </div>
            <div>
              <button
                type="submit"
                class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded w-full"
              >
                Sign up
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </section>
</template>

<style scoped></style>
