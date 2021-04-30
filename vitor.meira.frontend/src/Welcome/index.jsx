import React, { useEffect, useState, useContext } from 'react';
import SamuraiContext from "../Context/index.jsx"

const findSamurai = (id, samurais) => samurais?.find((val) => val.id === id)

const Welcome = (props) => {

  const {id} = props 

  const {samurais, setSamurais} = useContext(SamuraiContext)

  const [samurai, setSamurai] = useState(null)

  async function fetchSamurai() {  
    await fetch(`http://localhost:8080/samurais/${id}`)
    .then(response => response.json())
    .then(res =>  {
      setSamurais(samurais.push(res))
      setSamurai(res)
    })
    .catch(err => console.log(err));
  }


  useEffect(() => {
    // if a verificar se o samurai com o id passado na prop ja está guardado no context (no array samurais)
    let cachedSamurai = findSamurai(id)

    if(cachedSamurai) { // verifica se existe informação dentro da variavel cachedSamurai
      setSamurai(cachedSamurai)
      return 
    }
    
    fetchSamurai();
    // eslint-disable-next-line
  }, [id]); // [] significa que a callback passsada como primeiro argumento no useEffect só é executada 1 vez quando o componente é renderizado... isto é equivalente a num class component usarmos ComponentDidMount

 
  return (
  <>
  <h1>Hello, {samurai?.name}</h1>
  </>)
}

export default Welcome;