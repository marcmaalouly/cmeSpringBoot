import React from 'react';
import Home from './Home';
import './App.css';
import Nav from './Nav';
import {BrowserRouter as Router , Switch , Route} from 'react-router-dom';
import UserPage from './UserPage';

function App() {
 
  return (
    <Router>
      <div className="App">
        <Nav />
        <Switch>
          <Route path="/" exact component={Home} />
          <Route path="/userpage" component={UserPage} />
        </Switch>
      </div>
    </Router>
  );
}

export default App;
