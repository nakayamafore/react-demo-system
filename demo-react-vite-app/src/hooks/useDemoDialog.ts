import { createContext, useContext } from 'react'

type DemoDialogContextProps = {
  showDialog: (messages: string[], exec: () => void) => void // ダイアログ表示の関数
  showConfirm: (messages: string[]) => void // 確認ダイアログ表示の関数
}
/**
 * DialogContextの作成
 * アプリケーション全体でダイアログ表示機能を提供するためのコンテキスト
 */
export const DialogContext = createContext<DemoDialogContextProps | undefined>(
  undefined
)
/**
 * useDemoDialogカスタムフック
 * DialogContextからダイアログ表示機能を取得するためのフック
 * このフックを使用するには、コンポーネントがDemoDialogProvider内部にある必要がある
 *
 * @returns {DemoDialogContextProps} showDialogとshowConfirm関数を含むオブジェクト
 * @throws {Error} DialogContextが提供されていない場合にエラーを投げる
 */
export const useDemoDialog = () => {
  const context = useContext(DialogContext)
  if (!context) {
    throw new Error(
      'useDemoDialoggを使う場合は、DemoDialogProviderを設定する必要があります。'
    )
  }
  return context
}
