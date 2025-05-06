import { useEffect, useState } from "react";
import api from "../services/api";
import { toast } from "react-hot-toast";
import { useNavigate } from "react-router-dom";

function EditUserForm({ user }) {
  const navigate = useNavigate();

  const initialFormState = {
    firstName: "",
    middleName: "",
    lastName: "",
    department: "",
    dob: "",
    gender: "",
    phone: "",
    address: "",
    district: "",
    state: "",
    pinCode: "",
  };

  const [formData, setFormData] = useState(initialFormState);

  useEffect(() => {
    if (user) {
      setFormData((prev) => ({
        ...prev,
        ...Object.fromEntries(
          Object.entries(initialFormState).map(([key]) => [key, user[key] || ""])
        ),
      }));
    }
  }, [user]);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  const closeModal = () => {
    document.getElementById("edit_user")?.close();
  };

  const handleAddUser = async () => {
    try {
      const payload = { ...formData };

      const response = await api.put(`/user/${user.userId}`, payload);
      toast.success("User Updated Successfully!");
      closeModal();
      navigate("/userdetails", { state: { refresh: true } });
    } catch (err) {
      console.error("Failed to update user:", err);
      toast.error("Failed to update user");
    }
  };

  return (
    <div className="flex">
      <dialog id="edit_user" className="modal backdrop-blur-xs">
        <div className="modal-box max-w-3xl shadow-lg">
          <h3 className="font-bold text-xl mb-4">Edit User Details</h3>
          <form
            className="space-y-4"
            onSubmit={(e) => {
              e.preventDefault();
              handleAddUser();
            }}
          >
            {/* Name Fields */}
            <div className="flex gap-4">
              <input
                required
                name="firstName"
                value={formData.firstName}
                onChange={handleInputChange}
                placeholder="First Name"
                className="input input-bordered w-1/3"
              />
              <input
                name="middleName"
                value={formData.middleName}
                onChange={handleInputChange}
                placeholder="Middle Name"
                className="input input-bordered w-1/3"
              />
              <input
                required
                name="lastName"
                value={formData.lastName}
                onChange={handleInputChange}
                placeholder="Last Name"
                className="input input-bordered w-1/3"
              />
            </div>

            {/* DOB + Gender */}
            <div className="flex gap-4">
              <input
                type="date"
                name="dob"
                value={formData.dob}
                onChange={handleInputChange}
                required
                className="input input-bordered w-1/2"
              />
              <select
                name="gender"
                value={formData.gender}
                onChange={handleInputChange}
                required
                className="select select-bordered w-1/2"
              >
                <option value="">Select Gender</option>
                <option value="Male">Male</option>
                <option value="Female">Female</option>
                <option value="Other">Other</option>
              </select>
            </div>

            {/* Department + Phone */}
            <div className="flex gap-4">
              <input
                required
                name="department"
                value={formData.department}
                onChange={handleInputChange}
                placeholder="Department"
                className="input input-bordered w-1/2"
              />
              <input
                required
                name="phone"
                type="tel"
                pattern="[0-9]{10}"
                title="Enter a valid 10-digit phone number"
                value={formData.phone}
                onChange={handleInputChange}
                placeholder="Phone"
                className="input input-bordered w-1/2"
              />
            </div>

            {/* Address */}
            <textarea
              required
              name="address"
              value={formData.address}
              onChange={handleInputChange}
              placeholder="Address"
              className="textarea textarea-bordered w-full"
            ></textarea>

            {/* District + State + Pin Code */}
            <div className="flex gap-4">
              <input
                required
                name="district"
                value={formData.district}
                onChange={handleInputChange}
                placeholder="District"
                className="input input-bordered w-1/3"
              />
              <input
                required
                name="state"
                value={formData.state}
                onChange={handleInputChange}
                placeholder="State"
                className="input input-bordered w-1/3"
              />
              <input
                required
                name="pinCode"
                value={formData.pinCode}
                onChange={handleInputChange}
                placeholder="Pin Code"
                pattern="[0-9]{6}"
                title="Enter a 6-digit PIN code"
                className="input input-bordered w-1/3"
              />
            </div>

            {/* Action Buttons */}
            <div className="modal-action">
              <button
                type="button"
                className="btn btn-outline btn-error mx-2"
                onClick={closeModal}
              >
                Cancel
              </button>
              <button type="submit" className="btn btn-outline btn-success">
                Update
              </button>
            </div>
          </form>
        </div>
      </dialog>
    </div>
  );
}

export default EditUserForm;
