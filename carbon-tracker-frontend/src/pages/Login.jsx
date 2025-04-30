import { useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../services/api"; // axios instance
import toast from "react-hot-toast"; // optional but useful for error/success notifications

function Login() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      const response = await api.post("/auth/login", {
        username,
        password,
      });

      const token = response.data.token;
      localStorage.setItem("token", token);
      window.dispatchEvent(new Event("authChange"));
      navigate("/dashboard");
    } catch (error) {
      toast.error(
        "Login failed: " +
          (error.response?.data?.message ||
            error.response?.statusText ||
            error.message)
      );
    }
  };

  return (
    <div className="flex justify-center items-center min-h-[calc(100vh-150px)]">
      <div className="bg-white shadow-xl/30 p-8 rounded w-full max-w-sm">
        <h1 className="text-2xl font-bold mb-8 text-center">Login</h1>
        <form onSubmit={handleLogin}>
          <input
            className="w-full mb-4 p-2 border rounded"
            placeholder="Username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            required
          />
          <input
            type="password"
            className="w-full mb-6 p-2 border rounded"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
          <button
            type="submit"
            className="w-full bg-primary text-white font-bold py-2 rounded hover:bg-secondary"
          >
            Login
          </button>
        </form>
      </div>
    </div>
  );
}

export default Login;
