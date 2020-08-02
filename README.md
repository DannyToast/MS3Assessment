# MS3 Assessment
## Summary of this Repository
This repository is the solution to a coding test I've been given concerning writing a Java application that consumes a CSV file
and inserts valid records from this file into an SQLite database with 10 columns marked A, B, C, D, E, F, G, H, I, and J.

These records are each verified to ensure that they are of consistent formatting to the prompt, and invalid records in the input
CSV file are filtered out and displayed in a newly created CSV file marked ms3Interview-bad.csv. Statistics after running, concerning
the amount of records processed and the number of which that were marked as successful or failed, are written to a log file marked ms3Interview.log.

The final SQLite database and corresponding records table are written to ms3Interview.db. The input CSV file may be manipulated as the user desires 
so long as the records match the format specified and shown with the included CSV file located in the repository.

## Compiling and running the program
To Build:
1. Ensure you have the newest version of Apache Maven installed on your computer and added to the PATH in the system environment variables (same with Java).
2. In command line, navigate to the location of the pom.xml file (probably .\src\pom.xml)
3. run the command "mvn clean compile assembly:single"
4. It should build and download all the dependencies! The path to the .jar location will be listed in the command line output of the build.

To Run:
1. After building, navigate (cd) to MS3Assessment-1.0-jar-with-dependencies in command line, it will probably build in the "target" directory.
2. Run command "java -jar MS3Assessment-1.0-jar-with-dependencies"
3. The program should run!
4. I would ask that for subsequent runs that the database file be deleted. The point of the assignment was to create a program that creates a new database and table
so I didn't add functionality to overwrite existing databases, or append to them. Please let me know if there's any problem with this. The program is re-runnable as long
as you delete all generated files (ms3Interview-bad.csv, ms3Interview.db, and the log) and keep a CSV in the root directory with a valid format.

## Design Choices and Assumptions
Something pertinent I should mention first is that I kept all data types in the SQLite database as text only. I could have reassigned the Boolean values in the later columns to an integer 1 and 0 but I figured that since the exercise was to port the CSV to a database I should preserve the form of those CSV records as much as possible. Practically speaking I'd almost certainly have used data types, since text versions of boolean or double values (as both were present) are much more useful
when designing business logic after the fact to handle these columns.

I approached this first by thinking about how I could parse the CSV into a Records object. While this is something I think I could have easily written the logic for
on my own, given a little time, I saw in the instructions that using open source libraries was encouraged, so I sought to do just that. I ended up settling on Apache
Commons CSV since it appeared to have a lot of support and a strong community just in case I had any questions. I built the parser using this library and then set to work on separating out the failed and successful records. Luckily this library has a handy little method that checks for records with record column counts that don't match the header count.

I should also mention that I made the Records class members after what I assumed each column was representing, to the best of my ability, it was mostly an organizational tool as I figured getters and setters for singular letters (i.e. getA, setB) would start to look really confusing, to both me and anyone reading this later.

After confirming that these results were satisfactory, I built the SQLite database and table using using the JDBC SQLite driver, and set to work creating a function
that would insert the Record objects parsed from the CSV. This was pretty self-explanatory as I had worked with SQLite databases before on an Android application, although I hadn't used this specific driver before I was more or less familiar with forming queries and prepared statements. For optimization I used a batch insert, it now hovers around 300ms-500ms for the given CSV when otherwise it would most likely take several minutes. I thought about trying to take this lower but I didn't want to wait to turn the assignment in any longer since I had already been out of town for most of the week and been unable to work on it.

Afterwards I added some JavaDoc comments explaining what each function did for some added readability, then configured the Maven assembly plugin to handle the
dependencies for the jar, and then started writing this README. I'm not sure if I went into enough detail about my process, so feel free to email me with any further questions.
