import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import LogoutButton from "./LogoutButton";

function Navbar() {
  const [isLoggedIn, setIsLoggedIn] = useState(!!localStorage.getItem("token"));

  useEffect(() => {
    const updateLoginStatus = () => {
      setIsLoggedIn(!!localStorage.getItem("token"));
    };

    // Listen for our custom event
    window.addEventListener("authChange", updateLoginStatus);

    return () => {
      window.removeEventListener("authChange", updateLoginStatus);
    };
  }, []);

  return (
    <nav className="bg-primary text-white p-4 shadow-xl">
      <div className="flex justify-between">
        <Link to="/" className="hover:text-shadow-lg/20">
          <div className="font-bold text-xl">Carbon Tracker</div>
        </Link>

        <div className="space-x-4">
          <Link to="/emissioncalculation" className="hover:text-shadow-lg/20">
            Emission Calculation
          </Link>
          <Link to="/about" className="hover:text-shadow-lg/20">
            About
          </Link>
          {isLoggedIn && <LogoutButton />}
        </div>
      </div>
    </nav>
  );
}

export default Navbar;
