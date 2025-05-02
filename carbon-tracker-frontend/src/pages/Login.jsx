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
      toast.success("Successfully Logged In!");
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
    <div className="flex justify-center items-center min-h-[calc(100vh-190px)]">
      <div className="card bg-base-100 shadow-lg p-8 w-full max-w-sm">
        <h1 className="text-2xl font-bold mb-7 text-center">Login</h1>
        <form onSubmit={handleLogin}>
          <div>
            <label className="input validator">
              <svg
                className="h-[1em] opacity-50"
                xmlns="http://www.w3.org/2000/svg"
                viewBox="0 0 24 24"
              >
                <g
                  strokeLinejoin="round"
                  strokeLinecap="round"
                  strokeWidth="2.5"
                  fill="none"
                  stroke="currentColor"
                >
                  <path d="M19 21v-2a4 4 0 0 0-4-4H9a4 4 0 0 0-4 4v2"></path>
                  <circle cx="12" cy="7" r="4"></circle>
                </g>
              </svg>
              <input
                type="input"
                required
                placeholder="Username"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
                pattern="[a-z0-9._%+\-]+@[a-z0-9.\-]+\.[a-z]{2,}$"
                minlength="5"
                maxlength="100"
                title="Only letters, numbers or - and @"
              />
            </label>
            <p className="validator-hint hidden">
              Must be atleast 5 characters
              <br />
              containing only letters, numbers or - and @
            </p>
          </div>

          <div className="mt-3">
            <label className="input validator">
              <svg
                className="h-[1em] opacity-50"
                xmlns="http://www.w3.org/2000/svg"
                viewBox="0 0 24 24"
              >
                <g
                  strokeLinejoin="round"
                  strokeLinecap="round"
                  strokeWidth="2.5"
                  fill="none"
                  stroke="currentColor"
                >
                  <path d="M2.586 17.414A2 2 0 0 0 2 18.828V21a1 1 0 0 0 1 1h3a1 1 0 0 0 1-1v-1a1 1 0 0 1 1-1h1a1 1 0 0 0 1-1v-1a1 1 0 0 1 1-1h.172a2 2 0 0 0 1.414-.586l.814-.814a6.5 6.5 0 1 0-4-4z"></path>
                  <circle
                    cx="16.5"
                    cy="7.5"
                    r=".5"
                    fill="currentColor"
                  ></circle>
                </g>
              </svg>
              <input
                type="password"
                required
                placeholder="Password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                minlength="5"
                pattern="(?=.*\d)(?=.*[a-z]).{5,}"
                title="Must be more than 5 characters, including number"
              />
            </label>
            <p className="validator-hint hidden">
              Must be more than 5 characters, including
              <br />
              At least one number <br />
            </p>
          </div>

          <div>
            <button
              type="submit"
              className="btn btn-soft btn-block mt-5 bg-primary text-primary-content font-bold hover:bg-secondary"
            >
              Login
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}

export default Login;
