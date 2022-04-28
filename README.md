[figma](https://www.figma.com/file/H5t4Wc1Byj0ziRpQoC4Fsb/Untitled?node-id=0%3A1)
Login ID: Blob3
Login Password: 258kl




# Partially Implemented Features

> ## Main Activity
>> Main activity will include 2 tabs, one as a hub for quizzes, tests and surveys and another page as a profile page

>## Basic Quiz Activity
>> Basic Quizzes consists of bunch of questions, answers, and options. In the last version there will be 4 options for each question and options will be randomly ordered

>## RADS(Read And Decide Simultaneously) Activity
>> ### General Explanation
>>> RADS activity consist of a square matrice of buttons and will include a way of getting directional inputs by using more buttons or maybe swiping. Each button will include a word from a sentence or sentences. You will have an index button which will be the reference point when traversing through the buttons to reach a choice button that will fill the buttons with a new sentence(s)
>> ### Traversing
>>> Traversing through an array starts with directional inputs which must be given in a window of time before the inputs are executed. An input will make you traverse through buttons in given direction as far as you don't reach a bound, if you reach a bound the next input will be executed. If you reach a choice button you will move through the next stage depending of that particular button, if you are unable the reach any choice buttons before the time runs out you will be moved on to an fixed stage
>>>> #### Example
>>>>> Lets say we have inputs right and up with a 3x3 matrice of buttons. Button 2,0 will be our index button, traversed buttons will be represented by 1's and the other buttons will be represented by 0's
 
Buttons| 0 | 1 | 2 |
 --- | --- | --- | --- |
 0 | 0 | 0 | 0 | 
 1 | 0 | 0 | 0 | 
 2 | 1 | 0 | 0 | 

 Buttons| 0 | 1 | 2 |
 --- | --- | --- | --- |
 0 | 0 | 0 | 0 | 
 1 | 0 | 0 | 0 | 
 2 | 1 | 1 | 1 | 

 Buttons| 0 | 1 | 2 |
 --- | --- | --- | --- |
 0 | 0 | 0 | 1 | 
 1 | 0 | 0 | 1 | 
 2 | 1 | 1 | 1 | 

>> ### State Of The Buttons
>>> Other than normal Buttons we have three type(states) of buttons:
>>>> * Index Button
>>>>> these buttons will acts as a starting point for the user and will not be randomly selected
>>>> * Choice Buttons
>>>>> choice buttons are the buttons which on reaching them, will move you through a new stage
>>>> * Blocked Buttons
>>>>> blocked buttons are buttons which acts as borders. They can be already present on the stage, otherwise clicking on normal buttons will make them blocked buttons

>## Login Fragment
>> Login Fragments is an undismissable Dialog Fragment, a way to reset the password if forgotten will be added

# To Be Implemented Features


>## Profile Tab At Main Page
>> This page will store information on user like xp points, moxie and possibly quiz scores or a score depending on that

>## A New Quiz Type With Narration And Related Questions
>> This Quiz with almost being identical to Basic Quiz will include a dialog fragment at the beginning of the quiz for narration and will include related questions to that text

>## A Survey Activity
>> This Activity beign similar to Basic Quiz Activity will include questions and answers on a scale of a scale of a scale of 10(maybe 5)
