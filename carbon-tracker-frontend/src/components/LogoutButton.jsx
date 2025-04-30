import { useNavigate } from "react-router-dom";

function LogoutButton() {
  const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.removeItem("token");
    window.dispatchEvent(new Event("authChange"));
    navigate("/");
  };

  return (
    <button
      onClick={handleLogout}
      className="bg-secondary text-white px-3 py-1 rounded hover:bg-red-600"
    >
      Logout
    </button>
  );
}

export default LogoutButton;
