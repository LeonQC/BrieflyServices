import React from 'react';

import './UrlItem.css';

const UrlItem = (props) => {
  const deleteHandler = () => {
    props.onDelete(props.id);
  };
  /*
  const makeid = (length) => {
    var result           = '';
    var characters       = props.id.substring(1, props.id.length);
    var charactersLength = characters.length;
    for ( var i = 0; i < length; i++ ) {
      result += characters.charAt(Math.floor(Math.random() * 
 charactersLength));
   }
   return result;
}

console.log(makeid(5));

*/
  return (
    <li className="goal-item">   
      <ul className="test">
          <li className="test1">
            
            <p>{props.longUrl.length < 50 ? props.longUrl : props.longUrl.substring(0, 50) + "..." }</p>
            
          </li>

          <li className="test2">
            {"https://briefly/" + props.shortUrl}
          </li>

          <li className="test2"> 
            <button onClick={deleteHandler}>copy</button>
          </li>
        </ul>
    </li>
  );
};

export default UrlItem;


