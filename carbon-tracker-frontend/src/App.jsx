import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Navbar from "./components/Navbar";
import Footer from "./components/Footer";
import Login from "./pages/Login";
import UserDetails from "./pages/UserDetails";
import EmissionCalculation from "./pages/EmissionCalculation";
import About from "./pages/About";
import { Toaster } from "react-hot-toast";
import ProtectedRoute from "./components/ProtectedRoute";

function App() {
  return (
    <Router>
      <Toaster position="top-center" reverseOrder={false} />
      <div className="min-h-screen flex flex-col bg-base-200">
        <Navbar />
        <main className="flex-grow">
          <Routes>
            <Route path="/login" element={<Login />} />
            <Route
              path="/userdetails"
              element={
                <ProtectedRoute>
                  <UserDetails />
                </ProtectedRoute>
              }
            />

            <Route
              path="/emissionCalculation"
              element={<EmissionCalculation />}
            />
            <Route path="/about" element={<About />} />
          </Routes>
        </main>
        <Footer />
      </div>
    </Router>
  );
}

export default App;
