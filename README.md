Project Description
The app is designed to assist individuals during medical emergencies by automating the entire process of hospital and doctor selection, equipment verification, and emergency service coordination. Users can enter their symptoms or emergency details through an intuitive, animated user interface built with Jetpack Compose. The application uses rule-based triage logic to match these inputs with the relevant medical specialty, ensuring the patient is routed to a hospital best equipped to provide immediate care.

After analyzing the input, the system queries real-time hospital and doctor data to display facilities with verified specialist availability and essential treatment equipment. It highlights hospitals that support government health schemes like the Ayushman card. Doctor status is tracked by an automated attendance system, and displayed in the app using clear color signals (green for available, yellow for busy, and red for unavailable).

A key feature is its ambulance orchestration: once a hospital is chosen, users can call the specific hospital’s ambulance directly or dial a general emergency number from within the app. The system ensures paramedics and emergency services are informed about the selected hospital, reducing delays and misrouting. This puts all decision-making and critical communications in the user’s hands, streamlining care and improving outcomes.

Core Features
Symptom-Based Triage: Accepts detailed user input on symptoms or emergencies and applies rule-based logic for specialty mapping.

Hospital & Equipment Selection: Lists hospitals by specialty, equipment, Ayushman scheme support, and proximity, based on up-to-date database queries.

Real-Time Doctor Status: Automated attendance system keeps track of doctor availability, visible in the app for instantaneous decision support.

Ambulance Coordination: Enables direct calling of the recommended hospital’s ambulance service, or lets users call emergency numbers (such as 108, 112) and specify their destination hospital for clear, prompt routing.

Decision Transparency: All specialty, equipment, and status data is visible to users, increasing confidence and speeding the emergency response.

Navigation: Built-in location services provide live directions to selected hospitals, minimizing transit time and confusion.

Authentication & Security: User data and session security is managed using Firebase authentication.

Technology Stack
Layer	Technology	Description
Language	Kotlin	Application logic and structure
UI	Jetpack Compose	Animated, declarative UI for emergency interaction
Database/Backend	Firebase, Supabase	Real-time updates for doctor, hospital, equipment
Authentication	Firebase Auth	User session security and login
Networking	Ktor Client	Secure database and API communication
Data Serialization	Kotlinx Serialization JSON	Data transfer between app and backend
Permissions	Accompanist Permissions	Runtime permissions for location and sensors
Location/Maps	Google Play Services Location	GPS functionality for navigation and hospital search
Images	Coil Compose	Fast, native image loading for hospital listings and UI
Navigation	AndroidX Navigation Compose/UI	Routing and screen transitions
Workflow Example
User inputs emergency details.

App matches input to medical specialty using coded rules.

System queries live hospital/doctor/equipment data.

Recommended hospitals are presented, doctor status is color-coded.

User selects hospital and directly calls ambulance service of that hospital or emergency helpline and specifies destination.

The app delivers directions and prepares the chosen facility for patient arrival.

Value
This solution centralizes all essential emergency care functions—triage, hospital choice, specialist matching, ambulance communication, and navigation—into a single, fast, transparent workflow. It replaces uncertainty with clear, actionable information at every step, greatly improving the speed and quality of emergency care delivery.

