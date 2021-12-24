import React from 'react';
import '../../../App.css';
import { Button } from '../UI/Button';
import './Body.css';

function Body() {
  return (
    <div className='hero-container'>
      <video src='/videos/video-1.mp4' autoPlay loop muted />
      <h1>Short links, Big results</h1>
      <p>What are you waiting for?</p>
      <ul className='hero-btns'>
        <li>
        <Button
          className='btns'
          buttonStyle='btn--primary'
          buttonSize='btn--large'
        >
          GET STARTED
        </Button>
        </li>
        <li>
        <Button
          className='btns'
          buttonStyle='btn--test1'
          buttonSize='btn--small'
          onClick={console.log('hey')}
        >
          Get a Quotes
        </Button>
        </li>
      </ul>
    </div>
  );
}

export default Body;
