import React, { useEffect, useState } from 'react';

interface Student {
  id?: number;
  nom: string;
  prenom: string;
  email: string;
}

const Students: React.FC = () => {
  const [students, setStudents] = useState<Student[]>([]);
  const [form, setForm] = useState({ nom: '', prenom: '', email: '' });
  const [editingId, setEditingId] = useState<number | null>(null);

  const loadStudents = async () => {
    try {
      const response = await fetch('http://localhost:8888/students/all');
      if (!response.ok) throw new Error('Erreur chargement des étudiants');
      const data = await response.json();
      setStudents(data);
    } catch (error) {
      console.error(error);
    }
  };

  useEffect(() => {
    loadStudents();
  }, []);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      const url = editingId
        ? `http://localhost:8888/students/${editingId}`
        : 'http://localhost:8888/students/create';
      const method = editingId ? 'PUT' : 'POST';

      const response = await fetch(url, {
        method,
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(form),
      });

      if (response.ok) {
        setForm({ nom: '', prenom: '', email: '' });
        setEditingId(null);
        loadStudents();
      } else {
        const msg = await response.text();
        alert(msg);
      }
    } catch (error) {
      console.error(error);
    }
  };

  const handleDelete = async (id?: number) => {
    if (!id) return;
    try {
      const res = await fetch(`http://localhost:8888/students/${id}`, {
        method: 'DELETE',
      });
      if (res.ok) {
        setStudents(students.filter((s) => s.id !== id));
      }
    } catch (err) {
      console.error(err);
    }
  };

  const handleEdit = (student: Student) => {
    setForm({ nom: student.nom, prenom: student.prenom, email: student.email });
    setEditingId(student.id ?? null);
  };

  return (
    <div className="p-6 max-w-4xl mx-auto">
      <h2 className="text-3xl font-bold mb-6 text-center">Gestion des étudiants</h2>

      <form onSubmit={handleSubmit} className="grid grid-cols-1 sm:grid-cols-3 gap-4 mb-8">
        <input
          name="nom"
          value={form.nom}
          onChange={handleChange}
          placeholder="Nom"
          required
          className="border border-gray-300 rounded px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500"
        />
        <input
          name="prenom"
          value={form.prenom}
          onChange={handleChange}
          placeholder="Prénom"
          required
          className="border border-gray-300 rounded px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500"
        />
        <input
          name="email"
          value={form.email}
          onChange={handleChange}
          placeholder="Email"
          required
          className="border border-gray-300 rounded px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500"
        />
        <button
          type="submit"
          className="sm:col-span-3 bg-blue-600 hover:bg-blue-700 text-white py-2 rounded transition"
        >
          {editingId ? 'Mettre à jour' : 'Ajouter'}
        </button>
      </form>

      <div className="grid gap-4">
        {students.map((student) => (
          <div key={student.id} className="bg-white shadow-md rounded-lg p-4 flex flex-col sm:flex-row justify-between items-start sm:items-center">
            <div>
              <p className="font-semibold text-lg">{student.nom} {student.prenom}</p>
              <p className="text-gray-600">{student.email}</p>
            </div>
            <div className="mt-4 sm:mt-0 flex gap-2">
              <button
                onClick={() => handleEdit(student)}
                className="border border-blue-500 text-blue-500 hover:bg-blue-100 px-4 py-1 rounded transition"
              >
                Modifier
              </button>
              <button
                onClick={() => handleDelete(student.id)}
                className="border border-red-500 text-red-500 hover:bg-red-100 px-4 py-1 rounded transition"
              >
                Supprimer
              </button>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Students;
