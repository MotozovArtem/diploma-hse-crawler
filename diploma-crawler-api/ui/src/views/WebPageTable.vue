<template>
  <v-data-table
    :headers="computedTableHeaders"
    :items="webPages"
    :loading="isLoading"
  >
    <template v-slot:item.id="{ item }">
      <span>{{ item.id != null && item.id.length > 8 ? item.id.substring(0,8) + "...": item.id }}</span>
    </template>
    <template v-slot:item.metaData="{ item }">
      <span class="caption">{{ item.metaData != null && item.metaData.length > 50 ? item.metaData.substring(0,50) + "...": item.metaData }}</span>
    </template>
    <template v-slot:item.head="{ item }">
      <span class="caption">{{ item.head != null && item.head.length > 50 ? item.head.substring(0,50) + "...": item.head }}</span>
    </template>
    <template v-slot:item.pageText="{ item }">
      <span class="caption">{{ item.pageText != null && item.pageText.length > 50 ? item.pageText.substring(0,50) + "...": item.pageText }}</span>
    </template>
  </v-data-table>
</template>

<script lang='ts'>
import {Component, Mixins} from 'vue-property-decorator';
import Table from '@/mixins/Table';
import {TableHeader} from '@/models/Table';
import {WebPage} from '@/model/model';

@Component
export default class WebPortalTable extends Mixins(Table) {
  private isLoading = false;
  private webPages: WebPage[] = [];

  protected tableHeaders: TableHeader[] = [
    {value: 'id', text: 'ID'},
    {value: 'url', text: 'URL'},
    {value: 'resourceName', text: 'Resource'},
    {value: 'metaData', text: 'Meta data'},
    {value: 'head', text: 'Head'},
    {value: 'pageText', text: 'Page Text'},
    {value: 'webPortalName', text: 'Web Portal'},
    {value: 'webPageAnalyseResultName', text: 'Analyse result'},
    {value: 'creationTime', text: 'Creation Time'},
    {value: 'lastModifiedTime', text: 'Last ModifiedTime'}
  ];

  private async created(): Promise<void> {
    await this.loadTasks();
  }

  private async loadTasks(): Promise<void> {
    this.isLoading = true;
    this.webPages = await this.$api.getDomainObjectList({name: 'web_page_view'});
    this.isLoading = false;
  }
}
</script>

<style scoped>

</style>
