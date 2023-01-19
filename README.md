# Assignment 3: Software Quality Engineering
This is a repository for assignment 3 of the Software Quality Engineering course at the [Ben-Gurion University](https://in.bgu.ac.il/), Israel.

## Assignment Description
In this assignment, we tested an open-source software called Moodle(https://moodle.com).

Moodle is a free open source learning management system that allows custom websites creation with online courses etc. 
## Installation
   1. Download from link - https://download.moodle.org/download.php/windows/MoodleWindowsInstaller-latest-400.zip
   2. Unzip
   3. Run startMoodle.exe
   4. In order to run moodle you will also need:
      1. DB program (we used PostgreSQL)
      2. Server software (We used Apache)
   5. Before starting the test make sure your local moodle site has:
      1. a course
      2. a student signed to course
      3. a quiz with question in course
      4. a teacher signed to course (with ability to edit)

## What we tested

We tested the quiz module that allows for creating and taking quizzes. We chose to test the following user stories: 

*User story:* A student answers quiz question a when a is correct and submits.

*Preconditions:* There is a course with a student and quiz

*Expected outcome:* The student will be graded 10.0.

*User story:* A teacher edits the quiz question and changes correct answer from a to d

*Preconditions:* There is a course with a quiz and the teacher has edit allowance.

*Expected outcome:* Correct answer in quiz is now d.


## How we tested
We used two different testing methods:
1. [Cucumber](https://cucumber.io/), a BDD testing framework.
2. [Provengo](https://provengo.tech/), a story-based testing framework.

Each of the testing methods is elaborated in its own directory. 

## Results
Update all README.md files (except for d-e, see Section 1). Specifically, replace all $$*TODO*…$$ according to the instructions inside the $$.

  
