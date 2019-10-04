# UniversityAdmissionSystem
##### Project description:
*Topic №6: University Admission System*

        A student registers for exams.
        An administrator sets grades.
        A student chooses a specialty at the university.
        The system generates rating list of enrolled students.
        Students receive a notification of admission.

##### You should install to launch the project:
* JDK 8
* MySQl 8
* Tomcat 9
* Maven

##### Installation instructions:
* Click "Clone or download" button on the top-left corner
* Choose "Download ZIP"
* Unpack ZIP-archive
* Open folder "src\main\resources\sqlScripts"
* Launch "create.sql" script at MySQL Command Line
* Launch "insert.sql" script at MySQL Command Line

##### Instructions for launching the application:
* open command line
* cd {path to directory where you unpacked ZIP-archive}
* mvn clean install
* open folder {path to directory where you unpacked ZIP-archive}/target
* deploy "UniversityAdmissionSystem.war" file to Tomcat
* start up Tomcat, launch your web browser, navigate to application URL