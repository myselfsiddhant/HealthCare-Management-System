@Service
public class HealthcareService {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private AppointmentDAO appointmentDAO;

    @Transactional
    public UserCredentials registerUserWithValidation(UserCredentials user) {
        // Advanced validation
        validateUserData(user);
        return userDAO.registerUser(user);
    }

    @Transactional
    public Appointment bookAppointmentWithRules(Appointment appointment) {
        // Business logic and validation
        validateAppointmentBooking(appointment);
        return appointmentDAO.bookAppointment(appointment);
    }
}
