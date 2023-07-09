<script lang="ts">
export default {
  name: "ProfileView",
};
</script>

<template>
  <section class="bg-gray-50 dark:bg-gray-900">
    <div
      class="flex flex-col items-center justify-center px-6 py-8 mx-auto md:h-screen lg:py-0"
    >
      <!--- Error message -->
      <div
        class="p-4 mb-4 text-sm text-red-700 bg-red-100 rounded-lg dark:bg-red-200 dark:text-red-800"
        role="alert"
        v-if="error.show && !error.success"
      >
        <span class="font-medium">Error: </span> {{ error.message }}
      </div>
      <div
        class="p-4 mb-4 text-sm text-green-700 bg-green-100 rounded-lg dark:bg-green-200 dark:text-green-800"
        role="alert"
        v-if="!error.show && error.success"
      >
        {{ error.message }}
      </div>
      <div
        class="w-full bg-white rounded-lg shadow dark:border md:mt-0 sm:max-w-md xl:p-0 dark:bg-gray-800 dark:border-gray-700"
      >
        <div class="p-6 space-y-4 md:space-y-6 sm:p-8">
          <h1
            class="text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl dark:text-white"
          >
            Edit my profile
          </h1>
          <form class="space-y-4 md:space-y-6" @submit.prevent="submit">
            <div>
              <label
                class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
                >Lastname
                <input
                  type="text"
                  name="lastname"
                  class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                  v-model="lastname"
                  required
                />
              </label>
            </div>
            <div>
              <label
                class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
                >Firstname
                <input
                  type="text"
                  name="firstname"
                  class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                  v-model="firstname"
                  required
                />
              </label>
            </div>
            <div>
              <label
                class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
                >Email
                <input
                  type="email"
                  name="email"
                  class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                  v-model="email"
                  required
                />
              </label>
            </div>
            <div>
              <label
                class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
                >Phone number
                <input
                  type="text"
                  name="phone"
                  class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                  v-model="phone"
                  required
                />
              </label>
            </div>
            <div>
              <button
                type="submit"
                class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded w-full"
              >
                Update
              </button>
            </div>
            <div>
              <RouterLink to="/"
                ><button
                  class="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded w-full"
                >
                  Return to homepage
                </button></RouterLink
              >
            </div>
          </form>
        </div>
      </div>
    </div>
  </section>
</template>
