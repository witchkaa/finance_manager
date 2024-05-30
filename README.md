# Finance-Manager

[Design document](https://docs.google.com/document/d/16ffJH8ZshSTiegzC7IICThg30udqfneJf-LmUsteBg4/edit?usp=sharing)

## What is Finance Manager?
Planning your budget can be a hard thing as not everybody has the financial management skills. It can be hard for some to keep in mind all their financial plans, so it is essential to find a way to cope with it - use a paper planner, or download this application. This app is designed to help the user plan their budget. You can add planned expenses and incomes, save them in the app, see your current balance, view other useful information on your budget.

The user interface can be chosen (either console or graphic) and the storage mechanism is file serialization. It can be easily changed to a DB by implementing StorageProcessor interface, but we chose serialization
because currently our app is desktop-only and there's no connection to any kind of server.

## Functionality breakdown

- if you're first to the program, you will be asked to enter your name and current budget

- add expense/income: add your planned transaction (comment + amount) so it is stored in the program

- change budget: add immediate changes to your budget

- compare all: see financial stats based off your budget + planned expenses + planned incomes

- compare plans: see stats based off your planned expenses + incomes

- mark expense/income done: delete it from the planned list and change your budget accorsing to it being done

- remove expense/income: delete it without budget changes

- show history stats: see stats according to your previously marked as done expenses/incomes 

- view budget/expenses/incomes: see this info in a readable way

# Installing the app

Before downloading the app, you need Java installed on your machine.

### First option: using the JAR from this repo

[finance-manager.jar](finance-manager.jar)

After installing the JAR, cd to the directory you installed it in and use _java -jar finance-manager.jar_

### Second option: manual JAR build (Intellij IDEA program interface)

Clone the repository and open it in IntelliJ IDEA.

In the menu, select File - Project Structure - Artifacts - + - JAR - From modules with dependencies.

Choose Main as the main class and click "OK".

Next, in the menu, select Build - Build Artifacts - Build.

In the project structure, a directory named "out" will appear, containing the JAR file. 
You can now copy this file to any desired directory on your computer.

# How to run tests

While in root directory of the project, write:

```
mvn test
```

Or you can run tests using interface of your IDE.

# Conclusion 

While developing this app, we learned a lot about how to manage team development in Git/GitHub. 
We learned how to do reviews, and also practiced Docker, writing tests, and managing CI.

## License: GNU General Public License.
