import React, { useState, ChangeEvent, FormEvent } from 'react';

type Course = {
  code: string;
  title: string;
  description: string;
  credits: number;
};

type Props = {
  onAdd: (course: Course) => void;
};

const AddCourseForm: React.FC<Props> = ({ onAdd }) => {
  const [form, setForm] = useState({
    code: '',
    title: '',
    description: '',
    credits: ''
  });

  const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setForm({ ...form, [name]: value });
  };

  const handleSubmit = (e: FormEvent) => {
    e.preventDefault();

    // Validation rapide : tous les champs requis
    if (!form.code || !form.title || !form.description || !form.credits) {
      alert("Tous les champs sont requis");
      return;
    }

    const courseToSend: Course = {
      code: form.code.trim(),
      title: form.title.trim(),
      description: form.description.trim(),
      credits: parseInt(form.credits, 10)
    };

    if (isNaN(courseToSend.credits)) {
      alert("Les crédits doivent être un nombre");
      return;
    }

    onAdd(courseToSend);
    setForm({ code: '', title: '', description: '', credits: '' });
  };

  return (
    <form onSubmit={handleSubmit} className="mb-4">
      <h3>➕ Ajouter un nouveau cours</h3>
      <input
        name="code"
        placeholder="Code"
        value={form.code}
        onChange={handleChange}
        required
      />
      <input
        name="title"
        placeholder="Titre"
        value={form.title}
        onChange={handleChange}
        required
      />
      <input
        name="description"
        placeholder="Description"
        value={form.description}
        onChange={handleChange}
        required
      />
      <input
        name="credits"
        placeholder="Crédits"
        type="number"
        value={form.credits}
        onChange={handleChange}
        required
      />
      <button type="submit">✅ Ajouter</button>
    </form>
  );
};

export default AddCourseForm;
