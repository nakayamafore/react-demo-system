import { FC } from 'react'
import { CompanyDetail } from '../components/CompanyDetail'
import { useCompanyDetail } from '../components/useCompanyDetail'
import { useLocation, useNavigate } from 'react-router-dom'

/**
 * 企業詳細ページ
 * 新規作成モードまたは編集モードで企業の詳細情報を表示し、
 * ユーザーが情報を編集または登録できるようにします。
 */
export const CompanyDetailPage: FC = () => {
  const location = useLocation()
  const navigate = useNavigate()
  // 編集モード判定
  const isEditMode = location.pathname.includes(`/company/edit/`)
  // 戻るボタンでどこに遷移するか指定
  const handleReturnPage = () => {
    navigate(`/company/list`)
  }
  const { company, errors, handleOnChange, handleRegister, resetError } =
    useCompanyDetail(isEditMode, handleReturnPage)

  return (
    <CompanyDetail
      isEditMode={isEditMode}
      company={company}
      handleOnChange={handleOnChange}
      handleRegister={handleRegister}
      handleCancel={handleReturnPage}
      errors={errors}
      resetError={resetError}
    />
  )
}
