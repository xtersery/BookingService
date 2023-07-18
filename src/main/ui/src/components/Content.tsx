import React from 'react'
import {Layout} from 'antd'
import background from './images/background.png'
import FirstPage from './FirstPage'
import BookingArea from './BookingArea'
import { Route, Routes } from 'react-router-dom'


const { Content } = Layout;

const Head: React.FC = () => {
    return (<>
        <Content
        style={{
          backgroundImage: `url(${background})`,
          backgroundRepeat: 'no-repeat',
          backgroundSize: '74%',
          backgroundPosition: '60% 3%',
          padding: '10px 0px',
          alignItems: 'center'
        }}
      >
        {/* <BookingArea/> */}
        <Routes>
          <Route path="/" element={<FirstPage/>}/>
          <Route path="book" element={<BookingArea/>}/>
        </Routes>

      </Content>
        </>);

  }

  export default Head;
