import React, { useState } from 'react';
import { Row, Col, Typography, Space, DatePicker } from 'antd';
import {  Select } from 'antd';
import type { DatePickerProps } from 'antd';
import type { SelectProps } from 'antd';


const cities: Array<string> = ['Москва', 'Санкт-Петербург', 'Новосибирск', 'Екатеринбург', 'Казань',
'Нижний Новгород', 'Челябинск', 'Омск', 'Краснодар', 'Ростов-на-Дону', 'Уфа', 'Красноярск', 'Пермь', 'Воронеж', 'Волгоград', 'Самара'];

const options: SelectProps['options'] = [];

for (let i = 0; i < cities.length; i++) {
    options.push({
        value: cities.at(i),
        label: cities.at(i)
    });
}


const BookingArea: React.FC = () => {

    const [departure, setDeparture] = useState({});
    const [passenger, setPassenger] = useState({});
    const [passClass, setPassClass] = useState({});
    let destination = {};

    const pickDeparture = (value: string) => {
        setDeparture({ value });
    }

    const onChange: DatePickerProps['onChange'] = (date, dateString) => {
        console.log(date, dateString);
    }

    const pickPassenger = (value: string) => {
        setPassenger(value);
        console.log(`Picked ${value}`);
    }
    const pickClass = (value: string) => {
        setPassClass(value);
        console.log(`Picked ${value} class`);
    }

    const pickDestination = (value: string) => {
        destination = { value };
        console.log(`Changing destination to ${value}`);
        let json = {
            "departure": {...departure},
            "destination": {...destination}
        };
        sendRequest(json);
    }

    const sendRequest = async (data) => {
        // let req = new XMLHttpRequest();
        // req.open("POST", "http://localhost:8080/hello", true);
        // req.setRequestHeader('Content-type', 'application/json');
        // console.log(json);
        // req.send(JSON.stringify(json));

        await fetch('http://localhost:8080/hello', {
            method: 'POST',
            body: JSON.stringify(data),
            headers: {
                'Detail': 'requisites',
                'Content-type': 'application/json; charset=UTF-8',
            },
        })
        .catch((err) => console.log(err.message));
    }

    return (
        <>
            <Row justify="start" style={{width: '80%'}}>

                <Typography.Text>Destination details</Typography.Text>
                <Select
                    onSelect={pickDeparture}
                    options={options}
                    size="middle"
                    style={{ width: '90%' }}
                    placeholder={'Город вылета'}
                    />


            </Row>
            <Row style={{minHeight: '5pt'}}/>
            <Row style={{width: '80%'}}>


                <Select
                    onSelect={pickDestination}
                    options={options}
                    size="middle"
                    style={{ width: '90%' }}
                    placeholder={'Город прилета'}
                    />

            </Row>

            <Row>
                <Space direction="vertical">
                    <Typography.Text>
                        Travel Details
                    </Typography.Text>
                    <DatePicker onChange={onChange} placeholder="Departure date"/>
                    <DatePicker onChange={onChange} placeholder="Arrival date"/>
                </Space>
                <Space direction="vertical">
                    <Typography.Text>
                        Passenger Details
                    </Typography.Text>
                    <Select
                        defaultValue="lucy"
                        style={{ width: 120 }}
                        onChange={pickPassenger}
                        options={[
                            { value: 'jack', label: 'Jack' },
                            { value: 'lucy', label: 'Lucy' },
                            { value: 'Yiminghe', label: 'yiminghe' }
                        ]}
                    />
                    <Select
                        defaultValue="lucy"
                        style={{ width: 120 }}
                        onChange={pickClass}
                        options={[
                            { value: 'Econom', label: 'econom' },
                            { value: 'Business', label: 'business' },
                            { value: 'Comfort', label: 'comfort' }
                        ]}
                    />
                </Space>
            </Row>
        </>
    );
};

export default BookingArea;
