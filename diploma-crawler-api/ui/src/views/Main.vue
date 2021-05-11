<template>
  <v-row>
    <v-col>
      <h2>Search by keywords</h2>
      <v-text-field v-model="keywords" label="Keywords" hint="Enter keywords separated by a space symbol" class="mt-1"/>
      <v-slider v-model="count"
                label="Max sites"
                hint="Default 5 sites"
                max="10"
                min="1"
                :tick-labels="getTickLabels"
                ticks="always"
                tick-size="4"
                step="1"
                class="mt-1"/>
      <v-btn elevation="1" text :disabled="busy" @click="search" class="mt-1">
        Search
      </v-btn>
      <v-alert type="success" :value="searchRequestSuccess" transition="fade-transition" border="left" class="mt-1">
        Request sent successfully!
      </v-alert>
      <v-alert type="warning" :value="searchRequestFailed" transition="fade-transition" border="left" class="mt-1">
        Request send failed!
      </v-alert>
    </v-col>
    <v-col>
      <h2>Scrap site</h2>
      <v-text-field v-model="targetUrl" label="Target URL" hint="Example: https://quotes.toscrape.com" class="mt-1"/>
      <v-btn elevation="1" text :disabled="busy" @click="scrap" class="mt-1">
        Scrap
      </v-btn>
      <v-alert type="success" :value="scrapRequestSuccess" transition="fade-transition" border="left" class="mt-1">
        Request sent successfully!
      </v-alert>
      <v-alert type="warning" :value="scrapRequestFailed" transition="fade-transition" border="left" class="mt-1">
        Request send failed!
      </v-alert>
    </v-col>
  </v-row>
</template>

<script lang="ts">
import {Component, Vue} from 'vue-property-decorator';

@Component
export default class Main extends Vue {
  private keywords: string = "quotes";
  private count: number = 5;
  private targetUrl: string = "https://quotes.toscrape.com";
  private busy: boolean = false;
  private scrapRequestSuccess: boolean = false;
  private scrapRequestFailed: boolean = false;
  private searchRequestSuccess: boolean = false;
  private searchRequestFailed: boolean = false;

  private readonly popupTimeout: number = 3000;


  private get getTickLabels(): Array<string> {
    return ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10']
  }

  private async search(): Promise<void> {
    this.busy = true;
    const key_words = this.keywords.split(" ");
    try {
      await this.$searcher_scrapper_api.search({
        key_words: key_words,
        max_sites: this.count,
      });
      this.searchRequestSuccess = true;
      setTimeout(() => {
        this.searchRequestSuccess = false;
      }, this.popupTimeout);
    } catch (e) {
      this.searchRequestFailed = true;
      setTimeout(() => {
        this.searchRequestFailed = false;
      }, this.popupTimeout);
    }
    this.busy = false;
  }

  private async scrap(): Promise<void> {
    this.busy = true;
    try {
      await this.$searcher_scrapper_api.scrap({
        target: this.targetUrl
      });
      this.scrapRequestSuccess = true;
      setTimeout(() => {
        this.scrapRequestSuccess = false;
      }, this.popupTimeout);
    } catch (e) {
      this.scrapRequestFailed = true;
      setTimeout(() => {
        this.scrapRequestFailed = false;
      }, this.popupTimeout);
    }
    this.busy = false;
  }

}
</script>

<style scoped>

</style>
