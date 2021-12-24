import React, { useState, useEffect, useRef } from 'react';

import Button from '../../UI/Button/Button';
import styles from './UrlInput.module.css';
import isURL from 'validator/lib/isURL';

const UrlInput = (props) => {
  const [enteredValue, setEnteredValue] = useState('');
  const [isValid, setIsValid] = useState(true);
  const [err, setErr] = useState('');
  const [showElement, setShowElement] = useState(true);
  const urlRef = useRef('');



  useEffect(() => {
    setTimeout(function () {
      setShowElement(false);
    }, 5000);
  }, []);

  const goalInputChangeHandler = (event) => {
    if (event.target.value.trim().length > 0 ) {
      setIsValid(true);
    }
    setEnteredValue(event.target.value);
  };
  
  

  const formSubmitHandler = (event) => {
    event.preventDefault();
    if (enteredValue.trim().length === 0 || !isURL(enteredValue)) {
      setIsValid(false);
      setErr('Invalid URL');
      return;
    }
    /*props.onAddGoal(enteredValue);*/

    urlRef.current.value = enteredValue;
    const longtoshorturl = {
      url : urlRef.current.value,
    }
    props.onAddGoal(longtoshorturl);
  };
  
  
 

  return (
    <form onSubmit={formSubmitHandler}>
      <div className={`${styles['form-control']} ${!isValid && styles.invalid}`}>
        <input 
        type="text" 
        onChange={goalInputChangeHandler}
        ref={urlRef}
        />
      </div>
      <Button type="submit">Shorten</Button>

       <div>
        {showElement ? (
          <div>
            {err}
          </div>
        ) : (
          <div></div>
        )}{" "}
        </div>
    </form>
  );
};

export default UrlInput;
