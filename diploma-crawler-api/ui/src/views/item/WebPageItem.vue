<template>
  <v-container>
    <v-row>
      <v-col>
        <v-text-field label="ID" v-model="webPage.id" readonly/>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-text-field label="Portal" v-model="webPage.webPortalName" readonly/>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-text-field label="URL" v-model="webPage.url" readonly/>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-text-field label="Resource" v-model="webPage.resourceName" readonly/>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-textarea label="Head" v-model="webPage.head" outlined readonly/>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-textarea label="Meta" v-model="webPage.metaData" outlined readonly/>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-textarea label="Page text" v-model="webPage.pageText" outlined readonly/>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-text-field label="Creation Time" v-model="webPage.creationTime" readonly/>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-text-field label="Last Modified Time" v-model="webPage.lastModifiedTime" readonly/>
      </v-col>
    </v-row>
  </v-container>
</template>

<script lang="ts">
import {Component, Prop, Vue} from "vue-property-decorator";
import {WebPage} from "@/model/model";

@Component
export default class WebPageItem extends Vue {
  @Prop({type: String}) readonly id!: string;

  private webPageId!: string = '';
  private webPage: WebPage = null;

  private async created(): Promise<void> {
    this.webPageId = this.id;
    await this.loadObject();
  }

  private async loadObject(): Promise<void> {
    this.webPage = await this.$api.getDomainObject({name: "web_page_view", id: this.webPageId})
  }
}
</script>

<style scoped>

</style>
