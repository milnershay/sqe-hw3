/* @provengo summon selenium */

/**
 * This story opens a new browser window, goes to moodle, logs in as student, goes to quiz and answers d
 */



story('student answers a in quiz', function(){
  with(new SeleniumSession().start('http://localhost')){
    goToLogin()
    tryLogin({username: "student"})
    goToQuiz()
    startQuiz()
    answerA()
    finishQuiz()
    assertScore()
  }
})

story('teacher edits quiz answer from a to d', function(){
   with(new SeleniumSession().start('http://localhost')){
      goToLogin()
      tryLogin({username: "teacher"})
      goToQuiz()
      editQuiz()
      editAnswersAtoD()
   }
})





