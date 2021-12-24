import React, {useEffect, useState} from 'react';

import UrlList from '../../ShowList/URL/UrlList/UrlList';
import UrlInput from '../../ShowList/URL/UrlInput/UrlInput';
import './OutputForm.css';
import axios from 'axios';
import { render } from '@testing-library/react';

const OutputForm = () => {
  
  const [courseGoals, setCourseGoals] = useState([]);
  /*
  , useCallback, useEffect 
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState(null);
  
  const fetchMoviesHandler = useCallback(async () => {
    
    setIsLoading(true);
    setError(null);
    

    
    try {
      const response = await fetch('http://localhost:8080/api/v1/url/getAll');
      if (!response.ok) {
        throw new Error('Something went wrong!');
      }

      const data = await response.json();
      const loadedurl = [];
  
      for (const key in data) {
        loadedurl.push({
          id: key,
          longUrl: data[key].longUrl,
          shortUrl: data[key].shortUrl,
        });
      }

      setCourseGoals(loadedurl);
      console.log(data)
    } catch (error) {
      setError(error.message);
    }
    setIsLoading(false);
  }, []);

  useEffect(() => {
    fetchMoviesHandler();
  }, [fetchMoviesHandler]);

*/

  async function addGoalHandler(courseGoals){
    const response = await fetch('http://localhost:8080/url/transform/', {
      method: 'POST',
      body: JSON.stringify(courseGoals),
      headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json'
      }
    })
    const data = await response.json();
    console.log(data);
    axios.get("http://localhost:8080/api/v1/url/getAll")
    .then(response => {
      setCourseGoals(response.data);
    })
  };


  useEffect(() => {
    axios.get("http://localhost:8080/api/v1/url/getAll")
    .then(response => {
      setCourseGoals(response.data);
    })
  },[setCourseGoals])
    /*
    
    const response = await fetch('https://urltest-417d3-default-rtdb.firebaseio.com/tinyurl.json', {

    setCourseGoals((data) => {
      const updatedGoals = [...data];
      updatedGoals.unshift({ text: courseGoals, id: Math.random().toString() });
      return updatedGoals;
    });
    */
 
  
  const deleteItemHandler = (goalId) => {
    axios.delete(`http://localhost:8080/api/v1/url/delete/${goalId}`)
    .then(res => {  
      console.log(res);  
      console.log(res.data);  
    });
        setCourseGoals((prevGoals) => {
        const updatedGoals = prevGoals.filter((goal) => goal.id !== goalId);
        return updatedGoals;
        });
  };

  let content = (
    <p style={{ textAlign: 'center' }}></p>
  );

  if (courseGoals != null) {
    content = (
      <UrlList items={courseGoals}  onDeleteItem={deleteItemHandler} />
    );
  }

  /*
  if (error) {
    return (
      <section> 
      <p>{error}</p>
      </section>
    )
  }

  if (isLoading) {
    return (
    <section> 
    <p>Loading...</p>
    </section>
    )
  }
*/


render() 
  return (
    <React.Fragment>
      <section id="goal-form">
        <UrlInput onAddGoal={addGoalHandler} />
      </section>
      <section>
      </section>
      <section id="goals">{content}</section>
    </React.Fragment>
  );
};

export default OutputForm;