export type CompanyFindAllRes = {
  companyEntitys: CompanyEntity[]
}
export type CompanyFindRes = CompanyEntity
export type CompanyEntity = {
  companyCode: string
  bizName: string
  bizPlace: string
}
