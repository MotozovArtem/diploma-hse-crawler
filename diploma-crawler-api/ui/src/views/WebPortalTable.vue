<template>
  <v-data-table
    :headers="tableHeaders"
    :items="webPortals"
    :loading="isLoading"
    @click:row="showWebPortalItem"
  >
    <template v-slot:item.id="{ item }">
      <span>{{ item.id != null && item.id.length > 8 ? item.id.substring(0, 8) + "..." : item.id }}</span>
    </template>
  </v-data-table>
</template>

<script lang='ts'>
import {Component, Mixins} from 'vue-property-decorator';
import Table from '@/mixins/Table';
import {TableHeader} from '@/models/Table';
import {WebPortal} from '@/model/model';

@Component
export default class WebPortalTable extends Mixins(Table) {
  private isLoading = false;
  private webPortals: WebPortal[] = [];

  protected tableHeaders: TableHeader[] = [
    {value: 'id', text: 'ID'},
    {value: 'portalName', text: 'Portal Name'},
    {value: 'domainName', text: 'Domain Name'},
    {value: 'creationTime', text: 'Creation Time'},
    {value: 'lastModifiedTime', text: 'Last ModifiedTime'}
  ];

  private async created(): Promise<void> {
    await this.loadTasks();
  }

  private async loadTasks(): Promise<void> {
    this.isLoading = true;
    this.webPortals = await this.$api.getDomainObjectList({name: 'web_portal_view'});
    this.isLoading = false;
  }

  private showWebPortalItem(item: WebPortal): void {
    this.$router.push({name: "web_portal_item", params: {id: item.id}})
  }
}
</script>

<style scoped>

</style>
