import Welcome from './Welcome/index.jsx'
import Title from './Title/index.jsx'
import Info from './Info/index.jsx'
import CreateSamurai from './CreateSamurai/index.jsx'
import { SamuraiProvider } from './Context/index.jsx'

import './index.css';

function App() {
  return (
    <>
    <SamuraiProvider>
      <Welcome id={1}></Welcome>
      <Title></Title>
      <Info></Info>
      <CreateSamurai></CreateSamurai>
    </SamuraiProvider>
    </>
  );
}

export default App;