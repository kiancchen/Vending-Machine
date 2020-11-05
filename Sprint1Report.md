- [Scrum Teams](#scrum-teams)
  - [Roles](#roles)
  - [Individual Contribution](#individual-contribution)
- [Agile Development Tools and Practices](#agile-development-tools-and-practices)
  - [Github](#github)
    - [Workflow](#workflow)
    - [Branch](#branch)
      - [Issues of Branches](#issues-of-branches)
    - [Pull Request](#pull-request)
      - [Pull request history](#pull-request-history)
    - [Conflict Issues History](#conflict-issues-history)
    - [Release History](#release-history)
  - [Gradle](#gradle)
    - [Gradle Command](#gradle-command)
    - [`build.gradle` Files](#buildgradle-files)
  - [Build.Gradle File](#buildgradle-file)
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
  - [Demo](#demo)
    - [Sign In or Sign Up](#sign-in-or-sign-up)
    - [Display Users](#display-users)
    - [Add Users](#add-users)
    - [Change Users](#change-users)
    - [Remove Users](#remove-users)
    - [Display Cash](#display-cash)
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

   - Agile tools setup and their report
   - Storyboard
   - User data structure
   - User database handler
   - Cash database handler
   - Calculating changes in cash functionality
2. Yuanqun Wang (ywan3184):
   - User management GUI
3. Yufei Zuo (yzuo4982):
   - Demonstration
4. Zexuan Long (zlon4018):
   - Logic of sign in and sign up, and their tests
5. Songyin Li (soli3733):
   - A table shows the cash information
6. Zehui Lin (zlin3338):
   - User Story
   - UML
   - Sign out logic
   - Login system GUI

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

![CleanShot 2020-11-05 at 20.34.32@2x](https://i.loli.net/2020/11/05/Q2K8Px1IoRGmJte.png)

This is the version that's submmitted as the product at the end of Sprint 1.

You can use the account `blan` with password `123` to test functionalities.

Note if you run tests, the database will be restored to the initial state and all changed you made will lose (Note `gradle build` will run the tests automatically).

## Gradle

## build.gradle File

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

## Gradle command

- `gradle clean`: clean the output of last build

![CleanShot 2020-11-05 at 20.40.15@2x](https://i.loli.net/2020/11/05/syY1nRcxvMGuPgw.png)

- `gradle build`: build the program, link dependencies. Note this will run tests automatically.

![CleanShot 2020-11-05 at 20.40.31@2x](https://i.loli.net/2020/11/05/To65muQyeWj9Hgw.png)

- `gradle run`: run the application. This will invoke the GUI.

![CleanShot 2020-11-05 at 20.40.46@2x](https://i.loli.net/2020/11/05/ixNRZ718k2Idl9J.png)

- `gradle test`: run all tests using `Junit`

![CleanShot 2020-11-05 at 20.41.13@2x](https://i.loli.net/2020/11/05/CMnuR3BArog8ycW.png)

- `gradle test jacocoTestReport`: run all tests and produce a coverage report by `Jacoco`

![CleanShot 2020-11-05 at 20.41.40@2x](https://i.loli.net/2020/11/05/holF2VZ7HwyMakP.png)

Then you can see the report in `build/jacocoHtml/index.html`

![CleanShot 2020-11-05 at 20.43.03@2x](https://i.loli.net/2020/11/05/9R5tu7OZLBHQleX.png)

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

![](https://i.loli.net/2020/11/05/5YWy9euDxbim4wg.png)

- `Deleted` page: delete this build

![CleanShot 2020-11-05 at 18.50.55@2x](https://i.loli.net/2020/11/05/hsi84zkYC6SL3mf.png)

- `Polling Log` page: This page captures the polling log that triggered this build.

![CleanShot 2020-11-05 at 18.51.03@2x](https://i.loli.net/2020/11/05/fDNub37Scy2P9Ev.png)

- `Git Build Data` page: This page display the git revision and the branches has been build.

![CleanShot 2020-11-05 at 18.51.20@2x](https://i.loli.net/2020/11/05/EKzW8OI7FbovXn6.png)

#### Success Build Example

![CleanShot 2020-11-05 at 18.53.50@2x](https://i.loli.net/2020/11/05/8CbHy6DJfpWmagd.png)

`Build #48` is an examples of successful build.

- In the `Status` page, it briefly described some information include any changes in this build,why Jenkins started this built based on that change and git revision about which branch is associated with. The difference between these builds and failure one is that these also have test result and Jacoco - Overall Coverage Summary
- In the Test Result page, you can see the Junit test report

![CleanShot 2020-11-05 at 18.56.20@2x](https://i.loli.net/2020/11/05/GsC7if4xaZpUkOu.png)

- In the Coverage Report page, you can see the Jacoco test coverage report

![CleanShot 2020-11-05 at 18.56.41@2x](https://i.loli.net/2020/11/05/egBiy3YHQVpFN5R.png)

### Adopted CI Practices

We maintained two projects on Jenkins, one is for master branch only, and the other one is for all branches.

![CleanShot 2020-11-05 at 18.59.06@2x](https://i.loli.net/2020/11/05/hSyJTR2riaMlzBG.png)

- `Vending Machine - All branches` builds and tests all branches. It's convenient for each teammate to check if their code works and determine whether to create a pull request or not.
- `Vending Machine - Master Branch` only builds and tests master branch. It's used to test the integrated code on master branch, which maybe released later as a working program.

# Application Development

## UML Class Diagram

<img src='https://i.loli.net/2020/11/05/wkTBlMWon2xdCa7.png' alt='wkTBlMWon2xdCa7'/>

## Demo

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

| Product Backlog                         | Role          | Story Points |
| --------------------------------------- | ------------- | ------------ |
| Application codebase                    | Collaborative | 5            |
| Select variety types of snacks          | N/A           | 5            |
| Customer can cancel transaction anytime | N/A           | 3            |

## Sprint Goal

   - Have initial codebase with appropriate methods and data structure to implement basic functionality
   - Implement the login system with different roles of users.
   - Implement the user management system so that owner could manages all users.
   - Implement the cash system so that it will automatically calculate the change. And the owner and cashier can change the number of cashes.
   - Implement the database handler to save the users and cash data to files.

## User Stories and Sprint Backlog

The sprint backlog contains a set of items selected for this sprint. It is considered as a plan for delivering the product increment and realize the sprint goal. It is visible to anyone and to be added or modified by the development team.

| User Story                                                   | Acceptance Criteria                                          | Backlog                                                      | Story Points  |
| ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------- |
| As a user, <br/>I want to sign up an account, <br/>so that I can have my own data | The system needs to verify that the username is exists or not.<br>The System needs to ask user to enterr username and passwor.<br>In case the user enters an existed username then the system asks to try again.<br/>The  system should login automatically after the user signs up. | Check if the user exists in the database.<br/>The GUI of the sign up system.<br/>The user database. | 1<br/>2<br/>3 |
| As a user, <br/>I want to sign in own account, <br/>so that I can do transactions base on the records. | The system needs to verify that the username exists and the password is correct. <br/>The system needs to ask user to enter username and password.<br/>In case the user enters a wrong username or password then the system should warn the user. | Check if the user exists in the database.<br/>The GUI of the sign in system. | 1<br/>2<br/>  |
| As a user,<br/>I want to log out own account, <br/>so that I can protect own privacy and provide use to other users. | The system automatically provides the logout button when the user logs in.<br/>In case the user clicks the logout button, the system changes to the anonymous user. | Switch the current user to the anonymous<br/>                | 1<br/>        |
| As an owner, <br/>I want to see the information of all users, <br/>so that I can make changes on them. | The owner needs to login before adding users.<br/>A table of users should be displayed. | Get all users from the system<br/>A table shows the users.   | 2             |
| As an owner, <br/>I want to add users, <br/>so that the users I added can sign in. | The owner needs to login before adding users.<br/>The system asks owner to enter username and password and choose a user role.<br/>In case the owner adds successfully, new user information should be displayed on table. | Add a new user to the database<br/>Adding users GUI          | 1<br/>2<br/>  |
| As an owner, <br/>I want to remove users, <br/>so that the user I removed needs to sign up a new account. | The owner needs to login before removing users.<br/>The system asks owner to choose a user.<br/>In case the owner removes successfully, the removed user information disappears on table. | Remove an user from the database<br/>Removing users GUI      | 1<br/>2<br/>  |
| As an owner, <br/>I want to change user information, <br/>so that the user changed needs new account information to login or have different permissions. | The owner need to login before changing users.<br>The system asks the owner to choose a user to change,then enter new username or password, and choose a new user type.<br>In case the owner changes successfully, the new user information should be displayed on the table. | Change the information of an user from the database<br/>Changing users GUI | 1<br/>2<br/>  |
| As an owner or cashier,<br/>I want to see the information of all cashes, <br/>so that the I can change the number of cashes. | An owner or cashier should login.<br/>A table of cashes should be displayed. | Get all cash information from the system.<br/>A table shows the cash information | 1<br/>2       |

## Storyboard

### Main window

![CleanShot 2020-11-05 at 20.21.13@2x](https://i.loli.net/2020/11/05/jSvtx5oN2XfZyBe.png)

### User management window

![CleanShot 2020-11-05 at 20.24.32@2x](https://i.loli.net/2020/11/05/vxIn96oQieO2MBc.png)

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
  - What did I do yesterday: Review codes written by other teammates. Beeatify the code structure and remove unnecessary codes.
  - What I will do today: Continue working on refactoring the code.
  - Do I see any obstacles: None at the moment.
- Yuanqun Wang:
  - What did I do yesterday: Finished adding or removing user GUI
  - What I will do today: Working on changing user information GUI.
  - Do I see any obstacles: None at the moment.
- Yufei Zuo:
  - What did I do yesterday: Proofreading the report.
  - What I will do today: Continue proofreading the report.
  - Do I see any obstacles: None at the moment.
- Zexuan Long:
  - What did I do yesterday: Finished adding more test cases.
  - What I will do today: Writing report about class document.
  - Do I see any obstacles: None at the moment.
- Songyin Li:
  - What did I do yesterday: GUI of cash system
  - What I will do today: Continue working on GUI of cash system.
  - Do I see any obstacles: None at the moment.
- Zehui Lin:
  - What did I do yesterday: Finished UML class diagram.
  - What I will do today: Working on the user stories.
  - Do I see any obstacles: None at the moment.

## Sprint Review

### Client Feedback

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

The tasks have not created carefully and detailedly at the Sprint Planning, thus it will cause much conflict later when a new feature should be added. To improve this, we should spend more time on user stories and create tasks for each user stories while planning next sprint.

Besides, the tasks were not prioritized at the beginning, so that it will extend the duration of other tasks if one task that should have higher priority is not implemented yet in time. And the teammate have to wait for that task being completed. In the next sprint, all the tasks should be prioritized first, and teammates aim to finish tasks with higher priority first.

Also, we assigned some tasks to teammates that are not good at that. For example, some teammates are not familar with `Javafx` but we asked them to complete an GUI task. In the next sprint, we should consider the ability of each teammate and ask them to do what they are good at to improve the efficiency.

## Sprint Retrospective

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
