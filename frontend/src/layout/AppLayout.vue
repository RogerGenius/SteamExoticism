<script setup lang="ts">
import { storeToRefs } from 'pinia'
import { useAppStore } from '@/stores/app'

const appStore = useAppStore()
const { appName } = storeToRefs(appStore)
</script>

<template>
  <el-container class="app-layout">
    <el-header class="app-header" height="60px">
      <div class="app-header__inner">
        <span class="app-logo">{{ appName }}</span>
      </div>
    </el-header>
    <el-main class="app-main">
      <div class="app-content">
        <slot />
      </div>
    </el-main>
  </el-container>
</template>

<style scoped>
.app-layout {
  min-height: 100vh;
}
.app-header {
  background: #1f2d3d;
  color: #fff;
  display: flex;
  align-items: center;
  padding: 0 16px;
}
.app-header__inner {
  display: flex;
  align-items: center;
  width: 100%;
  max-width: 1200px;
  margin: 0 auto;
}
.app-logo {
  font-size: 18px;
  font-weight: 600;
}
.app-main {
  background: #f5f7fa;
  padding: 16px;
}
.app-content {
  max-width: 1200px;
  margin: 0 auto;
}
@media (max-width: 768px) {
  .app-main {
    padding: 8px;
  }
}
</style>
