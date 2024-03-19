import { FC } from 'react'
import {
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Paper,
  IconButton,
} from '@mui/material'
import DeleteIcon from '@mui/icons-material/Delete'
import { CompanyEntity } from '../common/modelType'

// CompanyListコンポーネントのプロパティの型定義
type CompanyListProps = {
  companys: CompanyEntity[] // 表示する企業のリスト
  handleOpenDetail: (companyCode: string) => void // 企業詳細ページへ遷移処理する関数
  handleDelete: (companyCode: string, companyName: string) => void // 企業を削除処理する関数
}
/**
 * 企業のリストを表示するテーブルコンポーネント
 */
export const CompanyList: FC<CompanyListProps> = ({
  companys,
  handleOpenDetail,
  handleDelete,
}) => {
  return (
    <TableContainer component={Paper}>
      <Table sx={{ minWidth: 500 }}>
        {/* テーブルヘッダー */}
        <TableHead>
          <TableRow>
            <TableCell component="th">企業コード</TableCell>
            <TableCell component="th">企業名</TableCell>
            <TableCell component="th">企業所在地</TableCell>
            <TableCell component="th">操作</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {/* 企業一覧 */}
          {companys &&
            companys.map((row) => (
              <TableRow
                key={row.companyCode}
                hover
                sx={{
                  cursor: 'pointer', // ホバー時に指カーソルに
                  '&:hover': {
                    // カーソルが当たっている行の色を変更
                    backgroundColor: 'rgba(0, 0, 0, 0.04)',
                  },
                }}
                onClick={() => {
                  // 詳細を開く
                  handleOpenDetail(row.companyCode)
                }}>
                <TableCell scope="row">{row.companyCode}</TableCell>
                <TableCell scope="row">{row.bizName}</TableCell>
                <TableCell scope="row">{row.bizPlace}</TableCell>
                <TableCell>
                  {/* 削除ボタン */}
                  <IconButton
                    aria-label="delete"
                    onClick={(e) => {
                      e.stopPropagation()
                      handleDelete(row.companyCode, row.bizName)
                    }}>
                    <DeleteIcon />
                  </IconButton>
                </TableCell>
              </TableRow>
            ))}
        </TableBody>
      </Table>
    </TableContainer>
  )
}
