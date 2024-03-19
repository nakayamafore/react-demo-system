import {
  Button,
  Dialog,
  DialogActions,
  DialogContent,
  DialogTitle,
  Typography,
} from '@mui/material'
import { FC } from 'react'

// DemoDialogコンポーネントのプロパティの型定義
type DemoDialogProps = {
  title?: string // ダイアログのタイトル
  messages?: string[] // ダイアログに表示するメッセージの配列
  isShowPopup: boolean // ダイアログを表示するかどうかのフラグ
  isConfirm: boolean // 確認ダイアログで表示するか
  closePopup: () => void // ダイアログを閉じる処理
  execute?: () => void // 実行ボタンが押されたときのコールバック関数
}
/**
 * 汎用的なダイアログコンポーネント
 * 確認ダイアログと実行/キャンセルダイアログの両方のケースに対応しています
 */
export const DemoDialog: FC<DemoDialogProps> = ({
  title = '処理を実行します。よろしいでしょうか？',
  messages = [],
  isShowPopup,
  isConfirm,
  closePopup,
  execute = () => {},
}) => {
  // ダイアログを閉じる
  const handleClose = () => {
    closePopup()
  }
  // 実行ボタンが押された時の処理
  const handleExecute = () => {
    execute()
    closePopup()
  }
  return (
    <>
      <Dialog disableScrollLock open={isShowPopup} fullWidth maxWidth="xs">
        <DialogTitle>{title}</DialogTitle>
        {messages.length !== 0 && (
          <DialogContent>
            {messages.map((message, index) => (
              <Typography key={index} variant="body1">
                {message}
              </Typography>
            ))}
          </DialogContent>
        )}
        <DialogActions>
          {/* 確認ダイアログの場合 */}
          {isConfirm && (
            <Button onClick={handleClose} variant="contained" size="small">
              OK
            </Button>
          )}
          {/* 通常のダイアログの場合 */}
          {!isConfirm && (
            <>
              <Button onClick={handleExecute} variant="contained" size="small">
                実行
              </Button>
              <Button onClick={handleClose} variant="contained" size="small">
                キャンセル
              </Button>
            </>
          )}
        </DialogActions>
      </Dialog>
    </>
  )
}
