import './App.css'
import { CssBaseline } from '@mui/material'
import { Navigate, Route, Routes } from 'react-router-dom'
import { CompanyListPage } from './page/CompanyListPage'
import { CompanyDetailPage } from './page/CompanyDetailPage'
import { NotFoundPage } from './page/NotFoundPage'
import { CmpDialogProvider } from './provider/DemoDialogProvider'

function App() {
  return (
    <CmpDialogProvider>
      <CssBaseline />
      <Routes>
        <Route path="/" element={<Navigate to="/company/list" replace />} />
        <Route path="/company/list" element={<CompanyListPage />} />
        <Route path="/company/new" element={<CompanyDetailPage />} />
        <Route
          path="/company/edit/:companyCode"
          element={<CompanyDetailPage />}
        />
        <Route path="*" element={<NotFoundPage />} />
      </Routes>
    </CmpDialogProvider>
  )
}
export default App
