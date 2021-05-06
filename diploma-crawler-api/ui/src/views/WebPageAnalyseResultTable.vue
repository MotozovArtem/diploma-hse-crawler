<template>
  <v-data-table
    :headers="computedTableHeaders"
    :items="webPageAnalyseResults"
    :loading="isLoading"
  >
    <template v-slot:item.id="{ item }">
      <span>{{ item.id != null && item.id.length > 8 ? item.id.substring(0,8) + "...": item.id }}</span>
    </template>
  </v-data-table>
</template>

<script lang="ts">
import {Component, Mixins} from 'vue-property-decorator';
import Table from "@/mixins/Table";
import {WebPageAnalyseResult} from "@/model/model";
import {TableHeader} from "@/models/Table";

@Component
export default class WebPageAnalyseResultTable extends Mixins(Table) {
  private isLoading = false;
  private webPageAnalyseResults: WebPageAnalyseResult[] = [];

  protected tableHeaders: TableHeader[] = [
    { value: 'id', text: 'ID' },
    { value: 'webPageName', text: 'Web Page Resource' },
    { value: 'startAnalyse', text: 'Started' },
    { value: 'finishAnalyse', text: 'Finished' },
    { value: 'errorText', text: 'Error' },
    { value: 'creationTime', text: 'Creation Time' },
    { value: 'lastModifiedTime', text: 'Last ModifiedTime' }
  ];

  private async created(): Promise<void> {
    await this.loadTasks();
  }

  private async loadTasks(): Promise<void> {
    this.isLoading = true;
    this.webPageAnalyseResults = await this.$api.getDomainObjectList({ name: 'web_page_analyse_result_view' });
    this.isLoading = false;
  }
}
</script>

<style scoped>

</style>
