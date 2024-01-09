import React, { useEffect, useState } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';
import { useCookies } from 'react-cookie';

const FieldList = () => {

    const [fields, setFields] = useState([]);
    const [loading, setLoading] = useState(false);
    const [cookies] = useCookies(['XSRF-TOKEN']);


    useEffect(() => {
        setLoading(true);

        fetch('api/fields')
            .then(response => response.json())
            .then(data => {
                setFields(data);
                setLoading(false);
            })
    }, []);

    const remove = async (id) => {
        await fetch(`/api/field/${id}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            let updatedFields = [...fields].filter(i => i.id !== id);
            setFields(updatedFields);
        });
    }

    if (loading) {
        return <p>Loading...</p>;
    }

    const fieldList = fields.map(field => {
        const address = `${field.address || ''} ${field.city || ''} ${field.stateOrProvince || ''}`;
        return <tr key={field.id}>
            <td style={{whiteSpace: 'nowrap'}}>{field.name}</td>
            <td>{address}</td>
            <td>{field.events.map(event => {
                return <div key={event.id}>{new Intl.DateTimeFormat('en-US', {
                    year: 'numeric',
                    month: 'long',
                    day: '2-digit'
                }).format(new Date(event.date))}: {event.title}</div>
            })}</td>
            <td>
                <ButtonGroup>
                    <Button size="sm" color="primary" tag={Link} to={"/fields/" + field.id}>Edit</Button>
                    <Button size="sm" color="danger" onClick={() => remove(field.id)}>Delete</Button>
                </ButtonGroup>
            </td>
        </tr>
    });

    return (
        <div>
            <AppNavbar/>
            <Container fluid>
                <div className="float-end">
                    <Button color="success" tag={Link} to="/fields/new">Add Field</Button>
                </div>
                <h3>ENSA Marrakech Fields</h3>
                <Table className="mt-4">
                    <thead>
                    <tr>
                        <th width="20%">Name</th>
                        <th width="20%">Location</th>
                        <th>Events</th>
                        <th width="10%">Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    {fieldList}
                    </tbody>
                </Table>
            </Container>
        </div>
    );
};

export default FieldList;