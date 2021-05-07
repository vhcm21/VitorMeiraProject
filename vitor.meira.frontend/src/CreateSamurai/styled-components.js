import styled from "styled-components";
import palette from "./palette.json";

export const Form = styled.form`
  display: flex;
  flex-direction: column;
`;

export const Submit = styled.button`
  width: 100px;
  height: 50px;
  background-color: ${palette.colors.darkOrange};
  color: ${palette.colors.floralWhite};
  border: none;
  border-radius: 4px;
  cursor: pointer;
`;