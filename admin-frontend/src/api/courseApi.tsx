// src/api/courseApi.js
export const fetchCourses = async () => {
  const response = await fetch('http://localhost:8888/courses');
  if (!response.ok) {
    throw new Error('Erreur lors de la récupération des cours');
  }
  return await response.json();
};


