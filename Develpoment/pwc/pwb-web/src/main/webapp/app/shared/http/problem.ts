export interface Problem {
  detail: string;
  message: string;
  path: string;
  status: number;
  title: string;
  type: string;
  level?: 'ERROR' | 'WARN' | 'INFO' | 'SUCCESS';
}
