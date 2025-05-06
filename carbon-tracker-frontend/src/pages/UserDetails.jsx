import Sidebar from "../components/Sidebar";
import UserDetailsTable from "../components/UserDetailsTable";
import NewUserForm from "../components/NewUserForm";

function UserDetails() {
  return (
    <div className="flex">
      <Sidebar />
      <div className="p-8 w-full overflow-hidden">
        <div className="flex justify-between items-center mb-6">
          <h2 className="text-2xl font-semibold text-shadow-lg">
            User Details
          </h2>
          <NewUserForm />
        </div>
        <UserDetailsTable />
      </div>
    </div>
  );
}

export default UserDetails;
