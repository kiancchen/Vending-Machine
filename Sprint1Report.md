- [Scrum Teams](#scrum-teams)
  - [Roles](#roles)
  - [Individual Contribution](#individual-contribution)
- [Agile Development Tools and Practices](#agile-development-tools-and-practices)
  - [Github](#github)
    - [Workflow](#workflow)
    - [Branch History](#branch-history)
    - [Pull Request History](#pull-request-history)
    - [Conflict Issues History](#conflict-issues-history)
    - [Release History](#release-history)
  - [Gradle](#gradle)
    - [Gradle Command](#gradle-command)
    - [`build.gradle` Files](#buildgradle-files)
  - [JUnit and Jacoco](#junit-and-jacoco)
    - [How to Check Test Report](#how-to-check-test-report)
      - [JUnit](#junit)
      - [Jacoco](#jacoco)
    - [Quality of Testing](#quality-of-testing)
  - [Jenkins](#jenkins)
    - [Initial Setup](#initial-setup)
      - [Plugins in Jenkins](#plugins-in-jenkins)
      - [Setup GitHub in Jenkins](#setup-github-in-jenkins)
      - [Setup Gradle in Jenkins](#setup-gradle-in-jenkins)
      - [Setup JUnit in Jenkins](#setup-junit-in-jenkins)
      - [Setup Jacoco in Jenkins](#setup-jacoco-in-jenkins)
      - [Host Jenkins on internet and Connect to Github](#host-jenkins-on-internet-and-connect-to-github)
    - [Outstanding Example](#outstanding-example)
      - [Failure Build Example](#failure-build-example)
      - [Success Build Example](#success-build-example)
    - [Adopted CI Practices](#adopted-ci-practices)
- [Application Development](#application-development)
  - [UML Class Diagram](#uml-class-diagram)
  - [Class Document](#class-document)
  - [Demo](#demo)
- [Sprint Artifacts](#sprint-artifacts)
  - [Sprint Goal](#sprint-goal)
  - [Tasks Board](#tasks-board)
    - [Product Backlog](#product-backlog)
    - [Sprint Backlog](#sprint-backlog)
  - [User Stories](#user-stories)
  - [Scrum Meetings](#scrum-meetings)
    - [Meeting 1 (Sprint Planning)](#meeting-1-sprint-planning)
    - [Meeting 2](#meeting-2)
    - [Meeting 3](#meeting-3)
  - [Sprint Review](#sprint-review)
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
    - Record product backlog items and order it
    - Optimize the value of the work
    - Ensure transparency and  clarity of the product backlog

3. Developer1: Yuanqun Wang
4. Developer2: Zexuan Long
5. Developer3: Songyin Li
6. Developer4: Zehui Lin

## Individual Contribution

1. Cheng Chen (cche7436):
2. Yuanqun Wang (ywan3184):
3. Yufei Zuo (yzuo4982):
4. Zexuan Long (zlon4018):
5. Songyin Li (soli3733):
6. Zehui Lin (zlin3338):

# Agile Development Tools and Practices

## Github

### Workflow

1. After clone/pull the latest version of application, create a new a **branch** other than local **master** branch to make changes for new features or bug fixes.

   - Name convention

      - `feat-xxx` for new features

      - `fix-xxx` for bug fixes

      - `report-xxx` for the report

   - Using `git checkout -b feat-testing` to create a `feat-testing` branch and checkout to this branch at the same time. `git switch master` could also be used to switch to other branches.

      ![4gJKLn5UqFGdpBO](https://i.loli.net/2020/09/30/4gJKLn5UqFGdpBO.png)

2. Write your code in `feat-xxx` or `fix-xxx` branch **only** and test it **on local** if you completed a single feature. **DO NOT PUSH NOT WORKING APPLICATION**. If fails to build or test, fix it and test again.

3. If it passes local build or test and ready to push to remote repository, using `git pull origin master` to **get the latest version of project before pushing your own code** (your teammates may change it before you).

   - At first, git will try to merge for you, if might appears the following image. If it happens, first type `i` and write your comment below, then click `esc` and type `:wq`.

      <img src='https://i.loli.net/2020/10/03/PbS1L8ImVE7ioJp.png' alt='PbS1L8ImVE7ioJp'/>

   - If git cannot automatically merge for you, it will list all the conflict file.

      - The conflict files could be `(content)`, so that to resolve this conflict, the conflict content should be chosen between two version.

         <img src='https://i.loli.net/2020/10/03/RAJyf82iEpF1Tlt.png' alt='RAJyf82iEpF1Tlt' style='width:70%'/>

         The content in conflict files:

         <img src='https://i.loli.net/2020/10/03/BEPnde37zjMH2Ow.png' alt='BEPnde37zjMH2Ow' style="width:80%"/>

      - The conflict files could be `(modifiy/delete)`, this means you have add more files in local repository, so you need to nothing.

         <img src='https://i.loli.net/2020/10/03/xgDs9Yz1Mo7jlA6.png' alt='xgDs9Yz1Mo7jlA6' style='width:70%'/>

4. Push the `feat-xxx` or `fix-xxx` branch to rempte repository with appropriate comments.
   ```
   git add xxx
   git commit -m "consice message"
   git push origin feat-xxx
   ```

   ![bzhJfO4wuvoYAr5](https://i.loli.net/2020/09/30/bzhJfO4wuvoYAr5.png)

5. Open a new pull request by clicking on `Compare & pull request` on Github's repository page on the top right corner. Write more details about what you have changed.

   ![zJikeRHTANL2OYf](https://i.loli.net/2020/09/30/zJikeRHTANL2OYf.png)
   ![QmTd5S3bRzHMsnJ](https://i.loli.net/2020/09/30/QmTd5S3bRzHMsnJ.png)

6. Then the code reviewer (your teammates) will review your code and determine whether merge into the master branch or not. If cannot merge into master, please write down some reasons about why cannot merge into the master branch.

   <img src='https://i.loli.net/2020/09/30/ps3QRDhNfJOKXTa.png' alt='' style="width:60%;"/>

7. After merging `feat-xxx` or `fix-xxx` branch to `master`branch, delete `feat-xxx` or `fix-xxx` branch on GitHub repository.

   <img src='https://i.loli.net/2020/09/30/ehUi5Rtgcs9dm64.png' alt='' style="width:60%;"/>

8. Meanwhile, Jenkin will automatically build and run unit test and coverage test for you.

   - A new commit is building in `build history`

      <img src='https://i.loli.net/2020/09/30/sD79dcwSPvLT4Ar.png' alt='' style="width:60%;"/>

   - Click on the that build, you will see commit message, commit id, JUnit test and Jacoco coverage test

      ![EXGvkQheW2PuBlj](https://i.loli.net/2020/09/30/EXGvkQheW2PuBlj.png)

9. At last, using `git pull  orign master` again and delete the `feat-xxx` or `fix-xxx` branch locally using `git branch -d feat-xxx`.

   <img src='https://i.loli.net/2020/10/03/aoipLxsVlRQ5CtT.png' alt='aoipLxsVlRQ5CtT' style="width:80%"/>

   - Otherwise, you may encounter the following issue

      <img src='https://i.loli.net/2020/10/03/fGRNOkm7de6pjaI.png' alt='fGRNOkm7de6pjaI'/>

### Branch History

Todo (直接抄上一个asm，换上这次asm的配图)

### Pull Request History

Todo (直接抄上一个asm，换上这次asm的配图)

### Conflict Issues History

Todo (直接抄上一个asm，换上这次asm的配图)

### Release History

Todo (每次sprint提交前再提交release)

## Gradle

### Gradle Command

Todo (直接抄上一个asm，换上这次asm的配图)

### `build.gradle` Files

Todo (直接抄上一个asm，换上这次asm的配图)

## JUnit and Jacoco

### How to Check Test Report

#### JUnit

Todo (直接抄上一个asm，换上这次asm的配图)

#### Jacoco

Todo (直接抄上一个asm，换上这次asm的配图)

### Quality of Testing

## Jenkins

### Initial Setup

#### Plugins in Jenkins
1.  Go to `Jenkins > Manage Jenkins > Manage Plugins > Download`, and ensure the following plugins have been installed. *Notes: Jenkins requires to restart after adding new plugins.*

       - JUnit
       - Gradle
       - Github
       - Github Integration
       - Jacoco
2. Create a new project for `Vending Machine`. First go to `Jenkins > New Item`, write the project name at `Enter an iterm name` and select `Freestyle project`.

#### Setup GitHub in Jenkins

1. Go to `Jenkins > Manage Jenkins > Configure System > GitHub Enterprise Servers`, write `https://github.sydney.edu.au/api/v3` at API Endpoint and set name to be `Github Sydney`.

   <img src='https://i.loli.net/2020/09/25/kxcrwBy1qiSaXtH.png' alt='kxcrwBy1qiSaXtH'/>

2. Go to `Currency Converter > Configure > Source Code Management` and select `Git`. Then add repository URL to `Repository URL` and set Credentials to one of group members.

   <img src='https://i.loli.net/2020/09/25/HXEIaKs1btfF3kQ.png' alt='HXEIaKs1btfF3kQ'/>

3. Go to `Currency Converter > Configure > Build Triggers` and select `Github hook trigger for GITScm polling`.

   <img src='https://i.loli.net/2020/09/30/DWOIpLk1M57ZTzr.png' alt='DWOIpLk1M57ZTzr' style="width:50%"/>

#### Setup Gradle in Jenkins

1.  Go to `Jenkins > Manage Jenkins > Global Tool Configuration > Gradle > Gradle Installation`. Then set gradle name with `gradle_build` and select `Gradle 6.7-rc-1` as Gradle.org Version.

   <img src='https://i.loli.net/2020/09/25/nhRTYzqfLpA1urW.png' alt='nhRTYzqfLpA1urW' />

2. Go to `Currency Converter > Configure > Build > Add build step`, select `Invoke Gradle script`.

   <img src='https://i.loli.net/2020/09/30/R8A4StWlFxQfcaG.png' alt='R8A4StWlFxQfcaG' style="width:50%"/>

3. Then select `Invoke Gradle` with Gradle Version to be `gradle_build` and set `Tasks` to be `clean build test`.

   <img src='https://i.loli.net/2020/09/30/k3vAD6g9h1URcBH.png' alt='k3vAD6g9h1URcBH' style="width:50%"/>

#### Setup JUnit in Jenkins

1. Go to `Currency Converter > Configure > Post-build Actions` and select `Publish JUnit test result report`.

   <img src='https://i.loli.net/2020/09/25/qEydhIY8gr6JHUS.png' alt='qEydhIY8gr6JHUS' style="width:50%"/>

2. Then set `**/test-results/**/*.xml` at `Test report XMLs`.

   <img src='https://i.loli.net/2020/09/30/1ELOifHtyD4plr8.png' alt='1ELOifHtyD4plr8'/>

#### Setup Jacoco in Jenkins

1. Go to `Currency Converter > Configure > Post-build Actions` and select `Record JaCoCo coverage report`.

   <img src='https://i.loli.net/2020/09/25/grLqx4UbiAZsJly.png' alt='grLqx4UbiAZsJly' style="width:50%"/>


#### Host Jenkins on internet and Connect to Github

1. Go to [GCP](https://console.cloud.google.com/), create a cluster under `Kubernetes Engine`, then click `Application` and search `Jenkins`. Then you should find this:

   <img src='https://i.loli.net/2020/10/06/GWMqYL2DzNixIRP.png' alt='' style="width:80%"/>

2. Deploy this application and then you can see the address.

   <img src='https://i.loli.net/2020/10/06/I5Bat4inL8UVdxP.png' alt='' style="width:80%"/>

3. Append `/github-webhook/` to the Jenkins address, which is `http://35.244.146.248/github-webhook/`

5. Go to `Github > Settings > Hooks > Add webhook` and add this link at `Payload URL`, e.g. `https://<hostname>/github-webhook/`. Then select `application/json` as `Content Type`.

   <img src='https://i.loli.net/2020/10/06/W2Nn1ZY8beXvxHl.png' alt='' style="width:80%"/>

### Outstanding Example

#### Failure Build Example

Todo (直接抄上一个asm，换上这次asm的配图)

#### Success Build Example

Todo (直接抄上一个asm，换上这次asm的配图)

### Adopted CI Practices

Todo (直接抄上一个asm，换上这次asm的配图)

# Application Development

## UML Class Diagram

Todo

## Class Document

Todo (按照java的class写heading)

## Demo

Todo (按照功能写heading)

# Sprint Artifacts

## Sprint Goal

   - Have initials codebase with appropriate methods to implement basic functionality
   - Implement the login system with different type of users.
   - Implement the user management system so that owner could change manages all the users.
   - Implement the cash system so that it will automatically calculate the change.
   - Implement the database to record the users and cash within the machine.

## Tasks Board

### Product Backlog

The product backlog contains a set of all features and sub-features to build the vending machine, including functions, requirements, enhancements and fixes identified from previous sprints. The items in product backlog are ordered by priority and constantly changed. Any additional requirements from client should be directly added to product backlog instead of sprint backlog.

|             Product Backlog             	|      Role     	| Story Points 	|
|:---------------------------------------:	|:-------------:	|:------------:	|
|           Application codebase          	| Collaborative 	|       5      	|
|      Select variety types of snacks     	|      N/A      	|       5      	|
| Customer can cancel transaction anytime 	|      N/A      	|       3      	|

Todo (现在先不用写，交之前把jira上的task抄上去就行了)

### Sprint Backlog

The sprint backlog contains a set of items selected for this sprint. It is considered as a plan for delivering the product increment and realize the sprint goal. It is visible to anyone and to be added or modified by the development team.

|             Product Backlog             	|      Role     	| Story Points 	| Finished 	|
|:---------------------------------------:	|:-------------:	|:------------:	|----------	|
|           Application codebase          	| Collaborative 	|       5      	| √        	|
| Setup database that is able to save data to file | Cheng Chen |       5     	| √       	|
| Login system is able to sign in, sign up 	|      Zexuan Long     	|       4      	| V        	|

Todo (现在先不用写，交之前把jira上的task抄上去就行了)

## User Stories

| User Story                                                   | Acceptance Criteria                                          |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| As a machine user, I want to sign up an accout, so that I can have my own privacy | The system needs to verify that the username is exists or not.<br>The System needs to ask user to enterr username and passwor.<br>In case the user enters an exists userrname then the system asks to try again or return to main window. |
| As a machine user, I want to sign in own account, so that I can do transactions base on the records. | The system needs to verify that the username exists and that the password is correct. <br/>The system needs to ask user to enter username and password.<br/>In case the user enters a wrong username or password then the system displays wrong message and back to login window. |
| As a machine user except anonymous users, I want to log out own account, so that I can protect own privacy and provide use to other users. | The system automatically provides the logout button when the user logs in.<br/>In case the user clicks the logout button, the system changes to anonymous user, backs to main window and displays last five products. |
| As an owner, I want to add users except anonymous users, so that the users I added can sign in immediately without signing up. | Owner need to be logged in before adding users.<br/>The system asks owner to either enter username and password and or choose a user type from a ComboBox.<br/>In case the owner adds successfully, new user information displays on table. |
| As an owner, I want to remove users except anonymous users, so that the user I removed needs to sign up a new account. | Owner need to be logged in before removing users.<br/>The system asks owner to choose a user to remove from a ComboBox.<br/>In case the owner removes successfully, the removed user information disappears on table. |
| As an owner, I want to change user information except anonymous users, so that the user I changed needs new login information when login or have different permissions. | Owner need to be logged in before changing users.<br>The system asks owner to choose a user to change from a ComboBox, or enter new username or password, or choose a new user type from a ComboBox.<br>In case the owner changes successfully, new user information displays on table. |
|                                                              |                                                              |


## Scrum Meetings

### Meeting 1 (Sprint Planning)

<img src='https://i.loli.net/2020/11/04/OBaT23sQecx9Cz4.png' alt='OBaT23sQecx9Cz4'/>

- Date: 30th October
- Attendance: Cheng Chen. Yuanqun Wang, Yufei Zuo, Zexuan Long, Songyin Li, Zehui Lin
- Content:
  - Identify the Sprint Goal
  - Discuss the data structure of users
  - Implement the codebase together
  - Refine, prioritize and assign tasks to team members
  - Produce the Sprint Backlog

### Meeting 2

- Date : 3rd November
- Attendance: Cheng Chen. Yuanqun Wang, Yufei Zuo, Zexuan Long, Songyin Li, Zehui Lin
- Content:
  - Online debug the obstacle in the code.
  - Discussion about the progress of each tasks.
  - Discussion about the code base of cash processor, and modified the map with enum to support double.
- Cheng Chen:
  - What did I do yesterday: Finished data handling of users and review pull request.
  - What I will do today: Working on data handling of cash.
  - Do I see any obstacles: None at the moment.
- Yuanqun Wang:
  - What did I do yesterday: Finished part of user management GUI.
  - What I will do today: Working on table on user management GUI.
  - Do I see any obstacles: None at the moment.
- Yufei Zuo:
  - What did I do yesterday: Finished GUI of main window.
  - What I will do today: Working on GUI of user management.
  - Do I see any obstacles: None at the moment.
- Zexuan Long:
  - What did I do yesterday: Finished logic of login system and its tests.
  - What I will do today: Continues fix the code and write more tests.
  - Do I see any obstacles: None at the moment.
- Songyin Li:
  - What did I do yesterday: Finished part of cash system.
  - What I will do today: Working on the logic of cash system.
  - Do I see any obstacles: None at the moment.
- Zehui Lin:
  - What did I do yesterday: Finished GUI of login system.
  - What I will do today: Working on the user stories and UML Class Diagram.
  - Do I see any obstacles: The code written by teammates does not satisfy the functionality of my part of code, so I need to negotiate with him.

### Meeting 3

- Date : 5th November
- Attendance: Cheng Chen. Yuanqun Wang, Yufei Zuo, Zexuan Long, Songyin Li, Zehui Lin
- Content:
  - Emphasize that if you meets the situation when a specific method is required, but has not been implemented, you should write yourself, and then notify the teammates who is responsible for that part, to add more test cases.
  - Discussion about the progression of each tasks.
  - Emphasize the important of burndown chart and change the status of each task as soon as possible.
- Cheng Chen:
  - What did I do yesterday: Modified some features of the code to looks nicer.
  - What I will do today: Continues working on refactor the code.
  - Do I see any obstacles: None at the moment.
- Yuanqun Wang:
  - What did I do yesterday: Finished add or remove user GUI
  - What I will do today: Working on change user GUI.
  - Do I see any obstacles: None at the moment.
- Yufei Zuo:
  - What did I do yesterday: Proofreading the report.
  - What I will do today: Continues proofreading the report.
  - Do I see any obstacles: None at the moment.
- Zexuan Long:
  - What did I do yesterday: Finished adding more test cases.
  - What I will do today: Writing report about class document.
  - Do I see any obstacles: None at the moment.
- Songyin Li:
  - What did I do yesterday: Finished part of cash system.
  - What I will do today: Working on GUI of cash system.
  - Do I see any obstacles: None at the moment.
- Zehui Lin:
  - What did I do yesterday: Finished UML class diagram.
  - What I will do today: Working on the user stories.
  - Do I see any obstacles: None at the moment.

## Sprint Review

## Sprint Retrospective
