- [Scrum Teams](#scrum-teams)
  - [Roles](#roles)
  - [Individual Contribution](#individual-contribution)
- [Agile Development Tools and Practices](#agile-development-tools-and-practices)
- [Application Development](#application-development)
  - [UML Class Diagram](#uml-class-diagram)
  - [Demo](#demo)
    - [Sign In or Sign Up](#sign-in-or-sign-up)
    - [Display Product](#display-product)
    - [Display cart](#display-cart)
    - [Add to Cart](#add-to-cart)
    - [Remove Cart](#remove-cart)
    - [Checkout Window](#checkout-window)
    - [Card Payment](#card-payment)
    - [Check card](#check-card)
    - [Cash Payment](#cash-payment)
    - [Set Cash to Pay](#set-cash-to-pay)
    - [Pay](#pay)
    - [Display the latest 5 Products](#display-the-latest-5-products)
    - [Display Sold History](#display-sold-history)
    - [Display Transaction History](#display-transaction-history)
    - [Display Cancelled History](#display-cancelled-history)
    - [Generate Report](#generate-report)
    - [Display Users](#display-users)
    - [Add Users](#add-users)
    - [Change Users](#change-users)
    - [Remove Users](#remove-users)
    - [Display Cash](#display-cash)
    - [Change Cash](#change-cash)
    - [Manage Product](#manage-product)
    - [Add Product](#add-product)
    - [Change Product](#change-product)
    - [Remove Product](#remove-product)
- [Sprint Artifacts](#sprint-artifacts)
  - [Product Backlog](#product-backlog)
  - [Sprint Goal](#sprint-goal)
  - [User Stories and Sprint Backlog](#user-stories-and-sprint-backlog)
  - [Burndown Chart](#burndown-chart)
    - [Sprint 3 Burndown Chart](#sprint-3-burndown-chart)
    - [Product Burndown Chart](#product-burndown-chart)
  - [Storyboard](#storyboard)
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
    - Optimize the value of the work
    - Ensure transparency and clarity of the product backlog
    - Coordinate the work among teammates

3. Developer1: Yuanqun Wang
4. Developer2: Zexuan Long
5. Developer3: Songyin Li
6. Developer4: Zehui Lin

## Individual Contribution

1. Cheng Chen (cche7436):
   - The data structure of `Transaction`
   - The saving of all data
   - The interaction between the user and transaction
   - The design of the shopping process
   - Pay by the credit card
   - Improve the experience of the user interaction
   - Refactor the code to support new features
   - Continuously fix bugs
   - Review pull requests

2. Yuanqun Wang (ywan3184):
   - Timeout cancelled feature
   - User cancelled feature
   - Change not available will automatically cancelled
   - User have not  enough money will automatically cancelled
3. Yufei Zuo (yzuo4982):
   - Current user cvs report
   - All transactions csv report
   - All cancelled transactions csv report
   - All current products csv report
   - All sold products csv report
   - All current change csv report
4. Zexuan Long (zlon4018):
   - The Transaction window on main window
   - Write tests for logic part
5. Songyin Li (soli3733):
   - Sold product history GUI
   - Demo report
   - Story board
6. Zehui Lin (zlin3338):
   - A table display last five products bought by customer
   - Prompt message for insufficient payment amount
   - Prompt message for insufficient change in system amount
   - Prompt message for the system does not have correct change combination

# Agile Development Tools and Practices

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

  ![fHwITVkpamtSrJ7](https://i.loli.net/2020/11/20/fHwITVkpamtSrJ7.png)

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

<img src='https://i.loli.net/2020/11/19/fKFEQcCzYepHn4L.png' alt='fKFEQcCzYepHn4L'/>

## Demo

`blan` is an owner. Its password is `123`. Feel free to use this account to test functionalities.

One of credit cards has name `Charles` and card number `40691`. Feel free to use this card to test functionalities.

Here we are using the code with tag `2.0` for demonstration which you can see on Release page on Github.

### Sign In or Sign Up

At first the window will display the current user is `anonymous` by default.

![Screen Shot 2020-11-19 at 11.53.35 PM.png](https://i.loli.net/2020/11/19/SRg5oT4AUZe3P91.png)

After click on `Account` button, it display a window to sign in or sign up.

<img src='https://i.loli.net/2020/11/05/EGFWUjfksDiKBrM.png' alt='EGFWUjfksDiKBrM'/>

When it sign in with correct username and password the main window will display corresponding username and its type on the top right corner, as well as the text on button will change to `Logout` instead of `Account`.

<a href="https://sm.ms/image/ig7YMaljtQCvZez" target="_blank"><img src="https://i.loli.net/2020/11/20/ig7YMaljtQCvZez.png" ></a>

When it sign in with wrong username or password, it will display an alert to notify user it has wrong username or password.

<img src='https://i.loli.net/2020/11/05/RsQorqTLCEMVWZa.png' alt='RsQorqTLCEMVWZa'/>

### Display Product

The Product table on default page is available for every user include all the products that the machine has.

![Screen Shot 2020-11-19 at 9.25.53 PM.png](https://i.loli.net/2020/11/19/1wu3kqCRMzKDQg8.png)

### Display cart

The cart display on product table on default page is available for every user include all the products were selected by current user.

![Screen Shot 2020-11-19 at 9.23.17 PM.png](https://i.loli.net/2020/11/19/PNF53hZKXsVxajG.png)

### Add to Cart

The user can select which product he want to add to cart, then click the row which the product is, after that this product's name will be automatically filled in the item name field, then choose the quantity he want. After that click on `Set Qty in cart` button. The cart table will update accordingly.

![Screen Shot 2020-11-19 at 9.37.05 PM.png](https://i.loli.net/2020/11/19/V6NQiOWn28kAwJL.png)

### Remove Cart

![Screen Shot 2020-11-19 at 9.39.47 PM.png](https://i.loli.net/2020/11/19/KMJj852vYg7hbCo.png)

The user can select which product he want to remove, then click the row which the product is,  then choose the quantity he want to remove. After that click on `Set Qty in cart` button. The cart table will update accordingly.

![Screen Shot 2020-11-19 at 9.40.26 PM.png](https://i.loli.net/2020/11/19/ExUvHzhpyrjlksm.png)

### Checkout Window

The Checkout window is available for every users. If current cart table has nothing, after click `Checkout` button, it will display an alert to notify current user have no item to purchase.

![Screen Shot 2020-11-19 at 9.58.21 PM.png](https://i.loli.net/2020/11/19/Rbx2XtHATY6Gfsw.png)

After click `Checkout` button, there is a window display items' amount in cart. Besides, there are three more operations, card, cash or cancel. And in the middle there is 120s countdown, when it comes to 0, it will automatically back to default window, and record this transaction to be cancelled with reason "timeout".

![Screen Shot 2020-11-19 at 9.59.17 PM.png](https://i.loli.net/2020/11/19/YmyXugcSDkZnzMd.png)

### Card Payment

After click the `Card` button, it need user input card name and number.  Besides, there are two more operations, check and pay. And in the upper left corner there is 120s countdown, when it comes to 0, it will automatically back to default window. And in the lower left corner, user can tick to save the card information or not.

![Screen Shot 2020-11-20 at 1.17.47 AM.png](https://i.loli.net/2020/11/20/qF1aWQxd4mVIE6Z.png)

User input the name and number of the card he want to pay this transaction.

![Screen Shot 2020-11-20 at 2.33.20 PM.png](https://i.loli.net/2020/11/20/4k8L7hq6X1ndluD.png)

If user save the card before, System will automatically filled the information.

![Screen Shot 2020-11-20 at 3.00.37 PM.png](https://i.loli.net/2020/11/20/DejKxI4AXYvU6W9.png)

After click the `Cancel` button, it will back to default window and record this transaction to be cancelled with reason "user cancelled".

### Check card

After click the `check` button, if the card information is incorrect, it will display an alert notify current user the card is wrong. If the information is correct, this transaction is succeeded.

![Screen Shot 2020-11-20 at 3.06.00 PM.png](https://i.loli.net/2020/11/20/EL5eHJ4KOFqbMtv.png)

![Screen Shot 2020-11-19 at 10.02.02 PM.png](https://i.loli.net/2020/11/20/oGTPhIwsXMNfWr8.png)

If the user tick the `Save card` and click `Check` button. if user is anonymous, it will display an alert to notify user is anonymous which does not right to save the card.

![Screen Shot 2020-11-20 at 3.00.37 PM.png](https://i.loli.net/2020/11/20/rSQKeTAFvxk3JWZ.png)

![Screen Shot 2020-11-20 at 1.24.01 AM.png](https://i.loli.net/2020/11/20/TBa8CVXLJlFpQgx.png)

### Cash Payment

After click the `Cash` button, there is a table include the cash's value and number.  Besides, there are two more operations, set, pay and cancel. In addition, in the upper left corner there is 120s countdown, when it comes to 0, it will automatically back to default window, and record this transaction to be cancelled with reason "timeout".

![Screen Shot 2020-11-19 at 10.02.02 PM.png](https://i.loli.net/2020/11/19/hUwDjcamTgzvPL1.png)

### Set Cash to Pay

![Screen Shot 2020-11-19 at 10.04.15 PM.png](https://i.loli.net/2020/11/19/pEbWNTgs1zwnFZv.png)

The user can input cash type and its amount. Then click the`Set` button, after that the table will update accordingly.

![Screen Shot 2020-11-19 at 10.04.23 PM.png](https://i.loli.net/2020/11/19/RXM7ZjG4A8J3viY.png)

### Pay

If user didn't add any cash. It will display an alert to notify current user doesn't pay any cashes.

![Screen Shot 2020-11-12 at 7.27.54 PM.png](https://i.loli.net/2020/11/12/zBiTOxtRXC4G75V.png)

After click the `Pay` button. There is a table of change include different values and amount. The cash in the machine also will be reduced accordingly.

![32131605174938_.pic.jpg](https://i.loli.net/2020/11/12/yDHv5XOYoZzQKgG.png)

If user didn't pay enough money to purchase, it will automatically cancelled this transaction with reason "no enough money paid".

If vending machine does not have enough money to give back change, it will also automatically cancelled this transaction with reason "no available changes".

### Display the latest 5 Products

The latest bought items table on default page is available for every user. If you have login, it will show your purchase history, else it will show the anonymous' last 5 bought items.

![Screen Shot 2020-11-19 at 11.06.53 PM.png](https://i.loli.net/2020/11/19/kJ54rwRtx3E2cDe.png)

### Display Sold History

The sold history window is only available for owner and seller users. If current user is not them, it will display an alert to notify current user have no access to this feature.

![截屏2020-11-12 下午3.03.15.png]( https://i.loli.net/2020/11/19/EerYG6ysmFgoq1W.png)

After click the `Sold History` button. There is a table of all the product that have been sold in the system.

![Screen Shot 2020-11-20 at 12.02.14 AM.png](https://i.loli.net/2020/11/20/BWj7oeMc9y1gDYa.png)

### Display Transaction History

The  Transaction History  window is only available for owner and cashier users. If current user is not owner or cashier, it will display an alert to notify current user have no access to this feature.

![截屏2020-11-12 下午3.03.15.png]( https://i.loli.net/2020/11/19/EerYG6ysmFgoq1W.png)

After click the `Transaction History` button. There is a table of transactions that includes transaction date and time, item sold, amount of money paid, returned change and payment method. Besides, there is one more operation, view transaction product history.

![Screen Shot 2020-11-20 at 2.33.43 PM.png](https://i.loli.net/2020/11/20/lNa1zkdXSg6Eu4H.png)

The owner can select which history he want to view, then then click on `View details` button. There is a table of all products sold in this transaction include name, category, price and quantity.

![Screen Shot 2020-11-20 at 2.33.49 PM.png](https://i.loli.net/2020/11/20/QkTlZ32IC8px5Hu.png)

### Display Cancelled History

The user management window is only available for owner users. If current user is not owner, it will display an alert to notify current user have no access to this feature.

![截屏2020-11-12 下午3.03.15.png]( https://i.loli.net/2020/11/19/EerYG6ysmFgoq1W.png)

After click the `Cancelled History` button. There is a table of cancelled transaction during this system includes date and time of the cancelled, the user and the reasons.

![Screen Shot 2020-11-20 at 2.50.26 PM.png](https://i.loli.net/2020/11/20/53AG9vUu2SVNKPO.png)

### Generate Report

![Screen Shot 2020-11-20 at 1.12.40 AM.png](https://i.loli.net/2020/11/20/nbldTSsXNvCmL3q.png)

After click the `Generae Report` button. There are some operations which can generate different report . After click these buttons, a new folder called `report` will be created, and then according to different permissions, different csv reports can be generated in the `report` folder.

![Screen Shot 2020-11-20 at 2.34.53 PM.png](https://i.loli.net/2020/11/20/XUSivCYakFQ8WNz.png)

### Display Users

The user management window is only available for owner users. If current user is not owner, it will display an alert to notify current user have no access to this feature.

![截屏2020-11-12 下午3.03.15.png]( https://i.loli.net/2020/11/19/EerYG6ysmFgoq1W.png)

After click the `Manage User` button. There is a table of all the users that have been registered in the system. Besides, there are three more operations, add or change or remove users, that owner have access.

<a href="https://sm.ms/image/uVJlwexs8d5XNrR" target="_blank"><img src="https://i.loli.net/2020/11/12/uVJlwexs8d5XNrR.png" ></a>

### Add Users

<a href="https://sm.ms/image/fr5Uy8JKEpTtXAS" target="_blank"><img src="https://i.loli.net/2020/11/12/fr5Uy8JKEpTtXAS.png" ></a>

The owner can input new username, password and user type, then click on `Add` button. The table will update accordingly.

<img src="https://i.loli.net/2020/11/12/hELAuvcTClirbmQ.png" >

If owner does not input full relevant information then click on `Add` button, it will display an alert to notify current owner to fill in the missing information.

![Screen Shot 2020-11-20 at 2.38.21 PM.png](https://i.loli.net/2020/11/20/9reMsNWm1GoULO5.png)

### Change Users

<a href="https://sm.ms/image/hELAuvcTClirbmQ" target="_blank"><img src="https://i.loli.net/2020/11/12/hELAuvcTClirbmQ.png" ></a>

The owner first need to select which users he want to change, then click on the row where the user is, then the relevant information of this user will be automatically filled in the information field, then edit the username, password or user which users want to be change, then click on `Change` button. The table will update accordingly.

### Remove Users

<img src="https://i.loli.net/2020/11/12/hELAuvcTClirbmQ.png" >

The owner can select which users he want to remove, then click the row which the user is, after that this user's relevant information will be automatically filled in the information field, then click on `Remove` button.  the table will update accordingly.

![Screen Shot 2020-11-20 at 2.42.57 PM.png](https://i.loli.net/2020/11/20/e8Ri71Lqu49lz2h.png)

### Display Cash

The cash management window is only available for owner and cashier users. If current user is not them, it will display an alert to notify current user have no access to this feature.

![截屏2020-11-12 下午3.03.15.png]( https://i.loli.net/2020/11/19/EerYG6ysmFgoq1W.png)

After click the `Manage Cash` button, there is a table has all types of cash exist in Australia. Besides, there are one more operation change the number of the  value.

<a href="https://sm.ms/image/uWpcfbNvVwKgyFC" target="_blank"><img src="https://i.loli.net/2020/11/12/uWpcfbNvVwKgyFC.png" ></a>

### Change Cash

<a href="https://sm.ms/image/5GQh6q7bvIAKiSo" target="_blank"><img src="https://i.loli.net/2020/11/12/5GQh6q7bvIAKiSo.png" ></a>

The user can select which value he wants to change, then click the row which the value is, after that the relevant information of this value will be automatically filled in the information field. Input the new number which the user want, then click the `Change number` button.  The table will update accordingly.

If the user input symbol or letters. An alert box will notify it  is fail to change.

![截屏2020-11-12 下午6.19.13.png](https://i.loli.net/2020/11/12/VBr1KYnGwzFufbT.png)

![Screen Shot 2020-11-12 at 7.30.26 PM.png](https://i.loli.net/2020/11/12/MvQ6oEKbcSIWXP3.png)

### Manage Product

The product management window is only available for owner and seller users. If current user is not them, it will display an alert to notify current user have no access to this feature.

![截屏2020-11-12 下午3.03.15.png]( https://i.loli.net/2020/11/19/EerYG6ysmFgoq1W.png)

After click the `Manage Product` button, there is a table of all the products that the machine has. Besides, there are three more operations, add or change or remove products.

<a href="https://sm.ms/image/eKgdJUSvxuiNYj9" target="_blank"><img src="https://i.loli.net/2020/11/12/eKgdJUSvxuiNYj9.png" ></a>

### Add Product

<a href="https://sm.ms/image/3a4CNjrhck5Et8q" target="_blank"><img src="https://i.loli.net/2020/11/12/3a4CNjrhck5Et8q.png" ></a>

The user can input new code, Name, Price and Quantity, then click on `Add` button. An alert box will notify it is successfully added and the table will update accordingly.

![Screen Shot 2020-11-20 at 2.58.12 PM.png](https://i.loli.net/2020/11/20/3nYacXBKGDzLt17.png)

If users input the repeated code or name then click on `Add` button, it will display an alert to notify current user the product exists in the system.![Screen Shot 2020-11-12 at 7.34.17 PM.png](https://i.loli.net/2020/11/12/DdxVQ1iSBzjKyw6.png)

### Change Product

<a href="https://sm.ms/image/Cl2ULbuFWf8KHx6" target="_blank"><img src="https://i.loli.net/2020/11/12/Cl2ULbuFWf8KHx6.png" ></a>

The user first need to select which product he want to change, then click on the row where the product is, then the relevant information of this product will be automatically filled in the information field, then edit the code,name,price,quantity and category which user want. then click on `Change` button. The table will update accordingly.

If user input invalid price like lettes or symbols. An alert box will notify it is failed changed.

![截屏2020-11-12 下午12.14.09.png](https://i.loli.net/2020/11/12/cnVwloX4rN9UEZ8.png)

![Screen Shot 2020-11-12 at 7.45.52 PM.png](https://i.loli.net/2020/11/12/BLDsrO2ANq9FVit.png)

### Remove Product

![截屏2020-11-12 下午12.17.26.png](https://i.loli.net/2020/11/12/OFZ1TarV2HNbJ4d.png)

The user can select which product he want to remove, then click the row which the product is, after that this product's relevant information will be automatically filled in the information field, then click on `Remove` button. The table will update accordingly.

# Sprint Artifacts

## Product Backlog

The product backlog contains a set of all features and sub-features to build the vending machine, including functions, requirements, enhancements and fixes identified from previous sprints. The items in product backlog are ordered by priority and constantly changed. Any additional requirements from client should be directly added to product backlog instead of sprint backlog.

<img src='https://i.loli.net/2020/11/19/fhx7j6zwtGqkUPi.png' alt='fhx7j6zwtGqkUPi'/>

If you can't see this table clearly, please refer to this [link](https://docs.google.com/spreadsheets/d/1bW-7sIUqPr8Pr3uHav8PKmb57BtOSc3_Uw_Vm2mQ-A8/edit?usp=sharing) (google sheet).

## Sprint Goal

   - Continues fix code structure for `Transaction`
   - Able to display transaction history table
   - Able to display cancelled transaction history tablee
   - Able to display sold product history table
   - Able to pay by credit card
   - Able to cancel the transaction
   - The user will be automatically logout after the checkout or cancelling the transaction
   - Save the credit card details if user wants to save, and it will be automatically filled in.
   - A shopping of last 5 products bought at the main window.
   - Able to generate reports of items, transactions, cashes and users.

## User Stories and Sprint Backlog

The sprint backlog contains a set of items selected for this sprint. It is considered as a plan for delivering the product increment and realize the sprint goal. It is visible to anyone and to be added or modified by the development team.

<mark>Review</mark>

<img src='https://i.loli.net/2020/11/19/X251LGUxrkCcl38.png' alt='X251LGUxrkCcl38'/>

**Notes:** Sprint 3 Backlog is colored with red.

## Burndown Chart

### Sprint 3 Burndown Chart

<img src='https://i.loli.net/2020/11/19/4k578V6noPeUtMD.png' alt='4k578V6noPeUtMD'/>

### Product Burndown Chart

<img src='https://i.loli.net/2020/11/19/1UO9BNGeLXpjDm2.png' alt='1UO9BNGeLXpjDm2'/>

## Storyboard

<img src='https://i.loli.net/2020/11/19/HcD8gjV1CBukSpe.png' alt='HcD8gjV1CBukSpe'/>

## Scrum Meetings

### Meeting 1 (Sprint Planning)

- Date: 14th November
- Attendance: Cheng Chen. Yuanqun Wang, Yufei Zuo, Zexuan Long, Songyin Li, Zehui Lin
- Content:
  - Identify the Sprint Goal
  - Discuss how the previous data structure adapted to the new features
  - Refine, prioritize and assign tasks to team members
  - Produce the Sprint Backlog

### Meeting 2

- Date : 16th November
- Attendance: Cheng Chen. Yuanqun Wang, Yufei Zuo, Zexuan Long, Songyin Li, Zehui Lin
- Content:
  - Track and review the current progress.
  - Talk about the obstacles met
- Cheng Chen:
  - What did I do yesterday: The interaction between `User` and `Transaction`
  - What I will do today: Add mores methods to support more features
  - Do I see any obstacles: None at the moment.
- Yuanqun Wang:
  - What did I do yesterday: User can cancel transaction.
  - What I will do today: Working on other reason that allows cancelling.
  - Do I see any obstacles: None at the moment.
- Yufei Zuo:
  - What did I do yesterday: Generate the user and cash report.
  - What I will do today: Generate the product and transaction report.
  - Do I see any obstacles: None at the moment.
- Zexuan Long:
  - What did I do yesterday: Finish Transaction History window on main window and write tests for logic part.
  - What I will do today: Continues write more tests.
  - Do I see any obstacles: None at the moment.
- Songyin Li:
  - What did I do yesterday: Finish the sold history window om main window
  - What I will do today:  Working on story board.
  - Do I see any obstacles: None at the moment.
- Zehui Lin:
  - What did I do yesterday: Working on the shopping history table which list last five products bought by customer.
  - What I will do today: Continues working on shopping history table in main window.
  - Do I see any obstacles: None at the moment.

### Meeting 3

- Date : 18th November
- Attendance: Cheng Chen. Yuanqun Wang, Yufei Zuo, Zexuan Long, Songyin Li, Zehui Lin
- Content:
  - Track the progress of the sprint
  - Report any bugs of current application
  - Determine the content during demo with client
- Cheng Chen:
  - What did I do yesterday: Fix bugs and improve the interaction between `User` and `Transaction`
  - What I will do today: Fix bugs
  - Do I see any obstacles: None at the moment.
- Yuanqun Wang:
  - What did I do yesterday: Finished all cancelled features
  - What I will do today: Fix some bugs in cancelled features.
  - Do I see any obstacles: None at the moment.
- Yufei Zuo:
  - What did I do yesterday: Finished all report generation features.
  - What I will do today: Fix bugs in transaction csv report in application.
  - Do I see any obstacles: None at the moment.
- Zexuan Long:
  - What did I do yesterday: Finished the Transaction History window on main window and write tests for logic.
  - What I will do today: Fix the Transaction History window split it into two windows and continuous to add tests.
  - Do I see any obstacles: None at the moment.
- Songyin Li:
  - What did I do yesterday: Finish the story board
  - What I will do today:  Finish the report demo and change the story board base on the new feature
  - Do I see any obstacles: None at the moment.
- Zehui Lin:
  - What did I do yesterday: Finished shopping history table display of last five products.
  - What I will do today: Fix the bug in cash payment system.
  - Do I see any obstacles: None at the moment.

## Sprint Review

<mark>Review</mark>

- What has been done?
   - Continues fix code structure for `Transaction`
   - Able to display transaction history table
   - Able to display cancelled transaction history tablee
   - Able to display sold product history table
   - Able to pay by credit card
   - Able to cancel the transaction
   - The user will be automatically logout after the checkout or cancelling the transaction
   - Save the credit card details if user wants to save, and it will be automatically filled in.
   - A shopping of last 5 products bought at the main window.
   - Able to generate reports of items, transactions, cashes and users.
- What has not been done?
  - All the tasks have been completed on time.

## Sprint Retrospective

<mark>Review</mark>

- Date: 20th November
- What went well during the Sprint?
  - Completed all of tasks on time.
  - Extends some features from clients requirement.
  - Improve experience of user interaction.
  - Worked on different features on different branches by different teammate well.
  - All agile tools and practices were utilized.
  - The communication among teammate was efficient so that we can adjust our tasks in time during the progress of the development.
- What can be improved in the future?
  - Parts of code could implement design patterns to improve reusability and extendability.
