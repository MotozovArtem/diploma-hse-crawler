<template>
  <v-list nav dense>
    <v-list-item
      v-for="(link, index) in links"
      :key="index"
      :to="link.href"
      :exact="exact"
      color="primary"
      @click="$emit('click:item')"
    >
      <v-list-item-icon v-if="link.icon">
        <v-tooltip right>
          <template #activator="{ on }">
            <v-icon color="primary" v-on="on">{{ link.icon }}</v-icon>
          </template>
          {{ link.text }}
        </v-tooltip>
      </v-list-item-icon>

      <v-list-item-content><b>{{ link.text }}</b></v-list-item-content>
    </v-list-item>
  </v-list>
</template>

<script lang="ts">
import {Component, Prop, Vue} from 'vue-property-decorator';

@Component
export default class NavigationLinks extends Vue {
  @Prop({type: Array, required: true}) readonly links!: string[];
  @Prop({type: Boolean, default: true}) readonly exact!: boolean;
}
</script>

<style scoped>

</style>
