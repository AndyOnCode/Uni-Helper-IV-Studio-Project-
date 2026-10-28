```
IV Studio - PRODUCT BACKLOG
```

```
Integrants: Andy Tiban, Nohemi Tocagon, Tomas
Tapia, Danilo Simba
```

# `1.-Adaptive Schedule Manager:` 

## **`TASKS:`** 

- `Records entries using standardized block and room identifiers (e.g., B202).` 

- `Supports specific flags for critical events such as Tests, laboratory sessions, and recurring coursework.` 

## **`No functional requirements:`** 

`1. Reject invalid room codes instantly using strict format validation (e.g., regex for B202) and use predefined types (enum) for flags (Test, Lab, Coursework).` 

`2. Keep room formats and event tags modular so new flags or buildings can be added easily without breaking existing code.` 

```
STARTED TASKS: No yet started
```

```
DONE: No
```

- `2.-Smart Budgeting Modes:` 

```
TASKS:
```

```
Offers customizable spending modes (`Save`,
```

```
`Recommended`, `Social`) that adapt based on the
```

```
student's needs.
```

## **`No functional requirements:`** 

`3. All mode-based calculations must use precise integer arithmetic (cents) to avoid floating-point rounding errors.` 

`4. Active mode selections must persist consistently across session views without unintended resets.` 

`5. The mode logic must be decoupled using clear` 

```
patterns (e.g., enum or Strategy pattern) so new
budget tiers can be added without altering
calculation algorithms.
```

```
STARTED TASKS: No yet started
```

```
DONE: No
```

- `3.-Task Priority System` 

```
TASKS:
```

```
Allows users to assign High, Medium and Lowpriority to
tasks, helping the students to organize and focus on the
most important assignments first.
```

## **`No functional requirements:`** 

`1. The priority options must be limited to Three-value input validation (High, Medium, Low)` 

`2. The system must sort task quickly using native functions` 

```
STARTED TASKS: No yet started
```

```
DONE: No
```

- `4.-Quick Study Break Reminder:` 

## **`TASKS:`** 

```
Provides friendly, automated reminders based on
```

```
cumulative study time or completed tasks to suggest a
```

```
short, healthy break.
```

## **`No functional requirements:`** 

`1. The break timer must run smoothly using simple local` 

   - `timers` 

`2. The reminder pop-up must be clear and easy to close` 

```
STARTED TASKS: No yet started
```

```
DONE: No
```

- `5.-Subject Filter` 

## **`TASKS:`** 

```
Allows students to view only the tasks associated with a
specific subject or course, helping users focus on one
```

```
course at a time.
```

## **`No functional requirements:`** 

`1. The subject search must match exact text strings` 

```
2.The filter menu must be clean and simple to use
```

```
STARTED TASKS: No yet started
```

```
DONE: No
```

- `6.-Task Registration and Management:` 

## **`TASKS:`** 

- `Create the task registration page` 

- `Add an option to edit a task` 

- `Add an option to delete a task` 

## **`Non functional requirements:`** 

- `Task must be protected against unauthorized changes` 

- `Task management options must be simple and easy to understand` 

- `The system must respond quickly when creating, editing,` 

   - `or deleting task` 

```
STARTED TASKS: NO
```

```
DONE: No
```

- `7.-User Login and Authentication` 

## **`TASKS:`** 

- `Create the login page (username and password).` 

- `Check whether the information is correct or incorrect` 

## **`Non functional requirements:`** 

- `The password must be kept secure and not displayed while being typed` 

- `The login screen must be simple and easy to understand` 

- `The system must respond quickly when data is entered` 

```
STARTED TASKS: No yet started
```

```
DONE: No
```

- `8.-Task Status Tracking:` 

## **`TASKS:`** 

- `Display the status of each task (in progress, pending, or completed)` 

- `Allow the user to change a task´s status` 

- `Verify that the status is saved correctly and update in the task list` 

## **`Non functional requirements:`** 

- `Task status must be clearly displayed` 

- `The system must correctly maintain task status without losing information` 

- `Status changes must be updated quickly` 

```
STARTED TASKS: No yet started
```

```
DONE: No
```

- `9.-Overdue Task Alert:` 

   - **`TASKS:`** 

```
Automatically checks if any task has passed its deadline
and visually labels it as "Overdue".
```

```
STARTED TASKS: No yet started
```

```
DONE: No
```

