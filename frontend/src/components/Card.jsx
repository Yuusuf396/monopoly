export default function Card({ children, className = '' }) {
    return (
      <div
        className={`
          bg-white bg-opacity-10
          backdrop-blur-md
          border border-white border-opacity-20
          rounded-3xl
          shadow-md
          p-8
          transition-all duration-300
          ${className}
        `}
      >
        {children}
      </div>
    );
  }
  