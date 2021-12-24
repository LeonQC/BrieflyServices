import React from 'react';
import './Footer.css';
import { Button } from '../UI/Button';
import { Link } from 'react-router-dom';

function Footer() {
  return (
    <div className='footer-container'>
      <section className='footer-subscription'>
        <h1 className='footer-subscription-heading'>
          More than a free link shortener
        </h1>
        <p className='footer-subscription-text'>
          You can get start at any time.
        </p>
        <div className='input-areas'>
          <form>
            <Button className='btns'
          buttonStyle='btn--test3'
          buttonSize='btn--large'>Get Start</Button>
          </form>
        </div>
      </section>
      <div className='footer-links'>
        <div className='footer-link-wrapper'>
          <div className='footer-link-items'>
            <h2>About Us</h2>
            <Link to='/sign-up'>Bitly 101</Link>
            <Link to='/'>Enterprise Class</Link>
            <Link to='/'>Link Management</Link>
            <Link to='/'>Branded Links</Link>
            <Link to='/'>Terms of Service</Link>
          </div>
          <div className='footer-link-items'>
            <h2>Solutions</h2>
            <Link to='/'>Social Media</Link>
            <Link to='/'>Digital Marketing</Link>
            <Link to='/'>Customer Service</Link>
            <Link to='/'>For Developers</Link>
          </div>
        </div>
        <div className='footer-link-wrapper'>
          <div className='footer-link-items'>
            <h2>Resources</h2>
            <Link to='/'>Blog</Link>
            <Link to='/'>Resource Library</Link>
            <Link to='/'>Developers</Link>
            <Link to='/'>Support</Link>
          </div>
          <div className='footer-link-items'>
            <h2>Company</h2>
            <Link to='/'>About Briefly</Link>
            <Link to='/'>Careers</Link>
            <Link to='/'>Partners</Link>
            <Link to='/'>Contact</Link>
          </div>
        </div>
      </div>
      <section className='social-media'>
        <div className='social-media-wrap'>
          <div className='footer-logo'>
            <Link to='/' className='social-logo'>
            Briefly
            </Link>
          </div>
          <small className='website-rights'>Briefly © 2021</small>
          <div className='social-icons'>
            <Link
              className='social-icon-link facebook'
              to='/'
              target='_blank'
              aria-label='Facebook'
            >
              <i className='fab fa-facebook-f' />
            </Link>
            <Link
              className='social-icon-link instagram'
              to='/'
              target='_blank'
              aria-label='Instagram'
            >
              <i className='fab fa-instagram' />
            </Link>
            <Link
              className='social-icon-link youtube'
              to='/'
              target='_blank'
              aria-label='Youtube'
            >
              <i className='fab fa-youtube' />
            </Link>
            <Link
              className='social-icon-link twitter'
              to='/'
              target='_blank'
              aria-label='Twitter'
            >
              <i className='fab fa-twitter' />
            </Link>
            <Link
              className='social-icon-link twitter'
              to='/'
              target='_blank'
              aria-label='LinkedIn'
            >
              <i className='fab fa-linkedin' />
            </Link>
          </div>
        </div>
      </section>
    </div>
  );
}

export default Footer;
