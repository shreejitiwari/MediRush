## 🎬 Demo Video (Private)

[Watch MediRush demo here](https://www.youtube.com/shorts/CnGUmduphRM)  
*(Accessible only to project visitors)*

# 🖼️ App Screenshots
See the app screens:
[Google Drive Screenshot Gallery](https://drive.google.com/drive/folders/1L6s8PJVoksT0E9oWF4NUkWPNrqdgY8NL?usp=sharing)

***
## 🖼️ App Screenshots

<div align="center">

<!-- Splash Screen -->
<img src="images/1.jpg" alt="Splash Screen" width="270"/>

<!-- Login & Emergency Access -->
<img src="images/2.jpg" alt="Login & Emergency Access" width="270"/>

<!-- Body Selector (Back View) -->
<img src="images/4.jpg" alt="Body Selector Back" width="270"/>

<!-- Body Selector (Front View) -->
<img src="images/3.jpg" alt="Body Selector Front" width="270"/>

<!-- Analysis Progress -->
<img src="images/6.jpg" alt="Analysis Progress" width="270"/>

<!-- Specialty Recommendation -->
<img src="images/7.jpg" alt="Specialty Recommendation" width="270"/>

<!-- Emergency Patient Details -->
<img src="images/5.jpg" alt="Emergency Patient Details" width="270"/>

<!-- Hospital Listing -->
<img src="images/8.jpg" alt="Hospital Listing" width="270"/>

<!-- Emergency Call Screen -->
<img src="images/11.jpg" alt="Emergency Call" width="270"/>

<!-- Hospital Info -->
<img src="images/10.jpg" alt="Hospital Info" width="270"/>

<!-- Directions Screen -->
<img src="images/9.jpg" alt="Directions Screen" width="270"/>

</div>

***

# 🚑 MediRush: Automated Medical Emergency Response App

MediRush is an Android application that streamlines emergency response, matching patients with the right hospital and specialist in real time, displaying doctor and equipment availability, and enabling ambulance coordination for rapid, transparent care.  
[1][2][3]

***

## 📝 Overview

MediRush helps users take decisive action during a medical emergency:

- 🌟 **Animated, intuitive UI:** Instantly input symptoms or select emergency types via a modern Compose interface.
- 🧑‍⚕️ **Rule-based triage:** Patient input is matched with the correct medical specialty (e.g., cardiology, trauma, pulmonology).
- 🏥 **Live hospital and equipment data:** Only hospitals with the correct specialist and necessary resources are recommended.
- 🟢🟡🔴 **Real-time doctor status:** Automated attendance system updates availability status (green = available, yellow = busy, red = unavailable).
- 💳 **Ayushman card support:** Hospitals supporting government schemes are marked for transparency and accessible care.
- 🚨 **Direct ambulance/call integration:** Instantly call the selected hospital’s ambulance or emergency number, and specify your destination hospital.
- 📍 **GPS-powered navigation:** Quickly get directions to the recommended facility.
- 🔐 **Secure authentication:** Every session is protected via Firebase Auth.

***

## ✨ Features

- 🚑 Input symptoms and emergencies with rapid, interactive UI.
- 🩺 Accurate triage and specialty mapping for every situation.
- 🏥 Filtered hospital listing by specialty, doctor, equipment, and scheme support.
- 🟢 Doctor status clearly shown for faster decisions.
- 📞 One-tap ambulance calling and emergency service integration.
- 🗺️ Live directions from your location to the selected hospital.
- 🔒 Safe and secure user accounts and health information.

***

## 🛠️ Technology Stack

| Component              | Technology                    |
|------------------------|------------------------------|
| 💻 Language            | Kotlin                       |
| 🎨 UI                  | Jetpack Compose              |
| 🖼️ Images/Animation    | Coil Compose                 |
| 🔐 Authentication      | Firebase Auth                |
| ☁️ Backend/Realtime    | Firebase, Supabase           |
| 🌐 Networking          | Ktor Client                  |
| 🗃️ Data Serialization  | Kotlinx Serialization JSON   |
| 🧭 Navigation          | AndroidX Navigation Compose  |
| 📍 Location            | Google Play Services         |
| ⚙️ Permissions         | Accompanist Permissions      |

***

## ⚡ Workflow Example

1. 📝 User enters symptoms or selects the emergency.
2. 🩺 MediRush matches input to a specialty and queries live hospital and doctor status.
3. 🏥 User sees available hospitals by proximity, resources, and scheme support.
4. 📞 User selects a hospital and directly calls the ambulance or emergency number, specifying their choice.
5. 🗺️ Turn-by-turn directions are provided to speed up response and access to care.

***
