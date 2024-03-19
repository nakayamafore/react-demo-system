import { Typography } from '@mui/material'
import { FC } from 'react'

export const NotFoundPage: FC = () => {
  return (
    <>
      <div
        style={{
          display: 'flex',
          justifyContent: 'center',
          alignItems: 'center',
          height: '100vh',
          width: '100vw',
          backgroundColor: '#f0f0f0',
          color: '#606060',
          fontFamily: 'Arial, sans-serif',
          fontSize: '24px',
          textAlign: 'center',
        }}>
        <Typography variant="h5" gutterBottom sx={{ fontWeight: 'bold' }}>
          指定のページが存在しません
        </Typography>
      </div>
    </>
  )
}
