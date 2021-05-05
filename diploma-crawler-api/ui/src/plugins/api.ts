import _Vue from 'vue';
import Router from 'vue-router';
import { Api as BaseApi } from '@/services/Api';
import router from '@/router';
import {
  DomainObject
} from '@/model/model';
import {
  DomainObjectListOptions,
  DomainObjectOptions
} from '@/models/Api';

export class Api extends BaseApi {
  private static instance: Api;

  public static getInstance(router: Router): Api {
    return Api.instance ?? (Api.instance = new Api(router));
  }

  public async getDomainObject<T extends DomainObject>(params: DomainObjectOptions): Promise<T> {
    const response = await this.execute({
      url: `${params.name}/${params.id}`
    });

    return response[0];
  }

  public async getDomainObjectList<T extends DomainObject>(params: DomainObjectListOptions): Promise<T[]> {
    const response = await this.execute({
      url: params.name
    });

    console.log(response._embedded);
    // TODO: Нужно исправить получение конкретного списка
    return response._embedded.web_portal;
  }
}

export default function apiPlugin(Vue: typeof _Vue): void {
  Vue.prototype.$api = Api.getInstance(router);
}
