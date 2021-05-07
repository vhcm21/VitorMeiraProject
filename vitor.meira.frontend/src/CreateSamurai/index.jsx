import React, { useState } from "react";
import Input from './../Input'
import { Form, Submit } from "./styled-components";

const CreateSamurai = () => {

  const [name, setName] = useState("");
  const [birthYear, setBirthYear] = useState("");
  const [deathYear, setDeathYear] = useState("");
  const [gender, setGender] = useState("");
  const [clanId, setClanId] = useState("");
  const [provinceId, setProvinceId] = useState("")

  const handleOnFormSubmit = (event) => {
    event.preventDefault();

    if (name && birthYear && deathYear && gender && clanId && provinceId) {

      fetch("http://localhost:8080/samurais", {
        method: "POST",
        headers: {
          "Content-type": "application/json",
        },
        body: JSON.stringify({
          name,
          birthYear,
          deathYear,
          gender,
          clanId,
          provinceId,
        }),
      })
        .then((response) => response.json())
        .then(console.log("YEAH!"))
    }
  };

  return (
    <div>
      <Form onSubmit={handleOnFormSubmit}>
        <Input
          label="Name"
          name="name"
          type="text"
          value={name}
          setValue={(value) => setName(value)}
        />
        <Input
          label="Birth Year"
          name="birth year"
          type="number"
          value={birthYear}
          setValue={(value) => setBirthYear(value)}
        />
        <Input
          label="Death Year"
          name="death year"
          type="number"
          value={deathYear}
          setValue={(value) => setDeathYear(value)}
        />
        <Input
          label="Gender"
          name="gender"
          type="text"
          value={gender}
          setValue={(value) => setGender(value)}
        />
        <Input
          label="Clan Id"
          name="clan id"
          type="number"
          value={clanId}
          setValue={(value) => setClanId(value)}
        />
        <Input
          label="Province Id"
          name="province id"
          type="number"
          value={provinceId}
          setValue={(value) => setProvinceId(value)}
        />

        <Submit>Create</Submit>
      </Form>
    </div>
  );
};

export default CreateSamurai;
