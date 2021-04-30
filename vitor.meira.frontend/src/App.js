import Welcome from './Welcome/index.jsx'
import { SamuraiProvider } from './Context/index.jsx'

function App() {
  return (
    <>
    <SamuraiProvider>
      <Welcome id={1}></Welcome>
    </SamuraiProvider>
    </>
  );
}

export default App;