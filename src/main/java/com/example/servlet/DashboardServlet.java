@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
    @Autowired
    private AppointmentDAO appointmentDAO;

    @Autowired
    private ReportDAO reportDAO;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("login");
            return;
        }

        String username = (String) session.getAttribute("username");

        // Fetch appointments
        List<Appointment> appointments = appointmentDAO.fetchUpcomingAppointments(username);
        request.setAttribute("appointments", appointments);

        // Fetch medical reports
        List<MedicalReport> medicalReports = reportDAO.fetchMedicalReports(username, null);
        request.setAttribute("medicalReports", medicalReports);

        request.getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request, response);
    }
}
