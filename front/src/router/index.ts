import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";
import LoginView from "@/views/LoginView.vue";
import RegisterView from "@/views/RegisterView.vue";
import ProfileView from "@/views/ProfileView.vue";
import TokenService from "@/services/TokenService";
import OAuthView from "@/views/OAuthView.vue";
import LogoutView from "@/views/LogoutView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "home",
      component: HomeView,
      async beforeEnter(to) {
        if (
            !TokenService.getToken() &&
            to.name !== "login" &&
            to.name !== "register"
        )
          return { name: "login" };
      },
    },
    {
      path: "/login",
      name: "login",
      component: LoginView,
    },
    {
      path: "/oauth2/redirect",
      name: "oauth",
      component: OAuthView,
    },
    {
      path: "/register",
      name: "register",
      component: RegisterView,
    },
    {
      path: "/profile",
      name: "profile",
      component: ProfileView,
      async beforeEnter(to) {
        if (
          !TokenService.getToken() &&
          to.name !== "login" &&
          to.name !== "register"
        )
          return { name: "login" };
      },
    },
    {
      path: "/logout",
      name: "logout",
      component: LogoutView,
      async beforeEnter(to) {
        if (
            !TokenService.getToken() &&
            to.name !== "login" &&
            to.name !== "register"
        )
          return { name: "login" };
      },
    },
  ],
});

export default router;
