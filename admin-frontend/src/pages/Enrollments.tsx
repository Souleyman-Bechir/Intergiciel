import React, { useEffect, useState } from "react";

const Enrollments: React.FC = () => {
  const [studentId, setStudentId] = useState("");
  const [courseId, setCourseId] = useState("");
  const [courseIds, setCourseIds] = useState("");
  const [semester, setSemester] = useState("");
  const [message, setMessage] = useState("");
  const [enrollments, setEnrollments] = useState<any[]>([]);
  const [editingId, setEditingId] = useState<number | null>(null);

  const loadEnrollments = () => {
    fetch("http://localhost:8888/api/enrollments")
      .then((res) => res.json())
      .then((data) => setEnrollments(data))
      .catch((err) => console.error("Erreur lors du chargement des inscriptions:", err));
  };

  useEffect(() => {
    loadEnrollments();
  }, []);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    const url = courseIds ? "multi" : "";
    const payload = courseIds
      ? {
          studentId: Number(studentId),
          courseIds: courseIds.split(",").map((id) => Number(id.trim())),
          semester: Number(semester),
        }
      : {
          studentId: Number(studentId),
          courseId: Number(courseId),
          semester: Number(semester),
        };

    try {
      const response = await fetch(
        editingId
          ? `http://localhost:8888/api/enrollments/${editingId}`
          : `http://localhost:8888/api/enrollments${url ? "/" + url : ""}`,
        {
          method: editingId ? "PUT" : "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(payload),
        }
      );

      const isJson = response.headers.get("content-type")?.includes("application/json");
      const data = isJson ? await response.json() : await response.text();

      if (!response.ok) {
        let errorMessage = "Erreur inconnue";

        if (isJson && data.message) errorMessage = data.message;
        else if (isJson && data.erreurs) errorMessage = data.erreurs.join("\n");
        else if (typeof data === "string") errorMessage = data;

        throw new Error(errorMessage);
      }

      if (url === "multi" && data.erreurs && data.erreurs.length > 0) {
        setMessage(
          `⚠️ Certaines inscriptions ont échoué :\n${data.erreurs.join("\n")}\n✅ Réussies : ${data.inscriptionsRéussies.join(", ")}`
        );
      } else {
        setMessage(editingId ? "✏️ Inscription modifiée !" : "✅ Inscription réussie !");
      }

      setStudentId("");
      setCourseId("");
      setCourseIds("");
      setSemester("");
      setEditingId(null);
      loadEnrollments();
    } catch (error: any) {
      setMessage(`❌ Erreur : ${error.message}`);
    }
  };

  const handleDelete = async (id: number) => {
    if (!window.confirm("Voulez-vous vraiment supprimer cette inscription ?")) return;

    try {
      await fetch(`http://localhost:8888/api/enrollments/${id}`, { method: "DELETE" });
      setMessage("🗑️ Inscription supprimée");
      loadEnrollments();
    } catch (err) {
      setMessage("❌ Erreur lors de la suppression");
    }
  };

  const handleEdit = (enrollment: any) => {
    setStudentId(enrollment.studentId);
    setCourseId(enrollment.courseId || "");
    setSemester(enrollment.semester);
    setEditingId(enrollment.id);
  };

  return (
    <div className="p-6 max-w-4xl mx-auto">
      <h1 className="text-3xl font-bold mb-6">📝 Gestion des Inscriptions</h1>

      <form onSubmit={handleSubmit} className="bg-white p-6 rounded-xl shadow mb-8">
        <h2 className="text-xl font-semibold mb-4">
          {editingId ? "Modifier une inscription" : "Nouvelle inscription"}
        </h2>

        <div className="mb-4">
          <label htmlFor="studentId" className="block mb-1 font-medium">ID Étudiant</label>
          <input
            id="studentId"
            type="number"
            title="Entrez l'identifiant de l'étudiant"
            placeholder="ex: 1"
            value={studentId}
            onChange={(e) => setStudentId(e.target.value)}
            className="w-full p-2 border rounded"
            required
          />
        </div>

        <div className="mb-4">
          <label htmlFor="courseId" className="block mb-1 font-medium">ID du Cours</label>
          <input
            id="courseId"
            type="number"
            title="Entrez l'identifiant du cours"
            placeholder="Laisser vide si multiple"
            value={courseId}
            onChange={(e) => setCourseId(e.target.value)}
            className="w-full p-2 border rounded"
          />
        </div>

        <div className="mb-4">
          <label htmlFor="courseIds" className="block mb-1 font-medium">IDs de Cours (séparés par des virgules)</label>
          <input
            id="courseIds"
            type="text"
            title="Entrez les identifiants des cours séparés par des virgules"
            placeholder="Ex: 1,2,3 (laisser vide si simple)"
            value={courseIds}
            onChange={(e) => setCourseIds(e.target.value)}
            className="w-full p-2 border rounded"
          />
        </div>

        <div className="mb-4">
          <label htmlFor="semester" className="block mb-1 font-medium">Semestre</label>
          <input
            id="semester"
            type="number"
            title="Entrez le numéro du semestre"
            placeholder="ex: 1"
            value={semester}
            onChange={(e) => setSemester(e.target.value)}
            className="w-full p-2 border rounded"
            required
          />
        </div>

        <button type="submit" className="bg-blue-600 hover:bg-blue-700 text-white px-4 py-2 rounded">
          {editingId ? "Mettre à jour" : "Soumettre"}
        </button>

        {message && <p className="mt-4 text-sm text-gray-700 whitespace-pre-line">{message}</p>}
      </form>

      <div className="bg-white p-6 rounded-xl shadow">
        <h2 className="text-xl font-semibold mb-4">📋 Liste des Inscriptions</h2>
        {enrollments.length === 0 ? (
          <p className="text-gray-500">Aucune inscription pour le moment.</p>
        ) : (
          <ul className="divide-y">
            {enrollments.map((enr) => (
              <li key={enr.id} className="py-2 flex justify-between items-center">
                <span>
                  Étudiant #{enr.studentId} inscrit au cours #{enr.courseId} pour le semestre {enr.semester}
                </span>
                <div className="flex space-x-2">
                  <button
                    onClick={() => handleEdit(enr)}
                    className="text-yellow-600 hover:underline"
                  >
                    ✏️ Modifier
                  </button>
                  <button
                    onClick={() => handleDelete(enr.id)}
                    className="text-red-600 hover:underline"
                  >
                    🗑️ Supprimer
                  </button>
                </div>
              </li>
            ))}
          </ul>
        )}
      </div>
    </div>
  );
};

export default Enrollments;
