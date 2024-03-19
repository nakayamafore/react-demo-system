import { Typography } from '@mui/material'
import { FC } from 'react'

// Titleコンポーネントのプロパティの型定義
type TitleProps = {
  title: string // タイトル
}
/**
 * タイトル
 */
export const Title: FC<TitleProps> = ({ title }) => {
  return (
    <Typography variant="h6" sx={{ my: 2 }}>
      {title}
    </Typography>
  )
}
