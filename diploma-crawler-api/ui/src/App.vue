<template>
  <!-- App.vue -->
  <v-app>
    <v-navigation-drawer app>
      <navigation-links :links="navLinks"/>
    </v-navigation-drawer>
    <v-app-bar app color="primary">
      <v-app-bar-title style="color: white">Crawler API UI</v-app-bar-title>
    </v-app-bar>
    <v-main>
      <v-container fluid>
        <router-view/>
      </v-container>
    </v-main>
  </v-app>
</template>

<script lang="ts">
import {Component, Vue} from 'vue-property-decorator';
import NavigationLinks from '@/components/NavigationLinks.vue';
import VueRouter, {RouteConfig} from "vue-router";

@Component({
  components: {
    NavigationLinks
  }
})
export default class App extends Vue {
  get navLinks(): { [key: string]: string | { [key: string]: string } }[] {
    const router: VueRouter = this.$router;
    return (router.options?.routes || [])
      .reduce((routes: { [key: string]: string | { [key: string]: string } }[], route: RouteConfig) => {
        if (route.meta?.showInMenu) {
          routes.push({
            text: route.meta?.title,
            href: {name: route.name || ''},
            icon: route.meta?.icon
          });
        }
        return routes;
      }, []);
  }
}
</script>
