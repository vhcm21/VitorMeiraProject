import React from "react";
import { Wrapper, InputStyled, Label } from "./styled-components";

const Input = ({ label, name, type, value, setValue, pattern }) => {
  return (
    <Wrapper>
      <Label htmlFor={name}>{label}</Label>
      <InputStyled
        type="text"
        id={name}
        value={value}
        onChange={(event) => setValue(event.target.value)}
        pattern={pattern}
      />
    </Wrapper>
  );
};

export default Input;
