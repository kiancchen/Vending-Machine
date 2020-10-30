- [Scrum Teams](#scrum-teams)
  - [Roles](#roles)
  - [Individual Contribution](#individual-contribution)
- [Agile Development Tools and Practices](#agile-development-tools-and-practices)
  - [Github](#github)
    - [Workflow](#workflow)
    - [Release](#release)
  - [Gradle](#gradle)
  - [JUnit](#junit)
  - [Jenkins](#jenkins)
    - [Plugins in Jenkins](#plugins-in-jenkins)
    - [Setup GitHub in Jenkins](#setup-github-in-jenkins)
    - [Setup Gradle in Jenkins](#setup-gradle-in-jenkins)
    - [Setup JUnit in Jenkins](#setup-junit-in-jenkins)
    - [Setup Jacoco in Jenkins](#setup-jacoco-in-jenkins)
    - [Host Jenkins on internet and Connect to Github](#host-jenkins-on-internet-and-connect-to-github)
- [Application Development](#application-development)
  - [UML Class Diagram](#uml-class-diagram)
  - [Class Document](#class-document)
  - [Demo](#demo)
- [Sprint 1 (starts week 9 and ends week 10)](#sprint-1-starts-week-9-and-ends-week-10)
  - [Sprint Planning](#sprint-planning)
    - [Sprint Goal](#sprint-goal)
    - [Sprint Task](#sprint-task)
  - [Tasks Board](#tasks-board)
    - [Sprint Backlog](#sprint-backlog)
    - [Product Backlog](#product-backlog)
  - [User Stories](#user-stories)
  - [Scrum Meetings](#scrum-meetings)
    - [Meeting 1](#meeting-1)
  - [Sprint Review](#sprint-review)
  - [Sprint Retrospective](#sprint-retrospective)
- [Sprint 2 (starts week 10 and ends week 11)](#sprint-2-starts-week-10-and-ends-week-11)
- [Sprint 3 (starts week 11 and ends week 12)](#sprint-3-starts-week-11-and-ends-week-12)

# Scrum Teams

## Roles

1. Product Owner: Yufei Zuo

    Responsible for:
    - maximize value of the product and the work
    - manage the product backlog
    - remove impediments

2. Scrum Master: Cheng Chen

    Responsible for:
    - record product backlog items and order it
    - optimize the value of the work
    - ensure transparency and  clarity of the product backlog

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
### Release

## Gradle

## JUnit

## Jenkins

### Plugins in Jenkins
1.  Go to `Jenkins > Manage Jenkins > Manage Plugins > Download`, and ensure the following plugins have been installed. *Notes: Jenkins requires to restart after adding new plugins.*

       - JUnit
       - Gradle
       - Github
       - Github Integration
       - Jacoco
2. Create a new project for `Vending Machine`. First go to `Jenkins > New Item`, write the project name at `Enter an iterm name` and select `Freestyle project`.

### Setup GitHub in Jenkins

1. Go to `Jenkins > Manage Jenkins > Configure System > GitHub Enterprise Servers`, write `https://github.sydney.edu.au/api/v3` at API Endpoint and set name to be `Github Sydney`.

   <img src='https://i.loli.net/2020/09/25/kxcrwBy1qiSaXtH.png' alt='kxcrwBy1qiSaXtH'/>

2. Go to `Currency Converter > Configure > Source Code Management` and select `Git`. Then add repository URL to `Repository URL` and set Credentials to one of group members.

   <img src='https://i.loli.net/2020/09/25/HXEIaKs1btfF3kQ.png' alt='HXEIaKs1btfF3kQ'/>

3. Go to `Currency Converter > Configure > Build Triggers` and select `Github hook trigger for GITScm polling`.

   <img src='https://i.loli.net/2020/09/30/DWOIpLk1M57ZTzr.png' alt='DWOIpLk1M57ZTzr' style="width:50%"/>

### Setup Gradle in Jenkins

1.  Go to `Jenkins > Manage Jenkins > Global Tool Configuration > Gradle > Gradle Installation`. Then set gradle name with `gradle_build` and select `Gradle 6.7-rc-1` as Gradle.org Version.

   <img src='https://i.loli.net/2020/09/25/nhRTYzqfLpA1urW.png' alt='nhRTYzqfLpA1urW' />

2. Go to `Currency Converter > Configure > Build > Add build step`, select `Invoke Gradle script`.

   <img src='https://i.loli.net/2020/09/30/R8A4StWlFxQfcaG.png' alt='R8A4StWlFxQfcaG' style="width:50%"/>

3. Then select `Invoke Gradle` with Gradle Version to be `gradle_build` and set `Tasks` to be `clean build test`.

   <img src='https://i.loli.net/2020/09/30/k3vAD6g9h1URcBH.png' alt='k3vAD6g9h1URcBH' style="width:50%"/>

### Setup JUnit in Jenkins

1. Go to `Currency Converter > Configure > Post-build Actions` and select `Publish JUnit test result report`.

   <img src='https://i.loli.net/2020/09/25/qEydhIY8gr6JHUS.png' alt='qEydhIY8gr6JHUS' style="width:50%"/>

2. Then set `**/test-results/**/*.xml` at `Test report XMLs`.

   <img src='https://i.loli.net/2020/09/30/1ELOifHtyD4plr8.png' alt='1ELOifHtyD4plr8'/>

### Setup Jacoco in Jenkins

1. Go to `Currency Converter > Configure > Post-build Actions` and select `Record JaCoCo coverage report`.

   <img src='https://i.loli.net/2020/09/25/grLqx4UbiAZsJly.png' alt='grLqx4UbiAZsJly' style="width:50%"/>


### Host Jenkins on internet and Connect to Github

1. Go to [GCP](https://console.cloud.google.com/), create a cluster under `Kubernetes Engine`, then click `Application` and search `Jenkins`. Then you should find this:

   <img src='https://i.loli.net/2020/10/06/GWMqYL2DzNixIRP.png' alt='' style="width:80%"/>

2. Deploy this application and then you can see the address.

   <img src='https://i.loli.net/2020/10/06/I5Bat4inL8UVdxP.png' alt='' style="width:80%"/>

3. Append `/github-webhook/` to the Jenkins address, which is `http://35.244.146.248/github-webhook/`

5. Go to `Github > Settings > Hooks > Add webhook` and add this link at `Payload URL`, e.g. `https://<hostname>/github-webhook/`. Then select `application/json` as `Content Type`.

   <img src='https://i.loli.net/2020/10/06/W2Nn1ZY8beXvxHl.png' alt='' style="width:80%"/>

# Application Development

## UML Class Diagram

## Class Document

## Demo

# Sprint 1 (starts week 9 and ends week 10)

## Sprint Planning

   ### Sprint Goal

   - Design data structure
   - Have initials codebase with appropriate methods to implement basic functionality
   - Features:
        - Login system
        - User management System
        - Database

   ### Sprint Task

    - identify work to be done to deliver this

## Tasks Board

### Sprint Backlog

- Main Window

   - Login system
        - Sign in
        - Sign up
        - GUI
   - User management System
        - Add user
        - Remove user
        - Change user information
        - GUI
   - Database
        - Save data to files

### Product Backlog

## User Stories

## Scrum Meetings

### Meeting 1

- date: 30th October
- attendance: All
- content: 
  - Identify the Sprint Goal
  - Discuss the data structure
  - Implement the codebase together
  - Refine, prioritize and assign tasks to team members
  - Produce the Sprint Backlog
- Cheng Chen:
  - What did I do yesterday:
  - What I will do today: Setup agile tools
  - Do I see any obstacles:
- Yuanqun Wang:
  - What did I do yesterday:
  - What I will do today:
  - Do I see any obstacles:
- Yufei Zuo:
  - What did I do yesterday:
  - What I will do today:
  - Do I see any obstacles:
- Zexuan Long:
  - What did I do yesterday:
  - What I will do today:
  - Do I see any obstacles:
- Songyin Li:
  - What did I do yesterday:
  - What I will do today:
  - Do I see any obstacles:
- Zehui Lin:
  - What did I do yesterday:
  - What I will do today:
  - Do I see any obstacles:

## Sprint Review

## Sprint Retrospective

# Sprint 2 (starts week 10 and ends week 11)

# Sprint 3 (starts week 11 and ends week 12)

