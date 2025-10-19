# Time Tracker App

## Usage

### Start logging time
`time-tracker start <task-name>`

`time-tracker start <task-name> <category-name>`

Note: Tasks are identified by task name, so attempting to start a new task with the same name as another will cause the existing task with the given name to be started once more, and no new task created.

### Stop logging time
`time-tracker stop <task-name>`

### Display current logged times
`time-tracker report tasks`
Shows all current tasks and their associated time elapsed

`time-tracker report category`
Shows all the current categories and their associated time elapsed
