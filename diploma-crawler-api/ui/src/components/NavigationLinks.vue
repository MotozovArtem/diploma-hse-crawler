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
      <v-list-item-content>{{ link.text }}</v-list-item-content>
    </v-list-item>
  </v-list>
</template>

<script lang="ts">
import { Vue, Component, Prop } from 'vue-property-decorator';

@Component
export default class NavigationLinks extends Vue {
  @Prop({ type: Array, required: true }) readonly links!: string[];
  @Prop({ type: Boolean, default: true }) readonly exact!: boolean;
}
</script>

<style scoped>

</style>
