# ClinicaUNA - A Medical Record and Appointment System 🏥
ClinicaUNA is a JavaFX application that allows managing medical records and appointments for a clinic. It uses web services to connect to a Payara application server and an Oracle 21c XE database. It is a multilingual application that supports Spanish and English.

## Features 🚀
- User management: Users can register, log in, recover passwords, and change their language preferences. Users have different roles and access levels: Administrator, Receptionist, or Doctor.
- Doctor management: Doctors can enter their personal and professional information, such as code, folio, license, schedule, and spaces per hour.
- Patient management: Patients can enter their personal information, such as name, ID, phone, email, gender, and date of birth.
- Appointment management: Appointments can be scheduled, canceled, moved, or confirmed. Appointments have different states: scheduled, attended, canceled, or absent. Appointments have different fields, such as patient information, user who registers, reason, phone, and email. Appointments can occupy more than one space in the agenda. Confirmation and reminder emails are sent to the patient’s email.
- Medical record management: Medical records are created for each patient, containing personal and family history, such as diseases, hospitalizations, surgeries, allergies, and treatments. Medical records also contain the details of each consultation, such as date, time, blood pressure, heart rate, weight, height, temperature, body mass index, nursing notes, doctor notes, plan of care, observations, physical examination, and treatment. The treatment is emailed to the patient along with the doctor’s information. Medical records can also include any type of exams, such as name, date, and doctor’s notes. The system can calculate and display the patient’s weight evolution based on the body mass index and the historical consultations.
- Report management: The system can generate reports using Jasper Report, such as the agenda of a specific doctor for a day or a range of days, and the complete medical record of a patient, including the general information and each consultation1. The system can also create a custom report based on the group’s proposal. The system can also create a report manager for Excel, where dynamic Excel reports can be registered, with a name, description, SQL query, title, destination emails, generation frequency, and parameter list. The report manager can generate the Excel report according to the query and parameters, and send it by email to the recipients.

## Requirements 🔩
Oracle 21c XE database
Payara application server
Netbeans IDE
JavaFX
JPA
REST web services
Jasper Report

## Installation 🔧
- Create a database schema with the name of the application (ClinicaUNA) and run the script file to create the necessary objects.
- Define a connection pool with the name “ClinicaPool” to manage the connection with the database2.
- Define a jdbc resource named “jdbc/Clinica” to give access to the application to the connection pool3.
- Develop a web application named “WsClinicaUNA” where a web service with all the methods required for the application is hosted.
- Compile and deploy the web application to the Payara server.
- Compile and run the JavaFX application.

- ## Authors ✒️

- Anthony Avila - Developer
- Kevin Arauz - Developer
- Luis Vargas - Developer

##Contact 📞

If you have any questions, suggestions or comments about this project, you can contact us through the following means:

- anthonyah131@gmail.com
- kjarj54@gmail.com
- Luvarapz@gmail.com