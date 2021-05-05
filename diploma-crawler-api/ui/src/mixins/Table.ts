import { Vue, Component } from 'vue-property-decorator';
import { TableHeader } from '@/models/Table';

@Component
export default class Table extends Vue {
  protected tableHeaders: TableHeader[] = [];

  protected get computedTableHeaders(): TableHeader[] {
    return this.tableHeaders.map((header: TableHeader) => ({
      ...header
    }));
  }
}
