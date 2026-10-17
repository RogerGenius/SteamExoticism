import axios, { type AxiosInstance, type AxiosRequestConfig } from 'axios'
import { ElMessage } from 'element-plus'

/** 后端统一响应体 */
export interface Result<T = unknown> {
  code: number
  message: string
  data: T
}

const service: AxiosInstance = axios.create({
  baseURL: import.meta.env.VITE_API_BASE || '/api',
  timeout: 15000,
})

service.interceptors.response.use(
  (response) => {
    const res = response.data as Result
    if (res.code !== 200) {
      ElMessage.error(res.message || '请求失败')
      return Promise.reject(new Error(res.message || '请求失败'))
    }
    // 直接返回业务数据，调用方无需再解包；
    // 此处 intentionally 返回非 AxiosResponse，交由下方 get/post 包装方法恢复类型
    return res.data as any
  },
  (error) => {
    const msg = error?.response?.data?.message || error.message || '网络错误'
    ElMessage.error(msg)
    return Promise.reject(error)
  },
)

/** 包装后的请求方法：返回后端 Result 中的 data */
function request<T = unknown>(config: AxiosRequestConfig): Promise<T> {
  return service.request(config) as unknown as Promise<T>
}

export function get<T = unknown>(url: string, config?: AxiosRequestConfig): Promise<T> {
  return request<T>({ url, method: 'GET', ...config })
}

export function post<T = unknown>(url: string, data?: unknown, config?: AxiosRequestConfig): Promise<T> {
  return request<T>({ url, method: 'POST', data, ...config })
}

export default service
