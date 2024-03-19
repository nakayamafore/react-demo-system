import { useCallback, useState } from 'react'

/**
 * エラー情報を管理するためのカスタムフック
 * エラーメッセージを管理し、特定のエラーを設定またはリセットする機能を提供します
 *
 * @returns
 */
export const useErrorInfo = () => {
  // エラー情報
  const [errors, setErrors] = useState<Record<string, string> | null>(null)

  /**
   * 指定のキーにエラーメッセージを設定する
   *
   * @param key 項目キー
   * @param message エラーメッセージ
   */
  const setError = useCallback((key: string, message: string) => {
    setErrors((errors) => {
      return {
        ...errors,
        [key]: message,
      }
    })
  }, [])

  /**
   * 指定のキーのエラーをリセット
   *
   * @param key リセットするエラーのキー。デフォルトは 'common'
   */
  const resetError = useCallback(
    (key: string = 'common') => {
      setErrors((errors) => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const { [key]: value, ...restErrors } = errors || {}
        return restErrors
      })
    },
    [setErrors]
  )
  return { errors, setErrors, setError, resetError }
}
