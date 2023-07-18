import React, {useState} from 'react';
import { Row, Menu, Col, MenuProps } from 'antd';
import { useNavigate } from 'react-router-dom';

export default function FirstPage() {
    const [current, setCurrent] = useState('');

    const navigate = useNavigate();

    const onClick: MenuProps['onClick'] = (e) => {
      setCurrent(e.key);
      navigate("book");
    }

    return (
        <>
            <Row style={{marginTop: '60vh'}}>
            {Array.of('01','02','03','04').map((item) => <Col span={5} offset={1}
            style={{fontFamily: 'Bebas', fontSize: '4rem', fontStyle: 'normal', color: '#a61513'}}>{item}</Col>)}
            </Row>
            <Menu onClick={onClick} selectedKeys={[current]} mode='horizontal'
                style={{
                    ...centerStyle,
                    minHeight: '20vh',
                    background: 'transparent',
                    }}>
                    {items.map((item, i) => <Menu.Item key={item.key}
                    style={{
                        ...centerStyle,
                        fontFamily: 'Bebas',
                        fontWeight: 'normal',
                        fontSize: '2rem',
                        width: '25%',
                        letterSpacing: '2px'
                    }}>{`${item.label}`}</Menu.Item>)}
            </Menu>
        </>
    );
}

const centerStyle = {
    display: 'flex',
    justifyContent: 'center',
  };


  const items = [
    {
      label: 'MY TRIPS',
      key: 'manage trips'
    },
    {
      label: 'BOOK A TRIP',
      key: 'book'
    },
    {
      label: 'FLIGHT STATUS',
      key: 'arrivals-departures'
    },
    {
      label: 'CHECK IN',
      key: 'boarding pass-baggage'
    }
  ];
