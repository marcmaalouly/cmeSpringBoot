import React from 'react';
import './Nav.css';
import {Link} from 'react-router-dom';

function Nav(){
    return(
        <div className="nav-content">
            <div>
                <Link to="/">
                    <p>Page 1</p>
                </Link>
            </div>
            <p> / </p>
            <div>
                <Link to="/userpage">
                    <p>Page 2</p>
                </Link>
            </div>
        </div>
    );
}

export default Nav;