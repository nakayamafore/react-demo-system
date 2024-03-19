import { FC, ReactNode, useCallback, useState } from 'react'
import { DemoDialog } from '../components/DemoDialog'
import { DialogContext } from '../hooks/useDemoDialog'

// ダイアログ表示時に利用するオプションの型
type DemoDialogOptions = {
  title?: string // ダイアログのタイトル
  messages?: string[] // 表示するメッセージの配列
  isShowPopup: boolean // ダイアログを表示するか
  isConfirm: boolean // 確認ダイアログか
  closePopup: () => void // ダイアログを閉じる関数
  execute?: () => void //  確認後実行する関数
}
// DemoDialogProviderコンポーネントのプロパティの型
type DemoDialogProviderProps = {
  children: ReactNode // 子コンポーネント
}
/**
 * ダイアログ表示機能を提供するコンテキストプロバイダー
 * 子コンポーネントにダイアログ表示機能を提供し、ダイアログの状態を管理します
 */
export const CmpDialogProvider: FC<DemoDialogProviderProps> = ({
  children,
}) => {
  const [dialogProps, setDialogProps] = useState<DemoDialogOptions | null>(null)
  const openDialog = (options: DemoDialogOptions) => {
    setDialogProps(options)
  }
  const closeDialog = () => {
    setDialogProps(null)
  }

  // 通常のダイアログを表示する関数
  const showDialog = useCallback((messages: string[], exec: () => void) => {
    openDialog({
      title: '確認',
      messages,
      isShowPopup: true,
      isConfirm: false,
      execute: () => {
        exec()
        closeDialog()
      },
      closePopup: () => {
        closeDialog()
      },
    })
  }, [])

  // 確認ダイアログを表示する関数
  const showConfirm = useCallback((messages: string[]) => {
    openDialog({
      title: '確認',
      messages,
      isShowPopup: true,
      isConfirm: true,
      closePopup: () => {
        closeDialog()
      },
    })
  }, [])

  return (
    <DialogContext.Provider value={{ showDialog, showConfirm }}>
      {children}
      {dialogProps && <DemoDialog {...dialogProps} />}
    </DialogContext.Provider>
  )
}
