Gilded Rose Exercise
===========

This is an exercise in using characterization tests and dependency breaking, the shield and the sword.

It is based on the kata "Gilded Rose", named after the inn. The requirements are in the [requirements document](requirements.md), next to this document.

Your first task is to get everything building and running in your development environment. Then you add characterization tests and break dependency.

1. Create a branch with your name so that any changes you push, should you do so, is not mixed with others.
1. Run the test "GildedRoseTest" and measure branch coverage. It should not be that impressive.
1. Add characterization tests (shield) until you have total branch coverage. Use the requirements document to find test cases. It is not possible to reach 100%, why?
1. When you reached the good branch coverage, it is time to refactor the code. Start with dependency breaking, the sword. 
    1. Create an Inventory interface, "InventoryIf"
    1. Make the current Inventory implement that interface. Use "pull up" refactoring to move methods to the interface. We tread safely as we pretend the problem is harder than it really is.
    1. Make the implementation, GildedRose, rely on the interface and get the inventory implementation injected by a constructor argument.
    1. Create a implementation of the inventory interface called TestInventory and use that in your tests.
1. With the dependency broken, you can refactor your current tests to simplify test data setup. Why couldn't you do that first?
1. Time to refactor the current implementation. Keep tests at green all the time. You can not change the Item class as it is owned by a Goblin. What can you do instead?
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
