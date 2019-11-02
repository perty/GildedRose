Gilded Rose Exercise
===========

This is an exercise in using characterization tests and dependency breaking, the shield and the sword.

It is based on the kata "Gilded Rose", named after the inn. 
The requirements are in the [requirements document](requirements.md), next to this document.

Your first task is to get everything building and running in your development environment. 
Then you add characterization tests and break dependency.

1. Run the test `GildedRoseTest` and measure branch coverage. Verify that you can get branch coverage and not just line
 coverage.
1. Add characterization tests (shield) until you have good branch coverage. Use the requirements document to find test 
cases. It is not possible to reach 100%, why?
    1. You may cheat and add a constructor that takes an inventory as argument, for test purposes. But you are 
    taking the risk that your tests are not truly representative. Also, you may only add code, not change anything.
    1. You have a series of test cases suggested for you. When you implement them don't bother understanding
    the outcome as long it is within the suggestion of the name of the test case. E.g if quality should
    not decrease, anything goes as long as that is true. We're just defining correct behaviour.
1. With characterization tests done, you probably discovered that the requirements are not in synchronization with the
 code. Most notably, there is a text printed that is not mentioned in the requirements
 and the code is very specific about the naming of some items, more than the requirements says.
    1. Pretend that you talk to the stake holder about these things. Add the answers to your requirements.
1. When you reached the good branch coverage, it is time to refactor the code. Start with dependency breaking, the sword. 
    1. Create an `Inventory` interface, `InventoryIf`.
    1. Make the current `Inventory` implement that interface. Use "pull up" refactoring to move methods to the interface. 
    We tread safely as we pretend the problem is harder than it really is.
    1. Make the implementation, `GildedRose`, rely on the interface and get the inventory implementation injected by a 
    constructor argument.
    1. Create a implementation of the inventory interface called TestInventory and use that in your tests.
1. Time to refactor the current implementation. Keep tests at green all the time. You can not change the `Item` class as 
it is owned by a Goblin. What can you do instead? Tip: to go to object oriented solution in small steps, first create 
switch statements. First of all, refactor by using "flip if" so that the if condition is positive, then use a switch 
statement.
1. Next, create a failing test and then implementation, for the new requirement. 
 
Reflections
------
Note what you learned. 

* Why was the shield used before the sword? 
* Why refactor the current implementation before adding a new requirement?
* How did you feel when the implementation was put under test? 
* When you refactored the implementation, was readability any of your concern? Why?


Credits
-------

* written by [Terry Hughes](https://twitter.com/TerryHughes)
