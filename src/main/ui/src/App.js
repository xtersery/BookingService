import React, {useState, useEffect} from 'react';
import { Layout } from 'antd';
import Content from './components/Content.tsx';
import Head from './components/Head.tsx';
import { BrowserRouter } from 'react-router-dom';
const { Footer } = Layout;

function App() {

  return (
    <>
        <Head/>
        <Content/>

        <Footer
          style={{
            textAlign: 'center',
          }}
        >
          FlightBookingService ©2023
        </Footer>


    </>
  );
}

export default App;
