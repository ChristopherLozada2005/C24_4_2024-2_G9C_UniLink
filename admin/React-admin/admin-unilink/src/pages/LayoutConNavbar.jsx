import Navbar from "../components/Navbar";

const LayoutConNavbar = ({ children }) => {
  return (
    <div>
      <Navbar />
      <div className="body">
        {children}
      </div>
    </div>
  );
};

export default LayoutConNavbar;