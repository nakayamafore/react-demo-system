import { FC } from 'react'
import { Button, Paper, Snackbar, TextField, useTheme } from '@mui/material'
import { Title } from './Title'
import { useSnackbar } from '../hooks/useSnackbar'
import { CompanyEntity } from '../common/modelType'

// 入力欄の共通設定
const baseProps = {
  fullWidth: true,
  InputLabelProps: {
    shrink: true,
  },
  // TextFieldがanyでないと受け付けない為
  // eslint-disable-next-line @typescript-eslint/no-explicit-any
} as any

// CompanyDetailコンポーネントのプロパティの型定義
type CompanyDetailProps = {
  isEditMode: boolean // 編集モードか
  company: CompanyEntity // 表示または編集する企業情報
  handleOnChange: (event: React.ChangeEvent<HTMLInputElement>) => void // 入力値の変更時処理
  handleRegister: () => void // 登録または更新処理を実行
  handleCancel: () => void // キャンセル処理
  errors: Record<string, string> | null // エラーメッセージ情報
  resetError: () => void // エラーメッセージのリセット
}
/**
 * 企業情報の詳細または編集フォームを表示するコンポーネント
 */
export const CompanyDetail: FC<CompanyDetailProps> = ({
  isEditMode,
  company,
  handleOnChange,
  errors,
  handleRegister,
  handleCancel,
  resetError,
}) => {
  const theme = useTheme()
  const { openbar, handleCloseSnackbar } = useSnackbar(
    errors?.common,
    resetError
  )

  return (
    <Paper
      sx={{
        display: 'flex',
        flexDirection: 'column',
        rowGap: 2,
        borderRadius: theme.shape.borderRadius,
        p: 3,
        minWidth: 400,
      }}>
      {/* エラーメッセージ表示用のスナックバー */}
      <Snackbar
        open={openbar}
        autoHideDuration={6000}
        onClose={handleCloseSnackbar}
        message={errors?.common}
      />
      {/* ページタイトル */}
      <Title title={isEditMode ? '企業編集' : '企業登録'} />
      <TextField
        {...baseProps}
        label="企業コード"
        name="companyCode"
        value={company.companyCode}
        disabled={isEditMode}
        onChange={handleOnChange}
        error={errors?.companyCode}
        helperText={errors?.companyCode}
      />
      <TextField
        {...baseProps}
        label="企業名"
        name="bizName"
        value={company.bizName}
        onChange={handleOnChange}
        error={errors?.bizName}
        helperText={errors?.bizName}
      />
      <TextField
        {...baseProps}
        label="企業所在地"
        name="bizPlace"
        value={company.bizPlace}
        onChange={handleOnChange}
        error={errors?.bizPlace}
        helperText={errors?.bizPlace}
      />
      <Button variant="contained" onClick={handleRegister} sx={{ mt: 2 }}>
        {isEditMode ? '更新' : '登録'}
      </Button>
      <Button variant="outlined" onClick={handleCancel}>
        戻る
      </Button>
    </Paper>
  )
}
