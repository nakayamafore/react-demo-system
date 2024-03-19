import { AxiosInstance } from 'axios'
import MockAdapter from 'axios-mock-adapter'

/**
 * APIのレスポンスをmockする
 * @param reAxios axiosインスタンス
 */
export const mockAxios = (reAxios: AxiosInstance) => {
  const enableMocking = import.meta.env.VITE_REACT_APP_MOCK_API
  if (!enableMocking) return

  const mock = new MockAdapter(reAxios)
  // 1. 企業情報全取得
  // mock.onGet('/api/company').reply(503, {})
  // mock.onGet('/api/company').networkError()

  // 2. 企業情報削除
  // mock.onDelete('/api/company').reply(503, {})
  // mock.onDelete('/api/company').networkError()

  // 3. 企業情報取得
  // mock.onGet('/api/company/AU38398721').reply(404, {})
  // mock.onGet('/api/company/AU38398721').networkError()

  // 4. 企業情報登録
  // mock.onPost('/api/company').reply(409, {})

  // 5. 企業情報更新
  // mock.onPut('/api/company').reply(404, {})

  // 上記の登録外のURLは通常のAPIをを呼び出す
  mock.onAny().passThrough()
}
