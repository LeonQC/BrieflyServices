// import React from 'react';
// import './Button.css';
// import { Link } from 'react-router-dom';

// export function Button() {
//   return (
//     <Link to='sign-up'>
//       <button className='btn'>Sign Up</button>
//     </Link>
//   );
// }

import React from 'react';
import './Button.css';
import { Link } from 'react-router-dom';

const STYLES = ['btn--primary', 'btn--outline', 'btn--test', 'btn--test1', 'btn--test2', 'btn--test3'];

const SIZES = ['btn--medium', 'btn--large', 'btn--small', 'btn--large2'];

export const Button = ({
  children,
  type,
  onClick,
  buttonStyle,
  buttonSize
}) => {
  const checkButtonStyle = STYLES.includes(buttonStyle)
    ? buttonStyle
    : STYLES[0];

  const checkButtonSize = SIZES.includes(buttonSize) ? buttonSize : SIZES[0];

  return (
    <Link to='/sign-up' className='btn-mobile'>
      <button className={`btn ${checkButtonStyle} ${checkButtonSize}`} onClick={onClick}  type={type}>
        {children}
      </button>
    </Link>
  );
};
