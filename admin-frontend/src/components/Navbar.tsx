import React from 'react';
import { Link } from 'react-router-dom';

const Navbar = () => (
  <nav style={{ background: '#eee', padding: '1rem' }}>
    <Link to="/courses" style={{ marginRight: '1rem' }}>Cours</Link>
  </nav>
);

export default Navbar;
