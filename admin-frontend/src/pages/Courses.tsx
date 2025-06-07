import React, { useEffect, useState } from 'react';
import AddCourseForm from '../components/AddCourseForm';
import { Link } from 'react-router-dom';
import './Courses.css';

type Course = {
  id?: number;
  code: string;
  title: string;
  description: string;
  credits: number;
};

const Courses: React.FC = () => {
  const [courses, setCourses] = useState<Course[]>([]);
  const [editingCourse, setEditingCourse] = useState<Course | null>(null);

  const loadCourses = async () => {
    try {
      const response = await fetch('http://localhost:8888/courses');
      if (!response.ok) {
        console.error('Erreur HTTP : ', response.status);
        return;
      }
      const data: Course[] = await response.json();
      setCourses(data);
    } catch (error) {
      console.error('Erreur lors du chargement des cours :', error);
    }
  };

  useEffect(() => {
    loadCourses();
  }, []);

  const handleAddCourse = async (newCourse: Course) => {
    try {
      const response = await fetch('http://localhost:8888/courses', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(newCourse),
      });
      if (response.ok) {
        loadCourses();
      } else {
        console.error('Échec du POST (status', response.status, ')');
      }
    } catch (error) {
      console.error("Erreur lors de l'ajout du cours :", error);
    }
  };

  const handleDeleteCourse = async (courseId?: number) => {
    if (courseId === undefined) return;

    try {
      const response = await fetch(`http://localhost:8888/courses/${courseId}`, {
        method: 'DELETE',
      });
      if (response.ok) {
        setCourses(courses.filter((course) => course.id !== courseId));
      } else {
        alert('Échec de la suppression');
      }
    } catch (error) {
      console.error('Erreur lors de la suppression du cours :', error);
    }
  };

  const handleUpdateCourse = async (updatedCourse: Course) => {
    if (updatedCourse.id === undefined) return;

    try {
      const response = await fetch(`http://localhost:8888/courses/${updatedCourse.id}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(updatedCourse),
      });
      if (response.ok) {
        setCourses(courses.map(c => (c.id === updatedCourse.id ? updatedCourse : c)));
        setEditingCourse(null);
      } else {
        alert('Erreur lors de la mise à jour');
      }
    } catch (error) {
      console.error('Erreur lors de la mise à jour :', error);
    }
  };

  return (
    <div className="courses-container">
      <div className="header-bar">
        <h2 className="title">📚 Liste des cours</h2>
        <Link to="/dashboard">
          <button className="dashboard-button">🏠 Dashboard</button>
        </Link>
      </div>

      <AddCourseForm onAdd={handleAddCourse} />

      <ul className="course-list">
        {courses.map((course) => (
          <li key={course.id} className="course-item">
            {editingCourse && editingCourse.id === course.id ? (
              <div className="bg-red-500 p-2">
                <input
                  type="text"
                  value={editingCourse.title}
                  onChange={(e) =>
                    setEditingCourse({ ...editingCourse, title: e.target.value })
                  }
                  placeholder="Titre"
                  className="edit-input"
                />
                <input
                  type="text"
                  value={editingCourse.description}
                  onChange={(e) =>
                    setEditingCourse({ ...editingCourse, description: e.target.value })
                  }
                  placeholder="Description"
                  className="edit-input"
                />
                <div className="edit-buttons">
                  <button onClick={() => handleUpdateCourse(editingCourse)}>
                    💾 Enregistrer
                  </button>
                  <button onClick={() => setEditingCourse(null)}>
                    ❌ Annuler
                  </button>
                </div>
              </div>
            ) : (
              <>
                <div>
                  <strong>{course.title || 'Sans titre'}</strong><br />
                <span className="text-sm text-gray-500">
                    {course.description || 'Pas de description'}
                  </span>
                </div>
                <div>
                  <button
                    onClick={() => setEditingCourse(course)}
                    className="modify-button"
                  >
                    Modifier
                  </button>
                  <button
                    onClick={() => handleDeleteCourse(course.id)}
                    className="delete-button"
                  >
                    Supprimer
                  </button>
                </div>
              </>
            )}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default Courses;
