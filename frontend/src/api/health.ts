import { get } from './request'

export interface HealthInfo {
  status: string
  app: string
  time: string
  database: string
}

export function getHealth(): Promise<HealthInfo> {
  return get<HealthInfo>('/health')
}
