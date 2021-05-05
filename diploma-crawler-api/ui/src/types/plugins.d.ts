import { Api } from '@/plugins/api';

declare module 'vue/types/vue' {
  interface Vue {
    $api: Api;
  }
}
