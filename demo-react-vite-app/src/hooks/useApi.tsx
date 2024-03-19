import { useEffect, useMemo, useState } from 'react'
import axios, { AxiosError, AxiosInstance, AxiosResponse } from 'axios'
import { useDemoDialog } from './useDemoDialog'
import { mockAxios } from '../common/mockAxios'
import { ERROR_MESSAGE } from '../common/messages'

const REACT_APP_API_ENDPOINT = import.meta.env.VITE_REACT_APP_API_ENDPOINT
type ApiRes = {
  isLoading: boolean
  reAxios: AxiosInstance
}
/**
 * APIリクエスト用のカスタムフック
 * API呼び出し中のローディング状態管理とエラーハンドリングを行います
 *
 * @returns  APIリクエストのローディング状態, axiosインスタンス
 */
export const useApi = (): ApiRes => {
  const [isLoading, setIsLoading] = useState(false)
  const instance = useMemo(() => {
    return axios.create({
      baseURL: REACT_APP_API_ENDPOINT,
      headers: {
        'Content-Type': 'application/json',
      },
      responseType: 'json',
    })
  }, [])
  const { showConfirm } = useDemoDialog()

  // APIリクエストとレスポンスに対する共通処理設定
  useEffect(() => {
    instance.interceptors.request.use(async (request) => {
      setIsLoading(true)
      return request
    })
    instance.interceptors.response.use(
      // 正常時
      (response: AxiosResponse) => {
        setIsLoading(false)
        return response
      },
      // エラー発生時
      async (error: AxiosError) => {
        console.log(`エラー発生: ${error}`)
        setIsLoading(false)
        if (axios.isAxiosError(error)) {
          throw error
        } else {
          showConfirm([ERROR_MESSAGE.CONTACT_SUPPORT])
        }
      }
    )
  }, [instance, showConfirm])

  // モック登録
  mockAxios(instance)

  return {
    isLoading,
    reAxios: instance,
  }
}
