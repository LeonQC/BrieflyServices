import React, { useState, useEffect } from 'react';
import { Button } from '../UI/Button';
import { Link } from 'react-router-dom';
import './Header.css';

function Header() {
  const [click, setClick] = useState(false);
  const [button, setButton] = useState(true);

  const handleClick = () => setClick(!click);
  const closeMobileMenu = () => setClick(false);

  const showButton = () => {
    if (window.innerWidth <= 960) {
      setButton(false);
    } else {
      setButton(true);
    }
  };

  useEffect(() => {
    showButton();
  }, []);

  window.addEventListener('resize', showButton);

  return (
    <>
      <nav className='navbar'>
        <div className='navbar-container'>
          <Link to='/' className='navbar-logo' onClick={closeMobileMenu}> Briefly</Link>
          <div className='menu-icon' onClick={handleClick}>
            <i className={click ? 'fas fa-times' : 'fas fa-bars'} />
          </div>

          <ul className={click ? 'nav-menu active' : 'nav-menu'}>
            <li className='nav-item'>
              <Link to='/' className='nav-links' onClick={closeMobileMenu}> Home </Link>      
            </li>
            <li className='nav-item'>
              <Link to='/services' className='nav-links' onClick={closeMobileMenu}> Solutions </Link>
            </li>
            <li className='nav-item'>
              <Link to='/products'className='nav-links' onClick={closeMobileMenu}> Products </Link>
            </li>   

            <li className='nav-item'>
              <Link to='/' className='nav-links' onClick={closeMobileMenu}> Test</Link>
            </li>
          
            <li >
              <Link to='/sign-up' className='nav-links-mobile' onClick={closeMobileMenu}>Sign In Free</Link>
            </li>
            <li>
              <Link to='/sign-up' className='nav-links-mobile' onClick={closeMobileMenu}>Sign Up</Link>
            </li>

            <li>
              <Link to='/sign-up' className='nav-links-mobile' onClick={closeMobileMenu}>Get a Quotes</Link>
            </li>
          </ul>
          {button && <Button buttonStyle='btn--outline' >Log In</Button>}
          {button && <Button buttonStyle='btn--outline'>Sign Up Free</Button>}
          {button && <Button buttonStyle='btn--primary'>Get a Quotes</Button>}
        </div>
      </nav>
    </>
  );
}

export default Header;
