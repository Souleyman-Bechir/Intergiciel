import React, { useEffect, useState } from "react";

const Dashboard: React.FC = () => {
  const [courseCount, setCourseCount] = useState<number | null>(null);
  const [studentCount, setStudentCount] = useState<number | null>(null);
  const [enrollmentCount, setEnrollmentCount] = useState<number | null>(null);

  useEffect(() => {
    // Récupérer le nombre de cours
    fetch("http://localhost:8888/courses")
      .then((res) => res.json())
      .then((data) => setCourseCount(data.length))
      .catch((err) => console.error("Erreur lors du chargement des cours :", err));

    // Récupérer le nombre d'étudiants
    fetch("http://localhost:8888/students/all")
      .then((res) => res.json())
      .then((data) => setStudentCount(data.length))
      .catch((err) => console.error("Erreur lors du chargement des étudiants :", err));

    // Récupérer le nombre d'inscriptions
    fetch("http://localhost:8888/api/enrollments")
      .then((res) => res.json())
      .then((data) => setEnrollmentCount(data.length))
      .catch((err) => console.error("Erreur lors du chargement des inscriptions :", err));
  }, []);

  return (
    <div className="p-6">
      <h1 className="text-3xl font-bold mb-4">📊 Dashboard Administrateur</h1>
      <p className="mb-6">Voici les statistiques globales de l'application :</p>

      <div className="grid grid-cols-1 sm:grid-cols-3 gap-4">
        <div className="bg-blue-100 p-4 rounded shadow">
          <h2 className="text-xl font-semibold">📘 Cours</h2>
          <p className="text-2xl">{courseCount !== null ? courseCount : "..."}</p>
        </div>
        <div className="bg-green-100 p-4 rounded shadow">
          <h2 className="text-xl font-semibold">🧑‍🎓 Étudiants</h2>
          <p className="text-2xl">{studentCount !== null ? studentCount : "..."}</p>
        </div>
        <div className="bg-yellow-100 p-4 rounded shadow">
          <h2 className="text-xl font-semibold">📝 Inscriptions</h2>
          <p className="text-2xl">{enrollmentCount !== null ? enrollmentCount : "..."}</p>
        </div>
      </div>
    </div>
  );
};

export default Dashboard;
