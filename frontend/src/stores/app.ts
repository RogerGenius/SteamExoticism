import { defineStore } from 'pinia'

/**
 * 全局应用状态示例（用于验证 Pinia 接线）。
 */
export const useAppStore = defineStore('app', {
  state: () => ({
    appName: 'SteamExoticism',
  }),
})
