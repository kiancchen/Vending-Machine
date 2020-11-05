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
    - [Sign In or Sign Up](#sign-in-or-sign-up)
    - [Display Users](#display-users)
    - [Add Users](#add-users)
    - [Change Users](#change-users)
    - [Remove Users](#remove-users)
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

   - Using `git checkout -b feat-testing` or `git switch -c feat-testing` to create a `feat-testing` branch and switch to this branch at the same time.

      ![CleanShot 2020-11-05 at 12.04.33@2x](https://i.loli.net/2020/11/05/TxgP7tNIcMRvjAd.png)

2. Write your code in `feat-xxx` or `fix-xxx` branch **only** and test it **on local** if you completed a single feature. **DO NOT PUSH NOT WORKING APPLICATION**. If fails to build or test, fix it and test again.

3. If build and test successfully on local and ready to push to remote repository, using `git pull origin master` to **get the latest version of project before pushing your own code** (your teammates may change it before you).

   - At first, git will try to merge for you, it might appears the following image. If it happens, first type `i` and write your comment below, then click `esc` and type `:wq`.

      ![CleanShot 2020-11-05 at 12.09.45@2x](https://i.loli.net/2020/11/05/R8WOF73Youec5dz.png)

   - If git cannot automatically merge for you, it will list all the conflict file.

      - The conflict files could be `(content)`, so that to resolve this conflict, the conflict content should be chosen between two version.

         <img src='https://i.loli.net/2020/10/03/RAJyf82iEpF1Tlt.png' alt='RAJyf82iEpF1Tlt' style='width:70%'/>

         The content in conflict files:

         <img src='https://i.loli.net/2020/10/03/BEPnde37zjMH2Ow.png' alt='BEPnde37zjMH2Ow' style="width:80%"/>

      - The conflict files could be `(modifiy/delete)`, this means you have add more files in local repository, so you need to nothing.

         <img src='https://i.loli.net/2020/10/03/xgDs9Yz1Mo7jlA6.png' alt='xgDs9Yz1Mo7jlA6' style='width:70%'/>

4. Push the `feat-xxx` or `fix-xxx` branch to rempte repository with appropriate comments.
   ```
   git status
   git add xxx
   git commit -m "consice message"
   git push origin feat-xxx
   ```

![CleanShot 2020-11-05 at 12.17.21@2x](https://i.loli.net/2020/11/05/bKjYHatNBFWx31C.png)

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

9. At last, using `git pull orign master` again and delete the merged branch locally using `git branch -d branch_name`.

   <img src='https://i.loli.net/2020/10/03/aoipLxsVlRQ5CtT.png' alt='aoipLxsVlRQ5CtT' style="width:80%"/>

   - Otherwise, you may encounter the following issue. Make sure you want to delete it. If so, use the flag `-D` to force to delete the branch.

      <img src='https://i.loli.net/2020/10/03/fGRNOkm7de6pjaI.png' alt='fGRNOkm7de6pjaI'/>

### Branch

- As name convention in the [Workflow](https://github.sydney.edu.au/SOFT2412-2020S2/R18B-Group5-CurrencyConverter#workflow)，we use `feat` as prefix with feature to name our branch. For example, as in the following picture, I am responsible to hide the password and show it as asterisk (\*), when I finish that part I push it and name as `feat-passwordField`

![CleanShot 2020-11-05 at 12.56.34@2x](https://i.loli.net/2020/11/05/1Uf3YzAuSQL5eq9.png)

And you can see all merged branches in the commit history

![CleanShot 2020-11-05 at 13.18.12@2x](https://i.loli.net/2020/11/05/bTVLmhANpk26HCZ.png)

#### Issues of Branches

Regarding the issues of `branch`, because we have wrote all the process of `branch` in the [Workflow](#workflow) part of the report first before we divided the code tasks, the team members would browse the report and easily get answer when they are confused about how to make branch or what is our name convention.

### Pull Request

1. Follow the process of [Workflow](#workflow), click the `Compare & pull request` button to create a pull request.

![CleanShot 2020-11-05 at 14.33.12@2x](https://i.loli.net/2020/11/05/1pYOnS5rC4QdVtK.png)

2. Then the code reviewer will review your code

   ![CleanShot 2020-11-05 at 14.34.54@2x](https://i.loli.net/2020/11/05/Eq5pSadHCJ19vIV.png)

   3. If the code has no problems, this branch will be merged to the master and deleted afterwards.

   ![CleanShot 2020-11-05 at 14.36.04@2x](https://i.loli.net/2020/11/05/JhwDembGWyIuFnK.png)

   4. Then you can see a new merging commit.

   ![CleanShot 2020-11-05 at 13.38.34@2x](https://i.loli.net/2020/11/05/SLMvdrHcNJeFQKg.png)

#### Pull request history

![CleanShot 2020-11-05 at 13.33.07@2x](https://i.loli.net/2020/11/05/3y7TrFtf1dDPYmU.png)

### Conflict Issues History

- The main issues we encountered during the project is that after pulling request, we will encounter conflicts during the merge process. Chen is the code reviewer for this project, so when there is a conflict, he will communicate with the committer to resolve the conflict.
- In the following picture, the new version submitted by Yufei conflicts with the version of the master. The system shows the conflict is this sentence `“The following sections of code has written by Cheng Chen:”` Then reviewer Chen communicated with the programmer Yufei to decide which version as the final one.

![CleanShot 2020-11-05 at 13.41.38@2x](https://i.loli.net/2020/11/05/H6JOjrmBIqD9QR7.png)

### Release History

Todo

## Gradle

### Gradle Command

Todo

### `build.gradle` Files

## Build.Gradle File

We have following content in this file

- `Plugins`

  We used four plugins to support gradle.

  - `java` : apply the java plugin to add support for Java
  - `application`: apply the application plugin to add support for building a CLI application.
  - `jacoco` :apply JaCoCo plugin ti provides code coverage metrics for Java code.
  - `javafx`: apply Javafx JavaFX to use highly portable modern hardware accelerated user interface to create Java applications.

  [![img](https://i.loli.net/2020/10/10/1vSf4oYT8nwjkze.png)](https://sm.ms/image/1vSf4oYT8nwjkze)

- `Repositories`

  Use jcenter for resolving dependencies and you can declare any Maven/Ivy/file repository here.

  [![img](https://i.loli.net/2020/10/10/89rhLdBKsi5qej1.png)](https://sm.ms/image/89rhLdBKsi5qej1)

- `Dependencies`

  This part declared for a Gradle project applies to a specific scope. Follow the order of `dependencies` from top to bottom. we applied to use application, `json` library, `junit `test framework

  ![CleanShot 2020-11-05 at 13.44.45@2x](https://i.loli.net/2020/11/05/69ZPvCUymMh1auT.png)

- Javafx

  We add two required modules, 

  - `javafx.controls` modules used for define UI controls, charts and appearances that can be used in the JavaFX UI toolbox.
  - `javafx.fxml` modules used for defines the FXML APIs for the JavaFX UI toolkit.

[![img](https://i.loli.net/2020/10/10/ApydPgaxe6Gt8zJ.png)](https://sm.ms/image/ApydPgaxe6Gt8zJ)

- `Jacoco and Jacoco Test Report`
  - We use jacoco with version 0.8.4, and will put report to the directory `coverge`.
  - For jacoco test report, we will put it into the directory `jacocoHtml`. We also have restriction for this report, both xml and csv formats are not enabled and we only allow html format.

[![img](https://i.loli.net/2020/10/10/K4GsbLVdkf3Ug2w.png)](https://sm.ms/image/K4GsbLVdkf3Ug2w)

- `application`

We define the main class `VendingMachine.App` for the application

![CleanShot 2020-11-05 at 13.46.21@2x](https://i.loli.net/2020/11/05/K7D1pdkN4QmBzXv.png)

## JUnit and Jacoco

### How to Check Test Report

#### JUnit

1. Choose a build
2. Click Test Result

![CleanShot 2020-11-05 at 13.51.31@2x](https://i.loli.net/2020/11/05/bzKTt5l9fi87dR6.png)

3. Click VendingMachine to see tests class by class.

![CleanShot 2020-11-05 at 13.51.47@2x](https://i.loli.net/2020/11/05/j7TBCWG3rMxuOIq.png)

4. Choose a class to see tested methods

![CleanShot 2020-11-05 at 13.52.42@2x](https://i.loli.net/2020/11/05/FvMC1qAfuwSnTgH.png)

#### Jacoco

1. Choose a build and click Coverage Report

![CleanShot 2020-11-05 at 13.53.55@2x](https://i.loli.net/2020/11/05/eGWjO3aIDxRpbuh.png)

2. Choose a package to see coverage of each class

![CleanShot 2020-11-05 at 13.56.06@2x](https://i.loli.net/2020/11/05/YLxoMSFEz8aBXJU.png)

3. Choose a method to see coverage of every method

![CleanShot 2020-11-05 at 13.56.47@2x](https://i.loli.net/2020/11/05/tVsGorXjBd1PgJc.png)

4. Below this you can see all statements that are tested or not. Red means it's not tested. Green means it's tested. Yellow means only part of branch is tested.

![CleanShot 2020-11-05 at 14.00.03@2x](https://i.loli.net/2020/11/05/S2EhbTsyYa7L3pI.png)

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

The system used to host Jenkins is MacOS.

1. Start the Jenkins on local by the command `brew services start jenkins`
2. Then you can visit Jenkins on local by http://localhost:8080/
3. To host it on internet, you need to download [ngrok](https://ngrok.com/). Then run it by `./ngrok http 8080`. You'll get a public URL to the Jenkins server.

![CleanShot 2020-11-05 at 14.04.52@2x](https://i.loli.net/2020/11/05/YBIwRxPmTja9fHz.png)

### Outstanding Example

During this process, we have successful and failed build output on jerkin, here are 4 examples selected from all the builds.

#### Failure Build Example

`Build #29` are an example of failure build.

- In the `Status` page, it briefly describes some information include any changes in this build,why jenkins started this built based on that change and commit id about which branch is associated with.

  ![CleanShot 2020-11-05 at 14.10.20@2x](https://i.loli.net/2020/11/05/PnsSEj6vJociZRX.png)

- In the `Changes` page, you can see the commit history of this branch

![CleanShot 2020-11-05 at 14.54.18@2x](https://i.loli.net/2020/11/05/EVDRoldSuNHnxXs.png)

- To find the error, go to `Console Output`

![CleanShot 2020-11-05 at 14.57.15@2x](https://i.loli.net/2020/11/05/S7hjcefsLmnbMgk.png)

- `Edit Built Information` page: make the display name and write description for that

- `Deleted` page: delete this build

- `Polling Log` page: This page captures the polling log that triggered this build.
- `Git Build Data` page: This page display the git revision and the branches has been build.

#### Success Build Example

Todo

### Adopted CI Practices

Todo

# Application Development

## UML Class Diagram

<img src='https://i.loli.net/2020/11/05/wkTBlMWon2xdCa7.png' alt='wkTBlMWon2xdCa7'/>

## Class Document

Todo (按照java的class写heading)

## Demo

### Sign In or Sign Up

At first the window will display the current user is `anonymous` by default.

<img src='https://i.loli.net/2020/11/05/H9WyOpgE41j8LGU.png' alt='H9WyOpgE41j8LGU'/>

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