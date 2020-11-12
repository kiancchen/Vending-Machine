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
  - [Storyboard](#storyboard)
  - [Scrum Meetings](#scrum-meetings)
    - [Meeting 1 (Sprint Planning)](#meeting-1-sprint-planning)
    - [Meeting 2](#meeting-2)
    - [Meeting 3](#meeting-3)
  - [Sprint Review](#sprint-review)
    - [Client Feedback](#client-feedback)
    - [Revisited Sprint Backlog](#revisited-sprint-backlog)
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
   - Refactor the initial data structure of `Product`
   - Refactore codes written by teammates that's not well designed
   - Fix bugs during the development
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
   - Fix bug of cash payment GUI
4. Zexuan Long (zlon4018):
   - Product options on main window
   - Write tests for the logic part.
5. Songyin Li (soli3733):
   - Change cashes GUI
   - Demo report
6. Zehui Lin (zlin3338):
   - Receive the cashes paid by customers.
   - Return cash changes to the customer

# Agile Development Tools and Practices

Same content in Sprint 1 Report, can be accessed at this [link](https://github.sydney.edu.au/SOFT2412-2020S2/R18B-Group5-VendingMachine/blob/master/Sprint1Report.md).

# Application Development

## UML Class Diagram

![image-20201112181604497](https://i.loli.net/2020/11/12/xTf2EzJ5pQZgjVn.png)

## Demo

`blan` is an owner. Its password is `123`. Feel free to use this account to test functionalities.

Here we are using the code with tag `1.1` for demonstration which you can see on Release page on Github.

### Sign In or Sign Up

At first the window will display the current user is `anonymous` by default.

<a href="https://sm.ms/image/cTMeioqlbsUXady" target="_blank"><img src="https://i.loli.net/2020/11/12/cTMeioqlbsUXady.png" ></a>

After click on `Account` button, it display a window to sign in or sign up.

<img src='https://i.loli.net/2020/11/05/EGFWUjfksDiKBrM.png' alt='EGFWUjfksDiKBrM'/>

When it sign in with correct username and password, it will display an alert to show sign in successfully, and the main window will display corresponding username and its type on the top right corner, as well as the text on button will change to `Logout` instead of `Account`.

<a href="https://sm.ms/image/2sxucgF4jB6W1OY" target="_blank"><img src="https://i.loli.net/2020/11/12/2sxucgF4jB6W1OY.png" ></a>

When it sign in with wrong username or password, it will display an alert to notify user it has wrong username or password.

<img src='https://i.loli.net/2020/11/05/RsQorqTLCEMVWZa.png' alt='RsQorqTLCEMVWZa'/>

When it sign up with different username not exists in the database, it will display an alert to notify sign up successfully.

<img src='https://i.loli.net/2020/11/05/FMrwN2eBJaX9EC5.png' alt='FMrwN2eBJaX9EC5'/>

### Display Product

The Product table on default page is available for every user include all the products that the machine has.

![截屏2020-11-12 下午12.30.47.png](https://i.loli.net/2020/11/12/2mPqnVceNYBgrXE.png)

### Display cart

The cart table on default page is available for every user include all the products were selected by current user.

![截屏2020-11-12 下午12.35.51.png](https://i.loli.net/2020/11/12/UBeFaMXjd9QuJtV.png)

### Add to Cart

![截屏2020-11-12 下午12.39.10.png](https://i.loli.net/2020/11/12/yQRHEwqA2tUZg4I.png)

The user can select which product he want to add to cart, then click the row which the product is, after that this product's name will be automatically filled in the item name field, then choose the quantity he want. After that click on `Add to Cart` button. The cart table will update accordingly.

![截屏2020-11-12 下午12.42.05.png](https://i.loli.net/2020/11/12/9IizV1c7QWA4xwU.png)

### Remove Cart

![截屏2020-11-12 下午12.45.48.png](https://i.loli.net/2020/11/12/3BSJws2Ln7ZkU9l.png)

The user can select which product he want to remove, then click the row which the product is,  then choose the quantity he want to remove. After that click on `Remove` button. The cart table will update accordingly.

![截屏2020-11-12 下午12.50.42.png](https://i.loli.net/2020/11/12/sY5j2ZRJ67zieot.png)

### Display Users

The user management window is only available for owner users. If current user is not owner, it will display an alert to notify current user have no access to this feature.

<a href="https://sm.ms/image/u7wckC8IEB5vW3s" target="_blank"><img src="https://i.loli.net/2020/11/12/u7wckC8IEB5vW3s.png" ></a>

After click the `Manage User` button. There is a table of all the users that have been registered in the system. Besides, there are three more operations, add or change or remove users, that owner have access.

<a href="https://sm.ms/image/uVJlwexs8d5XNrR" target="_blank"><img src="https://i.loli.net/2020/11/12/uVJlwexs8d5XNrR.png" ></a>

### Add Users

<a href="https://sm.ms/image/fr5Uy8JKEpTtXAS" target="_blank"><img src="https://i.loli.net/2020/11/12/fr5Uy8JKEpTtXAS.png" ></a>

The owner can input new username, password and user type, then click on `Add` button. An alert box will notify it is successfully added and the table will update accordingly.

<a href="https://sm.ms/image/nLSW9KEQj6tfUgT" target="_blank"><img src="https://i.loli.net/2020/11/12/nLSW9KEQj6tfUgT.png" ></a>

If owner does not input full relevant information then click on `Add` button, it will display an alert to notify current owner to fill in the missing information.

<a href="https://sm.ms/image/5BsG8jE3i1mCcgo" target="_blank"><img src="https://i.loli.net/2020/11/12/5BsG8jE3i1mCcgo.png" ></a>

### Change Users

<a href="https://sm.ms/image/hELAuvcTClirbmQ" target="_blank"><img src="https://i.loli.net/2020/11/12/hELAuvcTClirbmQ.png" ></a>

The owner first need to select which users he want to change, then click on the row where the user is, then the relevant information of this user will be automatically filled in the information field, then edit the username, password or user which users want to be change, then click on `Change` button. An alert box will notify it is successfully changed and the table will update accordingly.

<a href="https://sm.ms/image/y9IWzVFAqHKR8iT" target="_blank"><img src="https://i.loli.net/2020/11/12/y9IWzVFAqHKR8iT.png" ></a>

### Remove Users

<img src="https://i.loli.net/2020/11/12/hELAuvcTClirbmQ.png" >

The owner can select which users he want to remove, then click the row which the user is, after that this user's relevant information will be automatically filled in the information field, then click on `Remove` button. An alert box will notify it is successfully removed and the table will update accordingly.

<a href="https://sm.ms/image/gfIKHoJwp9RWA1N" target="_blank"><img src="https://i.loli.net/2020/11/12/gfIKHoJwp9RWA1N.png" ></a>

### Display Cash

The cash management window is only available for owner and cashier users. If current user is not them, it will display an alert to notify current user have no access to this feature.

<img src="https://i.loli.net/2020/11/12/u7wckC8IEB5vW3s.png" >

After click the `Manage Cash` button, there is a table has all types of cash exist in Australia. Besides, there are one more operation change the number of the  value.

<a href="https://sm.ms/image/uWpcfbNvVwKgyFC" target="_blank"><img src="https://i.loli.net/2020/11/12/uWpcfbNvVwKgyFC.png" ></a>

### Change Cash

<a href="https://sm.ms/image/5GQh6q7bvIAKiSo" target="_blank"><img src="https://i.loli.net/2020/11/12/5GQh6q7bvIAKiSo.png" ></a>

The user can select which value he wants to change, then click the row which the value is, after that the relevant information of this value will be automatically filled in the information field. Input the new number which the user want, then click the `Change number` button. An alert box will notify it is successfully changed and table will update accordingly.

<img src="https://i.loli.net/2020/11/12/lD7WjZQUm5qeYiO.png" >

### Manage Product

The product management window is only available for owner and seller users. If current user is not them, it will display an alert to notify current user have no access to this feature.

<img src="https://i.loli.net/2020/11/12/u7wckC8IEB5vW3s.png" >

After click the `Manage Product` button, there is a table of all the products that the machine has. Besides, there are three more operations, add or change or remove products.

<a href="https://sm.ms/image/eKgdJUSvxuiNYj9" target="_blank"><img src="https://i.loli.net/2020/11/12/eKgdJUSvxuiNYj9.png" ></a>



### Add Product

<a href="https://sm.ms/image/3a4CNjrhck5Et8q" target="_blank"><img src="https://i.loli.net/2020/11/12/3a4CNjrhck5Et8q.png" ></a>

The user can input new code, Name, Price and Quantity, then click on `Add` button. An alert box will notify it is successfully added and the table will update accordingly.

<a href="https://sm.ms/image/vupwcxQBHdSKPyj" target="_blank"><img src="https://i.loli.net/2020/11/12/vupwcxQBHdSKPyj.png" ></a>

If users input the repeated code or name then click on `Add` button, it will display an alert to notify current user the product exists in the system.![截屏2020-11-12 上午11.57.31.png](https://i.loli.net/2020/11/12/4ahVqQWKJLjndN6.png)

### Change Product

<a href="https://sm.ms/image/Cl2ULbuFWf8KHx6" target="_blank"><img src="https://i.loli.net/2020/11/12/Cl2ULbuFWf8KHx6.png" ></a>

The user first need to select which product he want to change, then click on the row where the product is, then the relevant information of this product will be automatically filled in the information field, then edit the code,name,price,quantity and category. then click on `Change` button. An alert box will notify it is successfully changed and the table will update accordingly.

![截屏2020-11-12 下午12.11.33.png](https://i.loli.net/2020/11/12/lSA7WcqazLxE8bn.png)

If user input invalid price like lettes or symbols. An alert box will notify it is failed changed.

![截屏2020-11-12 下午12.14.09.png](https://i.loli.net/2020/11/12/cnVwloX4rN9UEZ8.png)

![截屏2020-11-12 下午12.14.30.png](https://i.loli.net/2020/11/12/KGLdY3AJOqH1Qlj.png)

### Remove Product

![截屏2020-11-12 下午12.17.26.png](https://i.loli.net/2020/11/12/OFZ1TarV2HNbJ4d.png)

The user can select which product he want to remove, then click the row which the product is, after that this product's relevant information will be automatically filled in the information field, then click on `Remove` button. An alert box will notify it is successfully removed and the table will update accordingly.

![截屏2020-11-12 下午12.19.20.png](https://i.loli.net/2020/11/12/rH82p3FjC7PJzDM.png)

# Sprint Artifacts

## Product Backlog

The product backlog contains a set of all features and sub-features to build the vending machine, including functions, requirements, enhancements and fixes identified from previous sprints. The items in product backlog are ordered by priority and constantly changed. Any additional requirements from client should be directly added to product backlog instead of sprint backlog.

<img src='https://i.loli.net/2020/11/12/rM2aNhyep1wzvGH.png' alt='rM2aNhyep1wzvGH'/>

If you can't see this table clearly, please refer to this [link](https://docs.google.com/spreadsheets/d/1bW-7sIUqPr8Pr3uHav8PKmb57BtOSc3_Uw_Vm2mQ-A8/edit?usp=sharing) (google sheet).

## Sprint Goal

   - Implement the code structure for `Product` and `Transaction`
   - Complete the product management system
   - Complete the cash management system
   - The customer can select products they want to purchase, see the shopping cart and shopping history
   - Complete the cash payment system

## User Stories and Sprint Backlog

The sprint backlog contains a set of items selected for this sprint. It is considered as a plan for delivering the product increment and realize the sprint goal. It is visible to anyone and to be added or modified by the development team.

<img src='https://i.loli.net/2020/11/12/NpFoXwTc7dAWZf9.png' alt='NpFoXwTc7dAWZf9'/>

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
  - What did I do yesterday: Write code for logic part of `Product` and `Transaction`.
  - What I will do today: Add more methods and fix bugs.
  - Do I see any obstacles: None at the moment.
- Yuanqun Wang:
  - What did I do yesterday: Finished shopping cart GUI.
  - What I will do today: Modify feature of shopping cart.
  - Do I see any obstacles: None at the moment.
- Yufei Zuo:
  - What did I do yesterday: Finished GUI of adding and removing products.
  - What I will do today: Working on GUI of changing product.
  - Do I see any obstacles: None at the moment.
- Zexuan Long:
  - What did I do yesterday: Finished product option list on main window and write tests for logic part.
  - What I will do today: Write more tests and increase the coverage.
  - Do I see any obstacles: None at the moment.
- Songyin Li:
  - What did I do yesterday: Finished GUI of changing number of cashes.
  - What I will do today: Fix bugs.
  - Do I see any obstacles: None at the moment.
- Zehui Lin:
  - What did I do yesterday: Working on GUI of cash payment.
  - What I will do today: Continue working on cash payment GUI.
  - Do I see any obstacles: None at the moment.

### Meeting 3

- Date : 12th November
- Attendance: Cheng Chen. Yuanqun Wang, Yufei Zuo, Zexuan Long, Songyin Li, Zehui Lin
- Content:
  - Discussion about the progress of the sprint
  - Report any bugs of current application
  - Determine the content during demo with client
- Cheng Chen:
  - What did I do yesterday: Refactor the code and fix bugs
  - What I will do today: Continue refactoring and fixing
  - Do I see any obstacles: None at the moment.
- Yuanqun Wang:
  - What did I do yesterday: Finished removing product in shopping cart.
  - What I will do today: Working on changing the product quantity in shopping cart.
  - Do I see any obstacles: None at the moment.
- Yufei Zuo:
  - What did I do yesterday: Proofreading the report.
  - What I will do today: Fix bugs in application.
  - Do I see any obstacles: Some features is impossible to implement in current data structure.
- Zexuan Long:
  - What did I do yesterday: Wrote tests for the logic part.
  - What I will do today: Add more tests.
  - Do I see any obstacles: None at the moment.
- Songyin Li:
  - What did I do yesterday: Working on demo report
  - What I will do today: Continue working on demo report.
  - Do I see any obstacles: None at the moment.
- Zehui Lin:
  - What did I do yesterday: A table displaying of received cashes.
  - What I will do today: A table displaying of cash changes returned to the user.
  - Do I see any obstacles: The table is not display correctly.

## Sprint Review

### Client Feedback

Our client is satisfied with output of current sprint, and does not have any comments for current progression. The client just asks to continue finishing requirements.

- What has been done?
  - The database that stores products.
  - The owner and seller can modified number of cashes in machine.
  - The user can add, change or remove product from the shopping cart.
  - The user can purchase the products by cash.
  - The machine can display correct cash changes back to the user.
  - A table of products can display.
  - A table of the shopping cart can display.
- What has not been done?
  - Have not implemented the database for `transaction`.
  - Cannot display shopping history.

### Revisited Sprint Backlog

The tasks were not assigned according to priority, some features cannot be implemented while some underlying tasks have not been completed. For example, one of our sprint backlog is to display a table of shopping history, however the user can't make transactions now, which makes it impossible to display the shopping history.

Because of the same reason above, some teammates wrote low-quality code such as duplicated code, code in wrong places, which takes us much time to refactor.

In the next sprint, we need to focus on the order to implement features and aims to finish all the required features by the due day.

## Sprint Retrospective

- Date: 12th November
- What went well during the Sprint?
  - Completed almost all tasks on time.
  - Worked on different features on different branches by different teammate well.
  - All agile tools and practices were utilized well.
  - The communication among teammates was efficient so that we can adjust our tasks in time during the progress of the development if problems encountered.
- What can be improved in the future?
  - Prioritize all the tasks at the beginning.
  - Complete tasks according the priority strictly.
  - Avoid low-quality code and improve the readability and maintainability.
