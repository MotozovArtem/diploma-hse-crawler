<template>
  <v-container>
    <v-row>
      <v-col>
        <v-text-field label="ID" v-model="webPageAnalyseResult.id" readonly/>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-text-field label="Start analyse" v-model="webPageAnalyseResult.startAnalyse" readonly/>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-text-field label="Finish analyse" v-model="webPageAnalyseResult.finishAnalyse" readonly/>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-text-field label="Analyse Phase" v-model="webPageAnalyseResult.phase" readonly/>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-textarea label="Lemmatization" v-model="webPageAnalyseResult.lemmatization" outlined readonly/>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-textarea label="Normzalization" v-model="webPageAnalyseResult.normalization" outlined readonly/>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-textarea label="Raw Text" v-model="webPageAnalyseResult.rawText" outlined readonly/>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-textarea label="Error text" v-model="webPageAnalyseResult.errorText" outlined readonly/>
      </v-col>
    </v-row>
<!--    <v-row>-->
<!--      <v-col>-->
<!--        <v-text-field label="Word frequency analyse" v-model="webPageAnalyseResult." readonly/>-->
<!--      </v-col>-->
<!--    </v-row>-->
    <v-row>
      <v-col>
        <v-text-field label="Creation Time" v-model="webPageAnalyseResult.creationTime" readonly/>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-text-field label="Last Modified Time" v-model="webPageAnalyseResult.lastModifiedTime" readonly/>
      </v-col>
    </v-row>
  </v-container>
</template>

<script lang="ts">
import {Component, Prop, Vue} from "vue-property-decorator";
import {WebPageAnalyseResult} from "@/model/model";

@Component
export default class WebPageAnalyseResultItem extends Vue {
  @Prop({type: String}) readonly id!: string;

  private webPageAnalyseResultId: string = '';
  private webPageAnalyseResult: WebPageAnalyseResult = null;

  private async created(): Promise<void> {
    this.webPageAnalyseResultId = this.id;
    await this.loadObject();
  }

  private async loadObject(): Promise<void> {
    this.webPageAnalyseResult = await this.$api.getDomainObject({
      name: "web_page_analyse_result_view",
      id: this.webPageAnalyseResultId
    })
  }
}
</script>

<style scoped>

</style>
