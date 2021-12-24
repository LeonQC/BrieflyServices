import React from 'react';
import Header from './components/Pages/Content/Header';
import './App.css';
import Home from './components/Pages/Menu/Home';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import Services from './components/Pages/Menu/Services';
import Products from './components/Pages/Menu/Products';
import SignUp from './components/Pages/Menu/SignUp';

function App() {
  return (
    <>
      <Router>
        <Header/>
        <Switch>
          <Route path='/' exact component={Home} />
          <Route path='/services' component={Services} />
          <Route path='/products' component={Products} />
          <Route path='/sign-up' component={SignUp} />
        </Switch>
      </Router>
    </>
  );
}

export default App;
