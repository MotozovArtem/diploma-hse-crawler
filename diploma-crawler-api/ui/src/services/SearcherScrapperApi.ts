import axios, {AxiosInstance, AxiosRequestConfig} from 'axios';
import Router from 'vue-router';

interface RequestOptions extends AxiosRequestConfig {
  isFormData?: boolean;
}

export class SearcherScrapperApi {
  private readonly axiosInstance: AxiosInstance;
  private readonly router: Router;

  protected constructor(router: Router) {
    this.router = router;
    this.axiosInstance = axios.create({baseURL: process.env.VUE_APP_CRAWLER_HOST});
  }

  protected async execute(options: RequestOptions): Promise<any> {
    const method = "POST";
    const requestParams: RequestOptions = {
      url: options.url,
      method,
      data: options.data,
      headers: {'Content-Type': 'application/json'}
    };

    this.axiosInstance.interceptors.response.use(
      response => response,
      error => {
        return Promise.reject(error);
      }
    );

    try {
      const response = await this.axiosInstance.request(requestParams);
      return response.data;
    } catch (error) {
      console.error(error.response);
      throw error;
    }
  }
}
