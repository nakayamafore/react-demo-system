import { useCallback, useEffect, useState } from 'react'
import { CompanyEntity, CompanyFindAllRes } from '../common/modelType'
import axios from 'axios'
import { useNavigate } from 'react-router-dom'
import { useApi } from '../hooks/useApi'
import { useDemoDialog } from '../hooks/useDemoDialog'
import { ERROR_MESSAGE } from '../common/messages'

/**
 * 企業一覧ページのロジックを管理するカスタムフック
 * 企業データの取得、詳細画面への遷移、および企業の削除機能を提供します。
 */
export const useCompanyList = () => {
  const navigate = useNavigate()
  const { reAxios } = useApi()
  const { showDialog } = useDemoDialog()
  const [companies, setCompanies] = useState<CompanyEntity[]>([])
  const [message, setMessage] = useState<string | undefined>(undefined)

  /**
   * 指定の企業の詳細画面への遷移する
   *
   * @param {string} companyCode - 遷移する企業のコード。
   */
  const handleOpenDetail = useCallback(
    (companyCode: string) => {
      navigate(`/company/edit/${companyCode}`)
    },
    [navigate]
  )

  /**
   * APIから企業情報を取得して情報を更新する
   */
  const fetchCompanys = useCallback(async () => {
    try {
      const res = await reAxios.get<CompanyFindAllRes>('/api/company')
      setCompanies(res.data.companyEntitys)
    } catch (error: unknown) {
      if (axios.isAxiosError(error)) {
        setMessage(ERROR_MESSAGE.CONTACT_SUPPORT)
      }
    }
  }, [reAxios])

  // 初期表示時に企業情報を取得する
  useEffect(() => {
    fetchCompanys()
  }, [fetchCompanys])

  /**
   * 企業の削除処理を行う
   *
   * @param {string} companyCode - 削除する企業のコード
   * @param {string} companyName - 削除する企業の名称（ダイアログ表示用）
   */
  const handleDelete = useCallback(
    (companyCode: string, companyName: string) => {
      showDialog([`${companyName}を削除します。よろしいですか？`], async () => {
        try {
          await reAxios.delete('/api/company', {
            data: { companyCode },
          })
          // 削除成功時のメッセージ
          setMessage(ERROR_MESSAGE.COMPLETED_DELETION)
          // データ再取得で一覧を更新
          fetchCompanys()
        } catch (error: unknown) {
          if (axios.isAxiosError(error)) {
            setMessage(ERROR_MESSAGE.CONTACT_SUPPORT)
          }
        }
      })
    },
    [reAxios, showDialog, fetchCompanys]
  )

  return {
    companies,
    handleOpenDetail,
    handleDelete,
    message,
    setMessage,
  }
}
