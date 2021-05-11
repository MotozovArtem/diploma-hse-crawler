import _Vue from 'vue';
import Router from 'vue-router';
import { SearcherScrapperApi as BaseApi} from '@/services/SearcherScrapperApi';
import router from '@/router';

interface SearchParameters {
  key_words: Array<string>,
  max_sites: number
}

interface ScrapParameters {
  target: string
}

export class SearcherScrapperApi extends BaseApi {
  private static instance: SearcherScrapperApi;

  public static getInstance(router: Router): SearcherScrapperApi {
    return SearcherScrapperApi.instance ?? (SearcherScrapperApi.instance = new SearcherScrapperApi(router));
  }

  public async search(params: SearchParameters): Promise<void> {
    const response = await this.execute({
      url: 'search_sites',
      data: params
    });

    return response;
  }

  public async scrap(params: ScrapParameters): Promise<void> {
    const response = await this.execute({
      url: 'start_crawl',
      data: params
    });

    return response;
  }
}

export default function SearcherScrapperApiPlugin(Vue: typeof _Vue): void {
  Vue.prototype.$searcher_scrapper_api = SearcherScrapperApi.getInstance(router);
}
