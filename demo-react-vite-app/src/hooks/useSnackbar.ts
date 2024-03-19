import { useEffect, useState } from 'react'

/**
 * スナックバー（一時的なメッセージ表示）の表示状態を管理するカスタムフック
 * 指定されたメッセージに基づいてスナックバーを表示し、自動的に閉じた後にエラー状態をリセットします
 *
 * @param {string | undefined} message 表示するメッセージ
 * @param {Function} resetError エラーメッセージのリセット関数
 */
export const useSnackbar = (
  message: string | undefined,
  resetError: () => void
) => {
  const [openbar, setOpenbar] = useState(false)

  /**
   * スナックバーが閉じる時の処理
   * スナックバーの閉じボタンがクリックされたときや、自動的に閉じるタイミングで呼び出されます
   */
  const handleCloseSnackbar = () => {
    setOpenbar(false)
    resetError()
  }

  useEffect(() => {
    if (message) {
      setOpenbar(true)
    } else {
      setOpenbar(false)
    }
  }, [message])

  return { openbar, handleCloseSnackbar }
}
