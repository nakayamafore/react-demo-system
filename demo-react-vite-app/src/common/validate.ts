import * as yup from 'yup'

/**
 * 指定の定義で入力データを検証する
 *
 * @param schema エラーチェック定義
 * @param formValues 入力データ
 * @returns 項目エラー情報<項目名, エラーメッセージ>
 */
export const validate = async <T extends Record<string, unknown>>(
  schema: yup.ObjectSchema<T>,
  formValues: T
): Promise<Record<string, string> | null> => {
  try {
    await schema.validate(formValues, { abortEarly: false })
  } catch (error: unknown) {
    if (error instanceof yup.ValidationError) {
      const errorInfos = error.inner.reduce<Record<string, string>>(
        (acc, e) => {
          if (e.path) {
            console.info(`path: ${e.path}, message: ${e.message}`)
            acc[e.path] = e.message
          }
          return acc
        },
        {}
      )
      return errorInfos
    }
  }
  return null
}
