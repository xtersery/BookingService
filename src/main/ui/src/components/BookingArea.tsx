import React from 'react';
import { Col, Typography, Row, Button } from 'antd';
import styled from 'styled-components';
import CitiesEntry from './CitiesEntry';
import { useNavigate } from 'react-router-dom';

const Text = styled.div`
    padding: 0 30%;
    font-family: Bebas;
    font-weight: normal;
    font-size: 2rem;
    letter-spacing: 2px;
`

export default function BookingArea() {
    const navigate = useNavigate();

    return (
        <>
        <Row>
            <Col span={6} style={{ ...centerStyle}}>
                <Text>MY TRIPS</Text>
                <Typography.Text style={{padding: '0 25%'}}>Manage an existing trip</Typography.Text>
            </Col>
            <Col span={6} style={{ ...centerStyle, backgroundColor: '#9b0b0b' }}>
                <Text>BOOK A TRIP</Text>
                <Typography.Text style={{padding: '0 30%'}}>Flight, car</Typography.Text>
            </Col>
            <Col span={6} style={{ ...centerStyle }}>
                <Text>FLIGHT STATUS</Text>
                <Typography.Text style={{padding: '0 24%'}}>Arrivals & departures</Typography.Text>
            </Col>
            <Col span={6} style={{ ...centerStyle }}>
                <Text>CHECK IN</Text>
                <Typography.Text style={{padding: '0 32%'}}>Boarding pass</Typography.Text>
            </Col>
        </Row>
        <Row style={{ minHeight: '70vh'}}>
            <Col span={6} />
            <Col span={6} style={{backgroundColor: '#fff', padding: '1% 2%'}}>
                <Typography.Text style={{fontSize: '1rem', fontWeight: 'semi-bold'}}>Destination Details</Typography.Text>
                <CitiesEntry />
            <br/>
            <Row>
                <Button onClick={() => navigate(-1)} type="primary">Back</Button>
            </Row>
            </Col>
            <Col span={6} />
            <Col span={6} />
        </Row>
        </>
    );
};

const centerStyle = {
    display: 'block',
    justifyContent: 'center',
  };
