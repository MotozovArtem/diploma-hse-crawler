import {
  DomainObject,
} from '@/model/model';

export interface Response<T extends DomainObject> {
  hints: Record<string, boolean>;
  more: T[];
  result: T[];
}

export interface DomainObjectLists<T extends DomainObject> {
  [key: string]: T[];
}

export interface DomainObjectOptions {
  name: string;
  id: string;
}


export interface DomainObjectListOptions extends Omit<DomainObjectOptions, 'id'> {
  params?: Record<string, string | number | boolean>
}

export interface DomainObjectResponseMeta {
  ts?: number;
  isNew?: boolean;
  isRemoved?: boolean;
  type: string;
}
