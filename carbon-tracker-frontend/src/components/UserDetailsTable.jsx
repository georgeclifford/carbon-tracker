import { useEffect, useState } from "react";
import api from "../services/api";
import { toast } from "react-hot-toast";
import { useLocation } from "react-router-dom";
import EditUserForm from "./EditUserForm";

function UserDetailsTable() {
  const [users, setUsers] = useState(null); // null = loading, [] = empty
  const [selectedUser, setSelectedUser] = useState(null);
  const location = useLocation();

  useEffect(() => {
    fetchUsers();
  }, []);

  useEffect(() => {
    if (location.state?.refresh) {
      fetchUsers();
      window.history.replaceState({}, document.title); // clear refresh state
    }
  }, [location]);

  useEffect(() => {
    if (selectedUser) {
      const modal = document.getElementById("edit_user");
      if (modal) {
        modal.showModal();
      }
    }
  }, [selectedUser]);

  const fetchUsers = () => {
    api
      .get("/user/admin")
      .then((response) => setUsers(response.data))
      .catch((error) => {
        console.error("Error fetching users:", error);
        setUsers([]);
      });
  };

  const handleEdit = (user) => {
    setSelectedUser(user);
  };

  const handleActivate = (userId) => {
    api
      .post(`/user/admin/${userId}/activate`)
      .then((response) => {
        toast.success(response.data);
        setUsers((prevUsers) =>
          prevUsers.map((user) =>
            user.userId === userId
              ? { ...user, login: { ...user.login, accountStatus: "ACTIVE" } }
              : user
          )
        );
      })
      .catch((error) => {
        console.error("Error Activating User:", error);
        toast.error("Failed To Activate User.");
      });
  };

  const handleDeactivate = (userId) => {
    api
      .post(`/user/admin/${userId}/deactivate`)
      .then((response) => {
        toast.success(response.data);
        setUsers((prevUsers) =>
          prevUsers.map((user) =>
            user.userId === userId
              ? { ...user, login: { ...user.login, accountStatus: "INACTIVE" } }
              : user
          )
        );
      })
      .catch((error) => {
        console.error("Error Deactivating User:", error);
        toast.error("Failed To Deactivate User.");
      });
  };

  return (
    <div className="bg-base-100 shadow-lg p-5 rounded-xl">
      <div className="overflow-x-auto max-w-full">
        {selectedUser && <EditUserForm user={selectedUser} />}
        <table className="table table-zebra w-full min-w-[900px]">
          <thead>
            <tr>
              <th>User ID</th>
              <th>Name</th>
              <th>Department</th>
              <th>DOB</th>
              <th>Gender</th>
              <th>Phone</th>
              <th>Address</th>
              <th>Registration Date</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
            {users && users.length > 0 ? (
              users.map((user) => (
                <tr key={user.userId} className="hover:bg-base-300">
                  <td>{user.userId}</td>
                  <td>
                    {user.firstName}{" "}
                    {user.middleName ? `${user.middleName} ` : ""}
                    {user.lastName}
                  </td>
                  <td>{user.department}</td>
                  <td>{user.dob}</td>
                  <td>{user.gender}</td>
                  <td>{user.phone}</td>
                  <td>
                    {user.address}, {user.district}, {user.state},{" "}
                    {user.pinCode}
                  </td>
                  <td>{user.registrationDate}</td>
                  <td>
                    <div className="flex space-x-2">
                      <div
                        className="tooltip tooltip-left"
                        data-tip="Edit User Details"
                      >
                        <button
                          onClick={() => handleEdit(user)}
                          className="btn btn-soft btn-info btn-circle btn-sm"
                        >
                          <svg
                            xmlns="http://www.w3.org/2000/svg"
                            fill="none"
                            viewBox="0 0 24 24"
                            strokeWidth={1.5}
                            stroke="currentColor"
                            className="size-4.5"
                          >
                            <path
                              strokeLinecap="round"
                              strokeLinejoin="round"
                              d="m16.862 4.487 1.687-1.688a1.875 1.875 0 1 1 2.652 2.652L6.832 19.82a4.5 4.5 0 0 1-1.897 1.13l-2.685.8.8-2.685a4.5 4.5 0 0 1 1.13-1.897L16.863 4.487Zm0 0L19.5 7.125"
                            />
                          </svg>
                        </button>
                      </div>

                      {user.login.accountStatus === "ACTIVE" ? (
                        <div
                          className="tooltip tooltip-left"
                          data-tip="Deactivate"
                        >
                          <button
                            onClick={() => handleDeactivate(user.userId)}
                            className="btn btn-soft btn-error btn-circle btn-sm"
                          >
                            <svg
                              xmlns="http://www.w3.org/2000/svg"
                              fill="none"
                              viewBox="0 0 24 24"
                              strokeWidth={1.5}
                              stroke="currentColor"
                              className="size-6"
                            >
                              <path
                                strokeLinecap="round"
                                strokeLinejoin="round"
                                d="m9.75 9.75 4.5 4.5m0-4.5-4.5 4.5M21 12a9 9 0 1 1-18 0 9 9 0 0 1 18 0Z"
                              />
                            </svg>
                          </button>
                        </div>
                      ) : (
                        <div
                          className="tooltip tooltip-left"
                          data-tip="Activate"
                        >
                          <button
                            onClick={() => handleActivate(user.userId)}
                            className="btn btn-soft btn-success btn-circle btn-sm"
                          >
                            <svg
                              xmlns="http://www.w3.org/2000/svg"
                              fill="none"
                              viewBox="0 0 24 24"
                              strokeWidth={1.5}
                              stroke="currentColor"
                              className="size-5"
                            >
                              <path
                                strokeLinecap="round"
                                strokeLinejoin="round"
                                d="M9 12.75 11.25 15 15 9.75M21 12a9 9 0 1 1-18 0 9 9 0 0 1 18 0Z"
                              />
                            </svg>
                          </button>
                        </div>
                      )}
                    </div>
                  </td>
                </tr>
              ))
            ) : (
              <tr>
                <td colSpan="9" className="text-center py-4">
                  {users === null ? "Loading..." : "No users found."}
                </td>
              </tr>
            )}
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default UserDetailsTable;
