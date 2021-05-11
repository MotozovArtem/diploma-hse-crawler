import {Api} from '@/plugins/api';
import {SearcherScrapperApi} from "@/plugins/searcher_scrapper_api";

declare module 'vue/types/vue' {
  interface Vue {
    $api: Api;
    $searcher_scrapper_api: SearcherScrapperApi;
  }
}
