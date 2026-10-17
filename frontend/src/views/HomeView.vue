<script setup lang="ts">
import { onMounted, ref } from 'vue'
import { ElMessage } from 'element-plus'
import { getHealth, type HealthInfo } from '@/api/health'

const health = ref<HealthInfo | null>(null)
const loading = ref(false)

onMounted(async () => {
  loading.value = true
  try {
    health.value = await getHealth()
  } catch {
    ElMessage.error('无法连接后端，请确认后端已启动（http://localhost:18080）')
  } finally {
    loading.value = false
  }
})
</script>

<template>
  <div class="home">
    <el-card shadow="never">
      <template #header>
        <span>框架状态</span>
      </template>
      <el-descriptions :column="1" border>
        <el-descriptions-item label="前端">
          Vue 3 + Vite + TypeScript + Element Plus
        </el-descriptions-item>
        <el-descriptions-item label="后端连接">
          <template v-if="loading">检测中…</template>
          <template v-else-if="health">
            {{ health.status }}（数据库：{{ health.database }}）
          </template>
          <template v-else>未连接</template>
        </el-descriptions-item>
      </el-descriptions>
      <p class="home__tip">框架已就绪，业务功能将在后续里程碑中逐步加入。</p>
    </el-card>
  </div>
</template>

<style scoped>
.home__tip {
  margin-top: 12px;
  color: #909399;
  font-size: 13px;
}
</style>
