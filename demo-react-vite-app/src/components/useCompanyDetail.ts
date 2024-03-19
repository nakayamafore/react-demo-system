import * as yup from 'yup'
import { useCallback, useEffect, useState } from 'react'
import { validate } from '../common/validate'
import { CompanyEntity, CompanyFindRes } from '../common/modelType'
import { useParams } from 'react-router-dom'
import { useApi } from '../hooks/useApi'
import axios from 'axios'
import { useErrorInfo } from '../hooks/useErrorInfo'
import { ERROR_MESSAGE } from '../common/messages'

// 企業情報のバリデーションスキーマ
const companySchema: yup.ObjectSchema<CompanyEntity> = yup.object().shape({
  companyCode: yup
    .string()
    .matches(/^[a-zA-Z0-9]{10}$/, '企業コードは10桁の英数字のみ許可されます。')
    .required('企業コードは必須です。'),
  bizName: yup.string().required('企業名は必須です。'),
  bizPlace: yup.string().required('企業所在地は必須です。'),
})
// 企業情報の初期状態
const initCompanyEntity = {
  companyCode: '',
  bizName: '',
  bizPlace: '',
}

/**
 * 企業詳細ページの状態とロジックを管理するカスタムフック
 * 新規作成モードと編集モードの両方に対応しています
 *
 * @param {boolean} isEditMode - 編集モードかどうか
 * @param {Function} handleReturnPage - 戻る遷移先を指定する関数
 * @returns カスタムフックが管理する状態と関数のセット
 */
export const useCompanyDetail = (
  isEditMode: boolean,
  handleReturnPage: () => void
) => {
  const { reAxios } = useApi()
  const { companyCode } = useParams()
  const [company, setCompany] = useState<CompanyEntity>(initCompanyEntity)
  const { errors, setErrors, setError, resetError } = useErrorInfo()
  const [onSubmited, setOnSubmited] = useState(false) // 一度登録を押したか(バリデーションがONになる)

  // 編集モード時に企業情報をAPIから取得して設定する
  useEffect(() => {
    if (!isEditMode) return
    const apiCall = async () => {
      try {
        resetError('companyCode')
        const companyFindRes = await reAxios.get<CompanyFindRes>(
          `/api/company/${companyCode}`
        )
        setCompany(companyFindRes.data)
      } catch (error: unknown) {
        if (axios.isAxiosError(error)) {
          switch (error.response?.status) {
            case 404:
              setError('companyCode', ERROR_MESSAGE.NOT_EXIST_COMPANY_CODE)
              break
            default:
              setError('common', ERROR_MESSAGE.CONTACT_SUPPORT)
              break
          }
        }
      }
    }
    apiCall()
  }, [isEditMode, setError, resetError, companyCode, reAxios])

  // 入力値を企業情報へ反映する
  const handleOnChange = useCallback(
    (event: React.ChangeEvent<HTMLInputElement>) => {
      const { name, value } = event.target
      setCompany((company) => ({
        ...company,
        [name]: value,
      }))
    },
    [setCompany]
  )

  // 企業情報のバリデーション処理
  const validateCompany = useCallback(async () => {
    const errorInfos = await validate(companySchema, company)
    setErrors(errorInfos)
    return errorInfos
  }, [setErrors, company])

  // 入力値が変更される毎にバリデーション処理
  useEffect(() => {
    if (onSubmited) {
      validateCompany()
    }
  }, [onSubmited, validateCompany])

  // 登録ボタンの処理
  const handleRegister = useCallback(() => {
    const apiCall = async () => {
      setOnSubmited(true)
      const errors = await validateCompany()
      if (errors && Object.keys(errors).length >= 1) {
        return
      }
      try {
        if (isEditMode) {
          await reAxios.put(`/api/company`, company)
        } else {
          await reAxios.post(`/api/company`, company)
        }
        handleReturnPage()
      } catch (error: unknown) {
        if (axios.isAxiosError(error)) {
          switch (error.response?.status) {
            case 404:
              setError('companyCode', ERROR_MESSAGE.NOT_EXIST_COMPANY_CODE)
              break
            case 409:
              setError('companyCode', ERROR_MESSAGE.DUPLICATE_COMPANY_CODE)
              break
            default:
              setError('common', ERROR_MESSAGE.CONTACT_SUPPORT)
              break
          }
        }
      }
    }
    apiCall()
  }, [
    validateCompany,
    isEditMode,
    handleReturnPage,
    reAxios,
    company,
    setError,
  ])

  return {
    company,
    errors,
    handleOnChange,
    handleRegister,
    setError,
    resetError,
  }
}
