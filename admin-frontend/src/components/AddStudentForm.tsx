// src/components/AddStudentForm.tsx
import React, { useState, ChangeEvent, FormEvent } from 'react';

type Student = {
  id?: number;
  nom: string;
  prenom: string;
  email: string;
};

type Props = {
  onAdd: (student: Student) => void;
};

const AddStudentForm: React.FC<Props> = ({ onAdd }) => {
  const [form, setForm] = useState<Student>({ nom: '', prenom: '', email: '' });

  const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e: FormEvent) => {
    e.preventDefault();
    onAdd(form);
    setForm({ nom: '', prenom: '', email: '' });
  };

  return (
    <form onSubmit={handleSubmit} className="mb-4">
      <h3>Ajouter un étudiant</h3>
      <input name="nom" value={form.nom} onChange={handleChange} placeholder="Nom" required />
      <input name="prenom" value={form.prenom} onChange={handleChange} placeholder="Prénom" required />
      <input name="email" value={form.email} onChange={handleChange} placeholder="Email" required />
      <button type="submit">Ajouter</button>
    </form>
  );
};

export default AddStudentForm;
