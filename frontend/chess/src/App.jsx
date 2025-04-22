import logo from './logo.svg';
import './App.scss';
import { Route, Routes, BrowserRouter } from 'react-router'
import Home from "./Screens/Home/Home"
import CompGame from './Screens/CompGame/CompGame';

function App() {
  return (
    <BrowserRouter className="App">
      <Routes>
        <Route path="/" element={<Home />}></Route>
        <Route path="/engine" element={<CompGame />}></Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
