import React from 'react';

import UrlItem from '../UrlItem/UrlItem';
import './UrlList.css';

const UrlList = props => {
  return (
    <ul className="goal-list">
      {props.items.map(goal => (
        <UrlItem 
        key={goal.id} 
        id={goal.id} 
        longUrl={goal.longUrl}
        shortUrl = {goal.shortUrl}
        onDelete={props.onDeleteItem}
        >
        </UrlItem>
      ))}
    </ul>
  );
};

export default UrlList;

