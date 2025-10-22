# Time Tracker App

## Overview:
CLI Application that logs and keeps track of tasks by an identifying task name, and an optional category. The program will log the total elapsed time spent on each task and maintains its current status. The user can start and stop tasks as they like and can generate reports on their status and the amount of time they've spent on them so far. 

## Setup/Installation:
1. Clone the repository via HTTPS/SSH

    `git clone git@github.com:riley-hendrickson/time-tracker.git`  
    `git clone https://github.com/riley-hendrickson/time-tracker.git`
2. Produce a runnable jar with Maven (recommended command):

    `mvn -q clean package`
3. Run the program via the .jar file whose name includes -runner generated in the target directory

    Example Commands:  
    `java -jar /target/<name of runner jar file> start <task name>`  
    `java -jar /target/<name of runner jar file> stop <task name>`  
    `java -jar /target/<name of runner jar file> report tasks`  
    `java -jar /target/<name of runner jar file> report category`  

## Usage

### Start Logging Tasks
`time-tracker start <task-name>`

- Starts a new task with the given name and begins tracking elapsed time on this task

`time-tracker start <task-name> <category-name>`

- Starts a new task with the given name and category and begins tracking elapsed time on this task

#### Note: 
- Tasks are identified by task name, so attempting to start a new task with the same name as another will cause the existing task with the given name to be started once more, and no new task created.
- Starting a new task automatically stops any tasks currently in progress, so only one task is being worked on and tracked at a time.

### Stop Currently Logged Tasks
`time-tracker stop <task-name>`

### Display Current Logged Tasks
`time-tracker report tasks`

- Shows all current tasks and their associated time elapsed

`time-tracker report category`

- Shows all the current categories and their associated time elapsed

## Example Output
### After Running First Set of Commands:
1. `time-tracker start Breakfast`
2. `time-tracker stop Breakfast` 
3. `time-tracker start Coding Productivity`
4. `time-tracker start Running Exercise`
5. `time-tracker stop Running`
6. `time-tracker start JobApplications Productivity`
7. `time-tracker report tasks`

### Output:
![Output Screenshot 1](images/firstSS.jpg)
### After Running Second Set of Commands:
1. `time-tracker stop JobApplications`
1. `time-tracker report category`

### Output:
![Output Screenshot 2](images/secondSS.jpg)