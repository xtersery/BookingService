import React, {useState} from 'react';
import {Layout, Menu, MenuProps, Row, Col, Button} from 'antd';
import logo from './images/logo.png';
import { Route, Routes, useNavigate } from 'react-router-dom';
const { Header } = Layout;


const items: MenuProps['items'] = [
  {
    label: 'Travelling with us',
    key: 'info'
  },
  {
    label: 'Skymiles',
    key: 'skymiles'
  },
  {
    label: 'Need help?',
    key: 'help'
  }
];


const Head: React.FC = () => {
  const [current, setCurrent] = useState('info');
  const navigate = useNavigate();

  const onClick: MenuProps['onClick'] = (e) => {
    console.log('click', e);
    setCurrent(e.key);
  }

  const signUp = (e) => {
    navigate("authorization");
  }

  return (
   <Header
      style={{
        display: 'contents',
        alignItems: 'center',
      }}
    >
      <Row style = {{height: '10vh'}}>
        <Col offset={1}>
          <img src={logo} alt='logo' style={{height: '10vh'}}/>
        </Col>
        <Col flex='auto' offset={5}>
          <Menu onClick={onClick} selectedKeys={[current]} mode="horizontal" items={items} style={{height: '8vh'}}/>
        </Col>
        <Col style={{alignItems: 'center'}} flex='10hw' pull={1}>
          <Button style={{marginInline: '7pt'}} target='authorization' onClick={signUp}>Sign up</Button>
          <Button style={{marginInline: '7pt'}}>Log in</Button>
          <Button style={{marginInline: '7pt'}}>EN</Button>

        </Col>
      </Row>
    </Header>
    );

}

export default Head;
