import { FC, useCallback } from 'react'
import { useCompanyList } from '../components/useCompanyList'
import { CompanyList } from '../components/CompanyList'
import { Box, Fab, Paper, Snackbar, useTheme } from '@mui/material'
import AddIcon from '@mui/icons-material/Add'
import { useNavigate } from 'react-router-dom'
import { Title } from '../components/Title'
import { useSnackbar } from '../hooks/useSnackbar'

/**
 * 企業一覧ページ
 * 登録されている企業一覧を表示し、企業追加、詳細表示、削除機能を提供します
 */
export const CompanyListPage: FC = () => {
  const theme = useTheme()
  const navigate = useNavigate()
  const { companies, handleOpenDetail, handleDelete, message, setMessage } =
    useCompanyList()
  const { openbar, handleCloseSnackbar } = useSnackbar(message, () => {
    setMessage(undefined)
  })

  // 企業追加ボタン処理
  const handleAdd = useCallback(() => {
    // 企業追加ページへの遷移
    navigate(`/company/new`)
  }, [navigate])

  return (
    <Paper
      sx={{
        position: 'relative',
        display: 'flex',
        flexDirection: 'column',
        rowGap: 2,
        borderRadius: theme.shape.borderRadius,
        p: 3,
        minWidth: 400,
      }}>
      <Title title="企業一覧" />
      {/* 企業一覧 */}
      <CompanyList
        companys={companies}
        handleOpenDetail={handleOpenDetail}
        handleDelete={handleDelete}
      />
      {/* 企業追加ボタン */}
      <Box
        sx={{
          display: 'flex',
          justifyContent: 'flex-end',
        }}>
        <Fab color="primary" aria-label="add" onClick={handleAdd}>
          <AddIcon />
        </Fab>
      </Box>
      {/* メッセージ */}
      <Snackbar
        open={openbar}
        autoHideDuration={6000}
        onClose={handleCloseSnackbar}
        message={message}
      />
    </Paper>
  )
}
