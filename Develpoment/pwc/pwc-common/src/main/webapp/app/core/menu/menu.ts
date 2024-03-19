export interface Menu {
  id: number;
  path?: string;
  children?: Menu[];
  meta?: Meta;
}

interface Meta {
  label: string;
  functionId?: string;
  icon?: string;
  directory: boolean;
}
