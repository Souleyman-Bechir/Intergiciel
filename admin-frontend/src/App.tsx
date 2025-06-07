// src/App.jsx
import React from 'react';
import { BrowserRouter, Routes, Route, Link } from 'react-router-dom';
import Courses from './pages/Courses';
import Dashboard from './pages/Dashboard';
import Students from './pages/Students';
import Enrollments from './pages/Enrollments'; // <-- Import du composant
import './App.css';

function App() {
  return (
    <BrowserRouter>
      <nav className="bg-gray-100 p-4 flex gap-4 shadow">
        <Link to="/" className="text-blue-600 hover:underline">Accueil</Link>
        <Link to="/courses" className="text-blue-600 hover:underline">Cours</Link>
        <Link to="/students" className="text-blue-600 hover:underline">Étudiants</Link>
        <Link to="/enrollments" className="text-blue-600 hover:underline">Inscriptions</Link>
        <Link to="/dashboard" className="text-blue-600 hover:underline">Dashboard</Link>
      </nav>

      <div className="p-6">
        <Routes>
          <Route path="/" element={<div>Bienvenue dans le système d'inscription 📚</div>} />
          <Route path="/courses" element={<Courses />} />
          <Route path="/students" element={<Students />} />
          <Route path="/enrollments" element={<Enrollments />} />
          <Route path="/dashboard" element={<Dashboard />} />
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;
