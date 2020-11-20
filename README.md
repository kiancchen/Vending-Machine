# Vending Machine

R18B Group 5

[Sprint 1 Report](https://github.sydney.edu.au/SOFT2412-2020S2/R18B-Group5-VendingMachine/blob/master/Sprint1Report.md)

[Sprint 2 Report](https://github.sydney.edu.au/SOFT2412-2020S2/R18B-Group5-VendingMachine/blob/master/Sprint2Report.md)

[Sprint 3 Report](https://github.sydney.edu.au/SOFT2412-2020S2/R18B-Group5-VendingMachine/blob/master/Sprint3Report.md)

# How to run the program

You can use `gradle build` first to build the program. After that, you can use `gradle run` to run the program.

For testing, use `gradle test jacocoTestReport` to test the program. The jacoco test report can be found in `build/jacocoHtml/index.html`. We tested all logic part of code. GUI is not tested.

**NOTE: If you run the test, it will restore all resources to the initial state for a uniform testing environment. All changes on users, cashes or products will lose.**

**NOTE: `gradle build` will run the tests automatically. If you want to build the program again but want to keep the resources, please use `gradle build -x test` to exclude testing. Or if you just want to run the program again, please use `gradle run`.**

# Usage

`blan` is an owner. Its password is `123`. Feel free to use this account to test functionalities.

One of credit cards has name `Charles` and card number `40691`. Feel free to use this card to test functionalities.

Elaborated usage can be found in the Demo part in [Sprint 3 Report](https://github.sydney.edu.au/SOFT2412-2020S2/R18B-Group5-VendingMachine/blob/master/Sprint3Report.md).

