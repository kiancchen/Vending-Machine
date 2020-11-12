- [Scrum Teams](#scrum-teams)
  - [Roles](#roles)
  - [Individual Contribution](#individual-contribution)
- [Agile Development Tools and Practices](#agile-development-tools-and-practices)
- [Application Development](#application-development)
  - [UML Class Diagram](#uml-class-diagram)
  - [Demo](#demo)
    - [Sign In or Sign Up](#sign-in-or-sign-up)
    - [Display Users](#display-users)
    - [Add Users](#add-users)
    - [Change Users](#change-users)
    - [Remove Users](#remove-users)
    - [Display Cash](#display-cash)
- [Sprint Artifacts](#sprint-artifacts)
  - [Product Backlog](#product-backlog)
  - [Sprint Goal](#sprint-goal)
  - [User Stories and Sprint Backlog](#user-stories-and-sprint-backlog)
  - [Burndown Chart](#burndown-chart)
  - [Storyboard](#storyboard)
  - [Scrum Meetings](#scrum-meetings)
    - [Meeting 1 (Sprint Planning)](#meeting-1-sprint-planning)
    - [Meeting 2](#meeting-2)
    - [Meeting 3](#meeting-3)
  - [Sprint Review](#sprint-review)
    - [Client Feedback](#client-feedback)
    - [Revisited Product Backlog](#revisited-product-backlog)
  - [Sprint Retrospective](#sprint-retrospective)

# Scrum Teams

## Roles

1. Product Owner: Yufei Zuo

    Responsible for:
    - Maximize value of the product and the work
    - Manage the product backlog
    - Remove impediments

2. Scrum Master: Cheng Chen

    Responsible for:
    - Optimize the value of the work
    - Ensure transparency and clarity of the product backlog
    - Coordinate the work among teammates

3. Developer1: Yuanqun Wang
4. Developer2: Zexuan Long
5. Developer3: Songyin Li
6. Developer4: Zehui Lin

## Individual Contribution

1. Cheng Chen (cche7436):
   - Review pull requests
   - Logic of `Product` and `Transaction`
   - <mark>TODO</mark>
2. Yuanqun Wang (ywan3184):
   - Shopping cart GUI
   - Add product in shoooping cart
   - Change product in shopping cart
   - Remove product in shopping cart
3. Yufei Zuo (yzuo4982):
   - Add product GUI
   - Remove product GUI
   - Change product GUI
   - Product table
4. Zexuan Long (zlon4018):
   - Product options on main window
   - <mark>TODO</mark>
5. Songyin Li (soli3733):
   - Change notes GUI
   - Demo report
6. Zehui Lin (zlin3338):
   - A table display input notes
   - A table display change notes <mark>TODO</mark>

# Agile Development Tools and Practices

Same content in Sprint 1 Report, can be accessed at this [link](https://github.sydney.edu.au/SOFT2412-2020S2/R18B-Group5-VendingMachine/blob/master/Sprint1Report.md).

# Application Development

## UML Class Diagram

<mark>TODD</mark>

## Demo

<mark>TODD</mark>

`blan` is an owner. Its password is `123`. Feel free to use this account to test functionalities.

Here we are using the code with tag `1.0` for demonstration which you can see on Release page on Github.

### Sign In or Sign Up

At first the window will display the current user is `anonymous` by default.

<img src='https://i.loli.net/2020/11/05/esZRBvgfGN9tKVx.png' alt='esZRBvgfGN9tKVx'/>

After click on `Account` button, it display a window to sign in or sign up.

<img src='https://i.loli.net/2020/11/05/EGFWUjfksDiKBrM.png' alt='EGFWUjfksDiKBrM'/>

When it sign in with correct username and password, it will display an alert to show sign in successfully, and the main window will display corresponding username and its type on the top right corner, as well as the text on button will change to `Logout` instead of `Account`.

<img src='https://i.loli.net/2020/11/05/EAfrc6dzkVbGWaT.png' alt='EAfrc6dzkVbGWaT'/>

When it sign in with wrong username or password, it will display an alert to notify user it has wrong username or password.

<img src='https://i.loli.net/2020/11/05/RsQorqTLCEMVWZa.png' alt='RsQorqTLCEMVWZa'/>

When it sign up with different username not exists in the database, it will display an alert to notify sign up successfully.

<img src='https://i.loli.net/2020/11/05/FMrwN2eBJaX9EC5.png' alt='FMrwN2eBJaX9EC5'/>

### Display Users

The user management window is only available for owner users. If current user is not owner, it will display an alert to notify current user have no access to this feature.

<img src='https://i.loli.net/2020/11/05/y7VEQN8AOe9Fkgd.png' alt='y7VEQN8AOe9Fkgd'/>

There is a table of all the users that have been registered in the system. Besides, there are three more operations, add or change or remove users, that owner have access.

<img src='https://i.loli.net/2020/11/05/bY89vVtRzAmnKUP.png' alt='bY89vVtRzAmnKUP'/>

### Add Users

<img src='https://i.loli.net/2020/11/05/7lRXZSmgdse4fCx.png' alt='7lRXZSmgdse4fCx'/>

The owner can input new username, password and user type, then click on `Add` button. An alert box will notify it is successfully added and the table will update accordingly.

<img src='https://i.loli.net/2020/11/05/hnmqVSKLUQRz8DW.png' alt='hnmqVSKLUQRz8DW'/>

### Change Users

<img src='https://i.loli.net/2020/11/05/81KnGjpJzLxq3Ce.png' alt='81KnGjpJzLxq3Ce'/>

The owner first need to select which users he want to change, then write the corresponding username, password and user type again, then click on `Change` button. The table will automatically change the data inside.

<img src='https://i.loli.net/2020/11/05/sCvueUbHEmjFIVp.png' alt='sCvueUbHEmjFIVp'/>

### Remove Users

<img src='https://i.loli.net/2020/11/05/KjzosDu1Y8rxHPa.png' alt='KjzosDu1Y8rxHPa'/>

The owner can select which users he want to remove, then click on `Remove` button. Then table will automatically remove the row with the user that have just removed.

<img src='https://i.loli.net/2020/11/05/6EnXNj3FIrbADdT.png' alt='6EnXNj3FIrbADdT'/>

### Display Cash

<img src='https://i.loli.net/2020/11/05/12kCufNReFHyr8o.png' alt='12kCufNReFHyr8o'/>

The table can display all the amount of cash in the machine. after click on `Manage Cash` Button.

# Sprint Artifacts

## Product Backlog

The product backlog contains a set of all features and sub-features to build the vending machine, including functions, requirements, enhancements and fixes identified from previous sprints. The items in product backlog are ordered by priority and constantly changed. Any additional requirements from client should be directly added to product backlog instead of sprint backlog.

<mark>TODD</mark>

![CleanShot 2020-11-06 at 12.58.43@2x](https://i.loli.net/2020/11/06/MJkeKuVYPNHUq19.png)

If you can't see this table clearly, please refer to this [link](https://docs.google.com/spreadsheets/d/1bW-7sIUqPr8Pr3uHav8PKmb57BtOSc3_Uw_Vm2mQ-A8/edit?usp=sharing) (google sheet).

## Sprint Goal

   - Implement the code structure for `Product` and `Transaction`
   - Implement the product management system
   - Complete the cash management system
   - The customer can select products they want to purchase, see the shopping cart and shopping history
   - Implement the cash payment system

## User Stories and Sprint Backlog

The sprint backlog contains a set of items selected for this sprint. It is considered as a plan for delivering the product increment and realize the sprint goal. It is visible to anyone and to be added or modified by the development team.

<mark>TODD</mark>

## Burndown Chart

<mark>TODD</mark>

## Storyboard

<mark>TODD</mark>

## Scrum Meetings

### Meeting 1 (Sprint Planning)

- Date: 7th November
- Attendance: Cheng Chen. Yuanqun Wang, Yufei Zuo, Zexuan Long, Songyin Li, Zehui Lin
- Content:
  - Identify the Sprint Goal
  - Discuss the data structure of product and transaction
  - Implement the codebase together
  - Refine, prioritize and assign tasks to team members
  - Produce the Sprint Backlog

### Meeting 2

- Date : 10th November
- Attendance: Cheng Chen. Yuanqun Wang, Yufei Zuo, Zexuan Long, Songyin Li, Zehui Lin
- Content:
  - Emphasize the naming conventions of methods.
  - Discussion the GUI of transaction.
  - Review the progress of project.
- Cheng Chen:
  - What did I do yesterday: Finished write code for logic part of `Product` and `Transaction`.
  - What I will do today: Working on finalize storyboard and fix bugs.
  - Do I see any obstacles: None at the moment.
- Yuanqun Wang:
  - What did I do yesterday: Finished shopping cart GUI.
  - What I will do today: Working on modifying feature of shopping cart.
  - Do I see any obstacles: None at the moment.
- Yufei Zuo:
  - What did I do yesterday: Finished GUI of add and remove product.
  - What I will do today: Working on GUI of change product.
  - Do I see any obstacles: None at the moment.
- Zexuan Long:
  - What did I do yesterday: Finish product options on main window and write tests for logic part.
  - What I will do today: Continues write more tests.
  - Do I see any obstacles: None at the moment.
- Songyin Li:
  - What did I do yesterday: Finished GUI of changing amount of notes.
  - What I will do today: Continues fixing bugs.
  - Do I see any obstacles: None at the moment.
- Zehui Lin:
  - What did I do yesterday: Working on GUI of cash payment.
  - What I will do today: Continues working on cash payment GUI.
  - Do I see any obstacles: None at the moment.

### Meeting 3

- Date : 12th November
- Attendance: Cheng Chen. Yuanqun Wang, Yufei Zuo, Zexuan Long, Songyin Li, Zehui Lin
- Content:
  - Discussion about the progress of the sprint
  - Report any bugs of current application
  - Determine the content during demo with client
- Cheng Chen:
  - What did I do yesterday: <mark>todo</mark>
  - What I will do today: <mark>todo</mark>
  - Do I see any obstacles: None at the moment.
- Yuanqun Wang:
  - What did I do yesterday: Finished remove product in shopping cart.
  - What I will do today: Working on changing product quantity in shopping cart.
  - Do I see any obstacles: None at the moment.
- Yufei Zuo:
  - What did I do yesterday: Proofreading the report.
  - What I will do today: Fix bugs in application.
  - Do I see any obstacles: None at the moment.
- Zexuan Long:
  - What did I do yesterday: <mark>todo</mark>.
  - What I will do today: <mark>todo</mark>.
  - Do I see any obstacles: None at the moment.
- Songyin Li:
  - What did I do yesterday: Working on demo report
  - What I will do today: Continue working on demo report.
  - Do I see any obstacles: None at the moment.
- Zehui Lin:
  - What did I do yesterday: Finished table display of input notes.
  - What I will do today: Working on table display of changes notes.
  - Do I see any obstacles: The table is not display correctly.

## Sprint Review

### Client Feedback

<mark>TODD</mark>

Our client is satisfied with output of current sprint. However, client pointed out that the product backlog were not associated with corresponding user stories well. There should be more description and accepted criteria for each task.

- What has been done?
  - The database that stores users and cashes.
  - The user is able to log in and log out.
  - The user can log in automatically after sign up.
  - The owner can add, change or remove users.
  - A table of users can be displayed.
  - A table of numbers of cashes can be displayed.
- What has not been done?
  - The cashier and owner being able to set the cash is not yet implemented.
  - The feature that calculates the specific changes is not yet implemented.

### Revisited Product Backlog

<mark>TODD</mark>

The tasks have not created carefully and detailed at the Sprint Planning, thus it will cause much conflict later when a new feature should be added. To improve this, we should spend more time on user stories and create tasks for each user stories while planning next sprint.

Besides, the tasks were not prioritized at the beginning, so that it will extend the duration of other tasks if one task that should have higher priority is not implemented yet in time. And the teammate have to wait for that task being completed. In the next sprint, all the tasks should be prioritized first, and teammates aim to finish tasks with higher priority first.

Also, we assigned some tasks to teammates that are not good at that. For example, some teammates are not familiar with `Javafx` but we asked them to complete an GUI task. In the next sprint, we should consider the ability of each teammate and ask them to do what they are good at to improve the efficiency.

## Sprint Retrospective

<mark>TODD</mark>

- Date: 5th November
- What went well during the Sprint?
  - Completed all the tasks on time.
  - Worked on different features on different branches by different teammate well.
  - All agile tools and practices were utilized.
  - The communication among teammate was efficient so that we can adjust our tasks in time during the progress of the development.
- What can be improved in the future?
  - More description and acceptance criteria of each task.
  - Associate tasks with corresponding user stories.
  - Prioritize all the tasks at the beginning.
  - Assign tasks to the right teammates.
