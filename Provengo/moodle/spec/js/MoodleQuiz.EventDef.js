/* @Provengo summon selenium */

let a = "10.0"


// event to go to login page
defineEvent(SeleniumSession,"goToLogin", function(session, e) {
  session.sleep(400)
  session.click("//*[@id='usernavigation']/div[3]/div/span/a");
})


// event to attempt login with received username (password is same)
defineEvent(SeleniumSession, "tryLogin", function(session, e){
    with (session){
    writeText("//*[@id='username']", e.username);
    writeText("//*[@id='password']", "Aa!12345");
    click('//button[@id="loginbtn"]');

    }
})

// event that navigates to the quiz
defineEvent(SeleniumSession, "goToQuiz", function(session, e){
    session.click("/html/body/div[3]/nav/div[1]/nav/ul/li[3]/a");
    session.click("/html/body/div[3]/div[3]/div/div[2]/div/section/div/aside/section/div/div/div[1]/div[2]/div/div/div[1]/div/div/div/div[1]/div/div/a/span[3]");
    session.click("/html/body/div[3]/div[4]/div/div[3]/div/section/div/div/div/ul/li/div[2]/ul/li/div/div[1]/div/div[1]/div/div[2]/div[2]/a")
})

// event that starts a quiz attempt
defineEvent(SeleniumSession, "startQuiz", function(session, e){
    session.click("/html/body/div[3]/div[4]/div/div[2]/div/section/div[2]/div[1]/div/div/form/button")
})

// event that enters quiz edit
defineEvent(SeleniumSession, "editQuiz", function(session, e){
    session.click("/html/body/div[3]/div[4]/div/div[2]/nav/ul/li[3]/a")
    session.click("/html/body/div[4]/div[4]/div/div[3]/div/section/div[2]/div/ul/li/div/ul/li[2]/div/div/div[2]/a/span")
})

// switching the correct answer between A and D
defineEvent(SeleniumSession, "editAnswersAtoD", function(session, e){
    session.click("/html/body/div[3]/div[4]/div/div[3]/div/section/div[2]/form/fieldset[2]/div[2]/div[2]/div[2]/select")
    session.selectByValue("/html/body/div[3]/div[4]/div/div[3]/div/section/div[2]/form/fieldset[2]/div[2]/div[2]/div[2]/select","0.0")
    session.selectByValue("//*[@id=\"id_fraction_3\"]","1.0")
    session.click("//*[@id=\"id_submitbutton\"]")
    a = "0.0"

})

// choose answer D
defineEvent(SeleniumSession, "answerD", function(session, e){
    session.click("/html/body/div[3]/div[5]/div/div[2]/div/section/div[2]/form/div/div[1]/div[2]/div/div[2]/div[1]/div[4]/input")
})

// choose answer A
defineEvent(SeleniumSession, "answerA", function(session, e){
    session.click("/html/body/div[3]/div[5]/div/div[2]/div/section/div[2]/form/div/div[1]/div[2]/div/div[2]/div[1]/div[1]/input")
})

// submit the quiz
defineEvent(SeleniumSession, "finishQuiz", function(session, e){
    session.click("/html/body/div[3]/div[5]/div/div[2]/div/section/div[2]/form/div/div[2]/input")
    session.click("/html/body/div[3]/div[5]/div/div[2]/div/section/div[2]/div[5]/div/div/form/button")
    session.sleep(50)
    session.click("/html/body/div[5]/div[3]/div/div[2]/div/div[2]/input[1]")

})

// check that score is what we expected
defineEvent(SeleniumSession, "assertScore", function(session, e){
    session.assertText("/html/body/div[3]/div[5]/div/div[2]/div/section/div[2]/table/tbody/tr[6]/td/b[1]", a, TextAssertions.modifiers.Contains)

})
