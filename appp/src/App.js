import React from 'react';
import './App.css';
import Home from './Home';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import FieldList from './FieldList';
import FieldEdit from './FieldEdit';


const App = () => {
    return (
        <Router>
            <Routes>
                <Route exact path="/" element={<Home/>}/>
                <Route path='/fields' exact={true} element={<FieldList/>}/>
                <Route path='/fields/:id' element={<FieldEdit/>}/>

            </Routes>
        </Router>
    )
}

export default App;