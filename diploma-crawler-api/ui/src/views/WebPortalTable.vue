<template>
  <v-data-table
    :headers="tableHeaders"
    :items="webPortals"
    :loading="isLoading"
  />
</template>

<script lang='ts'>
import { Component, Mixins } from 'vue-property-decorator';
import Table from '@/mixins/Table';
import { TableHeader } from '@/models/Table';
import { WebPortal } from '@/model/model';

@Component
export default class WebPortalTable extends Mixins(Table) {
  private isLoading = false;
  private webPortals: WebPortal[] = [];

  protected tableHeaders: TableHeader[] = [
    // { value: 'id', text: 'ID' },
    { value: 'portalName', text: 'Portal Name' },
    { value: 'domainName', text: 'Domain Name' },
    { value: 'creationTime', text: 'Creation Time' },
    { value: 'lastModifiedTime', text: 'Last ModifiedTime' }
  ];

  private async created(): Promise<void> {
    await this.loadTasks();
  }

  private async loadTasks(): Promise<void> {
    this.isLoading = true;
    this.webPortals = await this.$api.getDomainObjectList({ name: 'web_portal' });
    this.isLoading = false;
    console.log(this.webPortals);
  }
}
</script>

<style scoped>

</style>
