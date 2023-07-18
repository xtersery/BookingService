import React, {useState} from 'react';
import styled from 'styled-components';
import { UserOutlined, MailOutlined } from '@ant-design/icons';
import { useNavigate, Routes, Route } from 'react-router-dom';
import FirstPage from './FirstPage';
import BookingArea from './BookingArea';
import { Layout, Typography, Input, Button, Row, Col } from 'antd';
const { Header, Content, Footer } = Layout;

const Form = styled.div`
    justify-content: center;
    min-height: 60vh;
    max-width: 70%;
`

export default function Authorize() {
    const [email, setEmail] = useState<string>("");
    const [username, setUsername] = useState<string>("");
    const navigate = useNavigate();

    const sendRequest = async () => {
        const user = {
            "email": email,
            "username": username,
        };
        await fetch('http://localhost:8080/hello', {
            method: 'POST',
            body: JSON.stringify(user),
            headers: {
                'Detail': 'user',
                'Content-type': 'application/json; charset=UTF-8',
            },
        })
        .catch((err) => console.log(err.message));

    }

    return (
        <Layout>
            <Header style={{minHeight: '20vh'}}/>
            <Content style={{alignContent: 'center'}}>

                    <Row style={{justifyContent: 'center'}}>
                        <Col span={7}>
                        <Typography.Title level={2}>Authorize</Typography.Title>
                        <Input size="large" placeholder="Email address" prefix={<MailOutlined />} value={email} onChange={(e) => setEmail(e.target.value)} />
                        <br />
                        <br />
                        <Input size="large" placeholder="Username" prefix={<UserOutlined />} value={username} onChange={(e) => setUsername(e.target.value)}/>

                        <br />
                        <br />
                        <br />

                        <Row>
                            <Col span={6}>
                            <Button onClick={sendRequest} type="primary">Confirm</Button>
                            </Col>
                            <Col span={3}>
                            <Button onClick={() => navigate(-1)} type="primary">Back</Button>
                            </Col>
                        </Row>
                        </Col>
                    </Row>
            </Content>
            <Footer />
        </Layout>
    );
}
